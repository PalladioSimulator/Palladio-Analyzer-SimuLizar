package org.palladiosimulator.simulizar.metrics.aggregators;

class Area implements Comparable<Area> {

    private final Double measureValue;
    private final Double area;

    public Area(final Double measureValue, final Double startTime, final Double endTime,
            final AreaCalculation areaCalculation) {
        this.area = calculate(measureValue, endTime - startTime, areaCalculation);
        this.measureValue = measureValue;
    }

    interface AreaCalculation {

        Double calculation(final Double value, final Double duration);
    }

    private Double calculate(final Double value, final Double duration, final AreaCalculation areaCalculation) {
        return areaCalculation.calculation(value, duration);
    }

    public Double getMeasureValue() {
        return this.measureValue;
    }

    public Double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(final Area other) {
        return this.measureValue.compareTo(other.measureValue);
    }
}