package org.palladiosimulator.simulizar.power.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.power.partitions.PowerInfrastructureRepositoryResourceSetPartition;
import org.palladiosimulator.simulizar.power.runconfig.LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public final class LoadPowerInfrastructureRepositoryIntoBlackboardJob extends AbstractWorkflowExtensionJob<MDSDBlackboard> {

    public static final String POWER_INFRASTRUCTURE_REPOSITORY_MODEL_PARTITION_ID = "de.fzi.power";
    
    private String path;
    
    public LoadPowerInfrastructureRepositoryIntoBlackboardJob() {
        super();
        System.out.println("Instantiate Load Job for Infrastructure Model!!!");
    }
    
    /**
     * Constructor
     * 
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    public LoadPowerInfrastructureRepositoryIntoBlackboardJob(final SimuComWorkflowConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("Given configuration must not be null.");
        }
        this.path = (String) configuration.getAttributes().get(LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig.INFRASTRUCTURE_MODEL_FILE);
    }

    @Override
    public void setJobConfiguration(AbstractExtensionJobConfiguration configuration) {
        if (!(configuration instanceof LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig)) {
            throw new IllegalArgumentException("Given configuration must be of type "
                    + "'LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig'.");
        }
        this.path = ((LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig) configuration).getInfrastructureRepositoryPath();
        super.setJobConfiguration(configuration);
    }
    
    @Override
    public void setBlackboard(MDSDBlackboard blackboard) {
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

    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        if (this.myBlackboard == null) {
            throw new IllegalStateException("Blackboard has been not set beforehand!");
        }
        PowerInfrastructureRepositoryResourceSetPartition partition = new PowerInfrastructureRepositoryResourceSetPartition();
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
