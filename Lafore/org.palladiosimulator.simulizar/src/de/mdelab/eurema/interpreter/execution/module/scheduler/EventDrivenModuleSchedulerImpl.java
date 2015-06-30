package de.mdelab.eurema.interpreter.execution.module.scheduler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.mdelab.eurema.interpreter.EventQueue;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventTypeHierarchy;
import de.mdelab.eurema.interpreter.execution.events.EventQueueInternal;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;

/**
 * Event-driven scheduler for a module (wrapped by a {@code ModuleExecutable})
 * at layer 1 of the layered architecture that senses a layer 0 module. The
 * scheduler consumes events from an individual queue of the module, i.e., each
 * module at layer 1 has its own sensor event queue and its own scheduler.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class EventDrivenModuleSchedulerImpl extends ModuleSchedulerImpl implements EventDrivenModuleScheduler {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(EventDrivenModuleSchedulerImpl.class);

	/**
	 * The local queue from which the scheduler consumes sensor events.
	 */
	private final EventQueueInternal localSensorEventQueue;

	/**
	 * Constructor.
	 * 
	 * @param moduleExecutable
	 *            the module whose execution should be scheduled.
	 */
	public EventDrivenModuleSchedulerImpl(ModuleExecutable<?, ?, ?, ?> moduleExecutable) {
		super(moduleExecutable);
		this.localSensorEventQueue = super.createLocalSensorEventQueue();
	}

	/**
	 * Schedules the execution of the module based on sensor events.
	 */
	@Override
	public void run() {
		while (super.continueRunning()) {
			try {
				eurema.Event eEvent = this.localSensorEventQueue.take();
				logger.debug("Processing sensor event " + EuremaEventTypeHierarchy.INSTANCE.getEEventAsString(eEvent)
						+ " from queue " + this.localSensorEventQueue.getName() + ".");
				// match eEvent against the triggering condition
				boolean conditionMatched = super.matchTriggeringCondition(eEvent);
				// if match, execute the module
				if (conditionMatched) {
					// synchronously execute the module.
					super.runExecutable();
				}
				// process the next event in the next run of the loop
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQueue getLocalSensorEventQueue() {
		return this.localSensorEventQueue;
	}

}
