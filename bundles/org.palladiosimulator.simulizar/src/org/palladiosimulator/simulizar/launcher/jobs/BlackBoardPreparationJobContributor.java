package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.simulizar.di.extension.Extension;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public interface BlackBoardPreparationJobContributor extends Extension {
    public interface Facade {
        /**
         * Contribute a blackboard interacting job with default priority
         * 
         * @param contribution
         *            the blackboard interacting job
         */
        void contribute(IBlackboardInteractingJob<MDSDBlackboard> contribution);

        /**
         * Contribute a blackboard interacting job with a given priority. Higher number means
         * earlier run time.
         * 
         * @param contribution
         *            the blackboard interacting job
         * @param priority
         *            the priority
         */
        void contribute(IBlackboardInteractingJob<MDSDBlackboard> contribution, int priority);

    }

    /**
     * The method is called after all models have been loaded to give extensions a chance of
     * modifying the global PCM model before running the interpreters, e. g. to conduct model
     * completions.
     * 
     * Please provide the job to the given delegate.
     * 
     * @param delegate
     *            the delegate to appen black board preparation jobs.
     * 
     */
    public void loadModel(BlackBoardPreparationJobContributor.Facade delegate);

}
