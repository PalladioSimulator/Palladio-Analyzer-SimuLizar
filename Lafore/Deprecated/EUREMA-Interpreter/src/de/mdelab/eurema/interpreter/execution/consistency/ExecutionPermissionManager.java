package de.mdelab.eurema.interpreter.execution.consistency;

import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;

/**
 * Execution permission managed used by module schedulers to acquire (wait for)
 * permission before execution and release permission after execution.
 * Permissions are based on the quiescence state of the system.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface ExecutionPermissionManager {

	/**
	 * Acquires for execution permission of the module and blocks the caller
	 * until permission can be granted.
	 * 
	 * @param moduleExecutable
	 *            the module that should be executed
	 */
	public void waitForExecutionPermission(
			ModuleExecutable<?, ?, ?, ?> moduleExecutable);

	/**
	 * Releases the permission of executing the module and notifies the
	 * permission manager for having terminated the execution.
	 * 
	 * @param moduleExecutable
	 *            the module that has been executed.
	 */
	public void releaseExecutionPermission(
			ModuleExecutable<?, ?, ?, ?> moduleExecutable);
}
