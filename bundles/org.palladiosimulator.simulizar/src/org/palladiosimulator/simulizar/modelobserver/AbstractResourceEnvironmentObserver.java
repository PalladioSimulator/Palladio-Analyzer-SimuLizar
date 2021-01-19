package org.palladiosimulator.simulizar.modelobserver;

import java.util.stream.Stream;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

public abstract class AbstractResourceEnvironmentObserver extends AbstractModelObserver<ResourceEnvironment> {

    public AbstractResourceEnvironmentObserver(@Global PCMResourceSetPartition globalPCMInstance) {
        super(globalPCMInstance);
    }
    
    @Override
    protected Stream<ResourceEnvironment> selectObservees(PCMResourceSetPartition partition) {
        return Stream.of(partition.getAllocation().getTargetResourceEnvironment_Allocation());
    }
}
