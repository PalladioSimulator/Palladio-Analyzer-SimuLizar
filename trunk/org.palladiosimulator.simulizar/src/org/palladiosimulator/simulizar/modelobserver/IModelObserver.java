package org.palladiosimulator.simulizar.modelobserver;

import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

public interface IModelObserver {

    /**
     * Syncs PCM models with SimuCom.
     */
    public void initialize(final AbstractSimuLizarRuntimeState runtimeState);

    public void unregister();

}