package org.palladiosimulizar.aggregation.aggregators;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.VariableSizeAggregation;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

public class VariableSizeMeasurementAggregator extends AbstractMeasurementAggregator {

    // assume that the consecutive measurements are chronologically ordered
    private final Deque<MeasuringValue> buffer;
    private final VariableSizeAggregation variableSizeAggregation;
    private final Amount<Duration> retrospectionLength;

    public VariableSizeMeasurementAggregator(NumericalBaseMetricDescription expectedMetric,
            RuntimeMeasurementModel runtimeMeasurementModel, VariableSizeAggregation variableSizeAggregation) {
        super(Objects.requireNonNull(expectedMetric), Objects.requireNonNull(runtimeMeasurementModel),
                (MeasurementSpecification) Objects.requireNonNull(variableSizeAggregation).eContainer(),
                variableSizeAggregation.getStatisticalCharacterization());

        this.buffer = new LinkedList<>();
        this.variableSizeAggregation = variableSizeAggregation;
        Measure<Double, Duration> retrospectionMeasure = this.variableSizeAggregation.getRetrospection()
                .getRetrospectionLengthAsMeasure();
        this.retrospectionLength = Amount.valueOf(retrospectionMeasure.getValue(), retrospectionMeasure.getUnit());
    }

    @Override
    public void clear() {
        this.buffer.clear();
    }

    @Override
    protected boolean aggregationRequired() {
        return !this.buffer.isEmpty() && isRetrospectionLengthReached();
    }

    @Override
    protected Amount<Duration> getIntervalStartTime() {
        return getIntervalEndTime().minus(this.retrospectionLength);
    }

    @Override
    protected Amount<Duration> getIntervalEndTime() {
        return getPointInTimeOfMeasurement(this.buffer.getLast());
    }

    @Override
    protected Iterable<MeasuringValue> getDataToAggregate() {
        return this.buffer;
    }

    @Override
    protected void collectMeasurement(MeasuringValue newMeasurement) {
        // assume that newMeasurement is more recent than the last in buffer
        // i.e., the measurements are chronologically ordered
        this.buffer.add(newMeasurement);
    }

    private void evictMeasurements() {
        while (!this.buffer.isEmpty() && getPointInTimeOfMeasurement(this.buffer.getLast())
                .minus(getPointInTimeOfMeasurement(this.buffer.getFirst())).isGreaterThan(this.retrospectionLength)) {
            this.buffer.pollFirst();
        }
    }

    @Override
    protected void onPreAggregate() {
        evictMeasurements();
    }

    private static Amount<Duration> getPointInTimeOfMeasurement(MeasuringValue measurement) {
        assert measurement != null;

        Measure<Double, Duration> pointInTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        return Amount.valueOf(pointInTimeMeasure.getValue(), pointInTimeMeasure.getUnit());
    }

    private boolean isRetrospectionLengthReached() {
        assert !this.buffer.isEmpty();

        return !getPointInTimeOfMeasurement(this.buffer.getLast()).minus(this.retrospectionLength)
                .isLessThan(getPointInTimeOfMeasurement(this.buffer.getFirst()));
    }
}
