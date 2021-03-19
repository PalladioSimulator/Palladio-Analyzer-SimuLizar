package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies;

import org.palladiosimulator.failuremodel.failurescenario.Occurence;
import org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;

public abstract class AbstractCrashBehaviorStrategy implements FailureBehaviorChangingStrategy {

	protected PreInterpretationBehaviorContainer pIBContainer;
	protected final PreInterpretationBehavior behavior;

	public AbstractCrashBehaviorStrategy(PreInterpretationBehaviorContainer pIBContainer,
			PreInterpretationBehavior behavior) {
		this.pIBContainer = pIBContainer;
		this.behavior = behavior;
	}

	public AbstractCrashBehaviorStrategy(PreInterpretationBehavior behavior) {
		this(null, behavior);
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
	public boolean allocateContext(ReferenceResolverSwitch r, PreInterpretationBehaviorManager pibManager,
			Occurence o) {
		String id = r.doSwitch(o.getOrigin());
		if (id == null) {
			return false;
		}
		this.pIBContainer = pibManager.getContainerForEntity(id);
		return true;
	}
}
