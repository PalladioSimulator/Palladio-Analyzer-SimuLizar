package org.palladiosimulator.simulizar.simulationevents;

import org.apache.log4j.Logger;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.commons.designpatterns.IAbstractObservable;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.runtimestate.CostModel;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * An entity that can trigger periodic events with an attached container.
 *
 * @author Hendrik Eikerling, Sebastian Lehrig
 *
 */
public class PeriodicallyTriggeredContainerEntity extends PeriodicallyTriggeredSimulationEntity
        implements IAbstractObservable<IAbstractPeriodicContainerListener> {

    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredSimulationEntity.class);

    private final ResourceContainer resourceContainer;
    private final CostModel costModel;
    private final double containerPrice;
    private final String unit;
    private final AbstractObservable<IAbstractPeriodicContainerListener> observableDelegate;

    public PeriodicallyTriggeredContainerEntity(final SimuComModel model, final CostModel costModel,
            final ResourceContainer resourceContainer) {
        super(model, 0.0, getDelay(resourceContainer));

        this.containerPrice = StereotypeAPI.getTaggedValue(resourceContainer, "amount", "Price");
        this.unit = StereotypeAPI.getTaggedValue(resourceContainer, "unit", "Price");
        this.resourceContainer = resourceContainer;
        this.costModel = costModel;
        this.observableDelegate = new AbstractObservable<IAbstractPeriodicContainerListener>() {
        };
    }

    private static double getDelay(final ResourceContainer resourceContainer) {
        if (!StereotypeAPI.isStereotypeApplied(resourceContainer, "Price")) {
            throw new RuntimeException(
                    "Periodically triggered container entities need to have a 'Price' stereotype applied!");
        }

        return StereotypeAPI.getTaggedValue(resourceContainer, "interval", "Price");
    }

    @Override
    protected void triggerInternal() {
        final Double timestamp = this.getModel().getSimulationControl().getCurrentSimulationTime();

        if (LOGGER.isInfoEnabled()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.resourceContainer.getEntityName());
            stringBuilder.append(" caused operation cost of ");
            stringBuilder.append(this.containerPrice);
            stringBuilder.append(" ");
            stringBuilder.append(this.unit);
            stringBuilder.append(" at time ");
            stringBuilder.append(timestamp.toString());
            LOGGER.info(stringBuilder.toString());
        }

        this.costModel.addCostTuple(this.resourceContainer.getId(), timestamp, new Double(this.containerPrice));
    }

    @Override
    public void addObserver(final IAbstractPeriodicContainerListener observer) {
        this.observableDelegate.addObserver(observer);
    }

    @Override
    public void removeObserver(final IAbstractPeriodicContainerListener observer) {
        this.observableDelegate.removeObserver(observer);
    }

    @Override
    public void removeEvent() {
        super.removeEvent();
    }
}
