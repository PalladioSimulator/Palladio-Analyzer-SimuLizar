package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.simucomframework.entities.SimuComEntity;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class PeriodicallyTriggeredSimulationEntity extends SimuComEntity {

    private final PeriodicSimulationEvent myTriggerEvent;
    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredSimulationEntity.class);

    public PeriodicallyTriggeredSimulationEntity(final SimuComModel model, final double firstOccurrence,
            final double delay) {
        super(model, "PeriodicallyTriggeredSimulationEntity");
        myTriggerEvent = new PeriodicSimulationEvent(model, delay);
        myTriggerEvent.schedule(this, firstOccurrence);
    }

    public final void trigger() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Periodic trigger for entity " + getName() + " occurred at simulation time "
                    + getModel().getSimulationControl().getCurrentSimulationTime());
        }
        triggerInternal();
    }

    protected void triggerInternal() {
    }

}
