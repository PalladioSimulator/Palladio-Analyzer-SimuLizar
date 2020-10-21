package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

public class UsageEvolverFacade {

    protected Map<Usage, PeriodicallyTriggeredUsageEvolver> usageEvolvers;
    
    /** Runtime state of the simulation. Required to start evolution(s). */
    private final PCMPartitionManager partitionManager;
    private final ISimulationControl simulationControl;
    
    //FIXME: This dependency should be factored out
    private SimuLizarRuntimeState runtimeState;

    @Inject
    public UsageEvolverFacade(final PCMPartitionManager partitionManager, 
            final ISimulationControl simulationControl) {
        this.partitionManager = partitionManager;
        this.simulationControl = simulationControl;
        this.usageEvolvers = new HashMap<Usage, PeriodicallyTriggeredUsageEvolver>();
    }

    public void start(SimuLizarRuntimeState runtimeState) {
        this.runtimeState = runtimeState;
        // TODO: add check on duration of evolutions for work parameters.
        // For now, assume that the duration of these are the same as for the load evolution
    	UsageEvolution ueModel = partitionManager.findModel(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
        ueModel.getUsages().forEach(usage -> startUsageEvolution(usage));
    }
    
    public void startUsageEvolution(Usage usage) {
        if (runtimeState == null) {
            throw new IllegalStateException("The UsageEvolverFacade has not been started yet.");
        }
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
        
        if (usage.isRepeatingPattern()) {
            return new LoopingUsageEvolver(runtimeState, 0d, timePerStep, usage.getScenario(), simulationTimeOffset);
        } else {
            // TODO remove this line once 'legacy' support is no longer needed.
            timePerStep = runtimeState.getModel().getConfiguration().getSimuTime()
                    / (usage.getLoadEvolution().getFinalDuration() + 1);
            return new StretchedUsageEvolver(runtimeState, 0d, timePerStep, usage.getScenario());
        }
    }
}
