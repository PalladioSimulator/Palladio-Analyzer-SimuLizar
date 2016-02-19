package org.palladiosimulator.simulizar.metrics.powerconsumption;

import org.palladiosimulator.experimentanalysis.SlidingWindow;

/**
 * By implementing this interface, classes are enabled to observe
 * {@link SimulationTimeEvaluationScope}s. Clients may want to do so in order to keep track of
 * measurements that are collected each time the scope's underlying {@link SlidingWindow} moves on.
 * 
 * @author Florian Rosenthal
 *
 */
public interface ISimulationEvaluationScopeListener {

    /**
     * An observed {@link SimulationTimeEvaluationScope} invokes this callback method once a new
     * measurement has been collected. The new measurement can be retrieved by means of the scope's
     * {@link SimulationTimeEvaluationScope#next()} method or by using the scope's {@code iterator}.
     */
    public void newElementAvailable();

    /**
     * If the observed scope is about to detach an observer (e.g., due to a call of
     * {@link SimulationTimeEvaluationScope#removeAllListeners()} or
     * {@link SimulationTimeEvaluationScope#removeListener(ISimulationEvaluationScopeListener)}),
     * this callback method is invoked.
     */
    public void preUnregister();
}
