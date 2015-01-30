package org.palladiosimulator.simulizar.metrics.powerconsumption;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.apache.commons.collections15.IteratorUtils;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.datastream.IDataStream;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.util.MeasuringPointUtility;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
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
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.util.PmsSwitch;

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
    private final UtilizationMeasurementsCollector collector;

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

    private static final PmsSwitch<Measure<Double, Duration>[]> WINDOW_PROPERTIES_SWITCH = new PmsSwitch<Measure<Double, Duration>[]>() {
        
        @Override
        public Measure<Double, Duration>[] caseDelayedIntervall(DelayedIntervall interval) {
            @SuppressWarnings("unchecked")
            Measure<Double, Duration>[] result = (Measure<Double, Duration>[]) new Measure<?, ?>[2];
            result[0] = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            result[1] = Measure.valueOf(interval.getDelay(), SI.SECOND);
            return result;
        }
        
        @Override
        public Measure<Double, Duration>[] caseIntervall(Intervall interval) {
            @SuppressWarnings("unchecked")
            Measure<Double, Duration>[] result = (Measure<Double, Duration>[]) new Measure<?, ?>[2];
            result[0] = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            result[1] = result[0];
            
            return result;
        }
        
        public Measure<Double, Duration>[] defaultCase(EObject obj) {
            throw new IllegalStateException(
                    "Temporal characterization for utilization measurement must be either Intervall or DelayedIntervall.");
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
        
        Measure<Double, Duration>[] windowProperties = WINDOW_PROPERTIES_SWITCH.doSwitch(powerMeasurementSpec.getTemporalRestriction());
        this.windowLength = windowProperties[0];
        this.windowIncrement = windowProperties[1];
        
        this.entityUnderMeasurement = entityUnderMeasurement;
        this.processingResourceSpecs = new HashSet<ProcessingResourceSpecification>();
        this.simModel = model;
        this.processingResourcesCollector.doSwitch(this.entityUnderMeasurement);
        this.collector = new UtilizationMeasurementsCollector(this.processingResourceSpecs.size());
        
        
        for (ProcessingResourceSpecification spec : this.processingResourceSpecs) {
            IDataStream<Measurement> stream = new DataStream();
           
            this.resourceMeasurements.put(spec, Collections.singleton(stream));
        }
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


            SimulationGovernedSlidingWindow slidingWindow = new SimulationGovernedSlidingWindow(this.windowLength,
                    this.windowIncrement, RESOURCE_STATE_METRIC, moveOnStrategy, this.simModel);
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
        //implementation is not required here, so do nothing at all
    }
    
    private static class DataStream implements IDataStream<Measurement> {
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
        }

        @Override
        public int size() {
            return 1;
        }
        
        public void add(Measurement m) {
            this.innerElement = m;
        }
    }
    
    
   private class UtilizationMeasurementsCollector extends AbstractObservable<ISimulationEvaluationScopeListener> {
       
       private Map<ProcessingResourceSpecification, Measurement> collectedMeasurements;
       private final int measurementsToCollect;
       
       public UtilizationMeasurementsCollector(int measurementsToCollect) {
           this.collectedMeasurements = new HashMap<>(measurementsToCollect);
           this.measurementsToCollect = measurementsToCollect;
       }
  
       private void addUtilizationMeasurementForProcessingResource(ProcessingResourceSpecification spec, Measurement utilMeasurement) {
           if (this.collectedMeasurements.put(spec, utilMeasurement) == null || !SimulationTimeEvaluationScope.this.simModel.getSimulationControl().isRunning()) {
                if (this.collectedMeasurements.size() == measurementsToCollect) {
                    //one "round" is complete: windows of all specs have produced their utilization measurement
                    //so forward data to power calculator, then clear
                    for (ProcessingResourceSpecification proc : SimulationTimeEvaluationScope.this.processingResourceSpecs) {
                        DataStream procMeasurements = (DataStream) SimulationTimeEvaluationScope.this.resourceMeasurements.get(proc).iterator().next();
                        procMeasurements.add(this.collectedMeasurements.get(proc));
                    }
                    Measure<Double, Duration> currentPointInTime = utilMeasurement.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
                    informListeners(currentPointInTime);
                    this.collectedMeasurements = new HashMap<>(measurementsToCollect);
               }
           } else {
               //TODO is this correct?
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
            if (measurement.isCompatibleWith(UTILIZATION_METRIC)) {
                SimulationTimeEvaluationScope.this.collector.addUtilizationMeasurementForProcessingResource(spec, measurement);
            }
        }

        @Override
        public void flush() {
            // don't do nothing
        }

    }
}
