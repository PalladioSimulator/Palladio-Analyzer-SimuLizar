package org.palladiosimulator.simulizar.metrics.powerconsumption;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.apache.commons.collections15.IteratorUtils;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.datastream.IDataStream;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.simulizar.metrics.aggregators.SimulationGovernedSlidingWindow;

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.interpreter.AbstractEvaluationScope;
import de.fzi.power.interpreter.InterpreterUtils;
import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SimulationTimeEvaluationScope extends AbstractEvaluationScope {

    private final Set<ProcessingResourceSpecification> processingResourceSpecs;
    private final SimuComModel simModel;
    private final UtilizationMeasurementsCollector collector;

    private static final MetricDescription UTILIZATION_METRIC = 
            MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE;
    private static final MetricDescription RESOURCE_STATE_METRIC = 
            MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;

    public SimulationTimeEvaluationScope(PowerProvidingEntity entityUnderMeasurement, SimuComModel model) {
        if (entityUnderMeasurement == null) {
            throw new IllegalArgumentException("Given PowerProvidingEntity must not be null.");
        }

        if (model == null) {
            throw new IllegalArgumentException("Given SimuComModel must not be null.");
        }
        this.simModel = model;
        this.processingResourceSpecs = InterpreterUtils
                .getProcessingResourceSpecsFromInfrastructureElement(entityUnderMeasurement);
        this.collector = new UtilizationMeasurementsCollector(this.processingResourceSpecs.size());

        for (ProcessingResourceSpecification spec : this.processingResourceSpecs) {
            IDataStream<Measurement> stream = new SingletonDataStream();
            this.resourceMeasurements.put(spec, Collections.singleton(stream));
        }
    }

    public void initialize(Measure<Double, Duration> windowLength, Measure<Double, Duration> windowIncrement) {
        ISlidingWindowMoveOnStrategy moveOnStrategy = new KeepLastElementPriorToLowerBoundStrategy();
        PcmmeasuringpointFactory pcmMeasuringpointFactory = PcmmeasuringpointFactory.eINSTANCE;

        RegisterCalculatorFactoryDecorator actualCalculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(this.simModel.getProbeFrameworkContext().getCalculatorFactory());

        for (ProcessingResourceSpecification proc : this.processingResourceSpecs) {
            ActiveResourceMeasuringPoint mp = pcmMeasuringpointFactory.createActiveResourceMeasuringPoint();
            mp.setActiveResource(proc);
            Calculator resourceStateCalculator = actualCalculatorFactory
                    .getCalculatorByMeasuringPointAndMetricDescription(mp, RESOURCE_STATE_METRIC);

            SlidingWindow slidingWindow = new SimulationGovernedSlidingWindow(windowLength,
                    windowIncrement, RESOURCE_STATE_METRIC, moveOnStrategy, this.simModel);
            SlidingWindowRecorder windowRecorder = new SlidingWindowRecorder(slidingWindow,
                    new SlidingWindowUtilizationAggregator(new ScopeRecorder(proc)));

            resourceStateCalculator.addObserver(windowRecorder);
        }
    }

    @Override
    public void reset() {
        this.iterator = iterator();
    }

    public void addScopeListener(ISimulationEvaluationScopeListener listener) {
        this.collector.addObserver(listener);
    }

    /**
     * This implementation does nothing.
     */
    @Override
    public void setResourceMetricsToEvaluate(Map<ProcessingResourceSpecification, Set<MetricDescription>> metricsMap) {
        // implementation is not required here
    }

    private static class SingletonDataStream implements IDataStream<Measurement> {
        private Measurement innerElement;

        @Override
        public Iterator<Measurement> iterator() {
            return IteratorUtils.singletonListIterator(innerElement);
        }

        @Override
        public MetricDescription getMetricDesciption() {
            return UTILIZATION_METRIC;
        }

        @Override
        public boolean isCompatibleWith(MetricDescription other) {
            return getMetricDesciption().equals(other);
        }

        @Override
        public void close() {
            this.innerElement = null;
        }

        @Override
        public int size() {
            return 1;
        }

        public void exchangeElement(Measurement m) {
            assert m != null;
            this.innerElement = m;
        }
    }

    private class UtilizationMeasurementsCollector extends AbstractObservable<ISimulationEvaluationScopeListener> {

        private Map<ProcessingResourceSpecification, Measurement> collectedMeasurements;
        private final int measurementsToCollect;

        public UtilizationMeasurementsCollector(int measurementsToCollect) {
            assert measurementsToCollect > 0;
            this.collectedMeasurements = new HashMap<>(measurementsToCollect);
            this.measurementsToCollect = measurementsToCollect;
        }

        private void addUtilizationMeasurementForProcessingResource(ProcessingResourceSpecification spec,
                Measurement utilMeasurement) {
            if (this.collectedMeasurements.put(spec, utilMeasurement) == null
                    || !SimulationTimeEvaluationScope.this.simModel.getSimulationControl().isRunning()) {
                if (this.collectedMeasurements.size() == this.measurementsToCollect) {
                    // one "round" is complete: windows of all specs have produced their utilization measurement
                    // so forward data to listeners (e.g., power calculators, consumption contexts), then clear
                    for (ProcessingResourceSpecification proc : SimulationTimeEvaluationScope.this.processingResourceSpecs) {
                        Set<IDataStream<Measurement>> dataset = SimulationTimeEvaluationScope.this.resourceMeasurements.get(proc);
                        assert dataset.size() == 1;
                        //this cast is safe as we insert only SingletonDataStream instances (cf. ctor)
                        SingletonDataStream procMeasurements = (SingletonDataStream) dataset.iterator().next();
                        procMeasurements.exchangeElement(this.collectedMeasurements.get(proc));
                    }
                    Measure<Double, Duration> currentPointInTime = utilMeasurement
                            .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
                    informListeners(currentPointInTime);
                    //start anew
                    this.collectedMeasurements.clear();
                }
            } else {
                throw new AssertionError("This should not happen");
            }
        }

        private void informListeners(Measure<Double, Duration> pointInTime) {
            this.getEventDispatcher().next(pointInTime);
        }
    }

    private class ScopeRecorder implements IRecorder {

        private final ProcessingResourceSpecification spec;

        public ScopeRecorder(ProcessingResourceSpecification spec) {
            this.spec = spec;
        }

        @Override
        public void newMeasurementAvailable(Measurement newMeasurement) {
            // implementation is not required
        }

        @Override
        public void preUnregister() {
            // implementation is not required
        }

        @Override
        public void initialize(IRecorderConfiguration recorderConfiguration) {
            // implementation is not required
        }

        @Override
        public void writeData(Measurement measurement) {
            if (measurement == null) {
                throw new IllegalStateException("Somehow 'null' measurement was passed to recorder.");
            }
            // we receive a new utilization measurement now
            if (measurement.isCompatibleWith(UTILIZATION_METRIC)) {
                SimulationTimeEvaluationScope.this.collector.addUtilizationMeasurementForProcessingResource(spec,
                        measurement);
            }
        }

        @Override
        public void flush() {
            // implementation is not required
        }

    }
}
