package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.scaledl.usageevolution.Usage;

public class UsageEvolver {
	/** Logger of this class. */
    private static final Logger LOGGER = Logger.getLogger(UsageEvolver.class);

    /** Runtime state of the simulation. Required to start evolution(s). */
	private final SimuLizarRuntimeState runtimeState;

    public UsageEvolver(final SimuLizarRuntimeState runtimeState) {
        super();
        this.runtimeState = runtimeState;
    }

    public void start() {
        // TODO: add check on duration of evolutions for work parameters.
        // For now, assume that the duration of these are the same as for the load evolution

        // Create the periodically triggered evolver that will update load and work of the usage
        // model
        for (final Usage usage : this.runtimeState.getModelAccess().getUsageEvolutionModel().getUsages()) {
            double timePerStep = 1d;
            if (usage.getEvolutionStepWidth() != 0d) {
                timePerStep = usage.getEvolutionStepWidth();
            }
            if (usage.isRepeatingPattern()) {
                new LoopingUsageEvolver(this.runtimeState, 0d, timePerStep, usage.getScenario());
            } else {
                // TODO remove this line once 'legacy' support is no longer needed.
                timePerStep = this.runtimeState.getModel().getConfiguration().getSimuTime()
                        / (usage.getLoadEvolution().getFinalDuration() + 1);
                new StretchedUsageEvolver(this.runtimeState, 0d, timePerStep, usage.getScenario());
            }
        }

    }
}
