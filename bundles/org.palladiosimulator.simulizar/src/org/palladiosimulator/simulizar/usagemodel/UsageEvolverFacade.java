package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.di.base.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.scaledl.usageevolution.Usage;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

@SimulationRuntimeScope
public class UsageEvolverFacade implements RuntimeStateEntityManager {

    protected Map<Usage, PeriodicallyTriggeredUsageEvolver> usageEvolvers;
    
    private final ISimulationControl simulationControl;

    private final LoopingUsageEvolverFactory loopingFactory;
    private final StretchedUsageEvolverFactory stretchedFactory;
    private final EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory;

    @Inject
    public UsageEvolverFacade(
            final ISimulationControl simulationControl,
            final LoopingUsageEvolverFactory loopingFactory,
            final StretchedUsageEvolverFactory stretchedFactory,
            final EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory) {
        this.simulationControl = simulationControl;
        this.loopingFactory = loopingFactory;
        this.stretchedFactory = stretchedFactory;
        this.usageEvolvers = new HashMap<Usage, PeriodicallyTriggeredUsageEvolver>();
        this.usageScenarioReferenceFactory = usageScenarioReferenceFactory;
    }
    
    public void startUsageEvolution(Usage usage) {
        this.usageEvolvers.put(usage, createUsageEvolver(usage));
    }
    
    public void stopUsageEvolution(Usage usage) {
        this.usageEvolvers.remove(usage).stop();
    }
    
    protected PeriodicallyTriggeredUsageEvolver createUsageEvolver(Usage usage) {
        double timePerStep = 1d;
        if (usage.getEvolutionStepWidth() != 0d) {
            timePerStep = usage.getEvolutionStepWidth();
        }
        
        double simulationTimeOffset = 0d; 
        if (simulationControl.isRunning()) {
            simulationTimeOffset = simulationControl.getCurrentSimulationTime();
        }
        
        var ref = usageScenarioReferenceFactory.createCached(usage.getScenario());
        if (usage.isRepeatingPattern()) {
            return loopingFactory.create(0d, timePerStep, simulationTimeOffset, ref);
        } else {
            return stretchedFactory.create(0.0, timePerStep, ref);
        }
    }
}
