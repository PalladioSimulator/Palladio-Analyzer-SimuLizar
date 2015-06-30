package de.mdelab.eurema.interpreter.execution.module.scheduler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.mdelab.eurema.interpreter.EventQueue;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventType;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventTypeHierarchy;
import de.mdelab.eurema.interpreter.execution.events.EventQueueInternal;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;
import de.mdelab.eurema.interpreter.execution.timer.TimerService;

/**
 * Combined event-driven and periodic scheduler for a module at layer 1 (wrapped
 * by a {@code ModuleExecutable}) of the layered architecture that senses a
 * layer 0 module. The scheduler consumes events from individual queues of the
 * module, i.e., each module at layer 1 has its own sensor event queue, timer
 * event queue, and its own scheduler.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class EventPeriodicModuleSchedulerImpl extends ModuleSchedulerImpl implements EventPeriodicModuleScheduler {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(EventPeriodicModuleSchedulerImpl.class);

	/**
	 * The local queue from which the scheduler consumes sensor events.
	 */
	private final EventQueueInternal localSensorEventQueue;

	/**
	 * The local queue from which the scheduler consumes timer events.
	 */
	private final EventQueueInternal localTimerEventQueue;

	/**
	 * Constructor.
	 * 
	 * @param moduleExecutable
	 *            the module whose execution should be scheduled..
	 */
	public EventPeriodicModuleSchedulerImpl(ModuleExecutable<?, ?, ?, ?> moduleExecutable) {
		super(moduleExecutable);
		this.localSensorEventQueue = super.createLocalSensorEventQueue();
		this.localTimerEventQueue = super.createLocalTimerEventQueueName();
	}

	/**
	 * Schedules the execution of the module based on sensor events and timer
	 * events.
	 */
	@Override
	public void run() {
		// create timer event and add it to the queue to initiate the first
		// execution of the megamodel module
		eurema.Event initialTimerEvent = super.createTimerEvent("Initial Timer Event");
		this.localTimerEventQueue.add(initialTimerEvent);
		// now, there is one timer event in the queue to be processed

		eurema.Trigger eTrigger = super.moduleExecutable.getETrigger();
		int periodInSec = eTrigger.getPeriod();

		while (super.continueRunning()) {
			try {
				eurema.Event eSensorEvent = this.localSensorEventQueue.take();
				logger.debug(
						"Processing sensor event " + EuremaEventTypeHierarchy.INSTANCE.getEEventAsString(eSensorEvent)
								+ " from queue " + this.localSensorEventQueue.getName() + ".");
				// match eEvent against the triggering condition
				boolean conditionMatched = super.matchTriggeringCondition(eSensorEvent);
				// if match, execute the module if a timer event is available
				// (period has elapsed)
				if (conditionMatched) {
					// check timer event: timer event notifies that the period
					// has elapsed.
					eurema.Event eTimerEvent = this.localTimerEventQueue.take();
					// the event must have the correct type
					assert eTimerEvent.getType()
							.equals(EuremaEventTypeHierarchy.INSTANCE.getEEventType(EuremaEventType.TIMER));
					logger.debug(
							"Processing timer event " + EuremaEventTypeHierarchy.INSTANCE.getEEventAsString(eTimerEvent)
									+ " from queue " + this.localTimerEventQueue.getName());

					// synchronously execute the module.
					super.runExecutable();

					// create timer to initiate the next execution of the module
					// after <code>periodInSec</code> seconds.
					TimerService.INSTANCE.createTimer(periodInSec, this.localTimerEventQueue);

					// process the next sensor event in the next run of the loop
				}
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQueue getLocalTimerEventQueue() {
		return this.localTimerEventQueue;
	}

}
