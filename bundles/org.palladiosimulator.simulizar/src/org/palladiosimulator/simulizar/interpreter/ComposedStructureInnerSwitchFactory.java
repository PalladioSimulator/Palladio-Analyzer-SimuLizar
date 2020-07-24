package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Signature;
/**
 * Factory interface for ComposedStructureInnerswitch with assisted inject
 * @author Jens Manig
 *
 */
public interface ComposedStructureInnerSwitchFactory {
	 public ComposedStructureInnerSwitch create(final InterpreterDefaultContext context, final Signature operationSignature,
	            final RequiredRole requiredRole);
}
