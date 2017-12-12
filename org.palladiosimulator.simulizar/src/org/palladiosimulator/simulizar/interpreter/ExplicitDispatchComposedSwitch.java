package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;

public class ExplicitDispatchComposedSwitch<T> extends ComposedSwitch<T> {

	@Override
	public T doSwitch(EClass theEClass, EObject theEObject) {
		return super.doSwitch(theEClass, theEObject);
	}
	
	

}
