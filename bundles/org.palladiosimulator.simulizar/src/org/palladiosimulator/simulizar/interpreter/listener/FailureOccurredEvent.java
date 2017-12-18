package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.interpreter.FailureStackFrame;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class FailureOccurredEvent<T extends EObject> {

    private final T modelElement;
	private final FailureStackFrame failure;
    private final double passageTime;
    private final SimuComSimProcess thread;
    
	public FailureOccurredEvent(T modelElement, FailureStackFrame failure, SimuComSimProcess thread) {
		super();
		this.modelElement = modelElement;
		this.failure = failure;
        this.passageTime = thread.getModel().getSimulationControl().getCurrentSimulationTime();
		this.thread = thread;
	}

	public T getModelElement() {
		return modelElement;
	}

	public FailureStackFrame getFailure() {
		return failure;
	}

	public double getPassageTime() {
		return passageTime;
	}

	public SimuComSimProcess getThread() {
		return thread;
	}
    
}
