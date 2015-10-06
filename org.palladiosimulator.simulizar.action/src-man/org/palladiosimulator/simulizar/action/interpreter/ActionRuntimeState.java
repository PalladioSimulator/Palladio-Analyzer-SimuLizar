package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Objects;

import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public class ActionRuntimeState implements IRuntimeStateAccessor {
    private static SimuLizarRuntimeState state;
    private static QVToModelCache availableModels;

    public static TransientEffectInterpreter createTransientEffectInterpreter(RoleSet set, ActionRepository repository) {
        return new TransientEffectInterpreter(state, set, repository, availableModels);
    }

    @Override
    public void setRuntimeStateModel(SimuLizarRuntimeState passedState) {
        ActionRuntimeState.state = Objects.requireNonNull(passedState);
        ActionRuntimeState.availableModels = new QVToModelCache(passedState.getModelAccess());
    }
}
