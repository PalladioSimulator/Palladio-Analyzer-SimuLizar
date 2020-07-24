package org.palladiosimulator.simulizar.runconfig;

import javax.inject.Inject;
import javax.inject.Provider;

import org.palladiosimulator.simulizar.utils.PCMPartitionManager;



import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class PCMPartitionManagerProvider implements Provider<PCMPartitionManager> {

	private final PCMPartitionManager pcmPartitionManager;
	
	@Inject
	public PCMPartitionManagerProvider(final SimuLizarWorkflowConfiguration configuration, final MDSDBlackboard blackboard) {
		this.pcmPartitionManager = new PCMPartitionManager(blackboard, configuration);
	}
	
	@Override
	public PCMPartitionManager get() {
		// TODO Auto-generated method stub
		return this.pcmPartitionManager;
	}

}
