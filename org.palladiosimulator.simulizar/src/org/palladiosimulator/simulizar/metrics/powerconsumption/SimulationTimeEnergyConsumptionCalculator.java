package org.palladiosimulator.simulizar.metrics.powerconsumption;

import javax.measure.Measurable;
import javax.measure.Measure;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Power;
import javax.measure.unit.Unit;

import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

import de.fzi.power.interpreter.calculators.energy.AbstractCumulativeEnergyCalculator;

public class SimulationTimeEnergyConsumptionCalculator extends MeasurementSource implements IMeasurementSourceListener {

   
    private static final Unit<Energy> DEFAULT_ENERGY_UNIT;
    private static final MetricSetDescription ENERGY_CONSUMPTION_TUPLE_METRIC_DESC =
            MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE;
    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC =
            MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;

    static {
        DEFAULT_ENERGY_UNIT = MetricDescriptionUtility.getDefaultUnit(
                (NumericalBaseMetricDescription) MetricDescriptionConstants.ENERGY_CONSUMPTION, Energy.class);
    }
    
    private final AbstractCumulativeEnergyCalculator energyCalculator;

    public SimulationTimeEnergyConsumptionCalculator(AbstractCumulativeEnergyCalculator energyCalculator) {
        super(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);
        if (energyCalculator == null) {
            throw new IllegalArgumentException("Given calculator must not be null.");
        }
        this.energyCalculator = energyCalculator;
    }

    private void informListeners(Measurement newEnergyMeasurement) {
        assert newEnergyMeasurement != null;
        assert newEnergyMeasurement.isCompatibleWith(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);

        super.notifyMeasurementSourceListener(newEnergyMeasurement);
    }

    @Override
    public void newMeasurementAvailable(Measurement newInputMeasurement) {
        if (newInputMeasurement == null || !newInputMeasurement.isCompatibleWith(POWER_CONSUMPTION_TUPLE_METRIC_DESC)) {
            throw new IllegalStateException("Somehow a wrong measurement kind was passed.");
        }
        Measurable<Power> powerSample = newInputMeasurement
                .getMeasureForMetric(MetricDescriptionConstants.POWER_CONSUMPTION);
        Measurable<Energy> energySample = this.energyCalculator.calculateNext(powerSample);
        Measure<?, ?> energyMeasure = Measure.valueOf(energySample.doubleValue(DEFAULT_ENERGY_UNIT), DEFAULT_ENERGY_UNIT);
        Measure<?, ?> pointInTimeMeasure = newInputMeasurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        Measurement newEnergyMeasurement = new TupleMeasurement(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC,
                pointInTimeMeasure, energyMeasure);
        informListeners(newEnergyMeasurement);
    }

    @Override
    public void preUnregister() {
        for (IMeasurementSourceListener listener: super.getMeasurementSourceListeners()) {
            listener.preUnregister();
            super.removeObserver(listener);
        }
    }
}
