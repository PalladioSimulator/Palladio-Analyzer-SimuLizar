package org.palladiosimulator.simulizar.modules.shared.mdsd;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Local;

import dagger.Provides;

@dagger.Module
public interface PCMPartitionManagerAdapterModule {
    
    @Provides
    @Global
    static PCMResourceSetPartition providesGlobalPartition(PCMPartitionManager partitionManager) {
        return partitionManager.getGlobalPCMModel();
    }
    
    @Provides
    @Local
    static PCMResourceSetPartition providesLocalPartition(PCMPartitionManager partitionManager) {
        return partitionManager.getLocalPCMModel();
    }

}
