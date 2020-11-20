package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.pcm.transformations.ApplyConnectorCompletionsJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * This job applies the model completion for middleware-based linking resource simulation.
 * 
 * We omit everything related to storing model files.
 * 
 * @author Sebastian Krach
 *
 */
public class SimuLizarApplyConnectorCompletionsJob extends ApplyConnectorCompletionsJob {

    public SimuLizarApplyConnectorCompletionsJob(SimuLizarWorkflowConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected ResourceSetPartition getPartitionForCompletedMiddleware() {
        return blackboard.getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
    }
    @Override
    protected URI getCompletionResourceURI(String resourceName) throws JobFailedException {
        return URI.createURI(resourceName);
    }
    
    @Override
    protected void postProcessCompletedMiddlewarePartition(ResourceSetPartition partition) {
        // We do not store the completed model in SimuLizar
        // therefore, nothing to do here
    }

    
}
