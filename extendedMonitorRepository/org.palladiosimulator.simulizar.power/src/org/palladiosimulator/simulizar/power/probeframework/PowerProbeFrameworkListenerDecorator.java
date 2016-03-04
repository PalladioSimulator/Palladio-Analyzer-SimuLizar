package org.palladiosimulator.simulizar.power.probeframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.monitorrepository.ProcessingType;
import org.palladiosimulator.monitorrepository.TimeDriven;
import org.palladiosimulator.monitorrepository.TimeDrivenAggregation;
import org.palladiosimulator.monitorrepository.WindowCharacterization;
import org.palladiosimulator.monitorrepository.util.MonitorRepositorySwitch;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.power.calculators.SimulationTimeEnergyCalculator;
import org.palladiosimulator.simulizar.power.calculators.SimulationTimePowerCalculator;
import org.palladiosimulator.simulizar.power.evaluationscope.SimulationTimeEvaluationScope;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.interpreter.ConsumptionContext;
import de.fzi.power.interpreter.InterpreterUtils;
import de.fzi.power.interpreter.PowerModelRegistry;
import de.fzi.power.interpreter.PowerModelUpdaterSwitch;
import de.fzi.power.interpreter.calculators.ExtensibleCalculatorInstantiatorImpl;
import de.fzi.power.interpreter.calculators.energy.AbstractCumulativeEnergyCalculator;
import de.fzi.power.interpreter.calculators.energy.SimpsonRuleCumulativeEnergyCalculator;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.ISimulationListener;

