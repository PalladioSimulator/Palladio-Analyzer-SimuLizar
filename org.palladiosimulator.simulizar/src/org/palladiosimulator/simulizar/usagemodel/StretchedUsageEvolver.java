package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Encapsulates a Usage evolver that stretches the DLIM curve to the simulation time.
 * 
 * @author stier, Erlend Stav
 *
 */
public class StretchedUsageEvolver extends PeriodicallyTriggeredUsageEvolver {

    // Stretching factor.
    private final double timeFactor;

    /**
     * Creates the stretching usage evolver.
     * 
     * @param rtState
     *            The SimuLizar runtime state.
     * @param firstOccurrence
     *            The first point in time at which the usage evolution should be executed.
     * @param delay
     *            The repeating interval in which usage evolution should be executed.
     * @param evolvedScenario
     *            The scenario evolved by <code>this</code>.
     */
    public StretchedUsageEvolver(SimuLizarRuntimeState rtState, double firstOccurrence, double delay,
            UsageScenario evolvedScenario) {
        super(rtState, firstOccurrence, delay, evolvedScenario);
        timeFactor = this.getModel().getConfiguration().getSimuTime() / this.getDLIMFinalDuration();
    }

    @Override
    protected double getNewRate(ModelEvaluator loadEvaluator) {
        return loadEvaluator.getArrivalRateAtTime(this.getCurrentTime() / timeFactor);
    }

}
