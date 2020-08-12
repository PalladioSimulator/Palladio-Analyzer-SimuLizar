package org.palladiosimulator.simulizar.reliability;

import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.reliability.MarkovFailureType;

public abstract class FailureStackFrame<T extends FailureType> {
	
	protected T type;
	
	private AbstractAction source;

	protected FailureStackFrame(T type, AbstractAction source) {
		super();
		this.type = type;
		this.source = source;
	}

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}

	public AbstractAction getSource() {
		return source;
	}

	public void setSource(InternalAction source) {
		this.source = source;
	}
	
	public abstract MarkovFailureType translateToMarkovFailureType(FailureStatistics stats);
	
	
	
	
	
}
