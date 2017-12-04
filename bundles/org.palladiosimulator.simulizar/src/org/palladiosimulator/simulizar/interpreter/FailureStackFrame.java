package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.reliability.FailureType;

public class FailureStackFrame {

	private FailureType type;

	public FailureStackFrame(FailureType type) {
		super();
		this.type = type;
	}

	public FailureType getType() {
		return type;
	}

	public void setType(FailureType type) {
		this.type = type;
	}
	
	
	
}
