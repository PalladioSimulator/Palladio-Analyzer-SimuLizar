package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies;

import org.palladiosimulator.failuremodel.failurescenario.Occurence;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ScheduledResourceProviderSwitch;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;

import de.uka.ipd.sdq.simucomframework.resources.DemandModifyingBehavior;
import de.uka.ipd.sdq.simucomframework.resources.IResourceDemandModifiable;

public abstract class AbstractDemandModifyingBehaviorStrategy implements FailureBehaviorChangingStrategy {

	protected IResourceDemandModifiable modifiableResource;
	protected final DemandModifyingBehavior behavior;

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
	public boolean isValid() {
		return this.modifiableResource != null && this.behavior != null;
	}

	@Override
	public boolean allocateContext(ReferenceResolverSwitch referenceResolver,
			ScheduledResourceProviderSwitch resourceProvider, PreInterpretationBehaviorManager pibManager,
			Occurence o) {
		this.modifiableResource = resourceProvider.doSwitch(o.getOrigin());
		return (this.modifiableResource != null);
	}
}
