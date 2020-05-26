package org.palladiosimulator.simulizar.utils;

import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.usagemodel.BranchTransition;
/**
 * Interface to determine a transition
 * @author Jens Manig
 *
 */
public interface TransitionDeterminer {

	/**
	 * Determines a branch transition out of a list of branch transitions, with respect to their
	 * probabilities.
	 *
	 * @param branchTransitions
	 *            the list of branch transition.
	 * @return a branch transition.
	 */
	public BranchTransition determineBranchTransition(EList<BranchTransition> branchTransitions);


    /**
     * Determines a branch transition in the list of branch transitions. The list can only contains
     * either probabilistic or guarded branch transitions.
     *
     * @param abstractBranchTransitions
     *            the list with branch transitions.
     * @return the determined AbstractBranchTransition.
     */
    public AbstractBranchTransition determineTransition(final EList<AbstractBranchTransition> abstractBranchTransitions);
}