package org.palladiosimulator.simulizar.runtimestate;
/**
 * Factory interface for SimulatedCompositeComponentInstance with assisted inject
 * @author Jens Manig
 *
 */
public interface SimulatedCompositeComponentInstanceFactory {
	 public SimulatedCompositeComponentInstance create(final String fqId);
}
