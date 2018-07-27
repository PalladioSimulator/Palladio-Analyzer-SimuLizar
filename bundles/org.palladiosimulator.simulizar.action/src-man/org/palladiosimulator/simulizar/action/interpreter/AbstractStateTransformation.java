package org.palladiosimulator.simulizar.action.interpreter;

import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

public abstract class AbstractStateTransformation {

    protected AbstractSimuLizarRuntimeState simulationState;

    public void setSimulationState(final AbstractSimuLizarRuntimeState state) {
        this.simulationState = state;
    }

    public abstract boolean execute(RoleSet roleSet);

}
