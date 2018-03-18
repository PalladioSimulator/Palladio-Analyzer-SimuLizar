package org.palladiosimulator.simulizar.interpreter.listener;

import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.simulizar.reliability.FailureStackFrame;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public abstract class FailureEvent<F extends FailureType> extends SimulationEvent {

	private final FailureStackFrame<? extends F> failure;
	
	public FailureEvent(FailureStackFrame<? extends F> failure, SimuComSimProcess thread) {
		super(thread);
		this.failure = failure;
	}

	public FailureStackFrame<? extends F> getFailure() {
		return failure;
	}

}

