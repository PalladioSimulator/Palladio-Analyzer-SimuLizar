package org.palladiosimulator.simulizar.launcher.jobs;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.configurations.AbstractPCMWorkflowRunConfiguration;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsJob;

/**
 * Job for loading PCM Models into Blackboard. Extends LoadPCMModelsJob from SimuCom. Overwrites
 * execute method to avoid loading of middleware and event middleware models. Because fields in
 * configuration UI are used for SDM models and PSM Model.
 * TODO: StB: Remove this ugly hack again and introduce proper config fields for this. Remove this class.
 * 
 */
public class LoadPCMModelsInterpreterJob extends LoadPCMModelsJob {

    /**
     * Static logger of this class.
     */
    private static final Logger LOGGER = Logger.getLogger(LoadPCMModelsJob.class);

    /**
     * SDQ Workflow engine blackboard which should contain the PCM models to be loaded. 
     */
    private MDSDBlackboard blackboard;

    /**
     * PCM Workflow configuration. 
     */
    private final AbstractPCMWorkflowRunConfiguration configuration;

    /**
     * @param configuration The configuration object for this job.
     */
    public LoadPCMModelsInterpreterJob(final AbstractPCMWorkflowRunConfiguration configuration) {
        super(configuration);
        this.configuration = configuration;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        final ResourceSetPartition pcmPartition = this.blackboard
                .getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);

        // Load the PCM model and its middleware completions
        LOGGER.info("Loading PCM Model Files");
        for (final String modelFile : this.configuration.getPCMModelFiles()) {
            pcmPartition.loadModel(modelFile);
        }
        pcmPartition.resolveAllProxies();
        // if (!configuration.getMiddlewareFile().equals(""))
        // {
        // middlewarePartition.loadModel(configuration.getMiddlewareFile());
        // middlewarePartition.resolveAllProxies();
        // }
        //
        // if (!configuration.getEventMiddlewareFile().equals(
        // AbstractPCMWorkflowRunConfiguration.PCM_DEFAULT_EVENT_MIDDLEWARE_FILE_URI))
        // {
        // // load the event middleware repository
        // eventMiddlewarePartition.loadModel(configuration.getEventMiddlewareFile());
        // eventMiddlewarePartition.resolveAllProxies();
        // }
    }

    /**
     * @see de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
     */
    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsJob#setBlackboard(de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard)
     */
    @Override
    public void setBlackboard(final MDSDBlackboard blackboard) {

        super.setBlackboard(blackboard);
        this.blackboard = blackboard;
    }
}
