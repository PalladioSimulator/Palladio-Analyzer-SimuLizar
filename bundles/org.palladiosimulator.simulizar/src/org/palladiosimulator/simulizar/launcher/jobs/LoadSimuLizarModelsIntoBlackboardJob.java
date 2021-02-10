package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.analyzer.workflow.jobs.CreateBlackboardPartitionJob;
import org.palladiosimulator.analyzer.workflow.jobs.LoadModelIntoBlackboardJob;
import org.palladiosimulator.analyzer.workflow.jobs.PreparePCMBlackboardPartitionJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Composite Job for preparing Blackboard and loading PCM Models into it.
 *
 * @author Joachim Meyer
 * @author Matthias Becker
 *
 */
public class LoadSimuLizarModelsIntoBlackboardJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> implements ModelContribution.Facade, PartitionContribution.Facade {

    public static final String PCM_MODELS_ANALYZED_PARTITION_ID = "org.palladiosimulator.analyzed.partition";

    /**
     * @param config
     */
    @Inject
    public LoadSimuLizarModelsIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration,
            Provider<Set<PartitionContribution>> partitionContributionExtensions,
            Provider<Set<ModelContribution>> modelContributionExtensions) {
        super(false);

        addJob(new PreparePCMBlackboardPartitionJob());
        
        partitionContributionExtensions.get().forEach(contrib -> contrib.contribute(this));
        
        addLoadPCMModelJobs(configuration);
        addLoadMonitorRepository(configuration);
        addSLORepository(configuration);
        addUsageEvolution(configuration);
        
        modelContributionExtensions.get().forEach(contrib -> contrib.loadModel(this));
    }
    
    protected void addLoadPCMModelJobs(final SimuLizarWorkflowConfiguration configuration) {
        configuration.getPCMModelFiles().forEach(m -> LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(m, this));
    }
    
    protected void addLoadMonitorRepository(final SimuLizarWorkflowConfiguration configuration) {
        LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(configuration.getMonitorRepositoryFile(), this);
    }
    
    protected void addSLORepository(final SimuLizarWorkflowConfiguration configuration) {
        LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(configuration.getServiceLevelObjectivesFile(), this);
    }
    
    protected void addUsageEvolution(final SimuLizarWorkflowConfiguration configuration) {
        LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(configuration.getUsageEvolutionFile(), this);
    }

    @Override
    public void loadModel(URI modelURI) {
        loadModel(modelURI, ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID);
    }

    @Override
    public void loadModel(URI modelURI, String partitionId) {
        this.addJob(new LoadModelIntoBlackboardJob(modelURI, partitionId));
    }

    @Override
    public void appendPartition(String partitionId, ResourceSetPartition partition) {
        this.addJob(new CreateBlackboardPartitionJob(partitionId, () -> partition));
    }
}
