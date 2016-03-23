package org.palladiosimulator.simulizar.modelobserver;

import java.util.Objects;

import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.scaledl.usageevolution.UsageEvolution;

public abstract class AbstractUsageEvolutionObserver extends AbstractModelObserver<UsageEvolution> {

    public AbstractUsageEvolutionObserver() {
        super();
    }

    @Override
    public void initialize(final SimuLizarRuntimeState runtimeState) {
        super.initialize(Objects.requireNonNull(runtimeState).getModelAccess().getUsageEvolutionModel(), runtimeState);
    }
}