public class PowerProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC = MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;
    private static final MetricSetDescription ENERGY_CONSUMPTION_TUPLE_METRIC_DESC = MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE;

    private static final MonitorRepositorySwitch<Optional<TimeDriven>> PROCESSING_TYPE_SWITCH = new MonitorRepositorySwitch<Optional<TimeDriven>>() {
        @Override
        public Optional<TimeDriven> caseTimeDriven(TimeDriven timeDriven) {
            return Optional.of(timeDriven);
        }

        @Override
        public Optional<TimeDriven> defaultCase(EObject eObject) {
            return Optional.empty();
        }
    };

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initPowerMeasurements();
    }

    /**
     * Initializes the sliding window based <i>power</i> and <i>energy</i> measurements. First gets
     * the monitored elements from the monitor repository, then creates corresponding calculators
     * and recorders.
     * 
     */
    private void initPowerMeasurements() {
        assert getProbeFrameworkListener() != null;

        Collection<MeasurementSpecification> powerMeasurementSpecs = getProbeFrameworkListener()
                .getMeasurementSpecificationsForMetricDescription(POWER_CONSUMPTION_TUPLE_METRIC_DESC);
        if (!powerMeasurementSpecs.isEmpty()) {
            PowerModelRegistry reg = new PowerModelRegistry();
            PowerModelUpdaterSwitch modelUpdaterSwitch = new PowerModelUpdaterSwitch(reg,
                    new ExtensibleCalculatorInstantiatorImpl());
            List<ConsumptionContext> createdContexts = new ArrayList<>(powerMeasurementSpecs.size());
            List<SimulationTimeEvaluationScope> createdScopes = new ArrayList<>(powerMeasurementSpecs.size());
            RuntimeMeasurementModel runtimeMeasurementModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
            SimuComModel simucomModel = getProbeFrameworkListener().getSimuComModel();
            PCMResourceSetPartition globalPCMModel = this.getProbeFrameworkListener().getModelAccess()
                    .getGlobalPCMModel();

            for (MeasurementSpecification powerSpec : powerMeasurementSpecs) {
                Monitor powerSpecMonitor = powerSpec.getMonitor();
                MeasuringPoint mp = powerSpecMonitor.getMeasuringPoint();

                Optional<TimeDriven> timeDrivenSpecification = PROCESSING_TYPE_SWITCH
                        .doSwitch(powerSpec.getProcessingType());
                PowerProvidingEntity ppe = InterpreterUtils
                        .getPowerProvidingEntityFromMeasuringPoint(globalPCMModel.getResourceSet(), mp);

                // this call crashes in case measurement specification or ppe are invalid
                checkValidity(powerSpec, ppe, timeDrivenSpecification);

                WindowCharacterization windowCharacterization = timeDrivenSpecification.get()
                        .getWindowCharacterization();

                Measure<Double, Duration> initialOffset = windowCharacterization.getWindowLengthAsMeasure();
                Measure<Double, Duration> samplingPeriod = windowCharacterization.getWindowIncrementAsMeasure();
                SimulationTimeEvaluationScope scope = SimulationTimeEvaluationScope.createScope(ppe, simucomModel,
                        initialOffset, samplingPeriod);

                modelUpdaterSwitch.doSwitch(ppe);
                ConsumptionContext context = ConsumptionContext.createConsumptionContext(ppe, scope, reg);

                AbstractCumulativeEnergyCalculator energyCalculator = new SimpsonRuleCumulativeEnergyCalculator(
                        samplingPeriod, initialOffset);

                createdContexts.add(context);
                createdScopes.add(scope);
                SimulationTimePowerCalculator powerConsumptionCalculator = new SimulationTimePowerCalculator(context,
                        scope, ppe);
                SimulationTimeEnergyCalculator energyConsumptionCalculator = new SimulationTimeEnergyCalculator(
                        energyCalculator);

                MeasurementSpecification energySpec = createSpecificationForEnergyMeasurements(powerSpecMonitor,
                        windowCharacterization.getWindowLength(), windowCharacterization.getWindowLength());

                // calculate power and energy consumption
                scope.addListener(powerConsumptionCalculator);
                powerConsumptionCalculator.addObserver(energyConsumptionCalculator);
                // the following two lines are optional: measurements are
                // recorded (e.g., by an EDP2 recorder)
                triggerMeasurementsRecording(powerConsumptionCalculator, mp, POWER_CONSUMPTION_TUPLE_METRIC_DESC);
                triggerMeasurementsRecording(energyConsumptionCalculator, mp, ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);
                // write measurements to RuntimeMeasurement (both power and energy measurements
                // are forwarded)
                powerConsumptionCalculator.addObserver(
                        new SlidingWindowRuntimeMeasurementsRecorder(runtimeMeasurementModel, powerSpec, mp));
                energyConsumptionCalculator.addObserver(
                        new SlidingWindowRuntimeMeasurementsRecorder(runtimeMeasurementModel, energySpec, mp));
            }
            triggerAfterSimulationCleanup(createdContexts, createdScopes);
        }
    }

    private static MeasurementSpecification createSpecificationForEnergyMeasurements(Monitor monitor,
            double windowLength, double windowIncrement) {
        assert monitor != null;

        MeasurementSpecification energySpec = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
        energySpec.setMetricDescription(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);

        WindowCharacterization windowCharacterization = MonitorRepositoryFactory.eINSTANCE
                .createWindowCharacterization();

        windowCharacterization.setWindowIncrement(windowIncrement);
        windowCharacterization.setWindowIncrement(windowLength);

        TimeDriven timeDrivenProcessingType = MonitorRepositoryFactory.eINSTANCE.createTimeDriven();
        timeDrivenProcessingType.setWindowCharacterization(windowCharacterization);

        energySpec.setProcessingType(timeDrivenProcessingType);
        monitor.getMeasurementSpecifications().add(energySpec);

        assert energySpec.getMonitor().getId().equals(monitor.getId());
        return energySpec;
    }

    /**
     * Checks whether the given measurement specification is valid. If this methods returns, then it
     * is valid:<br>
     * For validity, it is required that
     * <ul>
     * <li>the associated power providing entity exist (i.e., non-null),</li>
     * <li>the passed optional be not empty, i.e., the ProcessingType specified in the measurement
     * specification must be a {@link TimeDrivenAggregation}.</li>
     * </ul>
     * 
     * @param powerMeasurementSpec
     *            A {@link MeasurementSpecification} for power measurements.
     * @param ppe
     *            The {@link PowerProvidingEntity} associated with the given measurement
     *            specification.
     * @param aggregation
     *            An {@link Optional} that contains the {@link TimeDrivenAggregation}
     *            {@link ProcessingType} that has been extracted from the given measurement
     *            specification.
     * 
     * @throws IllegalStateException
     *             Iff {@code ppe == null || !aggregation.isPresent()}.
     */
    private static void checkValidity(MeasurementSpecification powerMeasurementSpec, PowerProvidingEntity ppe,
            Optional<TimeDriven> aggregation) {

        if (ppe == null) {
            throw new IllegalStateException("MeasurementSpecification '" + powerMeasurementSpec.getName()
                    + "' for metric " + POWER_CONSUMPTION_TUPLE_METRIC_DESC.getName()
                    + " has to be related to a PowerProvidingEntity!");
        }

        if (!aggregation.isPresent()) {
            throw new IllegalStateException("MetricDescription (" + POWER_CONSUMPTION_TUPLE_METRIC_DESC.getName()
                    + ") '" + powerMeasurementSpec.getName() + "' of Monitor '"
                    + powerMeasurementSpec.getMonitor().getEntityName() + "' must provide a "
                    + ProcessingType.class.getName() + " of Type '" + TimeDriven.class.getName() + "'!");
        }
    }

    private void triggerMeasurementsRecording(MeasurementSource measurementSource, MeasuringPoint mp,
            MetricDescription recorderAcceptedMetric) {
        assert measurementSource != null && mp != null && recorderAcceptedMetric != null;

        Map<String, Object> recorderConfigurationMap = createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
                recorderAcceptedMetric, mp);
        registerMeasurementsRecorder(measurementSource, initializeRecorder(recorderConfigurationMap));
    }

    /**
     * Method to clean up {@link ConsumptionContext}s and {@link SimulationTimeEvaluationScope}s
     * required for power and energy measurements. The clean-up operations are done once the
     * simulation has stopped.
     * 
     * @param contextsToCleanup
     *            {@link Collection} of contexts to clean up.
     * @param scopesToCleanup
     *            {@link Collection} of scopes to clean up.
     * @see #initPowerMeasurements()
     */
    private void triggerAfterSimulationCleanup(Collection<ConsumptionContext> contextsToCleanup,
            Collection<SimulationTimeEvaluationScope> scopesToCleanup) {
        assert contextsToCleanup != null && !contextsToCleanup.isEmpty();
        assert scopesToCleanup != null && !scopesToCleanup.isEmpty();
        assert getProbeFrameworkListener() != null;

        getProbeFrameworkListener().getSimuComModel().getConfiguration().addListener(new ISimulationListener() {
            @Override
            public void simulationStop() {
                contextsToCleanup.forEach(ConsumptionContext::cleanUp);
                scopesToCleanup.forEach(SimulationTimeEvaluationScope::removeAllListeners);
            }

            @Override
            public void simulationStart() {
            }
        });
    }
}
