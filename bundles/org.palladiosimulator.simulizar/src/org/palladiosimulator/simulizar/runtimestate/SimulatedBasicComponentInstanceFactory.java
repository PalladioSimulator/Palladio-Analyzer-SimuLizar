package org.palladiosimulator.simulizar.runtimestate;

import java.util.List;

import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

public interface SimulatedBasicComponentInstanceFactory {
	 public SimulatedBasicComponentInstance create(final InterpreterDefaultContext context,final FQComponentID fqID,
	            final List<PassiveResource> passiveResources);
}
