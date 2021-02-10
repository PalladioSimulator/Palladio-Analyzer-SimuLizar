package org.palladiosimulator.simulizar.events;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Provides;

@dagger.Module
public class EventsTransformationConfigurationModule {

    @Provides
    static EventsTransformationConfiguration provideEventConfig(SimuLizarWorkflowConfiguration config) {
        var attributes = config.getAttributes();
        String eventMiddlewareFile = null;
        Boolean storeTransformedModels = null;
        String storeTransformedModelsProject = null;
        Boolean simulateEvents =
                attributes.containsKey(EventsTransformationConfiguration.SIMULATE_EVENTS)
                && Boolean.parseBoolean(attributes.get(EventsTransformationConfiguration.SIMULATE_EVENTS).toString());
        if (simulateEvents) {
            eventMiddlewareFile = attributes
                    .get(EventsTransformationConfiguration.EVENT_MIDDLEWARE_FILE).toString();
                
            storeTransformedModels = Boolean.parseBoolean(attributes
                    .get(EventsTransformationConfiguration.STORE_TRANSFORMED_MODELS).toString());
            if (storeTransformedModels) {
                storeTransformedModelsProject = attributes
                    .get(EventsTransformationConfiguration.STORE_TRANSFORMED_MODELS_PROJECT).toString();
            }
        }

        return new EventsTransformationConfiguration(simulateEvents, eventMiddlewareFile, storeTransformedModelsProject,
                storeTransformedModels);
    }

}
