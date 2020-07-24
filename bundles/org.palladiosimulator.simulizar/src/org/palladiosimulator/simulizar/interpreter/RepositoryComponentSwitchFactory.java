package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;

/**
 * Factory interface for RepositoryComponentSwitch with assisted inject
 * @author Jens Manig
 *
 */
public interface RepositoryComponentSwitchFactory {
	public RepositoryComponentSwitch create(final InterpreterDefaultContext context,final AssemblyContext assemblyContext,
    		final Signature signature,final ProvidedRole providedRole);

}
