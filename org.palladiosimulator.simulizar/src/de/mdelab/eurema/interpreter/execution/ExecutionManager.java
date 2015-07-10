package de.mdelab.eurema.interpreter.execution;

import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.interpreter.EventQueue;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;

/**
 * Manages the execution of the EUREMA model containing at least one module to
 * be executed.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface ExecutionManager {

	/**
	 * Initializes the execution manager.
	 * 
	 * @param eRuntimeEnvironment
	 *            the {@code eurema.RuntimeEnvironment} instance
	 * @return the queue to which the adaptable software emits sensor events.
	 */
	public EventQueue initialize(eurema.RuntimeEnvironment eRuntimeEnvironment, IModelAccess a,
			RuntimeViolationsModel v, RuntimeStrategiesModel s);

}
