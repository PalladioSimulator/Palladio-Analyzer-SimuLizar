package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.palladiosimulator.simulizar.metrics.aggregators.Area.AreaCalculation;

class Areas {

    public static final AreaCalculation MULTIPLICATION = (value, duration) -> value * duration;
    public static final AreaCalculation LOGARITHMIC_VALUE_MULTIPLICATION = (value, duration) -> Math.log(value)
            * duration;
    public static final AreaCalculation INVERSE_VALUE_MULTIPLICATION = (value, duration) -> (1.0 / value) * duration;

    private final List<Area> areas;
    private Double totalArea;

    public Areas(final ContinuousIntervalMeasurements measurements) {
        this(measurements, MULTIPLICATION);
    }

    public Areas(final ContinuousIntervalMeasurements measurements, final AreaCalculation areaCalculation) {
        this.areas = new ArrayList<Area>(measurements.getMeasurements().size() - 1);
        this.totalArea = 0.0;

        calculateAreas(measurements, areaCalculation);
    }

    private void calculateAreas(final ContinuousIntervalMeasurements measurements,
            final AreaCalculation areaCalculation) {
        for (int i = 1; i < measurements.getMeasurements().size(); i++) {
            final Area area = new Area(measurements.getMeasurements().get(i - 1).getMeasurement(),
                    measurements.getMeasurements().get(i - 1).getMeasuringTime(),
                    measurements.getMeasurements().get(i).getMeasuringTime(), areaCalculation);
            this.areas.add(area);
            this.totalArea += area.getArea();
        }
    }

    public List<Area> getAreas() {
        return this.areas;
    }

    public Double getTotalArea() {
        return this.totalArea;
    }

    public void sort() {
        Collections.sort(this.areas);
    }

}
