package org.palladiosimulator.simulizar.metrics.powerconsumption;

import javax.measure.Measure;
import javax.measure.quantity.Power;
import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.interpreter.ConsumptionContext;
import de.fzi.power.interpreter.PowerConsumptionSwitch;

public class SimulationTimePowerConsumptionCalculator extends MeasurementSource implements ISimulationEvaluationScopeListener {
    
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
    
    public SimulationTimePowerConsumptionCalculator(ConsumptionContext consumptionContext, SimulationTimeEvaluationScope scope,
            PowerProvidingEntity ppe) {
        super(POWER_CONSUMPTION_TUPLE_METRIC_DESC);
        this.scope = scope;
        this.consumptionSwitch = PowerConsumptionSwitch.createPowerConsumptionSwitch(consumptionContext);
        this.ppe = ppe;
    }

    @Override
    public void newElementAvailable() {
        if (scope.hasNext()) {
            scope.next();
            Amount<Power> calulatedConsumption = this.consumptionSwitch.doSwitch(ppe);
            Measure<Double, ? extends Quantity> powerMeasure = Measure.valueOf(
                    calulatedConsumption.doubleValue(DEFAULT_POWER_UNIT), DEFAULT_POWER_UNIT);
            Measurement newPowerMeasurement = new TupleMeasurement(POWER_CONSUMPTION_TUPLE_METRIC_DESC, scope
                    .getCurrentPointInTime(), powerMeasure);
            informListeners(newPowerMeasurement);
        }
        
    }

    private void informListeners(Measurement newPowerMeasurement) {
        assert newPowerMeasurement != null;
        
        super.notifyMeasurementSourceListener(newPowerMeasurement);
    }
}
