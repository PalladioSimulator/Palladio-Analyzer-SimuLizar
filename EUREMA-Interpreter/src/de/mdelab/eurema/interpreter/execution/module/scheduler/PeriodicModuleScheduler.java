package de.mdelab.eurema.interpreter.execution.module.scheduler;

import de.mdelab.eurema.interpreter.EventQueue;

/**
 * Schedules the execution of a module (wrapped by a {@code ModuleExecutable})
 * at layer 1 of the layered architecture that senses a layer 0 module.
 * Scheduling is based on a period of time.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface PeriodicModuleScheduler extends ModuleScheduler {

	/**
	 * Returns the local timer event queue.
	 * 
	 * @return the local timer event queue.
	 */
	public EventQueue getLocalTimerEventQueue();

}
