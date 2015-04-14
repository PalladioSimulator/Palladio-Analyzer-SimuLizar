package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.exceptions.MonitorRepositoryModelLoadException;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.partitions.MonitorRepositoryResourceSetPartition;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

/**
 * Job for loading monitor repository model into blackboard. Resolving proxies to pcm.
 * 
 * @author Joachim Meyer
 * 
 */
public class LoadMonitorRepositoryModelIntoBlackboardJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

    public static final String MONITOR_REPOSITORY_MODEL_PARTITION_ID = "org.palladiosimulator.simulizar.monitorrepository";

    private MDSDBlackboard blackboard;

    private final String path;

    /**
     * Constructor
     * 
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    public LoadMonitorRepositoryModelIntoBlackboardJob(final SimuComWorkflowConfiguration configuration) {
        this.path = (String) configuration.getAttributes().get(SimulizarConstants.MONITOR_REPOSITORY_FILE);
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        if (this.getPCMResourceSetPartition() == null) {
            throw new MonitorRepositoryModelLoadException("The PCM models must be loaded first");
        }

        final MonitorRepositoryResourceSetPartition monitorRepositoryPartition = new MonitorRepositoryResourceSetPartition(
                this.getPCMResourceSetPartition());
        if (!this.getPath().equals("")) {

            // add file protocol if necessary
            String filePath = getPath();
            if (!getPath().startsWith("platform:")) {
                filePath = "file:///" + filePath;
            }

            monitorRepositoryPartition.loadModel(URI.createURI(filePath));

        }
        this.getBlackboard().addPartition(MONITOR_REPOSITORY_MODEL_PARTITION_ID, monitorRepositoryPartition);
        // now resolve all cross references from current resource to PCM
        monitorRepositoryPartition.resolveAllProxies();

    }

    /**
     * @return returns the blackboard.
     */
    private MDSDBlackboard getBlackboard() {
        return this.blackboard;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#getName()
     */
    @Override
    public String getName() {
        return "Perform Monitor Repository Load";
    }

    /**
     * @return returns the path.
     */
    private String getPath() {
        return this.path;
    }

    /**
     * @return the pcm resource set partition
     */
    private PCMResourceSetPartition getPCMResourceSetPartition() {
        return (PCMResourceSetPartition) (this.getBlackboard()
                .getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID));
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {

    }

    /**
     * @see de.uka.ipd.sdq.workflow.IBlackboardInteractingJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
     */
    @Override
    public void setBlackboard(final MDSDBlackboard blackboard) {
        this.blackboard = blackboard;

    }

}