package org.palladiosimulator.simulizar.interpreter.listener;

import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.pcm.seff.seff_reliability.FailureHandlingEntity;
import org.palladiosimulator.simulizar.reliability.FailureStackFrame;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class FailureHandledEvent<F extends FailureType> extends FailureEvent<F>{

    private final FailureHandlingEntity failureHandlingElement;
    
	public FailureHandledEvent(FailureHandlingEntity handler, FailureStackFrame<? extends F> failure, SimuComSimProcess thread) {
		super(failure, thread);
		this.failureHandlingElement = handler;
	}

	public FailureHandlingEntity getFailureHandlingElement() {
		return failureHandlingElement;
	}
    
}
