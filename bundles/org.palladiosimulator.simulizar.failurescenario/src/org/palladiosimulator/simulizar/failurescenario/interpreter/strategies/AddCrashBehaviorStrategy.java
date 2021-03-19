package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies;

import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;

public class AddCrashBehaviorStrategy extends AbstractCrashBehaviorStrategy {

	public AddCrashBehaviorStrategy(PreInterpretationBehaviorContainer pIBContainer, PreInterpretationBehavior behavior) {
		super(pIBContainer, behavior);
	}
	
	/**
	 * Alternative Constructor if the container is not yet known.
	 * It must be set in the strategy.allocate(context).
	 * 
	 * @param behavior
	 */
	public AddCrashBehaviorStrategy(PreInterpretationBehavior behavior) {
		super(behavior);
	}
	
	@Override
	public void execute() {
		super.pIBContainer.addBehavior(super.behavior);
	}

	@Override
	public FailureBehaviorChangingStrategy getRevertedStrategy() {
		return new RemoveCrashBehaviorStrategy(super.pIBContainer, super.behavior);
	}
}
