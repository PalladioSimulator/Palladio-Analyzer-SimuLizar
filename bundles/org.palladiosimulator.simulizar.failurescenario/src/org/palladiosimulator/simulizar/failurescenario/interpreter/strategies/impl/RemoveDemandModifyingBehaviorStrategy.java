package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl;

import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.AbstractDemandModifyingBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.FailureBehaviorChangingStrategy;

import de.uka.ipd.sdq.simucomframework.resources.DemandModifyingBehavior;
import de.uka.ipd.sdq.simucomframework.resources.IResourceDemandModifiable;

public class RemoveDemandModifyingBehaviorStrategy extends AbstractDemandModifyingBehaviorStrategy {
	public RemoveDemandModifyingBehaviorStrategy(IResourceDemandModifiable modifiableResource,
			DemandModifyingBehavior behavior) {
		super(modifiableResource, behavior);
	}
	
	/**
	 * Alternative Constructor if the resource is not yet known.
	 * It must be set in the strategy.allocate(context).
	 * 
	 * @param behavior
	 */
	public RemoveDemandModifyingBehaviorStrategy(DemandModifyingBehavior behavior) {
		this(null, behavior);
	}
	
	@Override
	public void execute() {
		super.modifiableResource.removeDemandModifyingBehavior(super.behavior);
	}

	@Override
	public FailureBehaviorChangingStrategy getRevertedStrategy() {
		return new AddDemandModifyingBehaviorStrategy(super.modifiableResource, super.behavior);
	}
}
