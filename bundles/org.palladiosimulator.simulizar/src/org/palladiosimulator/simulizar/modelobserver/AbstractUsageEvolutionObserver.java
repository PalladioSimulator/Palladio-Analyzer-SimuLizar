package org.palladiosimulator.simulizar.modelobserver;

import java.util.stream.Stream;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

public abstract class AbstractUsageEvolutionObserver extends AbstractModelObserver<UsageEvolution> {

    public AbstractUsageEvolutionObserver(@Global PCMResourceSetPartition globalPCMInstance) {
        super(globalPCMInstance);
    }
    
    @Override
    protected Stream<UsageEvolution> selectObservees(PCMResourceSetPartition partition) {
        return partition.<UsageEvolution>getElement(UsageevolutionPackage.Literals.USAGE_EVOLUTION).stream();
    }
}