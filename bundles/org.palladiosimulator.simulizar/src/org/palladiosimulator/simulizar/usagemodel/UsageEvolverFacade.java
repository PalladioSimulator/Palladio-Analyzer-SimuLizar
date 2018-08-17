package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

public class UsageEvolverFacade {

    protected Map<Usage, PeriodicallyTriggeredUsageEvolver> usageEvolvers;
    
    /** Runtime state of the simulation. Required to start evolution(s). */
    private final AbstractSimuLizarRuntimeState runtimeState;

    public UsageEvolverFacade(final AbstractSimuLizarRuntimeState runtimeState) {
        this.runtimeState = runtimeState;
        this.usageEvolvers = new HashMap<Usage, PeriodicallyTriggeredUsageEvolver>();
    }

    public void start() {
        // TODO: add check on duration of evolutions for work parameters.
        // For now, assume that the duration of these are the same as for the load evolution
        
    	PCMPartitionManager manager = this.runtimeState.getPCMPartitionManager();
    	UsageEvolution ueModel = manager.findModel(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
        ueModel.getUsages().forEach(this::startUsageEvolution);
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
