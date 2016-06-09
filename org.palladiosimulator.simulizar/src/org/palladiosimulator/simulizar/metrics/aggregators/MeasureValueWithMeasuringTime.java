package org.palladiosimulator.simulizar.metrics.aggregators;

public class MeasureValueWithMeasuringTime implements Comparable<MeasureValueWithMeasuringTime> {

    private final Double measurement;

    private final Double measuringTime;

    public MeasureValueWithMeasuringTime(final double measurement, final double measuringTime) {
        this.measurement = measurement;
        this.measuringTime = measuringTime;
    }

    public Double getMeasurement() {
        return this.measurement;
    }

    public Double getMeasuringTime() {
        return this.measuringTime;
    }

    @Override
    public int compareTo(final MeasureValueWithMeasuringTime other) {
        return this.measurement.compareTo(other.measurement);
    }
}