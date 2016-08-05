package org.palladiosimulator.simulizar.power.calculators;

import java.util.Objects;

import javax.measure.Measurable;
import javax.measure.Measure;
import javax.measure.quantity.Power;
import javax.measure.unit.Unit;

import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.simulizar.power.evaluationscope.ISimulationEvaluationScopeListener;
import org.palladiosimulator.simulizar.power.evaluationscope.SimulationTimeEvaluationScope;

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.interpreter.ConsumptionContext;
import de.fzi.power.interpreter.PowerConsumptionSwitch;

/**
 * Implementation of a {@link MeasurementSource} which uses measurements provided by a
 * {@link SimulationTimeEvaluationScope} to compute the power consumption of a
 * {@link PowerProvidingEntity} at simulation-time.
 * 
 * @author Florian Rosenthal
 *
 */
public class SimulationTimePowerCalculator extends MeasurementSource implements ISimulationEvaluationScopeListener {

    private static final Unit<Power> DEFAULT_POWER_UNIT;
    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC = MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;

    static {
        DEFAULT_POWER_UNIT = MetricDescriptionUtility.getDefaultUnit(
                (NumericalBaseMetricDescription) MetricDescriptionConstants.POWER_CONSUMPTION, Power.class);
    }

    private final PowerConsumptionSwitch consumptionSwitch;
    private final SimulationTimeEvaluationScope scope;
    private final PowerProvidingEntity ppe;

    /**
     * Initializes a new instance of the {@link SimulationTimePowerCalculator} class with the given
     * arguments.
     * 
     * @param consumptionContext
     *            The {@link ConsumptionContext} used for evaluating the power consumption.
     * @param scope
     *            The {@link SimulationTimeEvaluationScope} this instance will observe.
     * @param ppe
     *            The {@link PowerProvidingEntity} whose consumption is to be calculated.
     * @throws NullPointerException
     *             In case any of the arguments is {@code null}.
     */
    public SimulationTimePowerCalculator(final ConsumptionContext consumptionContext,
            final SimulationTimeEvaluationScope scope, final PowerProvidingEntity ppe) {
        super(POWER_CONSUMPTION_TUPLE_METRIC_DESC);

        this.scope = Objects.requireNonNull(scope, "Given SimulationTimeEvaluationScope instance must not be null.");
        this.consumptionSwitch = PowerConsumptionSwitch.createPowerConsumptionSwitch(
                Objects.requireNonNull(consumptionContext, "Given ConsumptionContext instance must not be null."));
        this.ppe = Objects.requireNonNull(ppe, "Given PowerProvidingEntity instance must not be null.");
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This implementation obtains a new power consumption measurement based on the new measurement
     * provided by the evaluation scope and forwards it to all attached listeners.
     */
    @Override
    public void newElementAvailable() {
        if (this.scope.hasNext()) {
            this.scope.next();
            Measurable<Power> calculatedConsumption = this.consumptionSwitch.doSwitch(this.ppe);
            TupleMeasurement newPowerMeasurement = new TupleMeasurement(POWER_CONSUMPTION_TUPLE_METRIC_DESC,
                    this.scope.getCurrentPointInTime(),
                    Measure.valueOf(calculatedConsumption.doubleValue(DEFAULT_POWER_UNIT), DEFAULT_POWER_UNIT));
            informListeners(newPowerMeasurement);
        } else {
            throw new IllegalStateException("Calculator was informed by scope that new measurement "
                    + "would be available but scope.hasNext() yields false");
        }

    }

    private void informListeners(final TupleMeasurement newPowerMeasurement) {
        assert newPowerMeasurement != null;
        assert newPowerMeasurement.isCompatibleWith(POWER_CONSUMPTION_TUPLE_METRIC_DESC);

        super.notifyMeasurementSourceListener(newPowerMeasurement);
    }

    @Override
    public void preUnregister() {
        for (IMeasurementSourceListener listener : getMeasurementSourceListeners()) {
            listener.preUnregister();
            super.removeObserver(listener);
        }
    }
}
