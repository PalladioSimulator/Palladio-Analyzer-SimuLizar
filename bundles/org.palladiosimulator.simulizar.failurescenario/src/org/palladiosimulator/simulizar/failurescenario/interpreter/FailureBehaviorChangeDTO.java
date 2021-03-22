package org.palladiosimulator.simulizar.failurescenario.interpreter;

import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.FailureBehaviorChangingStrategy;

/**
 * A Data Transfer Object which contains a strategy and a relativePointInTime value.
 * 
 * @author Jonas Lehmann
 *
 */
public class FailureBehaviorChangeDTO {
	
	private final FailureBehaviorChangingStrategy strategy;
	private final double relativePointInTime;
	
	public FailureBehaviorChangeDTO(FailureBehaviorChangingStrategy strategy, final double relativePointInTime) {
		this.strategy = strategy;
		this.relativePointInTime = relativePointInTime;
	}
	
	// getters
	public FailureBehaviorChangingStrategy getStrategy() {
		return this.strategy;
	}
	public double getRelativePointInTime() {
		return this.relativePointInTime;
	}
}
