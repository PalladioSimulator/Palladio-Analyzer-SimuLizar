package org.palladiosimulator.simulizar.events;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Provides;

@dagger.Module
public class EventsTransformationConfigurationModule {

    @Provides
    static EventsTransformationConfiguration provideEventConfig(SimuLizarWorkflowConfiguration config) {
        var attributes = config.getAttributes();
        String eventMiddlewareFile = (String) attributes
            .get(EventsTransformationConfiguration.EVENT_MIDDLEWARE_FILE);
        Boolean simulateEvents = (Boolean) attributes.get(EventsTransformationConfiguration.SIMULATE_EVENTS);
        String storeTransformedModelsProject = (String) attributes
            .get(EventsTransformationConfiguration.STORE_TRANSFORMED_MODELS_PROJECT);
        Boolean storeTransformedModels = (Boolean) attributes
            .get(EventsTransformationConfiguration.STORE_TRANSFORMED_MODELS);

        return new EventsTransformationConfiguration(simulateEvents, eventMiddlewareFile, storeTransformedModelsProject,
                storeTransformedModels);
    }

}
