package org.palladiosimulator.simulizar.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition;
import de.uka.ipd.sdq.pcm.seff.GuardedBranchTransition;
import de.uka.ipd.sdq.pcm.seff.ProbabilisticBranchTransition;
import de.uka.ipd.sdq.pcm.usagemodel.BranchTransition;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;

/**
 * 
 * Util class to determine a transition based on probabilities.
 * 
 * @author Joachim Meyer
 * 
 */
public class TransitionDeterminer {
    protected static final Logger logger = Logger.getLogger(TransitionDeterminer.class.getName());

    private final SimuComConfig config;
    private final InterpreterDefaultContext context;

    /**
     * Constructor.
     * 
     * @param config
     *            the SimuCom config for the random generator.
     * @param modelInterpreter
     *            the calling model interpreter.
     */
    public TransitionDeterminer(final InterpreterDefaultContext context) {
        super();
        this.config = context.getModel().getConfiguration();
        this.context = context;
    }

    /**
     * Checks whether the boolean expression in the condition holds or not.
     * 
     * @param condition
     *            the condition (must be a boolean expression).
     * @return true if holds, otherwise false.
     */
    private boolean conditionHolds(final PCMRandomVariable condition) {
        return StackContext.evaluateStatic(condition.getSpecification(), Boolean.class, this.context.getStack()
                .currentStackFrame());
    }

    /**
     * Sums the probabilities of the list of probabilities. In a list of summed probabilities, each
     * value of an element in the list has its own probability added by the previous probability.
     * Means, if the the first probabilities in the list of probabilities is 0.3, the value of the
     * first element in the summed probability list is 0.3. If the second probabilities in the list
     * of probabilities is 0.4, the corresponding value in the summed probability list is 0.4+0.3
     * and so on.
     * 
     * @param branchProbabilities
     *            a list with branch probabilities.
     * @return the summed probability list.
     */
    protected List<Double> createSummedProbabilityList(final List<Double> branchProbabilities) {
        double currentSum = 0;
        final List<Double> summedProbabilityList = new ArrayList<Double>();
        for (final Double probability : branchProbabilities) {
            summedProbabilityList.add((currentSum = currentSum + probability));
        }
        return summedProbabilityList;
    }

    /**
     * Determines a branch transition out of a list of branch transitions, with respect to their
     * probabilities.
     * 
     * @param branchTransitions
     *            the list of branch transition.
     * @return a branch transition.
     */
    public BranchTransition determineBranchTransition(final EList<BranchTransition> branchTransitions) {
        final List<Double> summedProbabilityList = this.createSummedProbabilityList(this
                .extractProbabiltiesUsageModel(branchTransitions));

        final int transitionIndex = this.getRandomIndex(summedProbabilityList, this.config);

        final BranchTransition branchTransition = branchTransitions.get(transitionIndex);
        if (logger.isDebugEnabled()) {
            logger.debug("Chosen branch transition " + transitionIndex + " " + branchTransition);
        }
        return branchTransition;
    }

    /**
     * Determines a guarded branch transition out of a list of guarded branch transitions.
     * 
     * @param guardedBranchTransitions
     *            the list of guarded branch transition.
     * @return a guarded branch transition. This is the branch transition whose condition holds
     *         first.
     */
    private GuardedBranchTransition determineGuardedBranchTransition(
            final EList<AbstractBranchTransition> guardedBranchTransitions) {

        /*
         * There is no predefined order in evaluating the guards attached to a BranchAction. So the
         * first guard which evaluates to true will be chosen.
         * 
         * Further: As it is unclear for INNER variables in branch conditions if different or if the
         * same collection element is meant by the component developer, the current PCM version
         * forbids the use of INNER characterizations in branch conditions. Thus, this problem has
         * not to be addressed like in the collection iterator (EvaluationProxies and the same value
         * for all occurrences in one iteration).
         */
        int i = 0;
        GuardedBranchTransition branchTransition = null;
        for (final AbstractBranchTransition abstractBranchTransition : guardedBranchTransitions) {
            final GuardedBranchTransition guardedBranchTransition = (GuardedBranchTransition) abstractBranchTransition;
            final PCMRandomVariable condition = guardedBranchTransition.getBranchCondition_GuardedBranchTransition();

            if (this.conditionHolds(condition)) {
                branchTransition = (GuardedBranchTransition) guardedBranchTransitions.get(i);
                if (logger.isDebugEnabled()) {
                    logger.debug("Conditions holds for branch transition " + i + " " + branchTransition);
                }
                break;
            }
            i++;

        }
        return branchTransition;
    }

