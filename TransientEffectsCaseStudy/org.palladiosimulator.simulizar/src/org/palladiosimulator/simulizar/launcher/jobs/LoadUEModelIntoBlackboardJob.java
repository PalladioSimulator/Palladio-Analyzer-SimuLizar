package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.exceptions.UEModelLoadException;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Job for loading usage evolution model into blackboard. Resolving proxies to pcm.
 *
 * @author Erlend Stav
 *
 */
public class LoadUEModelIntoBlackboardJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

    private static final String FILE_PREFIX = "file:///";

    private MDSDBlackboard blackboard;

    private final SimuLizarWorkflowConfiguration configuration;

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    public LoadUEModelIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        if (this.getPCMResourceSetPartition() == null) {
            throw new UEModelLoadException("The PCM models must be loaded first");
        }

        // Use the PCMResoureSetPartition for usage evolution
        final PCMResourceSetPartition uePartition = this.getPCMResourceSetPartition();
        if (!this.getPath().equals("")) {

            // add file protocol if necessary
            String filePath = this.getPath();
            if (!filePath.startsWith("platform:") && !filePath.startsWith(FILE_PREFIX)) {
                filePath = FILE_PREFIX + filePath;
            }

            uePartition.loadModel(URI.createURI(filePath));
            uePartition.resolveAllProxies();

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
        return "Perform Usage Evolution Model Load";
    }

    /**
     * @return returns the path.
     */
    private String getPath() {
        return this.configuration.getUsageEvolutionFile();
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