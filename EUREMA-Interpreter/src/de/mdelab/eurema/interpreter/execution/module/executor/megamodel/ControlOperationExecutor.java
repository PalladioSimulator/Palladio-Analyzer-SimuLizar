package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;

/**
 * Executes an instance of {@code eurema.ControlOperation} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public abstract class ControlOperationExecutor extends OperationExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	public ControlOperationExecutor(
			GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.ControlOperation}.
	 * 
	 * @param controlOperation
	 *            the control operation to be executed.
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the control operation to be
	 *            executed that has been taken
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation has just been executed.
	 */
	public abstract eurema.Transition execute(
			eurema.ControlOperation controlOperation, eurema.Transition before,
			MegamodelModuleExecutionContext context);

	/**
	 * {@inheritDoc}
	 */
	public eurema.Transition execute(eurema.Operation operation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		assert operation instanceof eurema.ControlOperation;
		return this.execute((eurema.ControlOperation) operation, before,
				context);
	}

}
