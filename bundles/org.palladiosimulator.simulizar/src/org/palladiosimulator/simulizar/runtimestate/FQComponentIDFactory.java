package org.palladiosimulator.simulizar.runtimestate;

import java.util.List;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
/**
 * Factory interface for FQComponentID with assisted inject
 * @author Jens Manig
 *
 */
public interface FQComponentIDFactory {
	 public FQComponentID create(final List<AssemblyContext> assemblyContextPath);

}
