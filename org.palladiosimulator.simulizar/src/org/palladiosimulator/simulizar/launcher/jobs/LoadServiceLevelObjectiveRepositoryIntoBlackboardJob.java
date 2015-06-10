package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.exceptions.SLORepositoryLoadException;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

/**
 * Job for loading service level objectives into blackboard and resolving proxies to Palladio models
 * and MonitorRepository.
 * 
 * @author Erlend Stav
 * 
 */
public class LoadServiceLevelObjectiveRepositoryIntoBlackboardJob implements IJob,
        IBlackboardInteractingJob<MDSDBlackboard> {

    public static final String SLO_REPOSITORY_PARTITION_ID = "org.palladiosimulator.servicelevelobjective";

    private MDSDBlackboard blackboard;

    private final String path;

    /**
     * Constructor
     * 
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    public LoadServiceLevelObjectiveRepositoryIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration) {
        this.path = configuration.getServiceLevelObjectivesFile();
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        if (this.getPCMResourceSetPartition() == null) {
            throw new SLORepositoryLoadException("Palladio models must be loaded first.");
        }

        final PCMResourceSetPartition sloPartition = this.getPCMResourceSetPartition();
        if (!this.getPath().equals("")) {

            // add file protocol if necessary
            String filePath = getPath();
            if (!getPath().startsWith("platform:")) {
                filePath = "file:///" + filePath;
            }

            sloPartition.loadModel(URI.createURI(filePath));
            this.getBlackboard().addPartition(SLO_REPOSITORY_PARTITION_ID, sloPartition);
            // now resolve all cross references from current resource to PCM
            sloPartition.resolveAllProxies();
        }

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
        return "Loading Service Level Objectives";
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