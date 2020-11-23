package org.palladiosimulator.simulizar.test.commons.util;

import java.util.function.Supplier;

import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;

@FunctionalInterface
public interface PartitionSupplier extends Supplier<PCMResourceSetPartition> {
    
    default String partitionId() {
        return ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID;
    }
}
