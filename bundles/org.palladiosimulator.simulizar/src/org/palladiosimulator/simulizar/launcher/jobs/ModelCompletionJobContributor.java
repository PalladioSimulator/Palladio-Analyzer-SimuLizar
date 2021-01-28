package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.simulizar.di.extension.Extension;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public interface ModelCompletionJobContributor extends Extension {
    public interface Facade {
        /**
         * Contribute a blackboard interacting job, which will be executed after all models were
         * loaded into the blackboard.
         * 
         * If the contributed job has to be executed in a particular order with respect to an other
         * job, have it implement {@link Comparator<IJob>}. Jobs are executed in the order small to
         * large. Therefore, to signal job o1 should be executed before job o2, compare(o1, o2)
         * needs to return a number < 0. Return 0 by default. All orderings are relative, two jobs
         * imposing a contradictory ordering, will raise an {@link IllegalStateException}.
         * 
         * 
         * @param contribution
         *            the blackboard interacting job
         */
        void contribute(IBlackboardInteractingJob<MDSDBlackboard> contribution);
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
    public void loadModel(ModelCompletionJobContributor.Facade delegate);

}
