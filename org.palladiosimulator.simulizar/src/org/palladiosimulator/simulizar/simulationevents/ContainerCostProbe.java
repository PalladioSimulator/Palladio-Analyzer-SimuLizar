package org.palladiosimulator.simulizar.simulationevents;

import static org.jscience.economics.money.Currency.EUR;

import javax.measure.Measure;

import org.jscience.economics.money.Money;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcmmeasuringpoint.ResourceContainerMeasuringPoint;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;

/**
 * Probe for measuring the cost of a container.
 *
 * @author Hendrik Eikerling, Sebastian Lehrig
 */
public class ContainerCostProbe extends BasicEventProbe<PeriodicallyTriggeredContainerEntity, Double, Money>
        implements IAbstractPeriodicContainerListener {

    public ContainerCostProbe(final PeriodicallyTriggeredContainerEntity triggeredContainerEntity) {
        // eventSource = triggeredContainerEntity
        super(triggeredContainerEntity, MetricDescriptionConstants.COST_PER_RESOURCE_CONTAINER);
    }

    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

    @Override
    public void triggerPeriodicUpdate(final ResourceContainerMeasuringPoint measuringPoint) {
        /*
         * FIXME hard coded container cost --> use Tagged Value form model to get variable cost
         */
        final Measure<Double, Money> costOfContainer = Measure.valueOf(10.0, EUR);
        this.notify(costOfContainer);
    }

}
