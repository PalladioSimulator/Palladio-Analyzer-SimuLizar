package org.palladiosimulator.simulizar.syncer;

import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public interface IModelObserver {

    /**
     * Syncs PCM models with SimuCom.
     */
    public void initialize(final SimuLizarRuntimeState runtimeState);

    public void unregister();

}