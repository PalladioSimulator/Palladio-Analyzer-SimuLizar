package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;

/**
 * Executes an instance of {@code eurema.Executable} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * <p>
 * Different megamodel modules may run concurrently and the executors are
 * thread-safe to concurrently execute elements each of which belongs to a
 * different module.
 * </p>
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public abstract class ElementExecutor {

	/**
	 * The global execution context.
	 */
	protected GlobalExecutionContext globalExecutionContext;

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	protected ElementExecutor(GlobalExecutionContext globalExecutionContext) {
		this.globalExecutionContext = globalExecutionContext;
	}

	/**
	 * Executes an instance of {@code eurema.Executable}, which is an executable
	 * megamodel element, and returns the executable megamodel element to be
	 * executed next.
	 * 
	 * @param executable
	 *            the executable megamodel element to be executed.
	 * @param before
	 *            the executable megamodel element executed just before
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the executable megamodel element to be executed next.
	 */
	public abstract eurema.Executable execute(eurema.Executable executable, eurema.Executable before,
			MegamodelModuleExecutionContext context);

}
