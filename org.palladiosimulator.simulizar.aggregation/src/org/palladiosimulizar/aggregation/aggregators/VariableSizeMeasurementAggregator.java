package org.palladiosimulizar.aggregation.aggregators;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
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
                Objects.requireNonNull(variableSizeAggregation));

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
        switch (getExpectedMetric().getScopeOfValidity()) {
        // in the case of a continuous scope
        // maintain the first measurement prior to the left interval bound
        case CONTINUOUS:
            MeasuringValue first = this.buffer.peekFirst(); // null if buffer is empty
            MeasuringValue lastPolled = null;
            while (first != null && getPointInTimeOfMeasurement(this.buffer.getLast())
                    .minus(getPointInTimeOfMeasurement(first)).isGreaterThan(this.retrospectionLength)) {
                lastPolled = this.buffer.pollFirst();
                first = this.buffer.peekFirst();
            }
            if (lastPolled != null) {
                this.buffer.addFirst(lastPolled);
            }
            break;
        case DISCRETE:
            while (!this.buffer.isEmpty() && getPointInTimeOfMeasurement(this.buffer.getLast())
                    .minus(getPointInTimeOfMeasurement(this.buffer.getFirst()))
                    .isGreaterThan(this.retrospectionLength)) {
                this.buffer.pollFirst();
            }
            break;
        default:
            throw new AssertionError("Should not be reached!");
        }

    }

    @Override
    protected void onPreAggregate() {
        evictMeasurements();
    }

    private boolean isRetrospectionLengthReached() {
        assert !this.buffer.isEmpty();

        return !getPointInTimeOfMeasurement(this.buffer.getLast()).minus(this.retrospectionLength)
                .isLessThan(getPointInTimeOfMeasurement(this.buffer.getFirst()));
    }
}
