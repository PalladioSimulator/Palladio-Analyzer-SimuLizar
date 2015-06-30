package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;

/**
 * Executes an instance of {@code eurema.FinalOperation} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class FinalOperationExecutor extends ControlOperationExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	public FinalOperationExecutor(GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.FinalOperation}.
	 * 
	 * @param finalOperation
	 *            the final operation to be executed.
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the final operation to be
	 *            executed that has been taken
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation has just been executed. Since final operations
	 *         have no outgoing transitions, <code>null</code> is returned.
	 */
	public eurema.Transition execute(eurema.FinalOperation finalOperation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		// final operations have no behavior and do not require any
		// checks or actions. Since final operations have no outgoing
		// transitions, there is no transition to be enabled and to be returned.
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.Transition execute(eurema.ControlOperation controlOperation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		assert controlOperation instanceof eurema.FinalOperation;
		return this.execute((eurema.FinalOperation) controlOperation, before,
				context);
	}

}
