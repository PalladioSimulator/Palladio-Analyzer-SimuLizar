package org.palladiosimulator.simulizar.interpreter.listener;

import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.pcm.seff.seff_reliability.RecoveryActionBehaviour;
import org.palladiosimulator.simulizar.reliability.FailureStackFrame;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class FailureHandledEvent<F extends FailureType> extends FailureEvent<F>{

    private final RecoveryActionBehaviour failureHandlingElement;
    
	public FailureHandledEvent(RecoveryActionBehaviour failureHandlingElement, FailureStackFrame<? extends F> failure, SimuComSimProcess thread) {
		super(failure, thread);
		this.failureHandlingElement = failureHandlingElement;
	}

	public RecoveryActionBehaviour getFailureHandlingElement() {
		return failureHandlingElement;
	}
    
}
