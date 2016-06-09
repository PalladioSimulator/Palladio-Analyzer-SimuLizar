package org.palladiosimulator.simulizar.power.probeframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.power.calculators.SimulationTimeEnergyCalculator;
import org.palladiosimulator.simulizar.power.calculators.SimulationTimePowerCalculator;
import org.palladiosimulator.simulizar.power.evaluationscope.SimulationTimeEvaluationScope;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;
import org.palladiosimulator.simulizar.slidingwindow.util.SimulizarSlidingWindowUtil;

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
    public void setProbeFrameworkListener(AbstractProbeFrameworkListener probeFrameworkListener) {
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
    private void initPowerMeasurements(Collection<MeasurementSpecification> powerMeasurementSpecs) {
        assert getProbeFrameworkListener() != null;

        if (!powerMeasurementSpecs.isEmpty()) {
            PowerModelRegistry powerModelRegistry = new PowerModelRegistry();
            PowerModelUpdaterSwitch modelUpdaterSwitch = new PowerModelUpdaterSwitch(powerModelRegistry,
                    new ExtensibleCalculatorInstantiatorImpl());
            Collection<ConsumptionContext> createdContexts = new ArrayList<>(powerMeasurementSpecs.size());
            Collection<SimulationTimeEvaluationScope> createdScopes = new ArrayList<>(powerMeasurementSpecs.size());

            for (MeasurementSpecification powerSpec : powerMeasurementSpecs) {
                Monitor powerSpecMonitor = powerSpec.getMonitor();
                MeasuringPoint measuringPoint = powerSpecMonitor.getMeasuringPoint();

                final PowerProvidingEntity ppe = InterpreterUtils
                        .getPowerProvidingEntityFromMeasuringPoint(this.globalPCMModelResourceSet, measuringPoint);
                if (ppe == null) {
                    throw new IllegalStateException(
                            "MeasurementSpecification for metric " + POWER_CONSUMPTION_TUPLE_METRIC_DESC.getName()
                                    + " has to be related to a PowerProvidingEntity!");
                }

                Measure<Double, Duration>[] windowProperties = SimulizarSlidingWindowUtil
                        .getWindowPropertiesFromTemporalCharacterization(powerSpec.getTemporalRestriction());
                Measure<Double, Duration> initialOffset = windowProperties[0];
                Measure<Double, Duration> samplingPeriod = windowProperties[1];
                SimulationTimeEvaluationScope scope = SimulationTimeEvaluationScope.createScope(ppe, this.model,
                        initialOffset, samplingPeriod);

                modelUpdaterSwitch.doSwitch(ppe);
                ConsumptionContext context = ConsumptionContext.createConsumptionContext(ppe, scope,
                        powerModelRegistry);

                AbstractCumulativeEnergyCalculator energyCalculator = new SimpsonRuleCumulativeEnergyCalculator(
                        samplingPeriod, initialOffset);

                createdContexts.add(context);
                createdScopes.add(scope);
                SimulationTimePowerCalculator powerConsumptionCalculator = new SimulationTimePowerCalculator(context,
                        scope, ppe);
                SimulationTimeEnergyCalculator energyConsumptionCalculator = new SimulationTimeEnergyCalculator(
                        energyCalculator);

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
                        createSpecificationForEnergyMeasurements(powerSpec));
            }
            triggerAfterSimulationCleanup(createdContexts, createdScopes);
        }
    }

    private MeasurementSpecification createSpecificationForEnergyMeasurements(MeasurementSpecification powerSpec) {
        MeasurementSpecification energySpec = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
        energySpec.setMetricDescription(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);
        energySpec.setMonitor(powerSpec.getMonitor());
        energySpec.setTemporalRestriction(powerSpec.getTemporalRestriction());
        return energySpec;
    }

    private void triggerMeasurementsRecording(MeasurementSource measurementSource, MeasuringPoint mp,
            MetricDescription recorderAcceptedMetric) {
        assert measurementSource != null && mp != null && recorderAcceptedMetric != null;

        Map<String, Object> recorderConfigurationMap = createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
                recorderAcceptedMetric, mp);
        registerMeasurementsRecorder(measurementSource, initializeRecorder(recorderConfigurationMap));
    }

    private void triggerRuntimeMeasurementsRecording(MeasurementSource calculator,
            MeasurementSpecification measurementSpec) {
        assert calculator != null && measurementSpec != null;

        calculator.addObserver(
                new SlidingWindowRuntimeMeasurementsRecorder(this.rmModel, measurementSpec));
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
