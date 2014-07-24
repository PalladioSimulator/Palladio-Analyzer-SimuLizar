package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Composite job loading pcm and pms model, as well as all sdm models and starting pcm
 * interpretation.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMInterpreterRootCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> implements
        IBlackboardInteractingJob<MDSDBlackboard> {

    /**
     * Constructor
     * 
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    public PCMInterpreterRootCompositeJob(final SimuLizarWorkflowConfiguration configuration) {
        super(false);

        this.addJob(new LoadPCMModelsIntoBlackboardInterpreterJob(configuration));

        this.addJob(new LoadPMSModelIntoBlackboardJob(configuration));

        this.addJob(new LoadSDMModelsIntoBlackboardJob(configuration));

        this.addJob(new PCMStartInterpretationJob(configuration));

    }

}
