package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.commons.designpatterns.IAbstractObservable;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

/**
 * An entity that can trigger periodic events with an attached container.
 *
 * @author Hendrik Eikerling, Sebastian Lehrig
 *
 */
public class PeriodicallyTriggeredContainerEntity extends PeriodicallyTriggeredSimulationEntity
        implements IAbstractObservable<IAbstractPeriodicContainerListener> {

    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredSimulationEntity.class);
    private final SimulatedResourceContainer container;

    private final AbstractObservable<IAbstractPeriodicContainerListener> observableDelegate;

    public PeriodicallyTriggeredContainerEntity(final SimuComModel model, final double firstOccurrence,
            final double delay, final SimulatedResourceContainer container) {
        super(model, firstOccurrence, delay);
        this.container = container;
        this.observableDelegate = new AbstractObservable<IAbstractPeriodicContainerListener>() {
        };
    }

    @Override
    protected void triggerInternal() {
        if (LOGGER.isInfoEnabled()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Periodic trigger for container ");
            stringBuilder.append(this.container.getResourceContainerID());
            stringBuilder.append(" occured at time ");
            stringBuilder.append(this.getModel().getSimulationControl().getCurrentSimulationTime());
            LOGGER.info(stringBuilder.toString());
        }

        this.observableDelegate.getEventDispatcher().triggerPeriodicUpdate(this, this.container);
    }

    @Override
    public void addObserver(final IAbstractPeriodicContainerListener observer) {
        this.observableDelegate.addObserver(observer);
    }

    @Override
    public void removeObserver(final IAbstractPeriodicContainerListener observer) {
        this.observableDelegate.removeObserver(observer);
    }
}
