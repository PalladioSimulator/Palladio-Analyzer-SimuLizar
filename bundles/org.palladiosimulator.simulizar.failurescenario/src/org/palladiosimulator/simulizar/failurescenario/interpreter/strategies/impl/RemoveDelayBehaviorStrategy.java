package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl;

import org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation.DelayBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.AbstractPreInterpretationBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.FailureBehaviorChangingStrategy;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;

public class RemoveDelayBehaviorStrategy extends AbstractPreInterpretationBehaviorStrategy {

	public RemoveDelayBehaviorStrategy(PreInterpretationBehaviorContainer pIBContainer, DelayBehavior behavior) {
		super(pIBContainer, behavior);
	}

	/**
	 * Alternative Constructor if the container is not yet known. It must be set in
	 * the strategy.allocate(context).
	 * 
	 * @param behavior
	 */
	public RemoveDelayBehaviorStrategy(DelayBehavior behavior) {
		this(null, behavior);
	}

	@Override
	public void execute() {
		super.pIBContainer.removeBehavior(super.behavior);
	}

	@Override
	public FailureBehaviorChangingStrategy getRevertedStrategy() {
		return new AddDelayBehaviorStrategy(super.pIBContainer, (DelayBehavior)super.behavior);
	}
}
