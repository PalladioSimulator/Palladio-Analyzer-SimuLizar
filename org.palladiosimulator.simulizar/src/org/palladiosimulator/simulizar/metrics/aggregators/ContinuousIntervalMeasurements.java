package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ContinuousIntervalMeasurements {

    /** Measurements taken in the interval currently investigated. */
    private final List<MeasureValueWithMeasuringTime> measurements;

    private boolean isClosed;

    /**
     * Initializes a list of continuous measurements taken in a given interval.
     * 
     * @param lastIntervalMeasurement
     *            The last measurement taken in a previous interval. Used to initialize the current
     *            measurement list.
     * @param intervalStartTime
     *            Start time of this interval.
     * @param intervalEndTime
     *            End time of this interval.
     */
    public ContinuousIntervalMeasurements(final Double lastIntervalMeasurement, final Double intervalStartTime) {
        super();
        this.measurements = new LinkedList<MeasureValueWithMeasuringTime>();
        this.measurements.add(new MeasureValueWithMeasuringTime(lastIntervalMeasurement, intervalStartTime));
        this.isClosed = false;
    }

    public ContinuousIntervalMeasurements(final ContinuousIntervalMeasurements previousMeasurements,
            final Double intervalStartTime) {
        this(previousMeasurements.getLastMeasurement(), intervalStartTime);
    }

    public void addMeasurement(final MeasureValueWithMeasuringTime measurement) {
        this.measurements.add(measurement);
    }

    public List<MeasureValueWithMeasuringTime> getMeasurements() {
        return Collections.unmodifiableList(this.measurements);
    }

    public Double getLastMeasurement() {
        return this.getLast().getMeasurement();
    }

    public Double getLastMeasurementTime() {
        return this.getLast().getMeasuringTime();
    }

    private MeasureValueWithMeasuringTime getLast() {
        return this.measurements.get(this.measurements.size() - 1);
    }

    public Double getIntervalStartTime() {
        return this.measurements.get(0).getMeasuringTime();
    }

    public Double getIntervalEndTime() {
        return this.getLastMeasurementTime();
    }

    public void closeInterval(final double currentTime) {
        if (this.isClosed()) {
            throw new RuntimeException("Can only close interval if it has not been closed yet!");
        }

        this.measurements.add(new MeasureValueWithMeasuringTime(getLastMeasurement(), currentTime));
        this.isClosed = true;
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public Double getIntervalTime() {
        return this.getIntervalEndTime() - this.getIntervalStartTime();
    }
}
