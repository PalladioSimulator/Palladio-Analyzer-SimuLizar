package org.palladiosimulator.simulizar.action.interpreter;

import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public abstract class AbstractStateTransformation {

    protected SimuLizarRuntimeState simulationState;

    public void setSimulationState(final SimuLizarRuntimeState state) {
        this.simulationState = state;
    }

    public abstract boolean execute(RoleSet roleSet);

}
