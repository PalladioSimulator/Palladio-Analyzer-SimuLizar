package de.mdelab.eurema.interpreter.execution;

import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;
import de.mdelab.eurema.interpreter.execution.module.executor.megamodel.ElementExecutorFactory;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;

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

	public RuntimeViolationsModel getRuntimeViolationsModel();

	public RuntimeStrategiesModel getRuntimeStrategiesModel();

	public IModelAccess getModelAccess();

}
