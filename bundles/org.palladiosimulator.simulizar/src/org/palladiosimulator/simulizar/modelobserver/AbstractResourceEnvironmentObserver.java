package org.palladiosimulator.simulizar.modelobserver;

import java.util.Objects;

import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public abstract class AbstractResourceEnvironmentObserver extends AbstractModelObserver<ResourceEnvironment> {

    public AbstractResourceEnvironmentObserver() {
        super();
    }

    @Override
    public void initialize(final SimuLizarRuntimeState runtimeState) {
        super.initialize(runtimeState.getPCMPartitionManager().getGlobalPCMModel().getAllocation()
                .getTargetResourceEnvironment_Allocation(), Objects.requireNonNull(runtimeState));
    }
}
