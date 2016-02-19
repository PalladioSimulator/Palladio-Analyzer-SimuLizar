package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;

/**
 * Executes an instance of {@code eurema.OperationBehavior} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public abstract class OperationBehaviorExecutor extends OperationExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	protected OperationBehaviorExecutor(
			GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.OperationBehavior}.
	 * 
	 * @param operationBehavior
	 *            the operation behavior to be executed.
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the operation behavior to be
	 *            executed that has been taken
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation has just been executed.
	 */
	public abstract eurema.Transition execute(
			eurema.OperationBehavior operationBehavior,
			eurema.Transition before, MegamodelModuleExecutionContext context);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.Transition execute(eurema.Operation operation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		assert operation instanceof eurema.OperationBehavior;
		return this.execute((eurema.OperationBehavior) operation, before,
				context);
	}

}
