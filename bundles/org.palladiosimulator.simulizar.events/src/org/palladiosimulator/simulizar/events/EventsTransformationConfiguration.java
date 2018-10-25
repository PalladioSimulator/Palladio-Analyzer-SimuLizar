package org.palladiosimulator.simulizar.events;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;

public class EventsTransformationConfiguration extends AbstractExtensionJobConfiguration {

	final public boolean simulateFailures;
	final public String eventMiddlewareFile;
	final public String storeTransformedModelsProject;
	final public boolean storeTransformedModels;

	public EventsTransformationConfiguration(boolean simulateFailures, String eventMiddlewareFile, String storeTransformedModelsProject, boolean storeTransformedModels) {
		this.simulateFailures = simulateFailures;
		this.eventMiddlewareFile = eventMiddlewareFile;
		this.storeTransformedModelsProject = storeTransformedModelsProject;
		this.storeTransformedModels = storeTransformedModels;
	}
	
	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public void setDefaults() {
		
	}
}