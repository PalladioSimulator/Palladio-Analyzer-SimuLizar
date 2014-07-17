package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.AbstractSimEventDelegator;

public class PeriodicSimulationEvent extends AbstractSimEventDelegator<PeriodicallyTriggeredSimulationEntity> {

    private static final Logger LOG = Logger.getLogger(PeriodicSimulationEvent.class);
    private final double delay;

    public PeriodicSimulationEvent(final SimuComModel model, final double delay) {
        super(model, "PeriodicSimulationEvent");
        this.delay = delay;
    }

    @Override
    public void eventRoutine(final PeriodicallyTriggeredSimulationEntity who) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Periodic event " + getName() + " occurred at simulation time "
                    + getModel().getSimulationControl().getCurrentSimulationTime());
        }
        if (getModel().getSimulationControl().isRunning()) {
            who.trigger();
            this.schedule(who, delay);
        }
    }
}
