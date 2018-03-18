package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.simulizar.reliability.FailureStackFrame;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class FailureOccurredEvent<T extends EObject, F extends FailureType> extends FailureEvent<F>{

    private final T failureRaisingElement;
    
	public FailureOccurredEvent(T failureRaisingElement, FailureStackFrame<? extends F> failure, SimuComSimProcess thread) {
		super(failure, thread);
		this.failureRaisingElement = failureRaisingElement;
	}

	public T getFailureRaisingElement() {
		return failureRaisingElement;
	}
    
}

