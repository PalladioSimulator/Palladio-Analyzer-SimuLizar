package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeStateAbstract;

import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Looping usage evolver. Assumes that the time unit of the DLIM sequence and simulation are
 * equivalent. Repeats the DLIM sequence once its end has been reached.
 *
 * @author stier
 *
 */
public class LoopingUsageEvolver extends PeriodicallyTriggeredUsageEvolver {

    static final Logger LOGGER = Logger.getLogger(LoopingUsageEvolver.class);

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
    public LoopingUsageEvolver(final SimuLizarRuntimeStateAbstract rtState, final double firstOccurrence,
            final double delay, final UsageScenario evolvedScenario) {
        super(rtState, firstOccurrence, delay, evolvedScenario);
        if (!this.getCorrespondingUsage().isRepeatingPattern()) {
            throw new IllegalArgumentException("The corresponding usage model must contain a repeating pattern.");
        }
    }

    @Override
    protected double getNewRate(final ModelEvaluator evaluator) {
        return evaluator.getArrivalRateAtTime(floorMod(this.getCurrentTime(), this.getDLIMFinalDuration()));
    }

    /**
     * Calculates the modulo between floored x and y.
     *
     * @param x
     *            The dividend.
     * @param y
     *            The divisor.
     * @return Floored modulo.
     */
    private static double floorMod(final double x, final double y) {
        return x - Math.floor(x / y) * y;
    }
}