    /**
     * Determines a probabilistic branch transition out of a list of probabilistic branch
     * transitions, with respect to their probabilities.
     * 
     * @param probabilisticBranchTransitions
     *            the list of probabilistic branch transition.
     * @return a probabilistic branch transition.
     */
    public ProbabilisticBranchTransition determineProbabilisticBranchTransition(
            final EList<AbstractBranchTransition> probabilisticBranchTransitions) {
        final List<Double> summedProbabilityList = this.createSummedProbabilityList(this
                .extractProbabiltiesRDSEFF(probabilisticBranchTransitions));

        final int transitionIndex = this.getRandomIndex(summedProbabilityList, this.config);

        final ProbabilisticBranchTransition branchTransition = 
                (ProbabilisticBranchTransition) probabilisticBranchTransitions.get(transitionIndex);
        if (logger.isDebugEnabled()) {
            logger.debug("Chosen branch transition " + transitionIndex + " " + branchTransition);
        }
        return branchTransition;
    }

    /**
     * Determines a branch transition in the list of branch transitions. The list can only contains
     * either probabilistic or guarded branch transitions.
     * 
     * @param abstractBranchTransitions
     *            the list with branch transitions.
     * @return the determined AbstractBranchTransition.
     */
    public AbstractBranchTransition determineTransition(
            final EList<AbstractBranchTransition> abstractBranchTransitions) {
        /*
         * Mixed types with branch is not allowed, so the following is sufficient
         */
        AbstractBranchTransition branchTransition = null;
        if (abstractBranchTransitions.get(0) instanceof ProbabilisticBranchTransition) {
            logger.debug("Found ProbabilisticBranchTransitions");
            branchTransition = this.determineProbabilisticBranchTransition(abstractBranchTransitions);

        } else {
            logger.debug("Found GuardedBranchTransitions");
            branchTransition = this.determineGuardedBranchTransition(abstractBranchTransitions);
        }
        return branchTransition;
    }

    /**
     * Extracts the probabilities of a list of ProbabilisticBranchTransition.
     * 
     * @param probabilisticBranchTransitions
     *            the list of ProbabilisticBranchTransition.
     * @return a list only containing the probabilities.
     */
    protected List<Double> extractProbabiltiesRDSEFF(
            final EList<AbstractBranchTransition> probabilisticBranchTransitions) {
        final List<Double> probabilityList = new ArrayList<Double>();
        for (final AbstractBranchTransition probabilisticBranchTransition : probabilisticBranchTransitions) {
            probabilityList.add(((ProbabilisticBranchTransition) probabilisticBranchTransition).getBranchProbability());
        }
        return probabilityList;
    }

    /**
     * Extracts the probabilities of a list of BranchTransition.
     * 
     * @param branchTransitions
     *            the list of BranchTransition.
     * @return a list only containing the probabilities.
     */
    protected List<Double> extractProbabiltiesUsageModel(final EList<BranchTransition> branchTransitions) {
        final List<Double> probabilityList = new ArrayList<Double>();
        for (final BranchTransition branchTransition : branchTransitions) {
            probabilityList.add(branchTransition.getBranchProbability());
        }
        return probabilityList;
    }

    /**
     * Method calculates a random index for the given list of summed probabilities.
     * 
     * @param summedProbabilityList
     *            a list of summed probabilities.
     * @param simuComConfig
     *            the SimuCom config.
     * @return a random index, or -1 if summedProbabilityList is empty, or no index can be
     *         determined.
     */
    private int getRandomIndex(final List<Double> summedProbabilityList, final SimuComConfig simuComConfig) {
        if (summedProbabilityList.size() == 0) {
            return -1;
        }

        final double lastSum = summedProbabilityList.get(summedProbabilityList.size() - 1);
        final double randomNumer = simuComConfig.getRandomGenerator().random();

        // get branch
        int i = 0;
        for (final Double sum : summedProbabilityList) {
            if (lastSum * randomNumer < sum) {
                return i;
            }
            i++;
        }
        return -1;
    }

}
