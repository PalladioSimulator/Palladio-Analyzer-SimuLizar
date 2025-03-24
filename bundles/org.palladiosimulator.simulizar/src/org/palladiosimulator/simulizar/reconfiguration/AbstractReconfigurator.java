package org.palladiosimulator.simulizar.reconfiguration;

import org.palladiosimulator.simulizar.core.reconfiguration.IReconfigurationEngine;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager;

public abstract class AbstractReconfigurator implements IReconfigurationEngine {
    protected PCMPartitionManager pcmPartitionManager;
	protected SimuLizarWorkflowConfiguration configuration;

    @Override
	public void setConfiguration(SimuLizarWorkflowConfiguration configuration) {
		this.configuration = configuration;	
	}
    
    @Override
   	public void setPCMPartitionManager(PCMPartitionManager pcmPartitionManager) {
   		this.pcmPartitionManager = pcmPartitionManager;
   	}
    
}