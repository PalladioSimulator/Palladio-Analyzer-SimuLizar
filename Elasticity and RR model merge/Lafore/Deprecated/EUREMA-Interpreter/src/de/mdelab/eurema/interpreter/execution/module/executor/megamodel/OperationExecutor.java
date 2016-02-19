package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;

/**
 * Executes an instance of {@code eurema.Operation} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public abstract class OperationExecutor extends ElementExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context
	 */
	protected OperationExecutor(GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.Operation}.
	 * 
	 * @param operation
	 *            the operation to be executed.
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the operation to be executed
	 *            that has been taken
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation has just been executed.
	 */
	public abstract eurema.Transition execute(eurema.Operation operation,
			eurema.Transition before, MegamodelModuleExecutionContext context);

	/**
	 * {@inheritDoc}
	 */
	public eurema.Executable execute(eurema.Executable executable,
			eurema.Executable before, MegamodelModuleExecutionContext context) {
		assert executable instanceof eurema.Operation;
		// "before" can be null! If the executable operation is an initial
		// operation, "before" is null! Otherwise, "before" is not null.
		if (before != null) {
			assert before instanceof eurema.Transition;
			return this.execute((eurema.Operation) executable,
					(eurema.Transition) before, context);
		} else {
			// before == null
			return this.execute((eurema.Operation) executable, null, context);
		}

	}

}
