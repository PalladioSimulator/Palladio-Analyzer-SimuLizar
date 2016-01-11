package org.palladiosimulator.simulizar.runtimestate;

public class CostTuple {
    private final Double pointInTime;
    private final Double cost;

    public CostTuple(final Double pointInTime, final Double cost) {
        super();
        this.pointInTime = pointInTime;
        this.cost = cost;
    }

    public Double getCost() {
        return this.cost;
    }

    public Double getPointInTime() {
        return this.pointInTime;
    }
}
