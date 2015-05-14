package de.mdelab.eurema.interpreter.execution.module.scheduler;

import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;

/**
 * Schedules the execution of a module (wrapped by a {@code ModuleExecutable})
 * with a triggering condition at layer 1 of the layered architecture. Only
 * modules with a triggering condition and located at layer 1 (i.e., layer 1
 * modules sensing layer 0 modules) do need a scheduler.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface ModuleScheduler extends Runnable {

	/**
	 * Returns the module executable.
	 * 
	 * @return the module executable.
	 */
	public ModuleExecutable<?, ?, ?, ?> getExecutable();

	/**
	 * Shuts down the module scheduler.
	 */
	public void shutDown();

}
