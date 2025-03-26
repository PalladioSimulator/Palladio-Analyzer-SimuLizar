package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.di.core.extension.Extension;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

/**
 * Abstract Factory used by the extensible behaviour switches extension point.
 * 
 * @author mrombach
 *
 */
public interface RDSeffSwitchContributionFactory extends Extension {
    public interface RDSeffElementDispatcher {
        InterpreterResult doSwitch(EClass theEClass, EObject theEObject);
        
        default InterpreterResult doSwitch(EObject theEObject) {
            return doSwitch(theEObject.eClass(), theEObject);
        }
    }
	
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
	public Switch<InterpreterResult> createRDSeffSwitch(final InterpreterDefaultContext context,
	        RDSeffElementDispatcher parentSwitch);
	
}