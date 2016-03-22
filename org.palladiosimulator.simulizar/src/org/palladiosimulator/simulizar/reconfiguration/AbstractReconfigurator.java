package org.palladiosimulator.simulizar.reconfiguration;

import org.palladiosimulator.simulizar.access.IModelAccess;

public abstract class AbstractReconfigurator implements IReconfigurationEngine {

    protected IModelAccess modelAccessFactory;

    public void setModelAccess(final IModelAccess modelAccess) {
        if (modelAccess == null) {
            throw new IllegalArgumentException("Given modelAccess must not be null.");
        }
        this.modelAccessFactory = modelAccess;
    }

}