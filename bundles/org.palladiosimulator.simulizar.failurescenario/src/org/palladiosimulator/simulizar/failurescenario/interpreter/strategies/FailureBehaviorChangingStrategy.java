package org.palladiosimulator.simulizar.failurescenario.interpreter.strategies;

import org.palladiosimulator.failuremodel.failurescenario.Occurence;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ScheduledResourceProviderSwitch;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;

public interface FailureBehaviorChangingStrategy {

	/**
	 * Executes the strategy. Before, you should test if this call is possible by
	 * calling isValid().
	 */
	public void execute();

	/**
	 * Checks if the strategys context has been allocated.
	 * 
	 * @return true if context is allocated.
	 */
	public boolean isValid();

	/**
	 * Allocate the strategies context.
	 * 
	 * @param referenceResolver ReferenceResolverSwitch
	 * @param resourceProvider  ScheduledResourceProviderSwitch
	 * @param pibManager        PreInterpretationBehaviorManager
	 * @param o                 Occurence
	 * @return Returns false if it was impossible to allocate the strategy.
	 */
	public boolean allocateContext(ReferenceResolverSwitch referenceResolver,
			ScheduledResourceProviderSwitch resourceProvider, PreInterpretationBehaviorManager pibManager, Occurence o);

	public FailureBehaviorChangingStrategy getRevertedStrategy();
}
