package org.palladiosimulator.simulizar.syncer;

public interface IModelSyncer {

    /**
     * Syncs PCM models with SimuCom.
     */
    public void initializeSyncer();

    public void stopSyncer();

}