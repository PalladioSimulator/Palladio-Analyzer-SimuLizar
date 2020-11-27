package org.palladiosimulator.simulizar.extension.adapter;

import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public class RuntimeStateAccessorAdapter implements InterpreterExtension {

    private final IRuntimeStateAccessor accessor;
    private final SimuLizarRuntimeState runtimeState;
    
    public RuntimeStateAccessorAdapter(IRuntimeStateAccessor accessor, SimuLizarRuntimeState runtimeState) {
        this.accessor = accessor;
        this.runtimeState = runtimeState;   
    }
    
    @Override
    public void initialized() {
        accessor.setRuntimeStateModel(runtimeState);
    }


}
