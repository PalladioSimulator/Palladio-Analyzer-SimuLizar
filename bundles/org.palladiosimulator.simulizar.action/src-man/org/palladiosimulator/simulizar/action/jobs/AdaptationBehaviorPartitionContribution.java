package org.palladiosimulator.simulizar.action.jobs;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

import org.palladiosimulator.simulizar.action.partitions.AdaptationBehaviorRepositoryResourceSetPartition;
import org.palladiosimulator.simulizar.launcher.jobs.PartitionContribution;

public class AdaptationBehaviorPartitionContribution implements PartitionContribution {
    private final Provider<AdaptationBehaviorRepositoryResourceSetPartition> partitionSupplier;

    @Inject
    public AdaptationBehaviorPartitionContribution(Provider<AdaptationBehaviorRepositoryResourceSetPartition> partitionSupplier) {
        this.partitionSupplier = partitionSupplier;
    }

    @Override
    public void contribute(Facade delegate) {
        delegate.appendPartition(AdaptationBehaviorModelContribution.ADAPTATION_BEHAVIOR__REPOSITORY_MODEL_PARTITION_ID,
                partitionSupplier.get());
    }

}
