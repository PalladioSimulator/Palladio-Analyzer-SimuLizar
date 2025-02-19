package org.palladiosimulator.simulizar.di.modules.stateless.mdsd;

import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import dagger.Provides;

@dagger.Module
public interface PCMPartitionManagerAdapterModule {
    
    @Provides
    @Global
    static PCMResourceSetPartition providesGlobalPartition(PCMPartitionManager partitionManager) {
        return partitionManager.getGlobalPCMModel();
    }

}
