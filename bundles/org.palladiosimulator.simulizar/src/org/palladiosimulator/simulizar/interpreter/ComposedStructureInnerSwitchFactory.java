package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Signature;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface ComposedStructureInnerSwitchFactory {
    ComposedStructureInnerSwitch create(final InterpreterDefaultContext context, final Signature operationSignature,
            final RequiredRole requiredRole);
}
