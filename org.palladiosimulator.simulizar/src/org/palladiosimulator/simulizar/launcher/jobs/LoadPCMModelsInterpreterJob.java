package org.palladiosimulator.simulizar.launcher.jobs;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.configurations.AbstractPCMWorkflowRunConfiguration;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsJob;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Job for loading PCM Models into Blackboard. Extends LoadPCMModelsJob from SimuCom. Overwrites
 * execute method to avoid loading of middleware and event middleware models. Because fields in
 * configuration UI are used for SDM models and PSM Model. TODO: StB: Remove this ugly hack again
 * and introduce proper config fields for this. Remove this class.
 *
 */
public class LoadPCMModelsInterpreterJob extends LoadPCMModelsJob {

    /**
     * Static LOGGER of this class.
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
     * @param configuration
     *            The configuration object for this job.
     */
    public LoadPCMModelsInterpreterJob(final AbstractPCMWorkflowRunConfiguration configuration) {
        super(configuration);
        this.configuration = configuration;
    }

    /**
     * @see org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    /*
     * (non-Javadoc)
     *
     * @see org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsJob#execute(org.eclipse.core.
     * runtime. IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        final ResourceSetPartition pcmPartition = this.blackboard
                .getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
                
        final ResourceSetPartition pcmOriginalPartition = new PCMResourceSetPartition();
        this.blackboard.addPartition(LoadSimuLizarModelsIntoBlackboardJob.PCM_MODELS_ANALYZED_PARTITION_ID,
                pcmOriginalPartition);

        // Load the PCM model and its middleware completions
        LOGGER.info("Loading PCM Model Files");
        for (final String modelFile : this.configuration.getPCMModelFiles()) {
            pcmPartition.loadModel(URI.createURI(modelFile));
            pcmOriginalPartition.loadModel(URI.createURI(modelFile));
        }
        pcmPartition.resolveAllProxies();
    }

    /**
     * @see de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsJob#setBlackboard(de.uka.ipd.sdq.
     * workflow.mdsd .blackboard.MDSDBlackboard)
     */
    @Override
    public void setBlackboard(final MDSDBlackboard blackboard) {

        super.setBlackboard(blackboard);
        this.blackboard = blackboard;
    }
}
