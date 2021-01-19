package org.palladiosimulator.simulizar.modelobserver;

import org.palladiosimulator.simulizar.component.core.SimuLizarRuntimeComponent;

public interface IModelObserver {
    
    public interface Factory {
        IModelObserver create(SimuLizarRuntimeComponent runtimeComponent);
    }

    /**
     * Syncs PCM models with SimuCom.
     */
    public void initialize();

    public void unregister();

}