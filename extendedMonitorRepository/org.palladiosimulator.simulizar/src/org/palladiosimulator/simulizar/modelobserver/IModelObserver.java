package org.palladiosimulator.simulizar.modelobserver;

import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeStateAbstract;

public interface IModelObserver {

    /**
     * Syncs PCM models with SimuCom.
     */
    public void initialize(final SimuLizarRuntimeStateAbstract runtimeState);

    public void unregister();

}
