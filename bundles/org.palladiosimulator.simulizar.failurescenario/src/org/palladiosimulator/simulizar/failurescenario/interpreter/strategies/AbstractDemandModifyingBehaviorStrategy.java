package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies;

import org.palladiosimulator.simulizar.failurescenario.interpreter.StrategyAllocationContextDTO;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.Decider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.IDecisionDecorated;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.demandmodifying.DecisionDecoratedDemandModifyingBehavior;

import de.uka.ipd.sdq.simucomframework.resources.DemandModifyingBehavior;
import de.uka.ipd.sdq.simucomframework.resources.IResourceDemandModifiable;

public abstract class AbstractDemandModifyingBehaviorStrategy implements FailureBehaviorChangingStrategy {

	protected IResourceDemandModifiable modifiableResource;
	protected DemandModifyingBehavior behavior;

	public AbstractDemandModifyingBehaviorStrategy(IResourceDemandModifiable modifiableResource,
			DemandModifyingBehavior behavior) {
		this.modifiableResource = modifiableResource;
		this.behavior = behavior;
	}

	@Override
	public abstract void execute();

	@Override
	public abstract FailureBehaviorChangingStrategy getRevertedStrategy();

	@Override
	public void setDecider(Decider decider) {
		if (this.behavior instanceof IDecisionDecorated) {
			((IDecisionDecorated) this.behavior).setDecider(decider);
		} else {
			this.behavior = new DecisionDecoratedDemandModifyingBehavior(this.behavior, decider);
		}
	}

	@Override
	public boolean isValid() {
		return this.modifiableResource != null && this.behavior != null;
	}

	@Override
	public boolean allocateContext(StrategyAllocationContextDTO allocationContext) {
		this.modifiableResource = allocationContext.getResourceProvider()
				.doSwitch(allocationContext.getOccurrence().getOrigin());
		return (this.modifiableResource != null);
	}
}
