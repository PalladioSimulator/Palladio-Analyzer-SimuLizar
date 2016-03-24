package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.scaledl.usageevolution.Usage;

public class UsageEvolverFacade {
	/** Logger of this class. */
    private static final Logger LOGGER = Logger.getLogger(UsageEvolverFacade.class);

    protected Map<Usage, PeriodicallyTriggeredUsageEvolver> usageEvolvers;
    
    /** Runtime state of the simulation. Required to start evolution(s). */
    private final SimuLizarRuntimeState runtimeState;

    public UsageEvolverFacade(final SimuLizarRuntimeState runtimeState) {
        this.runtimeState = runtimeState;
        this.usageEvolvers = new HashMap<Usage, PeriodicallyTriggeredUsageEvolver>();
    }

    public void start() {
        // TODO: add check on duration of evolutions for work parameters.
        // For now, assume that the duration of these are the same as for the load evolution
        
        this.runtimeState.getModelAccess().getUsageEvolutionModel().getUsages().forEach(this::startUsageEvolution);
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
        if (this.runtimeState.getModel().getSimulationControl().isRunning()) {
            simulationTimeOffset = this.runtimeState.getModel().getSimulationControl().getCurrentSimulationTime();
        }
        
        if (usage.isRepeatingPattern()) {
            return new LoopingUsageEvolver(this.runtimeState, 0d, timePerStep, usage.getScenario(), simulationTimeOffset);
        } else {
            // TODO remove this line once 'legacy' support is no longer needed.
            timePerStep = this.runtimeState.getModel().getConfiguration().getSimuTime()
                    / (usage.getLoadEvolution().getFinalDuration() + 1);
            return new StretchedUsageEvolver(this.runtimeState, 0d, timePerStep, usage.getScenario());
        }
    }
}
