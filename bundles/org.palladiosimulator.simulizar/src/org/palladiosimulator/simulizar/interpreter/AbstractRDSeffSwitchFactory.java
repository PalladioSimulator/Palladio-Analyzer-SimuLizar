package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.Switch;

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
	public abstract Switch<Object> createRDSeffSwitch(final InterpreterDefaultContext context,
            ExplicitDispatchComposedSwitch<Object> parentSwitch);
}