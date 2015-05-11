package de.mdelab.eurema.interpreter.execution;

import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;
import de.mdelab.eurema.interpreter.execution.module.executor.megamodel.ElementExecutorFactory;

/**
 * The execution context of the interpreter. It is shared by all modules for
 * execution.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface GlobalExecutionContext {

	/**
	 * Returns the executable of a module.
	 * 
	 * @param eModule
	 *            the module
	 * @return the executable of the module
	 */
	public ModuleExecutable<?, ?, ?, ?> getExecutable(eurema.Module eModule);

	/**
	 * Returns the factory to obain executors for elements of a megamodel
	 * module.
	 * 
	 * @return the factory to obain executors for elements of a megamodel
	 *         module.
	 */
	public ElementExecutorFactory getElementExecutorFactory();

}
