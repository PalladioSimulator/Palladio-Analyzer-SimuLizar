package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.simucomframework.entities.SimuComEntity;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class PeriodicallyTriggeredSimulationEntity extends SimuComEntity {

    private PeriodicSimulationEvent myTriggerEvent;
    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredSimulationEntity.class);

    public PeriodicallyTriggeredSimulationEntity(final SimuComModel model, final double firstOccurrence,
            final double delay) {
        super(model, "PeriodicallyTriggeredSimulationEntity");
        this.myTriggerEvent = new PeriodicSimulationEvent(model, delay);
        this.myTriggerEvent.schedule(this, firstOccurrence);
    }

    public final void trigger() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Periodic trigger for entity " + this.getName() + " occurred at simulation time "
                    + this.getModel().getSimulationControl().getCurrentSimulationTime());
        }
        this.triggerInternal();
    }

    public double getSimulationTimeOfNextEventTrigger() {
        return this.myTriggerEvent.scheduledAtTime();
    }

    public void setDelayAndReinitialize(final double nextOccurrence, final double delay) {
        this.removeEvent();
        this.myTriggerEvent = new PeriodicSimulationEvent(this.getModel(), delay);
        this.myTriggerEvent.schedule(this, nextOccurrence);
    }

    public void stopScheduling() {
        this.removeEvent();
    }

    protected void triggerInternal() {
    }

    protected void removeEvent() {
        this.myTriggerEvent.removeEvent();
    }

}
