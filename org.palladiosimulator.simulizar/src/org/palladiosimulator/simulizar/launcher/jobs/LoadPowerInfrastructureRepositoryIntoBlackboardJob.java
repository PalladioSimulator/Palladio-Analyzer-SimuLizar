package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.partitions.PowerInfrastructureRepositoryResourceSetPartition;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public final class LoadPowerInfrastructureRepositoryIntoBlackboardJob implements IJob,
        IBlackboardInteractingJob<MDSDBlackboard> {

    public static final String POWER_INFRASTRUCTURE_REPOSITORY_MODEL_PARTITION_ID = "de.fzi.power";

    private MDSDBlackboard blackboard;
    private final String path;

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
        this.path = (String) configuration.getAttributes().get(SimulizarConstants.INFRASTRUCTURE_MODEL_FILE);
    }

    @Override
    public void setBlackboard(MDSDBlackboard blackboard) {
        if (blackboard == null) {
            throw new IllegalArgumentException("Blackboard to set must not be null.");
        }
        this.blackboard = blackboard;
    }

    public MDSDBlackboard getBlackboard() {
        return this.blackboard;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }

    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        if (this.blackboard == null) {
            throw new IllegalStateException("Blackboard has been not set beforehand!");
        }
        PowerInfrastructureRepositoryResourceSetPartition partition = new PowerInfrastructureRepositoryResourceSetPartition();
        if (!this.path.equals("")) {
            partition.loadModel(URI.createURI(!this.path.startsWith("platform:") ? "file:///" + this.path : this.path));

        }
        this.blackboard.addPartition(POWER_INFRASTRUCTURE_REPOSITORY_MODEL_PARTITION_ID, partition);
        // now resolve all cross references from current resource to PCM
        partition.resolveAllProxies();
    }

    @Override
    public String getName() {
        return "Perform Power Infrastructure Repository Load";
    }

}
