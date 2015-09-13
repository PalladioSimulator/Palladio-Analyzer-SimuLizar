package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.commons.designpatterns.IAbstractObservable;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

/**
 * An entity that can trigger periodic events with an attached container.
 * 
 * @author Hendrik Eikerling
 *
 */
public class PeriodicallyTriggeredContainerEntity extends PeriodicallyTriggeredSimulationEntity
        implements IAbstractObservable<IAbstractPeriodicContainerListener> {

    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredSimulationEntity.class);
    private static SimulatedResourceContainer myContainer;

    private final AbstractObservable<IAbstractPeriodicContainerListener> observableDelegate;

    public PeriodicallyTriggeredContainerEntity(final SimuComModel model, final double firstOccurrence,
            final double delay, final SimulatedResourceContainer container) {
        super(model, firstOccurrence, delay);
        myContainer = container;
        this.observableDelegate = new AbstractObservable<IAbstractPeriodicContainerListener>() {
        };
    }

    @Override
    protected void triggerInternal() {
        if (LOGGER.isInfoEnabled()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Periodic trigger for container ");
            stringBuilder.append(myContainer.getResourceContainerID());
            stringBuilder.append(" occured at time ");
            stringBuilder.append(getModel().getSimulationControl().getCurrentSimulationTime());
            LOGGER.info(stringBuilder.toString());
        }

        this.observableDelegate.getEventDispatcher().triggerPeriodicUpdate(this, myContainer);
    }

    @Override
    public void addObserver(final IAbstractPeriodicContainerListener observer) {
        observableDelegate.addObserver(observer);
    }

    @Override
    public void removeObserver(final IAbstractPeriodicContainerListener observer) {
        observableDelegate.removeObserver(observer);
    }
}
