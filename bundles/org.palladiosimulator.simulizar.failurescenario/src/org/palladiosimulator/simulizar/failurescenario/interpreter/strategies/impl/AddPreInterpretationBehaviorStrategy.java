package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl;

import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.AbstractPreInterpretationBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.FailureBehaviorChangingStrategy;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;

public class AddPreInterpretationBehaviorStrategy extends AbstractPreInterpretationBehaviorStrategy {

	public AddPreInterpretationBehaviorStrategy(PreInterpretationBehaviorContainer pIBContainer,
			PreInterpretationBehavior behavior) {
		super(pIBContainer, behavior);
	}

	/**
	 * Alternative Constructor if the container is not yet known. It must be set in
	 * the strategy.allocate(context).
	 * 
	 * @param behavior
	 */
	public AddPreInterpretationBehaviorStrategy(PreInterpretationBehavior behavior) {
		this(null, behavior);
	}

	@Override
	public void execute() {
		super.pIBContainer.addBehavior(super.behavior);
	}

	@Override
	public FailureBehaviorChangingStrategy getRevertedStrategy() {
		return new RemovePreInterpretationBehaviorStrategy(super.pIBContainer, super.behavior);
	}
}
