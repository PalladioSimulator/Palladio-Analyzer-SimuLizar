package org.palladiosimulator.simulizar.usagemodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;
import org.scaledl.usageevolution.WorkParameterEvolution;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Usage evolver which updates the workload according to a Usage Evolution model.
 *
 * @author Erlend Stav, stier
 *
 */
public abstract class PeriodicallyTriggeredUsageEvolver extends PeriodicallyTriggeredSimulationEntity {

    static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredUsageEvolver.class);

    protected AbstractSimuLizarRuntimeState rtState;
    protected final String evolvedScenarioId;
    protected final double deltaTime;

    private final Map<Usage, ModelEvaluator> cachedLoadEvaluators = new HashMap<Usage, ModelEvaluator>();

    private final Map<Usage, Map<VariableCharacterisation, ModelEvaluator>> cachedWorkEvaluators = new HashMap<Usage, Map<VariableCharacterisation, ModelEvaluator>>();

    /**
     * Constructs the looping usage evolver.
     *
     * @param rtState
     *            SimuLizar runtime state.
     * @param firstOccurrence
     *            First point in time at which the evolver should evolve the load.
     * @param delay
     *            The interval in which the evolver should evolve the load.
     * @param evolvedScenario
     *            The evolved scenario.
     */
    public PeriodicallyTriggeredUsageEvolver(final AbstractSimuLizarRuntimeState rtState, final double firstOccurrence,
            final double delay, final UsageScenario evolvedScenario) {
        super(rtState.getModel(), firstOccurrence, delay);
        this.deltaTime = delay;
        this.evolvedScenarioId = evolvedScenario.getId();
        this.rtState = rtState;
    }
    
    /**
     * Stops the usage evolver from being scheduled in the simulation.
     */
    public void stop() {
        this.removeEvent();
    }

    /**
     * Get the load evaluator for <code>this</code>.
     *
     * @return The load evaluator.
     */
    protected ModelEvaluator getLoadEvaluator() {
        final Usage usage = this.getCorrespondingUsage();
        ModelEvaluator evaluator = this.cachedLoadEvaluators.get(usage);
        if (evaluator == null) {
            final Sequence loadEvolutionSequence = usage.getLoadEvolution();
            if (loadEvolutionSequence != null) {
                evaluator = new ModelEvaluator(loadEvolutionSequence);
                this.cachedLoadEvaluators.put(usage, evaluator);
            }
        }
        return evaluator;
    }

    /**
     * Get the Usage updated by <code>this</code>.
     *
     * @return the Usage updated by <code>this</code>.
     */
    protected Usage getCorrespondingUsage() {
    	PCMPartitionManager manager = this.rtState.getPCMPartitionManager();
        final UsageEvolution usageEvolution = manager.findModel(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
        for (final Usage usage : usageEvolution.getUsages()) {
            if (usage.getScenario().getId().equals(this.evolvedScenarioId)) {
                return usage;
            }
        }
        return null;
    }

    /**
     * Gets all the Work Evaluators for the Work Parameter evolutions of <code>this</code>.
     *
     * @return The Work Evaluators for the Work Parameter evolutions of <code>this</code>.
     */
    protected Map<VariableCharacterisation, ModelEvaluator> getWorkEvaluators() {
        // TODO: consider optimizing this by changing workEvaluators to be an instance variable that
        // is lazily initialized by this call. Must first determine whether this has side
        // effects on the simulation.

        // Build the hashmap from work parameters of the first usage in the usage evolution
        // and to their corresponding model evaluators
        final Usage usage = this.getCorrespondingUsage();
        Map<VariableCharacterisation, ModelEvaluator> workEvaluators = this.cachedWorkEvaluators.get(usage);
        if (workEvaluators == null) {
            if (usage != null && usage.getWorkEvolutions() != null && usage.getWorkEvolutions().size() > 0) {
                workEvaluators = new HashMap<VariableCharacterisation, ModelEvaluator>();
                for (final WorkParameterEvolution workParam : usage.getWorkEvolutions()) {
                    final VariableCharacterisation varChar = workParam.getVariableCharacterisation();
                    final Sequence paramSequence = workParam.getEvolution();
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
                this.cachedWorkEvaluators.put(usage, workEvaluators);
            } else {
                workEvaluators = Collections.emptyMap();
            }
        }

        return workEvaluators;
    }

    @Override
    protected void triggerInternal() {
        // First, evolve load if load evaluator exists
        final ModelEvaluator loadEvaluator = this.getLoadEvaluator();

        if (loadEvaluator != null) {
            this.evolveLoad(loadEvaluator);
        }

        // Then, iterate through work parameters to evolve
        final Map<VariableCharacterisation, ModelEvaluator> workEvaluators = this.getWorkEvaluators();
        for (final VariableCharacterisation workParam : workEvaluators.keySet()) {
            final VariableCharacterisation globalWorkParam = getGlobalWorkParameter(workParam);
            this.evolveWork(globalWorkParam, workEvaluators.get(workParam));
        }
    }

    private VariableCharacterisation getGlobalWorkParameter(final VariableCharacterisation workParam) {
        final VariableCharacterisation globalWorkParam = (VariableCharacterisation) this.rtState.getPCMPartitionManager()
                .getGlobalPCMModel().getResourceSet().getEObject(EcoreUtil.getURI(workParam), false);
        return globalWorkParam;
    }

    /**
     * The length of <code>this</code>' DLIM sequence.
     *
     * @return The length of <code>this</code>' DLIM sequence.
     */
    protected double getDLIMFinalDuration() {
        return this.getCorrespondingUsage().getLoadEvolution().getFinalDuration();
    }

    /**
     * Gets the current simulation time.
     *
     * @return The current time.
     */
    protected double getCurrentTime() {
        return this.getModel().getSimulationControl().getCurrentSimulationTime();
    }

    /**
     * Evolve the load.
     *
     * @param loadEvaluator
     *            DLIM evaluator used to fetch the load at the current point in time.
     */
    protected void evolveLoad(final ModelEvaluator loadEvaluator) {

        double newRate = this.getNewRate(loadEvaluator);
        final Workload wl = this.getCorrespondingUsage().getScenario().getWorkload_UsageScenario();
        if (wl != null) {
            if (wl instanceof OpenWorkload) {
                final PCMRandomVariable openwl = ((OpenWorkload) wl).getInterArrivalTime_OpenWorkload();
                if (newRate != 0) {
                    // Using inverse value to convert from arrival rate to inter arrival
                    // time
                    newRate = 1 / newRate;
                } else {
                    // If the arrival rate is 0, the inter arrival time should be
                    // infinite. We use the max integer value as an approximation
                    newRate = Integer.MAX_VALUE;
                }

                final String newRateStr = Double.toString(newRate);
                if (newRateStr.equals(openwl.getSpecification())) {
                    LOGGER.debug("Inter arrival time is still: " + newRateStr);
                } else {
                    LOGGER.debug(
                            "Changing inter arrival time from: " + openwl.getSpecification() + " to :" + newRateStr);
                    openwl.setSpecification(newRateStr);
                }
            } else if (wl instanceof ClosedWorkload) {
                final int newRateInt = (int) Math.round(newRate);
                final int oldRate = ((ClosedWorkload) wl).getPopulation();
                if (newRateInt == oldRate) {
                    LOGGER.debug("Closed workload population is still: " + newRateInt);
                } else {
                    LOGGER.debug("Changing closed workload population from: " + oldRate + " to " + newRateInt);
                    ((ClosedWorkload) wl).setPopulation(newRateInt);
                }
            }
        }
    }

    /**
     * Get the new rate of the Usage or workload parameter characterization.
     *
     * @param loadEvaluator
     *            The DLIM evaluator used for the evaluation.
     * @return The new Usage or workload parameter characterization.
     */
    protected abstract double getNewRate(ModelEvaluator loadEvaluator);

    /**
     * Evolves a workload parameter.
     *
     * @param workParameter
     *            The evolved parameter.
     * @param evaluator
     *            The evaluator used for evaluating DLIM sequence.
     */
    protected void evolveWork(final VariableCharacterisation workParameter, final ModelEvaluator evaluator) {
        if (evaluator == null) {
            return;
        }

        // Support only long values for now
        final long newRate = Math.round(this.getNewRate(evaluator));
        final String newRateStr = Long.toString(newRate);

        LOGGER.debug("Changing work from "
                + workParameter.getSpecification_VariableCharacterisation().getSpecification() + " to " + newRateStr);

        workParameter.getSpecification_VariableCharacterisation().setSpecification(newRateStr);
    }

}