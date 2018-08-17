package org.palladiosimulator.simulizar.modelobserver;

import java.util.Objects;

import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

public abstract class AbstractUsageEvolutionObserver extends AbstractModelObserver<UsageEvolution> {

    public AbstractUsageEvolutionObserver() {
        super();
    }

    @Override
    public void initialize(final AbstractSimuLizarRuntimeState runtimeState) {
    	PCMPartitionManager manager = Objects.requireNonNull(runtimeState).getPCMPartitionManager();
        super.initialize(manager.findModel(UsageevolutionPackage.eINSTANCE.getUsageEvolution()), runtimeState);
    }
}
