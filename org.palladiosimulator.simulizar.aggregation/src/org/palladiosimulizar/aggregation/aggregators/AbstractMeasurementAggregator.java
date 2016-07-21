package org.palladiosimulizar.aggregation.aggregators;

import java.util.Objects;
import java.util.Optional;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.StatisticalCharacterization;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

public abstract class AbstractMeasurementAggregator extends PRMRecorder implements IMeasurementSourceListener {

    private final StatisticalCharacterizationAggregator aggregator;
    private final NumericalBaseMetricDescription expectedMetric;

    public AbstractMeasurementAggregator(NumericalBaseMetricDescription expectedMetric,
            RuntimeMeasurementModel prmAccess, MeasurementSpecification measurementSpecification,
            StatisticalCharacterization statisticalCharacterization) {

        super(prmAccess, measurementSpecification);
        this.expectedMetric = Objects.requireNonNull(expectedMetric);
        this.aggregator = Objects.requireNonNull(statisticalCharacterization).getAggregator(expectedMetric);
    }

    @Override
    public final void newMeasurementAvailable(MeasuringValue newMeasurement) {
        if (!Objects.requireNonNull(newMeasurement).isCompatibleWith(this.expectedMetric)
                && !MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(this.expectedMetric,
                        newMeasurement.getMetricDesciption())) {
            throw new IllegalStateException("Somehow a wrong measurement kind was passed.");
        }
        collectMeasurement(newMeasurement);
        if (aggregationRequired()) {
            onPreAggregate();
            aggregate();
            onPostAggregate();
        }
    }

    @Override
    public void preUnregister() {
        super.detachFromPRM();
        clear();

    }

    public abstract void clear();

    protected abstract boolean aggregationRequired();

    protected abstract Amount<Duration> getIntervalStartTime();

    protected abstract Amount<Duration> getIntervalEndTime();

    protected abstract Iterable<MeasuringValue> getDataToAggregate();

    protected abstract void collectMeasurement(MeasuringValue newMeasurement);

    /**
     * This method is invoked directly before an aggregation of the measurements is to be performed
     * and is intended to be overriden by subclasses to include implementation-specific behavior.
     * <br>
     * The default implementation does nothing.
     */
    protected void onPreAggregate() {

    }

    /**
     * This method is invoked directly after an aggregation of the measurements has been performed
     * and is intended to be overriden by subclasses to include implementation-specific behavior.
     * <br>
     * The default implementation does nothing.
     */
    protected void onPostAggregate() {
    }

    /**
     * Convenience method for all subclasses to obtain the point in time (which is assumed to be
     * present) a measurement was taken.
     *
     * @param measurement
     *            A {@link MeasuringValue} representing the measurement.
     * @return The corresponding point in time, expressed in terms of an {@link Amount}.
     */
    protected static Amount<Duration> getPointInTimeOfMeasurement(MeasuringValue measurement) {
        assert measurement != null;

        Measure<Double, Duration> pointInTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        return Amount.valueOf(pointInTimeMeasure.getValue(), pointInTimeMeasure.getUnit());
    }

    private void aggregate() {
        Amount<Duration> start = getIntervalStartTime();
        Amount<Duration> end = getIntervalEndTime();

        MeasuringValue aggregatedData = this.aggregator.aggregateData(getDataToAggregate(), start, end,
                Optional.empty());

        // forward aggregated data (expressed as double in default unit of numerical base metric)
        super.updateMeasurementValue(aggregatedData.getMeasureForMetric(this.expectedMetric)
                .doubleValue(this.expectedMetric.getDefaultUnit()));
    }
}
