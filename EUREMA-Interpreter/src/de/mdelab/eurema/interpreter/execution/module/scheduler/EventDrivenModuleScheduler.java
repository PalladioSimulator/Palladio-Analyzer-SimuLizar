package de.mdelab.eurema.interpreter.execution.module.scheduler;

import de.mdelab.eurema.interpreter.EventQueue;

/**
 * Schedules the execution of a module (wrapped by a {@code ModuleExecutable})
 * at layer 1 of the layered architecture that senses a layer 0 module.
 * Scheduling is based on sensor events.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface EventDrivenModuleScheduler extends ModuleScheduler {

	/**
	 * Returns the local sensor event queue.
	 * 
	 * @return the local sensor event queue.
	 */
	public EventQueue getLocalSensorEventQueue();

}
