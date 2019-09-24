package org.palladiosimulator.simulizar.events;

import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.analyzer.workflow.jobs.EventsTransformationJob;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class EventsTransformationWorkflowExtensionJob extends AbstractWorkflowExtensionJob<MDSDBlackboard> {
	public static final String SIMULATE_EVENTS = "simulateEvents";
	public static final String EVENT_MIDDLEWARE_FILE = "eventMiddleware";
	public static final String STORE_TRANSFORMED_MODELS = "storeTransformedModels";
	public static final String STORE_TRANSFORMED_MODELS_PROJECT = "storageProject";

	public EventsTransformationWorkflowExtensionJob() {

	}

	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
		super.execute(monitor);

		EventsTransformationConfiguration configuration = (EventsTransformationConfiguration) getJobConfiguration();
		
		if (configuration.simulateFailures) {
			EventsTransformationJob delegate = new EventsTransformationJob(configuration.storeTransformedModelsProject,
					configuration.eventMiddlewareFile, configuration.storeTransformedModels);
	
			delegate.setBlackboard(myBlackboard);
			delegate.execute(monitor);
		}
	}
}
