package org.palladiosimulator.simulizar.metrics.powerconsumption;

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

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.interpreter.ConsumptionContext;
import de.fzi.power.interpreter.PowerConsumptionSwitch;

public class SimulationTimePowerConsumptionCalculator extends MeasurementSource implements
        ISimulationEvaluationScopeListener {

    private static final Unit<Power> DEFAULT_POWER_UNIT;
    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC =
            MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;

    static {
        DEFAULT_POWER_UNIT = MetricDescriptionUtility.getDefaultUnit(
                (NumericalBaseMetricDescription) MetricDescriptionConstants.POWER_CONSUMPTION, Power.class);
    }

    private final PowerConsumptionSwitch consumptionSwitch;
    private final SimulationTimeEvaluationScope scope;
    private final PowerProvidingEntity ppe;

    public SimulationTimePowerConsumptionCalculator(ConsumptionContext consumptionContext,
            SimulationTimeEvaluationScope scope, PowerProvidingEntity ppe) {
        super(POWER_CONSUMPTION_TUPLE_METRIC_DESC);

        if (consumptionContext == null) {
            throw new IllegalArgumentException("Given ConsumptionContext instance must not be null.");
        }
        if (scope == null) {
            throw new IllegalArgumentException("Given SimulationTimeEvaluationScope instance must not be null.");
        }
        if (ppe == null) {
            throw new IllegalArgumentException("Given PowerProvidingEntity instance must not be null.");
        }
        this.scope = scope;
        this.consumptionSwitch = PowerConsumptionSwitch.createPowerConsumptionSwitch(consumptionContext);
        this.ppe = ppe;
    }

    @Override
    public void newElementAvailable() {
        if (this.scope.hasNext()) {
            this.scope.next();
            Measurable<Power> calculatedConsumption = this.consumptionSwitch.doSwitch(this.ppe);
            TupleMeasurement newPowerMeasurement = new TupleMeasurement(POWER_CONSUMPTION_TUPLE_METRIC_DESC,
                    this.scope.getCurrentPointInTime(), Measure.valueOf(
                            calculatedConsumption.doubleValue(DEFAULT_POWER_UNIT), DEFAULT_POWER_UNIT));
            informListeners(newPowerMeasurement);
        } else {
            throw new IllegalStateException("Calculator was informed by scope that new measurement "
                    + "would be available but scope.hasNext() yields false");
        }
        
    }

    private void informListeners(TupleMeasurement newPowerMeasurement) {
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
