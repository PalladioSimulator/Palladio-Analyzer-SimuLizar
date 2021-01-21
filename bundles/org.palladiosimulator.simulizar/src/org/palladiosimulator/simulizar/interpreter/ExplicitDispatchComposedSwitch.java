package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory.RDSeffElementDispatcher;

public class ExplicitDispatchComposedSwitch<T> extends ComposedSwitch<T> implements RDSeffElementDispatcher<Object> {

	@Override
	public T doSwitch(EClass theEClass, EObject theEObject) {
		return super.doSwitch(theEClass, theEObject);
	}

}
