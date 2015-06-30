package org.palladiosimulator.simulizar.syncer;

public interface IModelSyncer {

    /**
     * Syncs resource environment model with SimuCom.
     */
    public void initializeSyncer();

    public void stopSyncer();

}