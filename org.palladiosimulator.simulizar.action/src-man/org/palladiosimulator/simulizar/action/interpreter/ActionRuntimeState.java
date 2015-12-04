package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Objects;

import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public class ActionRuntimeState implements IRuntimeStateAccessor {
    private static SimuLizarRuntimeState state;
    private static IModelAccess modelAccess;

    public static TransientEffectInterpreter createTransientEffectInterpreter(RoleSet set,
            AdaptationBehaviorRepository repository) {
        return new TransientEffectInterpreter(state, set, repository, modelAccess);
    }

    @Override
    public void setRuntimeStateModel(SimuLizarRuntimeState passedState) {
        ActionRuntimeState.state = Objects.requireNonNull(passedState);
        ActionRuntimeState.modelAccess = passedState.getModelAccess();
    }
}
