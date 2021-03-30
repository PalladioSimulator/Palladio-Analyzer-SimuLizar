package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior;

public interface IDecisionDecorated {
	public Decider getDecider();

	public void setDecider(Decider d);
}
