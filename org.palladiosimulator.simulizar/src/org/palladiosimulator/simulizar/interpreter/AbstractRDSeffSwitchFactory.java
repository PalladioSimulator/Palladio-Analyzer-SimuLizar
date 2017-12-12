package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;

/**
 * Abstract Factory used by the extensible behaviour switches extension point.
 * 
 * @author mrombach
 *
 */
public abstract class AbstractRDSeffSwitchFactory {
	
	/**
	 * 
     * @param context
     *				Default context for the pcm interpreter.
     * @param basicComponentInstance
     *				Simulated component
     * @param parentSwitch
     *				The composed switch which is containing the created switch
	 * @return a composable switch
	 */
	protected abstract Switch<Object> createRDSeffSwitch(final InterpreterDefaultContext context,
            final SimulatedBasicComponentInstance basicComponentInstance, ExplicitDispatchComposedSwitch<Object> parentSwitch);
}