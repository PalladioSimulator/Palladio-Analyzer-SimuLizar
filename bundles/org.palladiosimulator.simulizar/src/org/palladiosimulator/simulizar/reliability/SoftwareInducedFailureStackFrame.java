package org.palladiosimulator.simulizar.reliability;

import org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.reliability.MarkovFailureType;

public class SoftwareInducedFailureStackFrame extends FailureStackFrame<SoftwareInducedFailureType> {

	public SoftwareInducedFailureStackFrame(SoftwareInducedFailureType type, AbstractAction source) {
		super(type, source);
	}

	@Override
	public MarkovFailureType translateToMarkovFailureType(FailureStatistics stats) {
		return stats.getInternalSoftwareFailureType(type.getId(), getSource().getId());
	}
	
	

}
