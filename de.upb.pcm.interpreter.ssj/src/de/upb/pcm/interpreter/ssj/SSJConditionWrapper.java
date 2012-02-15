package de.upb.pcm.interpreter.ssj;

import de.uka.ipd.sdq.simulation.abstractsimengine.SimCondition;

public class SSJConditionWrapper {

	private SimCondition abstractCondition;

	public SSJConditionWrapper(
			SimCondition abstractCondition) {
		// super(owner, abstractCondition.getName(), false);
		this.abstractCondition = abstractCondition;
	}
}
