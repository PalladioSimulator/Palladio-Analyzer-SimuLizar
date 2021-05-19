package org.palladiosimulator.simulizar.failurescenario.interpreter.provider;

import org.palladiosimulator.failuremodel.failurescenario.LinkingResourceReference;
import org.palladiosimulator.failuremodel.failurescenario.ProcessingResourceReference;
import org.palladiosimulator.failuremodel.failurescenario.util.FailurescenarioSwitch;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.resources.IResourceDemandModifiable;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

public class ScheduledResourceProviderSwitch extends FailurescenarioSwitch<IResourceDemandModifiable> {

	private ResourceRegistry resourceRegistry;

	public ScheduledResourceProviderSwitch(ResourceRegistry r) {
		this.resourceRegistry = r;

	}

	public IResourceDemandModifiable caseLinkingResourceReference(LinkingResourceReference object) {
		String modelEntityIdentifier = object.getLinkingResource().getId();
		var container = resourceRegistry.getResourceContainer(modelEntityIdentifier);
		if (container instanceof SimulatedLinkingResourceContainer) {
			var linkId = ((SimulatedLinkingResourceContainer) container).getLinkingResourceTypeId();
			var resource = container.getAllActiveResources().get(linkId);
			if (resource instanceof SimulatedLinkingResource) {
				return (SimulatedLinkingResource) resource;
			}
		}
		return null;
	}

	public IResourceDemandModifiable caseProcessingResourceReference(ProcessingResourceReference object) {
		String modelEntityIdentifier = object.getProcessingResource()
				.getActiveResourceType_ActiveResourceSpecification().getId();
		String containerId = object.getProcessingResource().getResourceContainer_ProcessingResourceSpecification()
				.getId();
		var container = resourceRegistry.getResourceContainer(containerId);
		if (container instanceof SimulatedResourceContainer) {
			var resource = container.getAllActiveResources().get(modelEntityIdentifier);
			if (resource instanceof ScheduledResource) {
				return (ScheduledResource) resource;
			}
		}
		return null;
	}
}
