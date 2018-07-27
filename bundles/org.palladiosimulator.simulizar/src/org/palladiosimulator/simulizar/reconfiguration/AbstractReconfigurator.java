package org.palladiosimulator.simulizar.reconfiguration;

import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

public abstract class AbstractReconfigurator implements IReconfigurationEngine {
    protected IModelAccess modelAccessFactory;
    protected SimuLizarWorkflowConfiguration configuration;

    @Override
    public void setModelAccess(final IModelAccess modelAccess) {
        if (modelAccess == null) {
            throw new IllegalArgumentException("Given modelAccess must not be null.");
        }
        this.modelAccessFactory = modelAccess;
    }

    @Override
	public void setConfiguration(SimuLizarWorkflowConfiguration configuration) {
		this.configuration = configuration;	
	}
    
}