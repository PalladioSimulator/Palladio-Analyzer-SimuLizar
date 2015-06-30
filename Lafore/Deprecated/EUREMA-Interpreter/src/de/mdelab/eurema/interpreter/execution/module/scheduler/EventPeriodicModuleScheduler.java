package de.mdelab.eurema.interpreter.execution.module.scheduler;

/**
 * Schedules the execution of a module (wrapped by a {@code ModuleExecutable})
 * at layer 1 of the layered architecture that senses a layer 0 module.
 * Scheduling is based sensor events and a period of time.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface EventPeriodicModuleScheduler extends
		EventDrivenModuleScheduler, PeriodicModuleScheduler {

}
