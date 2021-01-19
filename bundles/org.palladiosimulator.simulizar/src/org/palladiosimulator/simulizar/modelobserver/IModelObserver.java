package org.palladiosimulator.simulizar.modelobserver;

public interface IModelObserver {
    /**
     * Syncs PCM models with SimuCom.
     */
    public void initialize();

    default void unregister() {
    }

}