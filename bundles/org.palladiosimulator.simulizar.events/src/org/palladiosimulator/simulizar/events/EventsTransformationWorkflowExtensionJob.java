package org.palladiosimulator.simulizar.events;

import javax.inject.Inject;

import org.palladiosimulator.analyzer.workflow.jobs.EventsTransformationJob;
import org.palladiosimulator.simulizar.launcher.jobs.BlackBoardPreparationJobContributor;

public class EventsTransformationWorkflowExtensionJob implements BlackBoardPreparationJobContributor {
    
	private final EventsTransformationConfiguration config;

	@Inject
	public EventsTransformationWorkflowExtensionJob(EventsTransformationConfiguration config) {
        this.config = config;
	}

    @Override
    public void loadModel(Facade delegate) {
        if (config.simulateEvents) {
            delegate.contribute(new EventsTransformationJob(config.storeTransformedModelsProject,
                    config.eventMiddlewareFile, config.storeTransformedModels));
        }
    }
}
