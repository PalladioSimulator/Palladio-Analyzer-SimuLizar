package org.palladiosimulator.simulizar.action.interpreter;

import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeStateAbstract;

public abstract class AbstractStateTransformation {

    protected SimuLizarRuntimeStateAbstract simulationState;

    public void setSimulationState(final SimuLizarRuntimeStateAbstract state) {
        this.simulationState = state;
    }

    public abstract boolean execute(RoleSet roleSet);

}
