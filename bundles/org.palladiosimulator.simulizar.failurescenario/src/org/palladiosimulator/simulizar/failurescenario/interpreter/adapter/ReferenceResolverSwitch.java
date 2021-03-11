package org.palladiosimulator.simulizar.failurescenario.interpreter.adapter;

import org.eclipse.emf.common.notify.Notifier;
import org.palladiosimulator.failuremodel.failurescenario.InternalActionReference;
import org.palladiosimulator.failuremodel.failurescenario.LinkingResourceReference;
import org.palladiosimulator.failuremodel.failurescenario.ProcessingResourceReference;
import org.palladiosimulator.failuremodel.failurescenario.util.FailurescenarioSwitch;

public class ReferenceResolverSwitch extends FailurescenarioSwitch<Notifier> {

	public Notifier caseLinkingResourceReference(LinkingResourceReference object) {
		return object.getLinkingResource();
	}

	public Notifier caseInternalActionReference(InternalActionReference object) {
		return object.getInternalAction();
	}

	public Notifier caseProcessingResourceReference(ProcessingResourceReference object) {
		return object.getProcessingResource();
	}
}
