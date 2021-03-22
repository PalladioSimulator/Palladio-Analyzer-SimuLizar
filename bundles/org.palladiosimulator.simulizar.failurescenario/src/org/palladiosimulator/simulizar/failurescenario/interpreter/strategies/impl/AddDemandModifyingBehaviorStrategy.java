package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl;

import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.AbstractDemandModifyingBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.FailureBehaviorChangingStrategy;

import de.uka.ipd.sdq.simucomframework.resources.DemandModifyingBehavior;
import de.uka.ipd.sdq.simucomframework.resources.IResourceDemandModifiable;

public class AddDemandModifyingBehaviorStrategy extends AbstractDemandModifyingBehaviorStrategy {

	public AddDemandModifyingBehaviorStrategy(IResourceDemandModifiable modifiableResource,
			DemandModifyingBehavior behavior) {
		super(modifiableResource, behavior);
	}
	
	/**
	 * Alternative Constructor if the resource is not yet known.
	 * It must be set in the strategy.allocate(context).
	 * 
	 * @param behavior
	 */
	public AddDemandModifyingBehaviorStrategy(DemandModifyingBehavior behavior) {
		this(null, behavior);
	}
	
	@Override
	public void execute() {
		super.modifiableResource.addDemandModifyingBehavior(super.behavior);
	}

	@Override
	public FailureBehaviorChangingStrategy getRevertedStrategy() {
		return new RemoveDemandModifyingBehaviorStrategy(super.modifiableResource, super.behavior);
	}

}
