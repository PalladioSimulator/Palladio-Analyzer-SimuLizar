package org.palladiosimulator.simulizar.usagemodel;

import java.util.Optional;

import jakarta.inject.Named;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationTimeProvider;
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
    @AssistedInject
    public StretchedUsageEvolver(@Assisted final double firstOccurrence, @Assisted final double delay,
            @Assisted final EntityReference<UsageScenario> evolvedScenario,
            @Named("maxSimTime") Optional<Double> maxSimTime, @Global PCMResourceSetPartition pcmPartition,
            ISimEventFactory simEventFactory, ISimulationTimeProvider timeProvider) {
        super(firstOccurrence, delay, evolvedScenario, pcmPartition, simEventFactory, timeProvider);
        if (maxSimTime.isEmpty()) {
            throw new IllegalArgumentException("Initializing a streched usage evolver requires a specified maximum simulation time");
        }
        this.timeFactor = maxSimTime.get() / this.getDLIMFinalDuration();
        setDelay(delay / timeFactor);
    }

    @Override
    protected double getNewRate(final ModelEvaluator loadEvaluator) {
        final double evaluationTime = this.getCurrentTime() / this.timeFactor;
        if (evaluationTime >= this.getDLIMFinalDuration() - DELTA) {
            // The LIMBO evaluator do not define a value at the total duration
            // time, so get a value close to end of the simulation by requesting
            // the value one millionth of a time unit before the total duration
            return loadEvaluator.getArrivalRateAtTime(evaluationTime - DELTA);
        }
        return loadEvaluator.getArrivalRateAtTime(this.getCurrentTime() / this.timeFactor);
    }

}
