package org.palladiosimulator.simulizar.action.repository.black;

import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeStateAbstract;

public final class LibraryStateAccessor implements IRuntimeStateAccessor {

    public LibraryStateAccessor() {
    }

    @Override
    public void setRuntimeStateModel(SimuLizarRuntimeStateAbstract state) {
        SimulationStateLibrary.injectRuntimeStateModel(state);
    }
}
