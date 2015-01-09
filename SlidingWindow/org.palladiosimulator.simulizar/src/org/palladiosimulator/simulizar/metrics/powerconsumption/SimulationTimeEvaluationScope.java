package org.palladiosimulator.simulizar.metrics.powerconsumption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.edp2.datastream.IDataStream;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.ResourceURIMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.util.MeasuringpointSwitch;
import org.palladiosimulator.edp2.util.MeasuringPointUtility;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.AbstractRecorderConfiguration;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.recorderframework.utils.RecorderExtensionHelper;
import org.palladiosimulator.simulizar.metrics.aggregators.SimulationGovernedSlidingWindow;
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;

import de.fzi.power.infrastructure.PowerConsumingEntity;
import de.fzi.power.infrastructure.PowerConsumingResource;
import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.infrastructure.util.InfrastructureSwitch;
import de.fzi.power.interpreter.AbstractEvaluationScope;
import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SimulationTimeEvaluationScope extends AbstractEvaluationScope {

    private final PowerProvidingEntity entityUnderMeasurement;
    private final Set<ProcessingResourceSpecification> processingResourceSpecs;
    private final Measure<Double, Duration> windowLength;
    private final Measure<Double, Duration> windowIncrement;
    private final SimuComModel simModel;

    private final InfrastructureSwitch<Void> processingResourcesCollector = new InfrastructureSwitch<Void>() {

        // inner node (composite nodes): just step down until we reach a leaf
        public Void casePowerProvidingEntity(PowerProvidingEntity ppe) {
            for (PowerConsumingEntity dist : ppe.getNestedPowerConsumingEntities()) {
                this.doSwitch(dist);
            }
            return null;
        }

        // process leaf
        public Void casePowerConsumingResource(PowerConsumingResource resource) {
            SimulationTimeEvaluationScope.this.processingResourceSpecs.add(resource
                    .getProcessingResourceSpecification());
            return null;

        }
    };

    private static final MetricDescription UTILIZATION_METRIC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE;
    private static final MetricDescription RESOURCE_STATE_METRIC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;

    public SimulationTimeEvaluationScope(PowerProvidingEntity entityUnderMeasurement, MeasurementSpecification powerMeasurementSpec,
            SimuComModel model) {
        if (powerMeasurementSpec == null || powerMeasurementSpec.getPerformanceMetric() != PerformanceMetricEnum.POWER_CONSUMPTION) {
            throw new IllegalArgumentException(
                    "Given measurement specification must be a valid power measurement specifiction.");
        }
        if (entityUnderMeasurement == null) {
            throw new IllegalArgumentException("Given PowerProvidingEntity must not be null.");
        }
        
        TemporalCharacterization temporalRestriction = powerMeasurementSpec.getTemporalRestriction();
        if (temporalRestriction instanceof DelayedIntervall) {
            DelayedIntervall interval = (DelayedIntervall) temporalRestriction;
            this.windowLength = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            this.windowIncrement = Measure.valueOf(interval.getDelay(), SI.SECOND);
        } else if (temporalRestriction instanceof Intervall) {
            Intervall interval = (Intervall) temporalRestriction;
            windowLength = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            windowIncrement = windowLength;
        } else {
            throw new IllegalStateException(
                    "Temporal characterization for utilization measurement must be either Intervall or DelayedIntervall.");
        }
        
        this.entityUnderMeasurement = entityUnderMeasurement;
        this.processingResourceSpecs = new HashSet<ProcessingResourceSpecification>();

        this.simModel = model;

        this.processingResourcesCollector.doSwitch(this.entityUnderMeasurement);
    }

    public void initialize() {
        ISlidingWindowMoveOnStrategy moveOnStrategy = new KeepLastElementPriorToLowerBoundStrategy();
        PcmmeasuringpointFactory pcmMeasuringpointFactory = PcmmeasuringpointFactory.eINSTANCE;
        MeasuringpointFactory measuringpointFactory = MeasuringpointFactory.eINSTANCE;

        RegisterCalculatorFactoryDecorator actualCalculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(this.simModel.getProbeFrameworkContext().getCalculatorFactory());

        for (ProcessingResourceSpecification proc : this.processingResourceSpecs) {
            ActiveResourceMeasuringPoint mp = pcmMeasuringpointFactory.createActiveResourceMeasuringPoint();
            mp.setActiveResource(proc);
            Calculator resourceStateCalculator = actualCalculatorFactory
                    .getCalculatorByMeasuringPointAndMetricDescription(mp, RESOURCE_STATE_METRIC);

            StringMeasuringPoint calcMeasuringPoint = measuringpointFactory.createStringMeasuringPoint();
            calcMeasuringPoint.setMeasuringPoint(MeasuringPointUtility.measuringPointToString(resourceStateCalculator
                    .getMeasuringPoint()));

            UtilizationMeasurementsDataStream utilizationDataStream = new UtilizationMeasurementsDataStream();
            super.utilizationMeasurements.put(proc, utilizationDataStream);

            SimulationGovernedSlidingWindow slidingWindow = new SimulationGovernedSlidingWindow(this.windowLength,
                    this.windowIncrement, RESOURCE_STATE_METRIC, moveOnStrategy, this.simModel);
            SlidingWindowRecorder windowRecorder = new SlidingWindowRecorder(slidingWindow,
                    new SlidingWindowUtilizationAggregator(new ScopeRecorder(utilizationDataStream)));

            resourceStateCalculator.addObserver(windowRecorder);
        }
    }

    private static class UtilizationMeasurementsDataStream implements IDataStream<Measurement> {

        private final List<Measurement> data = new ArrayList<Measurement>();

        public void addUtilizationMeasurement(Measurement m) {
            this.data.add(m);
        }

        @Override
        public Iterator<Measurement> iterator() {
            return this.data.iterator();
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

        }

        @Override
        public int size() {
            return this.data.size();
        }

    }

    private static class ScopeRecorder implements IRecorder {

        private final UtilizationMeasurementsDataStream outputStream;

        public ScopeRecorder(UtilizationMeasurementsDataStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void newMeasurementAvailable(Measurement newMeasurement) {
            // don't do nothing
        }

        @Override
        public void preUnregister() {
            // don't do nothing
        }

        @Override
        public void initialize(IRecorderConfiguration recorderConfiguration) {
            // don't do nothing
        }

        @Override
        public void writeData(Measurement measurement) {
            // we receive a new utilization measurement now
            if (measurement.isCompatibleWith(UTILIZATION_METRIC))
                this.outputStream.addUtilizationMeasurement(measurement);
        }

        @Override
        public void flush() {
            // don't do nothing
        }

    }
}
