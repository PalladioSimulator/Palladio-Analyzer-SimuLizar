package org.palladiosimulator.simulizar.launcher.jobs;

import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.configurations.AbstractPCMWorkflowRunConfiguration;
import de.uka.ipd.sdq.workflow.pcm.jobs.PreparePCMBlackboardPartionJob;

/**
 * Composite Job for preparing Blackboard and loading PCM Models into it.
 * 
 * @author Joachim Meyer
 * 
 */
public class LoadPCMModelsIntoBlackboardInterpreterJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    /**
     * @param config
     */
    public LoadPCMModelsIntoBlackboardInterpreterJob(final AbstractPCMWorkflowRunConfiguration config) {
        super(false);
        
        this.add(new PreparePCMBlackboardPartionJob());
        this.add(new LoadPCMModelsInterpreterJob(config));
    }

}
