package org.palladiosimulator.simulizar.runtimestate;

import java.util.List;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;

public interface FQComponentIDFactory {
	 public FQComponentID create(final List<AssemblyContext> assemblyContextPath);

}
