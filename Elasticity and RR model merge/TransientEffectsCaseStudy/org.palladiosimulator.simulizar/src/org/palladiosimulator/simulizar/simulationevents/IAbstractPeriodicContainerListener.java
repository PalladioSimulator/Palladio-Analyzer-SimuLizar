package org.palladiosimulator.simulizar.simulationevents;

import org.palladiosimulator.simulizar.runtimestate.CostModel;

/**
 * Interface for listening to the periodically triggered container events.
 *
 * @author Hendrik Eikerling, Sebastian Lehrig
 */
public interface IAbstractPeriodicContainerListener {

    /**
     * The method that is triggered periodically.
     *
     * @param measuringPoint
     *            The container that fires the periodic trigger.
     */

    public abstract void triggerPeriodicUpdate(CostModel costModel, double timestamp, final double delay);
}
