package org.palladiosimulator.simulizar.simulationevents;

import static org.jscience.economics.money.Currency.EUR;

import java.util.List;

import javax.measure.Measure;

import org.jscience.economics.money.Money;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;
import org.palladiosimulator.simulizar.runtimestate.CostModel;
import org.palladiosimulator.simulizar.runtimestate.CostTuple;

public class AggregatedContainerCostProbe extends BasicEventProbe<PeriodicallyTriggeredCostModelEntity, Double, Money>
        implements IAbstractPeriodicContainerListener {

    public AggregatedContainerCostProbe(final PeriodicallyTriggeredCostModelEntity triggeredContainerEntity) {
        super(triggeredContainerEntity, MetricDescriptionConstants.COST_OF_RESOURCE_CONTAINERS);
    }

    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

    @Override
    public void triggerPeriodicUpdate(final CostModel costModel, final double timestamp, final double delay) {
        final List<CostTuple> costTuplesForInterval = costModel.getCostTuplesForInterval(0.0, timestamp);
        double summedCost = 0;
        for (final CostTuple costTuple : costTuplesForInterval) {
            summedCost += costTuple.getCost();
        }
        final Measure<Double, Money> costOfInterval = Measure.valueOf(summedCost, EUR);

        this.notify(costOfInterval);
    }

}
