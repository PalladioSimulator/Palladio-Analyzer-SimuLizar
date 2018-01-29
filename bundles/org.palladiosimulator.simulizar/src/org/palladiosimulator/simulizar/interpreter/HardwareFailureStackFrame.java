package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.reliability.HardwareInducedFailureType;
import org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.reliability.MarkovFailureType;

public class HardwareFailureStackFrame extends FailureStackFrame<HardwareInducedFailureType> {

	private ResourceContainer resourceContainer;
	
	public HardwareFailureStackFrame(HardwareInducedFailureType type, ResourceContainer container, AbstractAction source) {
		super(type, source);
		this.resourceContainer = container;
	}

	public ResourceContainer getResourceContainer() {
		return resourceContainer;
	}
	
	public void setResourceContainer(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
	}

	@Override
	public MarkovFailureType translateToMarkovFailureType(FailureStatistics stats) {
		return stats.getInternalHardwareFailureType(resourceContainer.getId(), type.getProcessingResourceType__HardwareInducedFailureType().getId());
	}
	
	

}
