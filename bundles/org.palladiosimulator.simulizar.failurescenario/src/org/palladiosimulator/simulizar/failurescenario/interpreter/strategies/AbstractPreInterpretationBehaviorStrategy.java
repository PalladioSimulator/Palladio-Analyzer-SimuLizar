package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies;

import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.BehavioralDecider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.IBehavioralDecisionDecorated;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation.DecisionDecoratedPreInterpretationBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.dto.StrategyAllocationContextDTO;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;

public abstract class AbstractPreInterpretationBehaviorStrategy implements FailureBehaviorChangingStrategy {

	protected PreInterpretationBehaviorContainer pIBContainer;
	protected PreInterpretationBehavior behavior;

	public AbstractPreInterpretationBehaviorStrategy(PreInterpretationBehaviorContainer pIBContainer,
			PreInterpretationBehavior behavior) {
		this.pIBContainer = pIBContainer;
		this.behavior = behavior;
	}

	@Override
	public abstract void execute();

	@Override
	public abstract FailureBehaviorChangingStrategy getRevertedStrategy();

	@Override
	public void setDecider(BehavioralDecider decider) {
		if (this.behavior instanceof IBehavioralDecisionDecorated) {
			((IBehavioralDecisionDecorated) this.behavior).setDecider(decider);
		} else {
			this.behavior = new DecisionDecoratedPreInterpretationBehavior(this.behavior, decider);
		}
	}

	@Override
	public boolean isValid() {
		return this.pIBContainer != null && this.behavior != null;
	}

	@Override
	public boolean allocateContext(StrategyAllocationContextDTO allocationContext) {
		// get the reference where the adapter should be added
		String id = allocationContext.getReferenceResolver().doSwitch(allocationContext.getOccurrence().getOrigin());
		if (id == null) {
			return false;
		}
		this.pIBContainer = allocationContext.getPibManager().getContainerForEntity(id);
		return true;
	}
}
