package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.commons.designpatterns.IAbstractObservable;
import org.palladiosimulator.simulizar.runtimestate.CostModel;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class PeriodicallyTriggeredCostModelEntity extends PeriodicallyTriggeredSimulationEntity
        implements IAbstractObservable<IAbstractPeriodicContainerListener> {

    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredSimulationEntity.class);
    private final CostModel costModel;
    private final double delay;

    private final AbstractObservable<IAbstractPeriodicContainerListener> observableDelegate;

    public PeriodicallyTriggeredCostModelEntity(final SimuComModel model, final CostModel costModel,
            final double firstOccurrence, final double delay) {
        super(model, firstOccurrence, delay);
        this.costModel = costModel;
        this.delay = delay;
        this.observableDelegate = new AbstractObservable<IAbstractPeriodicContainerListener>() {
        };
    }

    @Override
    protected void triggerInternal() {
        final Double timestamp = this.getModel().getSimulationControl().getCurrentSimulationTime();

        if (LOGGER.isInfoEnabled()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Periodic trigger for the cost model occured at time");
            stringBuilder.append(timestamp.toString());
            LOGGER.info(stringBuilder.toString());
        }
        this.observableDelegate.getEventDispatcher().triggerPeriodicUpdate(this.costModel, timestamp, this.delay);
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
