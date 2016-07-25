package org.palladiosimulizar.aggregation.aggregators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import javax.measure.quantity.Duration;

import org.apache.commons.collections15.IteratorUtils;
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

    private final FixedSizeAggregation fixedSizeAggregation;
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
    public FixedSizeMeasurementsAggregator(NumericalBaseMetricDescription expectedMetric,
            RuntimeMeasurementModel runtimeMeasurementModel, FixedSizeAggregation fixedSizeAggregation) {
        super(Objects.requireNonNull(expectedMetric), Objects.requireNonNull(runtimeMeasurementModel),
                Objects.requireNonNull(fixedSizeAggregation));

        this.buffer = new InternalBuffer(checkAndGetNumberOfMeasurementsAttribute(fixedSizeAggregation));
        this.fixedSizeAggregation = fixedSizeAggregation;
    }

    private static int checkAndGetNumberOfMeasurementsAttribute(FixedSizeAggregation fixedSizeAggregation) {
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
    protected void collectMeasurement(MeasuringValue newMeasurement) {
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
            adjustEldestElementPointer();
        }

        private void addNotFull(MeasuringValue elementToAdd) {
            assert !isFull() && elementToAdd != null;

            int index = (this.eldestElementPointer + this.currentElementCount) % capacity();
            this.data[index] = elementToAdd;
            this.currentElementCount++;
        }

        private void adjustEldestElementPointer() {
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
