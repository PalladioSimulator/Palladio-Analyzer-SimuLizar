package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import de.mdelab.eurema.interpreter.Metamodel;
import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventType;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventTypeHierarchy;
import de.mdelab.eurema.interpreter.execution.module.inerceptor.MegamodelModuleInterceptionManager;

/**
 * Executes an instance of {@code eurema.Transition} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.02
 */
public class TransitionExecutor extends ElementExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context
	 */
	public TransitionExecutor(GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.Transition}.
	 * 
	 * @param transition
	 *            the transition to be executed
	 * @param before
	 *            the {@code eurema.Operation} instance that is the source of
	 *            the transition to be executed and that has just been executed.
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the {@code eurema.Operation} instance to be executed next, which
	 *         is the target of the transition that is currently executed.
	 */
	public eurema.Operation execute(eurema.Transition transition,
			eurema.Operation before, MegamodelModuleExecutionContext context) {
		
		// CHECK FOR ON_TRANSITION INTERCEPTIONS
		eurema.Event eEvent =
				Metamodel.INSTANCE.getEuremaFactory().createEvent();
		eEvent.setName(transition.getName());
		eEvent.setType(EuremaEventTypeHierarchy.INSTANCE.getEEventType(EuremaEventType.ON_TRANSITION));
		MegamodelModuleInterceptionManager.INSTANCE
				.executeInterceptorModules(context.getMegamodelModule(), eEvent);
		
		// return the target operation of this transition
		return transition.getTarget().getOperation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.Executable execute(eurema.Executable executable,
			eurema.Executable before, MegamodelModuleExecutionContext context) {
		assert executable instanceof eurema.Transition
				&& before instanceof eurema.Operation;
		return this.execute((eurema.Transition) executable,
				(eurema.Operation) before, context);
	}

}
