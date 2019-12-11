package org.palladiosimulator.simulizar.modelobserver;

import java.util.Objects;

import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

public abstract class AbstractUsageModelObserver extends AbstractModelObserver<UsageModel> {

    public AbstractUsageModelObserver() {
        super();
    }

    @Override
    public void initialize(final AbstractSimuLizarRuntimeState runtimeState) {
        super.initialize(runtimeState.getPCMPartitionManager().getGlobalPCMModel().getUsageModel(),
                Objects.requireNonNull(runtimeState));
    }
}
