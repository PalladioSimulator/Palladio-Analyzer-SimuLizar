package org.palladiosimulator.simulizar.metrics.powerconsumption;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
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
import org.palladiosimulator.measurementframework.TupleMeasurement;
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
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;

import de.fzi.power.infrastructure.PowerConsumingEntity;
import de.fzi.power.infrastructure.PowerConsumingResource;
import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.infrastructure.util.InfrastructureSwitch;
import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

//TODO inherit from EvaluationScope or some joint subclass
public class SimulationTimeEvaluationScope {

    private final PowerProvidingEntity entityUnderMeasurement;
    private final MeasurementSpecification powerMeasurmentSpec;
    private final Set<ProcessingResourceSpecification> processingResourceSpecs;
    private final Measure<Double, Duration> windowLength;
    private final Measure<Double, Duration> windowIncrement;
    private final SimuComModel simModel;
    private final IRecorder powerDataRecorder;
    private final UtilizationMeasurementsCollector measurementsCollector;

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
    private static final MetricDescription RESOURCE_STATE_METRIC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC;
    private static final MetricDescription POWER_METRIC = MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;

    private static final MeasuringpointSwitch<PowerProvidingEntity> MEASURING_POINT_SWITCH = new MeasuringpointSwitch<PowerProvidingEntity>() {

        private final InfrastructureSwitch<PowerProvidingEntity> infSwitch = new InfrastructureSwitch<PowerProvidingEntity>() {
            public PowerProvidingEntity casePowerProvidingEntity(PowerProvidingEntity ppe) {
                return ppe;
            }
        };

        public PowerProvidingEntity caseResourceURIMeasuringPoint(ResourceURIMeasuringPoint mp) {
            EObject modeObject = EMFLoadHelper.loadModel(mp.getResourceURI());
            return infSwitch.doSwitch(modeObject);
        }
    };

    public SimulationTimeEvaluationScope(MeasurementSpecification powerMeasurementSpec, MeasuringPoint measuringPoint,
            SimuComModel model) {
        if (powerMeasurementSpec == null) { //TODO add check for metric (once the metric has been added)
            throw new IllegalArgumentException("Given measurement specification must be a valid power measurement specifiction.");
        }
        this.powerMeasurmentSpec = powerMeasurementSpec;
        this.entityUnderMeasurement = MEASURING_POINT_SWITCH.doSwitch(measuringPoint);
        this.processingResourceSpecs = new HashSet<ProcessingResourceSpecification>();

        this.simModel = model;

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

        this.processingResourcesCollector.doSwitch(this.entityUnderMeasurement);

        Map<String, Object> recorderConfigurationMap = new HashMap<String, Object>();
        recorderConfigurationMap.put(AbstractRecorderConfiguration.RECORDER_ACCEPTED_METRIC, POWER_METRIC);
        recorderConfigurationMap.put(AbstractRecorderConfiguration.MEASURING_POINT, measuringPoint);

        this.powerDataRecorder = RecorderExtensionHelper.instantiateRecorderImplementationForRecorder(this.simModel
                .getConfiguration().getRecorderName());
        this.powerDataRecorder.initialize(this.simModel.getConfiguration().getRecorderConfigurationFactory()
                .createRecorderConfiguration(recorderConfigurationMap));

        this.measurementsCollector = new UtilizationMeasurementsCollector(this.processingResourceSpecs.size());
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
                    new SlidingWindowUtilizationAggregator(new ScopeRecorder(proc, this.measurementsCollector)));

            resourceStateCalculator.addObserver(windowRecorder);
        }
    }

    public Measurement getMeasurement(ProcessingResourceSpecification proc) {
        return this.measurementsCollector.getMeasurement(proc);
    }

    public void reset() {
        this.measurementsCollector.clear();
    }

    private static class UtilizationMeasurementsCollector {

        private final Map<ProcessingResourceSpecification, TupleMeasurement> currentUtilizationMeasurements;

        public UtilizationMeasurementsCollector(int size) {
            this.currentUtilizationMeasurements = new HashMap<ProcessingResourceSpecification, TupleMeasurement>(size);
        }

        public void collectMeasurementFromProcessingResourceSpec(ProcessingResourceSpecification proc,
                TupleMeasurement newUtilMeasurement) {
            // TODO synchronization or blocking map etc needed?
            TupleMeasurement lastMeasurementForResource = this.currentUtilizationMeasurements.get(proc);
            if (lastMeasurementForResource != null) {
                // there is already one utilization measurement for this processing resource
                // collected, so the underlying window has moved once
                // as all windows have same length and increment, we can process the current
                // measurements and clear the data
                // this assumes that this happens exactly once per window increment/"round"
                forwardCurrentMeasurements();
                clear();
            }
            this.currentUtilizationMeasurements.put(proc, newUtilMeasurement);
        }

        private void forwardCurrentMeasurements() {

        }

        public Measurement getMeasurement(ProcessingResourceSpecification processingResourceSpecification) {

            Measurement result = this.currentUtilizationMeasurements.get(processingResourceSpecification);
            if (result == null) {
                for (ProcessingResourceSpecification p : this.currentUtilizationMeasurements.keySet()) {
                    if (p.getId().equals(processingResourceSpecification.getId())) {
                        result = this.currentUtilizationMeasurements.get(p);
                        break;
                    }
                }
            }
            return result;
        }

        public void clear() {
            this.currentUtilizationMeasurements.clear();
        }
    }

    private static class ScopeRecorder implements IRecorder {

        private final ProcessingResourceSpecification correspondingResourceSpec;
        private final UtilizationMeasurementsCollector measurementsCollector;

        public ScopeRecorder(ProcessingResourceSpecification resourceSpec,
                UtilizationMeasurementsCollector measurementsCollector) {
            this.correspondingResourceSpec = resourceSpec;
            this.measurementsCollector = measurementsCollector;
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
                //the following downcast is safe now since UTILIZATION_METRIC represents a tuple
                this.measurementsCollector.collectMeasurementFromProcessingResourceSpec(this.correspondingResourceSpec,
                        (TupleMeasurement) measurement);
        }

        @Override
        public void flush() {
            // don't do nothing
        }

    }

}
