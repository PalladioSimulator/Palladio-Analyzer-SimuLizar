package de.mdelab.eurema.interpreter.execution.module.executor;

/**
 * A <tt>ModuleExecutable</tt> is used for executing an instance of
 * {@code eurema.Module} in an execution context. To initiate the execution, use
 * the <tt>run(I entryState)</tt> method.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 * @param <M>
 *            the module to be executed.
 * @param <T>
 *            the triggering condition of the module to be executed.
 * @param <I>
 *            the entry state for starting the execution of the module.
 * @param <E>
 *            the exit state in which the execution of the module has
 *            terminated.
 */
public interface ModuleExecutable<M extends eurema.Module, T extends eurema.Trigger, I extends Object, E extends Object> {

	/**
	 * Returns the module to be executed.
	 * 
	 * @return the module to be executed.
	 */
	public M getEModule();

	/**
	 * Returns the triggering condition of the module to be executed.
	 * 
	 * @return the triggering condition of the module to be executed.
	 */
	public T getETrigger();

	/**
	 * Executes the module starting in the <tt>entryState</tt>.
	 * 
	 * @param entryState
	 *            the entry state for executing the module
	 * @return the result/exit state of having executed the module.
	 */
	public E run(I entryState);

	/**
	 * Returns the {@code eurema.ExecutionContext} of the megamodel module to be
	 * executed. Such an execution context is only maintained for instances of
	 * {@code eurema.MegamodelModule} but not for {@code eurema.SoftwareModule}.
	 * If this executable is used for a software module, this method returns
	 * <code>null</code>. EUREMA is in charge of executing megamodel modules
	 * while software modules are considered and executed as black boxes running
	 * in arbitrary locations or environments.
	 * 
	 * @return the execution context of the megamodel module or
	 *         <code>null</code> if the module is a software module.
	 */
	public eurema.ExecutionContext getExecutionContext();

}
