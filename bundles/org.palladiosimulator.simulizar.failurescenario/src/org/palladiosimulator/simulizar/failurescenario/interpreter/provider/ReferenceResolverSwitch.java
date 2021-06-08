package org.palladiosimulator.simulizar.failurescenario.interpreter.provider;

import org.palladiosimulator.failuremodel.failurescenario.InternalActionReference;
import org.palladiosimulator.failuremodel.failurescenario.LinkingResourceReference;
import org.palladiosimulator.failuremodel.failurescenario.ProcessingResourceReference;
import org.palladiosimulator.failuremodel.failurescenario.util.FailurescenarioSwitch;

public class ReferenceResolverSwitch extends FailurescenarioSwitch<String> {

	public String caseLinkingResourceReference(LinkingResourceReference object) {
		return object.getLinkingResource().getId();
	}

	public String caseInternalActionReference(InternalActionReference object) {
		return object.getInternalAction().getId();
	}

	public String caseProcessingResourceReference(ProcessingResourceReference object) {
		return object.getProcessingResource().getId();
	}
}
