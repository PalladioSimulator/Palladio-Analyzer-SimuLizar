package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies;

import org.palladiosimulator.failuremodel.failurescenario.Occurence;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ScheduledResourceProviderSwitch;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;

public abstract class AbstractPreInterpretationBehaviorStrategy implements FailureBehaviorChangingStrategy {

	protected PreInterpretationBehaviorContainer pIBContainer;
	protected final PreInterpretationBehavior behavior;

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
	public boolean isValid() {
		return this.pIBContainer != null && this.behavior != null;
	}

	@Override
	public boolean allocateContext(ReferenceResolverSwitch referenceResolver,
			ScheduledResourceProviderSwitch resourceProvider, PreInterpretationBehaviorManager pibManager,
			Occurence o) {
		// get the reference where the adapter should be added
		String id = referenceResolver.doSwitch(o.getOrigin());
		if (id == null) {
			return false;
		}
		this.pIBContainer = pibManager.getContainerForEntity(id);
		return true;
	}
}
