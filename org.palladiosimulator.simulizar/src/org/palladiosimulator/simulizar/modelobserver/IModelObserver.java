package org.palladiosimulator.simulizar.modelobserver;

import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public interface IModelObserver {

    /**
     * Syncs PCM models with SimuCom.
     */
    public void initialize(final SimuLizarRuntimeState runtimeState);

    public void unregister();

}