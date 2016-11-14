package org.palladiosimulator.simulizar.action.repository.black;

import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

public final class LibraryStateAccessor implements IRuntimeStateAccessor {

    public LibraryStateAccessor() {
    }

    @Override
    public void setRuntimeStateModel(AbstractSimuLizarRuntimeState state) {
        SimulationStateLibrary.injectRuntimeStateModel(state);
    }
}
