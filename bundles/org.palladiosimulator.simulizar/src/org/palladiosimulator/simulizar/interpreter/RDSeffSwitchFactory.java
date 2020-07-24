package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;
import org.palladiosimulator.simulizar.utils.TransitionDeterminer;
/**
 * Factory interface for RDSeffSwitch with assisted inject
 * @author Jens Manig
 *
 */
public interface RDSeffSwitchFactory {
	 public RDSeffSwitch create(final InterpreterDefaultContext context,
	            final SimulatedBasicComponentInstance basicComponentInstance,
	            final TransitionDeterminer transitionDeterminer);
	 
	 public RDSeffSwitch create(final InterpreterDefaultContext context,
	            final SimulatedBasicComponentInstance basicComponentInstance, 
	            ComposedSwitch<Object> parentSwitch,
	            final TransitionDeterminer transitionDetermine);
}
