package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior;

public interface IBehavioralDecisionDecorated {
	public BehavioralDecider getDecider();

	public void setDecider(BehavioralDecider d);
}
