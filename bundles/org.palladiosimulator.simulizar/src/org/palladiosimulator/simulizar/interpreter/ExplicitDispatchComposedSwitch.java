package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory.RDSeffElementDispatcher;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class ExplicitDispatchComposedSwitch extends ComposedSwitch<InterpreterResult> implements RDSeffElementDispatcher {

	@Override
	public InterpreterResult doSwitch(EClass theEClass, EObject theEObject) {
		return super.doSwitch(theEClass, theEObject);
	}
	
	@Override
	public InterpreterResult defaultCase(EObject eObject) {
	    throw new UnsupportedOperationException("Unsupported model element encountered: " + eObject.eClass().getName());
	}

}