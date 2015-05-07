package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.WorkParameterEvolution;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ModelEvaluator;
import de.uka.ipd.sdq.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;

public class UsageEvolver {

    private final SimuLizarRuntimeState runtimeState;
    private final UsageModel usageModel;
    private final UsageEvolution usageEvolution;
    private final Usage usage;
    private final Sequence loadEvolutionSequence;
    private ModelEvaluator loadEvaluator = null;
    private static final Logger LOGGER = Logger.getLogger(UsageEvolver.class);

    public UsageEvolver(final SimuLizarRuntimeState runtimeState) {
        super();
        this.runtimeState = runtimeState;
        this.usageModel = runtimeState.getModelAccess().getGlobalPCMModel().getUsageModel();
        this.usageEvolution = runtimeState.getModelAccess().getUsageEvolutionModel();

        // If a usage evolution model is specified, initialize the usage and loadEvaluator for later
        // use
        if (usageEvolution != null) {
            LOGGER.info("Usages: " + usageEvolution.getUsages().size());
            usage = usageEvolution.getUsages().get(0);
            if (usage != null) {
                loadEvolutionSequence = usage.getLoadEvolution();
                if (loadEvolutionSequence != null) {
                    LOGGER.info("LIMBO duration: " + loadEvolutionSequence.getFinalDuration());
                    loadEvaluator = new ModelEvaluator(loadEvolutionSequence);
                }
            } else {
                loadEvolutionSequence = null;
            }

        } else {
            usage = null;
            loadEvolutionSequence = null;
        }

        LOGGER.info("SimuTime: " + runtimeState.getModel().getConfiguration().getSimuTime());
    }

    public void start() {
        final long simuTime = runtimeState.getModel().getConfiguration().getSimuTime();
        final long dlimDuration;
        long timePerStep = 1000;

        // Build a hashmap from work parameters to their model evaluators
        Map<VariableCharacterisation, ModelEvaluator> workEvaluators = new HashMap<VariableCharacterisation, ModelEvaluator>();
        if (usage != null) {
            for (WorkParameterEvolution workParam : usage.getWorkEvolutions()) {
                VariableCharacterisation varChar = workParam.getVariableCharacterisation();
                Sequence paramSequence = workParam.getEvolution();
                if (varChar == null) {
                    LOGGER.error("Skipping evolution of unspecified work parameter");
                    continue;
                }
                if (paramSequence == null) {
                    LOGGER.error("Skipping unspecified evolution for work parameter " + varChar);
                    continue;
                }

                // Add parameter and model evaluator for the work parameter
                workEvaluators.put(varChar, new ModelEvaluator(paramSequence));
            }
        }

        // Determine the duration of the evolution from the load sequence,
        // and determine the simulation time per dlim time step
        if (loadEvolutionSequence != null) {
            LOGGER.info("LIMBO First Iteration end: " + loadEvolutionSequence.getFirstIterationEnd());
            // Divide the simulation time available on the duration in the LIMBO
            // model, but add one to include simulation time 0 and max.
            dlimDuration = (long) loadEvolutionSequence.getFinalDuration();
            timePerStep = simuTime / (dlimDuration + 1);
        } else {
            dlimDuration = 24;
        }

        // TODO: add check on duration of evolutions for work parameters.
        // For now, assume that the duration of these are the same as for the load evolution

        // Create the periodically triggered evolver that will update load and work of the usage
        // model
        new PeriodicallyTriggeredWorkloadEvolver(this.runtimeState.getModel(), 0, timePerStep, simuTime, dlimDuration,
                loadEvaluator, workEvaluators, usageModel);
    }

}
