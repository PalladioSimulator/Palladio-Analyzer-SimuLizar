package org.palladiosimulator.simulizar.simulationevents;

import java.util.Optional;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.SimpleEventBasedSimEntity;

public abstract class PeriodicallyTriggeredSimulationEntity extends SimpleEventBasedSimEntity {

    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredSimulationEntity.class);
    private double delay;

    public PeriodicallyTriggeredSimulationEntity(final ISimEventFactory eventFactory, final double firstOccurrence,
            final double delay) {
        super(eventFactory, "PeriodicallyTriggeredSimulationEntity");
        this.activate(firstOccurrence);
        this.delay = delay;
    }

    public double getSimulationTimeOfNextEventTrigger() {
        return this.getNextOccurence().get();
    }

    public void stopScheduling() {
        this.unschedule();
    }

    protected void setDelay(double delay) {
        this.delay = delay;        
    }
    
    @Override
    protected Optional<Double> entityRoutine() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Periodic trigger for periodic entity entity " + this.getClass().getName() + " occurred.");
        }
        triggerInternal();
        return Optional.of(delay);
    }
    
    abstract protected void triggerInternal();

}
