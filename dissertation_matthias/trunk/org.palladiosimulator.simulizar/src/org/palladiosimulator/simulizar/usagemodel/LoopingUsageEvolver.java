package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

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
    
    private final double simulationTimeOffset;

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
     * @param simulationTimeOffset 
     */
    public LoopingUsageEvolver(final AbstractSimuLizarRuntimeState rtState, final double firstOccurrence, final double delay,
            final UsageScenario evolvedScenario, double simulationTimeOffset) {
        super(rtState, firstOccurrence, delay, evolvedScenario);
        if (!this.getCorrespondingUsage().isRepeatingPattern()) {
            throw new IllegalArgumentException("The corresponding usage model must contain a repeating pattern.");
        }
        this.simulationTimeOffset = simulationTimeOffset;
    }

    @Override
    protected double getNewRate(final ModelEvaluator evaluator) {
        return evaluator.getArrivalRateAtTime(floorMod(this.getCurrentTime() - this.simulationTimeOffset, this.getDLIMFinalDuration()));
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
