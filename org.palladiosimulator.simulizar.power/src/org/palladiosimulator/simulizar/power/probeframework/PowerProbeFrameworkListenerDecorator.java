package org.palladiosimulator.simulizar.power.probeframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.power.calculators.SimulationTimeEnergyCalculator;
import org.palladiosimulator.simulizar.power.calculators.SimulationTimePowerCalculator;
import org.palladiosimulator.simulizar.power.evaluationscope.SimulationTimeEvaluationScope;
import org.palladiosimulator.simulizar.power.runtimemeasurement.EnergyRuntimeMeasurementsRecorder;
import org.palladiosimulator.simulizar.power.runtimemeasurement.PowerRuntimeMeasurementsRecorder;
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

    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC =
            MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;
    private static final MetricSetDescription ENERGY_CONSUMPTION_TUPLE_METRIC_DESC =
            MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE;
    

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
        
        Collection<MeasurementSpecification> powerMeasurementSpecs = getProbeFrameworkListener().
                getMeasurementSpecificationsForMetricDescription(POWER_CONSUMPTION_TUPLE_METRIC_DESC);
        if (!powerMeasurementSpecs.isEmpty()) {
            final PowerModelRegistry reg = new PowerModelRegistry();
            final PowerModelUpdaterSwitch modelUpdaterSwitch = new PowerModelUpdaterSwitch(reg,
                    new ExtensibleCalculatorInstantiatorImpl());
            final List<ConsumptionContext> createdContexts = new ArrayList<ConsumptionContext>(powerMeasurementSpecs.size());
            final List<SimulationTimeEvaluationScope> createdScopes = new ArrayList<SimulationTimeEvaluationScope>(
                    powerMeasurementSpecs.size());
            final RuntimeMeasurementModel runtimeMeasurementModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
            final SimuComModel simucomModel = getProbeFrameworkListener().getSimuComModel();
            final PCMResourceSetPartition globalPCMModel = this.getProbeFrameworkListener().getModelAccess().getGlobalPCMModel();
            for (MeasurementSpecification powerSpec : powerMeasurementSpecs) {
                MeasuringPoint mp = powerSpec.getMonitor().getMeasuringPoint();
                final PowerProvidingEntity ppe = InterpreterUtils
                        .getPowerProvidingEntityFromMeasuringPoint(globalPCMModel.getResourceSet(), mp);
                if (ppe == null) {
                    throw new IllegalStateException("MeasurementSpecification for metric "
                            + POWER_CONSUMPTION_TUPLE_METRIC_DESC.getName()
                            + " has to be related to a PowerProvidingEntity!");
                }

                Measure<Double, Duration>[] windowProperties = SimulizarSlidingWindowUtil.
                        getWindowPropertiesFromTemporalCharacterization(powerSpec
                        .getTemporalRestriction());
                Measure<Double, Duration> initialOffset = windowProperties[0];
                Measure<Double, Duration> samplingPeriod = windowProperties[1];
                SimulationTimeEvaluationScope scope = SimulationTimeEvaluationScope.createScope(ppe, simucomModel,
                        initialOffset, samplingPeriod);

                modelUpdaterSwitch.doSwitch(ppe);
                ConsumptionContext context = ConsumptionContext.createConsumptionContext(ppe, scope, reg);

                AbstractCumulativeEnergyCalculator energyCalculator = new SimpsonRuleCumulativeEnergyCalculator(
                        samplingPeriod, initialOffset);

                createdContexts.add(context);
                createdScopes.add(scope);
                SimulationTimePowerCalculator powerConsumptionCalculator =
                        new SimulationTimePowerCalculator(context, scope, ppe);
                SimulationTimeEnergyCalculator energyConsumptionCalculator =
                        new SimulationTimeEnergyCalculator(energyCalculator);

                MeasurementSpecification energySpec = MonitorRepositoryFactory.eINSTANCE
                        .createMeasurementSpecification();
                energySpec.setMetricDescription(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);
                energySpec.setMonitor(powerSpec.getMonitor());
                energySpec.setTemporalRestriction(powerSpec.getTemporalRestriction());

                // calculate power and energy consumption
                scope.addListener(powerConsumptionCalculator);
                powerConsumptionCalculator.addObserver(energyConsumptionCalculator);
                // the following two lines are optional: measurements are
                // recorded (e.g., by an EDP2 recorder)
                triggerMeasurementsRecording(powerConsumptionCalculator, mp, POWER_CONSUMPTION_TUPLE_METRIC_DESC);
                triggerMeasurementsRecording(energyConsumptionCalculator, mp, ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);
                // write measurements to RuntimeMeasurement (both power and energy measurements
                // are forwarded)
                powerConsumptionCalculator.addObserver(new PowerRuntimeMeasurementsRecorder(runtimeMeasurementModel,
                        powerSpec, mp));
                energyConsumptionCalculator.addObserver(new EnergyRuntimeMeasurementsRecorder(runtimeMeasurementModel,
                        energySpec, mp));
            }
            triggerAfterSimulationCleanup(createdContexts, createdScopes);
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
    private void triggerAfterSimulationCleanup(final Collection<ConsumptionContext> contextsToCleanup,
            final Collection<SimulationTimeEvaluationScope> scopesToCleanup) {
        assert contextsToCleanup != null && !contextsToCleanup.isEmpty();
        assert scopesToCleanup != null && !scopesToCleanup.isEmpty();
        assert getProbeFrameworkListener() != null;

        getProbeFrameworkListener().getSimuComModel().getConfiguration().addListener(new ISimulationListener() {
            @Override
            public void simulationStop() {
                for (ConsumptionContext context : contextsToCleanup) {
                    context.cleanUp();
                }
                for (SimulationTimeEvaluationScope scope : scopesToCleanup) {
                    scope.removeAllListeners();
                }
            }

            @Override
            public void simulationStart() {
            }
        });
    }
}
