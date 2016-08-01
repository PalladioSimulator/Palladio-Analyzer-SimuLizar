package org.palladiosimulator.simulizar.power.probeframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
import org.palladiosimulator.monitorrepository.util.MonitorRepositorySwitch;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
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
import de.fzi.power.interpreter.calculators.energy.SimpsonRuleCumulativeEnergyCalculator;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.ISimulationListener;

public class PowerProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC = MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;
    private static final MetricSetDescription ENERGY_CONSUMPTION_TUPLE_METRIC_DESC = MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE;

    private static final MonitorRepositorySwitch<Optional<TimeDriven>> PROCESSING_TYPE_SWITCH = new MonitorRepositorySwitch<Optional<TimeDriven>>() {
        @Override
        public Optional<TimeDriven> caseTimeDriven(final TimeDriven timeDriven) {
            return Optional.of(timeDriven);
        }

        @Override
        public Optional<TimeDriven> defaultCase(final EObject eObject) {
            return Optional.empty();
        }
    };

    private SimuComModel model = null;
    private RuntimeMeasurementModel rmModel;
    private ResourceSet globalPCMModelResourceSet;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initPowerMeasurements(getProbeFrameworkListener()
                .getMeasurementSpecificationsForMetricDescription(POWER_CONSUMPTION_TUPLE_METRIC_DESC));
    }

    @Override
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener probeFrameworkListener) {
        super.setProbeFrameworkListener(probeFrameworkListener);
        this.model = getProbeFrameworkListener().getSimuComModel();
        this.rmModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
        this.globalPCMModelResourceSet = getProbeFrameworkListener().getModelAccess().getGlobalPCMModel()
                .getResourceSet();
    }

    /**
     * Initializes the sliding window based <i>power</i> and <i>energy</i> measurements. First gets
     * the monitored elements from the monitor repository, then creates corresponding calculators
     * and recorders.
     * 
     */
    private void initPowerMeasurements(final Collection<MeasurementSpecification> powerMeasurementSpecs) {

        if (!powerMeasurementSpecs.isEmpty()) {
            PowerModelRegistry powerModelRegistry = new PowerModelRegistry();
            PowerModelUpdaterSwitch modelUpdaterSwitch = new PowerModelUpdaterSwitch(powerModelRegistry,
                    new ExtensibleCalculatorInstantiatorImpl());
            Collection<ConsumptionContext> createdContexts = new ArrayList<>(powerMeasurementSpecs.size());
            Collection<SimulationTimeEvaluationScope> createdScopes = new ArrayList<>(powerMeasurementSpecs.size());

            for (MeasurementSpecification powerSpec : powerMeasurementSpecs) {
                Monitor powerSpecMonitor = powerSpec.getMonitor();
                MeasuringPoint measuringPoint = powerSpecMonitor.getMeasuringPoint();

                Optional<TimeDriven> timeDrivenSpecification = PROCESSING_TYPE_SWITCH
                        .doSwitch(powerSpec.getProcessingType());
                PowerProvidingEntity powerProvidingEntity = InterpreterUtils
                        .getPowerProvidingEntityFromMeasuringPoint(this.globalPCMModelResourceSet, measuringPoint);

                // this call crashes in case measurement specification or ppe are invalid
                checkValidity(powerSpec, powerProvidingEntity, timeDrivenSpecification);

                TimeDriven timeDriven = timeDrivenSpecification.get();

                Measure<Double, Duration> initialOffset = timeDriven.getWindowLengthAsMeasure();
                Measure<Double, Duration> samplingPeriod = timeDriven.getWindowIncrementAsMeasure();
                SimulationTimeEvaluationScope scope = SimulationTimeEvaluationScope.createScope(powerProvidingEntity,
                        this.model, initialOffset, samplingPeriod);

                modelUpdaterSwitch.doSwitch(powerProvidingEntity);
                ConsumptionContext context = ConsumptionContext.createConsumptionContext(powerProvidingEntity, scope,
                        powerModelRegistry);

                createdContexts.add(context);
                createdScopes.add(scope);
                SimulationTimePowerCalculator powerConsumptionCalculator = new SimulationTimePowerCalculator(context,
                        scope, powerProvidingEntity);
                SimulationTimeEnergyCalculator energyConsumptionCalculator = new SimulationTimeEnergyCalculator(
                        new SimpsonRuleCumulativeEnergyCalculator(samplingPeriod, initialOffset));

                // calculate power and energy consumption
                scope.addListener(powerConsumptionCalculator);
                powerConsumptionCalculator.addObserver(energyConsumptionCalculator);
                // the following two lines are optional: measurements are
                // recorded (e.g., by an EDP2 recorder)
                triggerMeasurementsRecording(powerConsumptionCalculator, measuringPoint,
                        POWER_CONSUMPTION_TUPLE_METRIC_DESC);
                triggerMeasurementsRecording(energyConsumptionCalculator, measuringPoint,
                        ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);

                // write measurements to RuntimeMeasurement (both power and energy measurements
                // are forwarded)
                triggerRuntimeMeasurementsRecording(powerConsumptionCalculator, powerSpec);
                triggerRuntimeMeasurementsRecording(energyConsumptionCalculator,
                        createSpecificationForEnergyMeasurements(powerSpecMonitor, timeDriven));
            }
            triggerAfterSimulationCleanup(createdContexts, createdScopes);
        }
    }

    private static MeasurementSpecification createSpecificationForEnergyMeasurements(final Monitor monitor,
            final TimeDriven fromProcessingType) {
        assert monitor != null && fromProcessingType != null;

        MeasurementSpecification energySpec = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
        energySpec.setMetricDescription(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);

        TimeDriven timeDrivenProcessingType = MonitorRepositoryFactory.eINSTANCE.createTimeDriven();
        timeDrivenProcessingType.setWindowIncrement(fromProcessingType.getWindowIncrement());
        timeDrivenProcessingType.setWindowLength(fromProcessingType.getWindowLength());

        energySpec.setProcessingType(timeDrivenProcessingType);
        monitor.getMeasurementSpecifications().add(energySpec);

        energySpec.setTriggersSelfAdaptations(
                fromProcessingType.getMeasurementSpecification().isTriggersSelfAdaptations());

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
     * @param powerProvidingEntity
     *            The {@link PowerProvidingEntity} associated with the given measurement
     *            specification.
     * @param aggregation
     *            An {@link Optional} that contains the {@link TimeDrivenAggregation}
     *            {@link ProcessingType} that has been extracted from the given measurement
     *            specification.
     * 
     * @throws IllegalStateException
     *             Iff {@code powerProvidingEntity == null || !aggregation.isPresent()}.
     */
    private static void checkValidity(final MeasurementSpecification powerMeasurementSpec,
            final PowerProvidingEntity powerProvidingEntity, final Optional<TimeDriven> aggregation) {

        if (powerProvidingEntity == null) {
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

    private void triggerMeasurementsRecording(final MeasurementSource measurementSource, final MeasuringPoint mp,
            final MetricDescription recorderAcceptedMetric) {
        assert measurementSource != null && mp != null && recorderAcceptedMetric != null;

        Map<String, Object> recorderConfigurationMap = createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
                recorderAcceptedMetric, mp);
        registerMeasurementsRecorder(measurementSource, initializeRecorder(recorderConfigurationMap));
    }

    private void triggerRuntimeMeasurementsRecording(final MeasurementSource calculator,
            final MeasurementSpecification measurementSpec) {
        assert calculator != null && measurementSpec != null;

        if (measurementSpec.isTriggersSelfAdaptations()) {
            calculator.addObserver(new SlidingWindowRuntimeMeasurementsRecorder(this.rmModel, measurementSpec));
        }
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
    private void triggerAfterSimulationCleanup(final Collection<ConsumptionContext> contextsToCleanup,
            final Collection<SimulationTimeEvaluationScope> scopesToCleanup) {
        assert contextsToCleanup != null && !contextsToCleanup.isEmpty();
        assert scopesToCleanup != null && !scopesToCleanup.isEmpty();
        assert this.model != null;

        this.model.getConfiguration().addListener(new ISimulationListener() {
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
