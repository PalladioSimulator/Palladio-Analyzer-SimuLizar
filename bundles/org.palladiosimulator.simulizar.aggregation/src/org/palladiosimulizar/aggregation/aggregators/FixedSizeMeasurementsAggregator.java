package org.palladiosimulizar.aggregation.aggregators;

import static org.apache.commons.collections15.IteratorUtils.arrayIterator;
import static org.apache.commons.collections15.IteratorUtils.unmodifiableIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import jakarta.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.FixedSizeAggregation;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

/**
 * Implementation of the {@link AbstractMeasurementAggregator} class dedicated to aggregate a fixed
 * number of consecutive measurements and to forward the aggregation result to a
 * {@link RuntimeMeasurementModel}.
 * 
 * @author Florian Rosenthal
 *
 */
public class FixedSizeMeasurementsAggregator extends AbstractMeasurementAggregator {

    private final InternalBuffer buffer;

    /**
     * Initializes a new instance of the {@link FixedSizeMeasurementsAggregator} class with the
     * given parameters.
     * 
     * @param expectedMetric
     *            The expected {@link NumericalBaseMetricDescription} of the aggregated measurements
     *            to be forwarded to the runtime measurement model.
     * @param prmAccess
     *            The {@link RuntimeMeasurementModel} where the aggregation results are forwarded
     *            to.
     * @param aggregation
     *            The {@link FixedSizeAggregation} model element specifying the properties of
     *            measurement aggregation.
     * @throws NullPointerException
     *             In case any of the parameters is {@code null}.
     * @throws IllegalStateException
     *             If the value of the 'Number Of Measurements' attribute of the passed
     *             {@link FixedSizeAggregation} is not positive.
     */
    public FixedSizeMeasurementsAggregator(final NumericalBaseMetricDescription expectedMetric,
            final RuntimeMeasurementModel runtimeMeasurementModel, final FixedSizeAggregation fixedSizeAggregation) {
        super(Objects.requireNonNull(expectedMetric), Objects.requireNonNull(runtimeMeasurementModel),
                Objects.requireNonNull(fixedSizeAggregation));

        this.buffer = new InternalBuffer(checkAndGetNumberOfMeasurementsAttribute(fixedSizeAggregation));
    }

    private static int checkAndGetNumberOfMeasurementsAttribute(final FixedSizeAggregation fixedSizeAggregation) {
        int numMeas = fixedSizeAggregation.getNumberOfMeasurements();
        if (numMeas < 1) {
            throw new IllegalStateException("Value of '"
                    + MonitorRepositoryPackage.Literals.FIXED_SIZE_AGGREGATION__NUMBER_OF_MEASUREMENTS.getName()
                    + "' attribute of " + "'" + fixedSizeAggregation.eClass().getName() + "' with id "
                    + fixedSizeAggregation.getId() + " must be positive!");
        }
        return numMeas;
    }

    @Override
    protected void collectMeasurement(final MeasuringValue newMeasurement) {
        this.buffer.add(newMeasurement);
    }

    @Override
    protected boolean aggregationRequired() {
        return this.buffer.isFull();
    }

    @Override
    protected Amount<Duration> getIntervalStartTime() {
        return getPointInTimeOfMeasurement(this.buffer.getEldestElement());
    }

    @Override
    protected Amount<Duration> getIntervalEndTime() {
        return getPointInTimeOfMeasurement(this.buffer.getNewestElement());
    }

    @Override
    protected Iterable<MeasuringValue> getDataToAggregate() {
        return this.buffer;
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

        private InternalBuffer(final int capacity) {
            assert capacity > 0;
            this.data = new MeasuringValue[capacity];
        }

        public int size() {
            return this.currentElementCount;
        }

        public int capacity() {
            return this.data.length;
        }

        public boolean isFull() {
            return size() == capacity();
        }

        public boolean add(final MeasuringValue measuringValue) {
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

        private void addFull(final MeasuringValue newElemement) {
            assert isFull() && newElemement != null;

            this.data[this.eldestElementPointer] = newElemement;
            // adjust the pointer to the eldest/least recent element
            this.eldestElementPointer++;
            this.eldestElementPointer %= capacity();
            assert this.eldestElementPointer >= 0 && this.eldestElementPointer < capacity();
        }

        private void addNotFull(final MeasuringValue elementToAdd) {
            assert !isFull() && elementToAdd != null;

            this.data[this.currentElementCount++] = elementToAdd;
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
            return unmodifiableIterator(arrayIterator(this.data));
        }

    }

}
