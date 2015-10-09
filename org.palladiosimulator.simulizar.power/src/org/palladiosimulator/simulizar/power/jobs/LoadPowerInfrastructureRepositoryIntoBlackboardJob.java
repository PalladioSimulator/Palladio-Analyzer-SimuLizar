package org.palladiosimulator.simulizar.power.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.power.runconfig.LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.power.runconfig.LoadPowerInfrastructureRepositoryIntoBlackboardJobConfigBuilder;

import de.fzi.power.infrastructure.PowerInfrastructureRepository;
import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * This class is an {@link AbstractWorkflowExtensionJob} implementation to load a 
 * {@link PowerInfrastructureRepository} model (which is specified by an URI) into an {@link MDSDBlackboard} instance.
 * @see LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig
 * @see LoadPowerInfrastructureRepositoryIntoBlackboardJobConfigBuilder
 * @author Florian Rosenthal
 *
 */
public final class LoadPowerInfrastructureRepositoryIntoBlackboardJob extends AbstractWorkflowExtensionJob<MDSDBlackboard> {

    /**
     * Constant which holds the id of the {@link PowerInfrastructureRepositoryResourceSetPartition} within the blackboard.
     */
    public static final String POWER_INFRASTRUCTURE_REPOSITORY_MODEL_PARTITION_ID = "de.fzi.power";
    
    private String path;
    
    /**
    * Initializes a new instance of the {@link LoadPowerInfrastructureRepositoryIntoBlackboardJob} class.
    */
    public LoadPowerInfrastructureRepositoryIntoBlackboardJob() {
        
    }
    
    /**
     * {@inheritDoc}<br>
     * In this case, the passed configuration must be an {@link LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig}
     * which contains the path of the {@link PowerInfrastructureRepository} that will be loaded by this job.
     * @throws IllegalArgumentException In case the given configuration is not a 
     * {@code LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig}.
     */
    @Override
    public void setJobConfiguration(AbstractExtensionJobConfiguration configuration) throws IllegalArgumentException {
        if (!(configuration instanceof LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig)) {
            throw new IllegalArgumentException("Given configuration must be of type "
                    + "'LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig'.");
        }
        this.path = ((LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig) configuration).getInfrastructureRepositoryPath();
        super.setJobConfiguration(configuration);
    }
    
    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException In case the given blackboard is {@code null}.
     */
    @Override
    public void setBlackboard(MDSDBlackboard blackboard) throws IllegalArgumentException {
        if (blackboard == null) {
            throw new IllegalArgumentException("Blackboard to set must not be null.");
        }
        super.setBlackboard(blackboard);
    }

    @Override
    public MDSDBlackboard getBlackboard() {
        return this.myBlackboard;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }

    /**
     * {@inheritDoc}<br>
     * In this implementation, the infrastructure model is loaded into the blackboard.
     * Therefore, a {@link PowerInfrastructureRepositoryResourceSetPartition} is created and added to the blackboard.
     * @see LoadPowerInfrastructureRepositoryIntoBlackboardJob#POWER_INFRASTRUCTURE_REPOSITORY_MODEL_PARTITION_ID
     */
    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        if (this.myBlackboard == null) {
            throw new IllegalStateException("Blackboard has been not set beforehand!");
        }
        ResourceSetPartition partition = this.myBlackboard.getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
        if (!this.path.equals("")) {
            partition.loadModel(URI.createURI(!this.path.startsWith("platform:") ? "file:///" + this.path : this.path));
        }
        this.myBlackboard.addPartition(POWER_INFRASTRUCTURE_REPOSITORY_MODEL_PARTITION_ID, partition);
        // now resolve all cross references from current resource to PCM
        partition.resolveAllProxies();
    }

    @Override
    public String getName() {
        return "Perform Power Infrastructure Repository Load";
    }

}
