package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.Set;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

import org.palladiosimulator.analyzer.workflow.jobs.CreateBlackboardPartitionJob;
import org.palladiosimulator.analyzer.workflow.jobs.PreparePCMBlackboardPartitionJob;

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
public class SimuLizarPrepareBlackboardJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> implements PartitionContribution.Facade {

    /**
     * @param config
     */
    @Inject
    public SimuLizarPrepareBlackboardJob(Provider<Set<PartitionContribution>> partitionContributionExtensions) {
        super(false);

        addStandardJobs();
        addExtensionJobs(partitionContributionExtensions);
    }
    
    protected void addStandardJobs() {
        addJob(new PreparePCMBlackboardPartitionJob());
    }
    
    protected void addExtensionJobs(Provider<Set<PartitionContribution>> partitionContributionExtensions) {
        partitionContributionExtensions.get().forEach(contrib -> contrib.contribute(this));
    }
    
    @Override
    public void appendPartition(String partitionId, ResourceSetPartition partition) {
        this.addJob(new CreateBlackboardPartitionJob(partitionId, () -> partition));
    }
}
