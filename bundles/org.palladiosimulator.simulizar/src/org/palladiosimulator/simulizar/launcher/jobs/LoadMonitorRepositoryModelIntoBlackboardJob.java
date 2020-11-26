package org.palladiosimulator.simulizar.launcher.jobs;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.exceptions.MonitorRepositoryModelLoadException;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Job for loading monitor repository model into blackboard. Resolving proxies to pcm.
 *
 * @author Joachim Meyer
 *
 */
public class LoadMonitorRepositoryModelIntoBlackboardJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

    private MDSDBlackboard blackboard;

    private final SimuLizarWorkflowConfiguration configuration;


    public LoadMonitorRepositoryModelIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        if (StringUtils.isEmpty(configuration.getMonitorRepositoryFile())) {
            throw new MonitorRepositoryModelLoadException("Monitor repository file path is missing from workflow configuration");
        }
        
        PCMResourceSetPartition pcmResourceSetPartition = (PCMResourceSetPartition) (this.getBlackboard().getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID));
        if (pcmResourceSetPartition == null) {
            throw new MonitorRepositoryModelLoadException("The PCM models must be loaded first");
        }
        
        URI monitorRepositoryURI = URI.createURI(configuration.getMonitorRepositoryFile());
        pcmResourceSetPartition.loadModel(monitorRepositoryURI);
        pcmResourceSetPartition.resolveAllProxies();
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