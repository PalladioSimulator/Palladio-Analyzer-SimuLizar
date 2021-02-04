package org.palladiosimulator.simulizar.modelobserver;

import java.util.stream.Stream;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

public abstract class AbstractUsageModelObserver extends AbstractModelObserver<UsageModel> {

    public AbstractUsageModelObserver(@Global PCMResourceSetPartition globalPCMInstance) {
        super(globalPCMInstance);
    }
    
    @Override
    protected Stream<UsageModel> selectObservees(PCMResourceSetPartition partition) {
        return Stream.of(partition.getUsageModel());
    }
}
