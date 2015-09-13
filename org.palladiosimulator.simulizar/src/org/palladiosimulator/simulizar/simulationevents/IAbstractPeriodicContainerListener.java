package org.palladiosimulator.simulizar.simulationevents;

import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

/**
 * Interface for listening to the periodically triggered container events.
 * 
 * @author Hendrik Eikerling
 */
public interface IAbstractPeriodicContainerListener {
    /**
     * The method that is triggered periodically.
     * 
     * @param periodicEntity
     *            The entity that fires the periodic trigger.
     * @param myContainer
     *            The container that is attached to the periodic trigger entity.
     */
    public abstract void triggerPeriodicUpdate(final PeriodicallyTriggeredContainerEntity periodicEntity,
            SimulatedResourceContainer myContainer);
}
