package de.upb.pcm.interpreter.ssj;

public class SSJConditionWrapper {

	private de.uka.ipd.sdq.simucomframework.abstractSimEngine.Condition abstractCondition;

	public SSJConditionWrapper(
			de.uka.ipd.sdq.simucomframework.abstractSimEngine.Condition abstractCondition) {
		// super(owner, abstractCondition.getName(), false);
		this.abstractCondition = abstractCondition;
	}
}
