package org.palladiosimulator.simulizar.events;

public class EventsTransformationConfiguration {
    public static final String SIMULATE_EVENTS = "simulateEvents";
    public static final String EVENT_MIDDLEWARE_FILE = "eventMiddleware";
    public static final String STORE_TRANSFORMED_MODELS = "storeTransformedModels";
    public static final String STORE_TRANSFORMED_MODELS_PROJECT = "storageProject";

	final public boolean simulateEvents;
	final public String eventMiddlewareFile;
	final public String storeTransformedModelsProject;
	final public boolean storeTransformedModels;

	public EventsTransformationConfiguration(boolean simulateEvents, String eventMiddlewareFile, String storeTransformedModelsProject, boolean storeTransformedModels) {
		this.simulateEvents = simulateEvents;
		this.eventMiddlewareFile = eventMiddlewareFile;
		this.storeTransformedModelsProject = storeTransformedModelsProject;
		this.storeTransformedModels = storeTransformedModels;
	}
}