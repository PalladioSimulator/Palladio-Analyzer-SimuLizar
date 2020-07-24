package org.palladiosimulator.simulizar.usagemodel;

import javax.inject.Inject;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.assistedinject.Assisted;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
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

    // Value subtracted for evaluation of last interval
    private final static double DELTA = 0.000001;

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
    @Inject
    public StretchedUsageEvolver(@Assisted("firstOccurrence") final double firstOccurrence,
    		@Assisted("delay") final double delay, @Assisted final UsageScenario evolvedScenario,
    		final PCMPartitionManager pcmManager, final SimuComModel model) {
        super(pcmManager, model, firstOccurrence, delay, evolvedScenario);
        this.timeFactor = this.getModel().getConfiguration().getSimuTime() / this.getDLIMFinalDuration();
    }

    @Override
    protected double getNewRate(final ModelEvaluator loadEvaluator) {
        final double evaluationTime = this.getCurrentTime() / this.timeFactor;
        if (evaluationTime == this.getDLIMFinalDuration()) {
            // The LIMBO evaluator do not define a value at the total duration
            // time, so get a value close to end of the simulation by requesting
            // the value one millionth of a time unit before the total duration
            return loadEvaluator.getArrivalRateAtTime(evaluationTime - DELTA);
        }
        return loadEvaluator.getArrivalRateAtTime(this.getCurrentTime() / this.timeFactor);
    }

}
