package org.palladiosimulator.simulizar.events;

import javax.inject.Inject;

import org.palladiosimulator.analyzer.workflow.jobs.EventsTransformationJob;
import org.palladiosimulator.simulizar.launcher.jobs.ModelCompletionJobContributor;

public class EventsTransformationWorkflowExtensionJob implements ModelCompletionJobContributor {

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
