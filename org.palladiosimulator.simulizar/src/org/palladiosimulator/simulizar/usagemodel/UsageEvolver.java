package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;

import tools.descartes.dlim.Sequence;

public class UsageEvolver {

    private final SimuLizarRuntimeState runtimeState;
    private final Sequence loadEvolutionSequence;
    private static final Logger LOGGER = Logger.getLogger(UsageEvolver.class);

    public UsageEvolver(final SimuLizarRuntimeState runtimeState) {
        super();
        this.runtimeState = runtimeState;
        UsageEvolution usageEvolution = runtimeState.getModelAccess().getUsageEvolutionModel();

        // If a usage evolution model is specified, initialize the usage and loadEvaluator for later
        // use
        if (usageEvolution != null) {
            LOGGER.info("Usages: " + usageEvolution.getUsages().size());
            Usage usage = usageEvolution.getUsages().get(0);
            if (usage != null) {
                loadEvolutionSequence = usage.getLoadEvolution();
                if (loadEvolutionSequence != null) {
                    LOGGER.info("LIMBO duration: " + loadEvolutionSequence.getFinalDuration());
                }
            } else {
                loadEvolutionSequence = null;
            }

        } else {
            loadEvolutionSequence = null;
        }

        LOGGER.info("SimuTime: " + runtimeState.getModel().getConfiguration().getSimuTime());
    }

    public void start() {
        // TODO: add check on duration of evolutions for work parameters.
        // For now, assume that the duration of these are the same as for the load evolution

        // Create the periodically triggered evolver that will update load and work of the usage
        // model
        for (Usage usage : this.runtimeState.getModelAccess().getUsageEvolutionModel().getUsages()) {
            double timePerStep = 1d;
            if (usage.getEvolutionStepWidth() != 0d) {
                timePerStep = usage.getEvolutionStepWidth();
            }
            if (usage.isRepeatingPattern()) {
                new LoopingUsageEvolver(runtimeState, 0d, timePerStep, usage.getScenario());
            } else {
                // TODO remove this line once 'legacy' support is no longer needed.
                timePerStep = this.runtimeState.getModel().getConfiguration().getSimuTime()
                        / (usage.getLoadEvolution().getFinalDuration() + 1);
                new StretchedUsageEvolver(runtimeState, 0d, timePerStep, usage.getScenario());
            }
        }

    }
}
