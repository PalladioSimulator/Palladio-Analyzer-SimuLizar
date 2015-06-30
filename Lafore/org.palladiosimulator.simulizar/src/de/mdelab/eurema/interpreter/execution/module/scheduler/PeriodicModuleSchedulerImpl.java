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
 * Periodic scheduler for a module (wrapped by a {@code ModuleExecutable}) at
 * layer 1 of the layered architecture that senses a layer 0 module. The
 * scheduler consumes timer events from an individual queue of the module, i.e.,
 * each module at layer 1 has its own timer event queue and its own scheduler.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class PeriodicModuleSchedulerImpl extends ModuleSchedulerImpl implements PeriodicModuleScheduler {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(PeriodicModuleSchedulerImpl.class);

	/**
	 * The local queue from which the scheduler consumes timer events.
	 */
	private final EventQueueInternal localTimerEventQueue;

	/**
	 * Constructor.
	 * 
	 * @param moduleExecutable
	 *            the module whose execution should be scheduled.
	 */
	public PeriodicModuleSchedulerImpl(ModuleExecutable<?, ?, ?, ?> moduleExecutable) {
		super(moduleExecutable);
		this.localTimerEventQueue = super.createLocalTimerEventQueueName();
	}

	/**
	 * Schedules the module based on timer events.
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
				eurema.Event eEvent = this.localTimerEventQueue.take();
				// eEvent has to be a timer event
				assert eEvent.getType().equals(EuremaEventTypeHierarchy.INSTANCE.getEEventType(EuremaEventType.TIMER));
				logger.debug("Processing timer event " + EuremaEventTypeHierarchy.INSTANCE.getEEventAsString(eEvent)
						+ " from queue " + this.localTimerEventQueue.getName());

				// synchronously execute the module.
				super.runExecutable();

				// create timer to initiate the next execution of the module
				// after <code>periodInSec</code> seconds.
				TimerService.INSTANCE.createTimer(periodInSec, this.localTimerEventQueue);
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
	public EventQueue getLocalTimerEventQueue() {
		return this.localTimerEventQueue;
	}

}
