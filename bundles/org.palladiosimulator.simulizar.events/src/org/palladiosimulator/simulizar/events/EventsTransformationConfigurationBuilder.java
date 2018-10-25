package org.palladiosimulator.simulizar.events;
import java.util.Map;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

public class EventsTransformationConfigurationBuilder extends AbstractWorkflowExtensionConfigurationBuilder {

	public EventsTransformationConfigurationBuilder() {
	}

	@Override
	public AbstractExtensionJobConfiguration buildConfiguration(Map<String, Object> attributes) {
		String eventMiddlewareFile = (String) attributes.get(EventsTransformationWorkflowExtensionJob.EVENT_MIDDLEWARE_FILE);
		Boolean simulateFailures = (Boolean) attributes.get(EventsTransformationWorkflowExtensionJob.SIMULATE_EVENTS);
		String storeTransformedModelsProject = (String) attributes.get(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS_PROJECT);
		Boolean storeTransformedModels = (Boolean) attributes.get(EventsTransformationWorkflowExtensionJob.STORE_TRANSFORMED_MODELS);
		
		return new EventsTransformationConfiguration(simulateFailures, eventMiddlewareFile, storeTransformedModelsProject, storeTransformedModels);
	}

}
