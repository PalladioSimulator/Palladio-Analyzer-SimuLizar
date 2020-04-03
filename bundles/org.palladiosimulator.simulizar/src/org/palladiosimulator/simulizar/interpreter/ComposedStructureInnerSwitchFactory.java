package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Signature;

public interface ComposedStructureInnerSwitchFactory {
	ComposedStructureInnerSwitch createComposedStructureInnerSwitch(final InterpreterDefaultContext context,
			final Signature operationSignature, final RequiredRole requiredRole);
}
