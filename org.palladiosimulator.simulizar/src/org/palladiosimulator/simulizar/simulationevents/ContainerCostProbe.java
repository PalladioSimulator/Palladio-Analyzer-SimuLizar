package org.palladiosimulator.simulizar.simulationevents;

import static org.jscience.economics.money.Currency.EUR;

import javax.measure.Measure;

import org.jscience.economics.money.Money;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;

import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

/**
 * Probe for measuring the cost of a container.
 * 
 * @author Hendrik Eikerling
 */
public class ContainerCostProbe extends BasicEventProbe<PeriodicallyTriggeredContainerEntity, Double, Money>
        implements IAbstractPeriodicContainerListener {

    public ContainerCostProbe(final PeriodicallyTriggeredContainerEntity triggeredContainerEntity) {
        super(triggeredContainerEntity, MetricDescriptionConstants.COST_PER_RESOURCE_CONTAINER); // eventSource
                                                                                                 // =
                                                                                                 // triggeredContainerEntity

    }

    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

    @Override
    public void triggerPeriodicUpdate(PeriodicallyTriggeredContainerEntity periodicEntity,
            SimulatedResourceContainer myContainer) {
        // final Measure<Double, Money> costPerContainer = Measure.valueOf(9.99,
        // EUR);
        final Measure<Double, Money> costPerContainer = Measure.valueOf(10.0, EUR);
        notify(costPerContainer);
        /*
         * FIXME Still hard coded container cost --> use Tagged Value form model to get variable
         * cost
         */
    }

}
