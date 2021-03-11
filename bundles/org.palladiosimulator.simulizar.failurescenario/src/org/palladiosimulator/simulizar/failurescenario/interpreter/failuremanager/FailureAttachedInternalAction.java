package org.palladiosimulator.simulizar.failurescenario.interpreter.failuremanager;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.pcm.seff.impl.InternalActionImpl;

public class FailureAttachedInternalAction extends InternalActionImpl implements FailureManager {
	
	private InternalActionImpl internalAction;
	private boolean isActivated;
	
	public FailureAttachedInternalAction(InternalActionImpl internalAction) {
		this.internalAction = internalAction;
		this.isActivated = false;
		//EcoreUtil.getAdapter(this.eAdapters(), PreInterpretationBehaviorAdapter.class);
	}
	
	// Override internal Action getter, and return corrupted values if isActiveted
	
	@Override
	public void activateFailure() {
		this.isActivated = true;
	}

	@Override
	public void deactivateFailure() {
		this.isActivated = false;
	}
}
