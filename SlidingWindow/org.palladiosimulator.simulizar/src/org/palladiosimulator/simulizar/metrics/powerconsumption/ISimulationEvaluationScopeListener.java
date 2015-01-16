package org.palladiosimulator.simulizar.metrics.powerconsumption;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

public interface ISimulationEvaluationScopeListener {
    public void next(Measure<Double, Duration> currentPointInTime);
}
