package org.palladiosimulizar.aggregation.aggregators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.apache.commons.collections15.IteratorUtils;
import org.jscience.physics.amount.Amount;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.FixedSizeAggregation;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

public class FixedSizeMeasurementsAggregator extends AbstractMeasurementAggregator {

    private final FixedSizeAggregation fixedSizeAggregation;
    private final InternalBuffer buffer;

    private int measurementsUntilNextAggregation = 0;

    public FixedSizeMeasurementsAggregator(NumericalBaseMetricDescription expectedMetric,
            RuntimeMeasurementModel runtimeMeasurementModel, FixedSizeAggregation fixedSizeAggregation) {
        super(Objects.requireNonNull(expectedMetric), Objects.requireNonNull(runtimeMeasurementModel),
                (MeasurementSpecification) Objects.requireNonNull(fixedSizeAggregation).eContainer(),
                fixedSizeAggregation.getStatisticalCharacterization());

        this.fixedSizeAggregation = fixedSizeAggregation;

        this.buffer = new InternalBuffer(this.fixedSizeAggregation.getNumberOfMeasurements());

        resetCounter();
    }

    private void resetCounter() {
        this.measurementsUntilNextAggregation = this.fixedSizeAggregation.getFrequency();
    }

    private void decrementCounter() {
        --this.measurementsUntilNextAggregation;
    }

    @Override
    protected void collectMeasurement(MeasuringValue newMeasurement) {
        this.buffer.add(newMeasurement);
        decrementCounter();
    }

    @Override
    protected boolean aggregationRequired() {
        return this.measurementsUntilNextAggregation == 0;
    }

    @Override
    protected Amount<Duration> getIntervalStartTime() {
        Measure<Double, Duration> start = this.buffer.getEldestElement()
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        return Amount.valueOf(start.getValue(), start.getUnit());
    }

    @Override
    protected Amount<Duration> getIntervalEndTime() {
        Measure<Double, Duration> end = this.buffer.getNewestElement()
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        return Amount.valueOf(end.getValue(), end.getUnit());
    }

    @Override
    protected Iterable<MeasuringValue> getDataToAggregate() {
        return this.buffer;
    }

    @Override
    protected void onPostAggregate() {
        resetCounter();
    }

    @Override
    public void clear() {
        this.buffer.clear();
    }

    private static final class InternalBuffer implements Iterable<MeasuringValue> {

        // the data container will be used in a circular way
        private final MeasuringValue[] data;
        // pointer to the least recent element of the data
        // that is the element which was added least recently
        // this is used as base "address" for all data accesses
        private int eldestElementPointer = 0;
        private int currentElementCount = 0;

        private InternalBuffer(int capacity) {
            assert capacity > 0;
            this.data = new MeasuringValue[capacity];
        }

        public int size() {
            return this.currentElementCount;
        }

        public int capacity() {
            return this.data.length;
        }

        public boolean isEmpty() {
            return this.currentElementCount == 0;
        }

        public boolean isFull() {
            return size() == capacity();
        }

        public boolean add(MeasuringValue measuringValue) {
            if (isFull()) {
                addFull(measuringValue);
            } else {
                addNotFull(measuringValue);
            }
            return true;
        }

        public MeasuringValue getEldestElement() {
            return this.data[this.eldestElementPointer];
        }

        private void addFull(MeasuringValue newElemement) {
            assert isFull() && newElemement != null;

            this.data[this.eldestElementPointer] = newElemement;
            adjustOldestElementPointer();
        }

        private void addNotFull(MeasuringValue elementToAdd) {
            assert !isFull() && elementToAdd != null;

            int index = (this.eldestElementPointer + this.currentElementCount) % capacity();
            this.data[index] = elementToAdd;
            this.currentElementCount++;
        }

        private void adjustOldestElementPointer() {
            this.eldestElementPointer++;
            this.eldestElementPointer %= capacity();
            assert this.eldestElementPointer >= 0 && this.eldestElementPointer < capacity();
        }

        public void clear() {
            Arrays.fill(data, null);
            this.eldestElementPointer = this.currentElementCount = 0;

        }

        public MeasuringValue getNewestElement() {
            return this.data[(this.eldestElementPointer + size() - 1) % capacity()];
        }

        @Override
        public Iterator<MeasuringValue> iterator() {
            return IteratorUtils.arrayIterator(this.data);
        }

    }

}
