package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface RepositoryComponentSwitchFactory {
    RepositoryComponentSwitch create(final InterpreterDefaultContext context, final AssemblyContext assemblyContext,
            final Signature signature, final ProvidedRole providedRole);
}
