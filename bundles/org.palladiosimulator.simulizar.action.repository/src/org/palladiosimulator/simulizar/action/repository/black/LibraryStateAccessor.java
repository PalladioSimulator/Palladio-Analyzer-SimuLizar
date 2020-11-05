package org.palladiosimulator.simulizar.action.repository.black;

import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public final class LibraryStateAccessor implements IRuntimeStateAccessor {

    public LibraryStateAccessor() {
    }

    @Override
    public void setRuntimeStateModel(SimuLizarRuntimeState state) {
        SimulationStateLibrary.injectRuntimeStateModel(state);
    }
}
