package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simframework.SimulatedResourceContainerRegistry;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;
import org.palladiosimulator.simulizar.utils.TransitionDeterminer;

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
     * @param resourceContainerRegistry
     *            a SimulatedResourceContainerRegistry
     * @param transitionDeterminer
     *            transitionDeterminer for the switch
     * @param componentInstanceRegistry
     *              componentInstanceRegistry for the switch
     * @param eventHelper
     *              eventNotifictationHelper for the switch
	 * @return a composable switch
	 */
	protected abstract Switch<Object> createRDSeffSwitch(final InterpreterDefaultContext context,
            final SimulatedBasicComponentInstance basicComponentInstance, ExplicitDispatchComposedSwitch<Object> parentSwitch);
}