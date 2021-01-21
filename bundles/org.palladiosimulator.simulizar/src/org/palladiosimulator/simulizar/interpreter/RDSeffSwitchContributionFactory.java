package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.di.extension.Extension;

/**
 * Abstract Factory used by the extensible behaviour switches extension point.
 * 
 * @author mrombach
 *
 */
public interface RDSeffSwitchContributionFactory<T> extends Extension {
    public interface RDSeffElementDispatcher<T> {
        T doSwitch(EClass theEClass, EObject theEObject);
        
        default T doSwitch(EObject theEObject) {
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
	public Switch<T> createRDSeffSwitch(final InterpreterDefaultContext context,
	        RDSeffElementDispatcher<T> parentSwitch);
	
	@Override
	default Class<? extends Extension> getExtensionType() {
	    return RDSeffSwitchContributionFactory.class;
	}
}