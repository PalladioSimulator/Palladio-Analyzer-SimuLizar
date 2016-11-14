package org.palladiosimulator.simulizar.power.calculators;

import java.util.Objects;

import javax.measure.Measurable;
import javax.measure.Measure;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Power;
import javax.measure.unit.Unit;

import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

import de.fzi.power.interpreter.calculators.energy.AbstractCumulativeEnergyCalculator;

/**
 * Implementation of a {@link MeasurementSource} which uses power measurements provided by another
 * {@link MeasurementSource} (e.g., a {@link SimulationTimePowerCalculator}) to compute energy
 * consumptions at simulation-time.<br>
 * 
 * @author Florian Rosenthal
 *
 */
public class SimulationTimeEnergyCalculator extends MeasurementSource implements IMeasurementSourceListener {

    private static final Unit<Energy> DEFAULT_ENERGY_UNIT;
    private static final MetricSetDescription ENERGY_CONSUMPTION_TUPLE_METRIC_DESC = MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE;
    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC = MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;

    static {
        DEFAULT_ENERGY_UNIT = MetricDescriptionUtility.getDefaultUnit(
                (NumericalBaseMetricDescription) MetricDescriptionConstants.ENERGY_CONSUMPTION, Energy.class);
    }

    private final AbstractCumulativeEnergyCalculator energyCalculator;

    /**
     * Initializes a new instance of the {@link SimulationTimeEnergyCalculator} class with the given
     * argument.
     * 
     * @param energyCalculator
     *            An {@link AbstractCumulativeEnergyCalculator} denoting the algorithm to use power
     *            the computation.
     */
    public SimulationTimeEnergyCalculator(final AbstractCumulativeEnergyCalculator energyCalculator) {
        super(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);
        this.energyCalculator = Objects.requireNonNull(energyCalculator, "Given calculator must not be null.");
    }

    private void informListeners(final TupleMeasurement newEnergyMeasurement) {
        assert newEnergyMeasurement != null;
        assert newEnergyMeasurement.isCompatibleWith(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);

        super.notifyMeasurementSourceListener(newEnergyMeasurement);
    }

    @Override
    public void newMeasurementAvailable(final MeasuringValue newInputMeasurement) {
        if (newInputMeasurement == null || !newInputMeasurement.isCompatibleWith(POWER_CONSUMPTION_TUPLE_METRIC_DESC)) {
            throw new IllegalStateException("Somehow a wrong measurement kind was passed.");
        }
        Measurable<Power> powerSample = newInputMeasurement
                .getMeasureForMetric(MetricDescriptionConstants.POWER_CONSUMPTION);
        Measurable<Energy> energySample = this.energyCalculator.calculateNext(powerSample);
        Measure<?, ?> energyMeasure = Measure.valueOf(energySample.doubleValue(DEFAULT_ENERGY_UNIT),
                DEFAULT_ENERGY_UNIT);
        Measure<?, ?> pointInTimeMeasure = newInputMeasurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        TupleMeasurement newEnergyMeasurement = new TupleMeasurement(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC,
                pointInTimeMeasure, energyMeasure);
        informListeners(newEnergyMeasurement);
    }

    @Override
    public void preUnregister() {
        for (IMeasurementSourceListener listener : super.getMeasurementSourceListeners()) {
            listener.preUnregister();
            super.removeObserver(listener);
        }
    }
}
