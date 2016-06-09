package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.AbstractSimEventDelegator;

public class PeriodicSimulationEvent extends AbstractSimEventDelegator<PeriodicallyTriggeredSimulationEntity> {

    private static final Logger LOGGER = Logger.getLogger(PeriodicSimulationEvent.class);
    private final double delay;

    public PeriodicSimulationEvent(final SimuComModel model, final double delay) {
        super(model, "PeriodicSimulationEvent");
        if (delay <= 0) {
            throw new RuntimeException("Delay must be greater than 0!");
        }
        this.delay = delay;
    }

    @Override
    public void eventRoutine(final PeriodicallyTriggeredSimulationEntity who) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Periodic event " + this.getName() + " occurred at simulation time "
                    + this.getModel().getSimulationControl().getCurrentSimulationTime());
        }
        if (this.getModel().getSimulationControl().isRunning()) {
            who.trigger();
            this.schedule(who, this.delay);
        }
    }
}
