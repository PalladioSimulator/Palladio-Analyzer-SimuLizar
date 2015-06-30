package de.mdelab.eurema.interpreter.execution.timer;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.mdelab.eurema.interpreter.Metamodel;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventType;
import de.mdelab.eurema.interpreter.execution.events.EventQueueInternal;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventTypeHierarchy;

/**
 * Implementation of the timer service for the EUREMA interpreter.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class TimerServiceImpl implements TimerService {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(TimerServiceImpl.class);

	/**
	 * The timer.
	 */
	private Timer timer;

	/**
	 * Constructor.
	 */
	public TimerServiceImpl() {
		boolean isDeamon = true;
		this.timer = new Timer("Timer Service", isDeamon);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createTimer(int periodInSec, EventQueueInternal timerEventQueue) {
		SendTimerEvent ste = new SendTimerEvent(periodInSec, timerEventQueue);
		this.timer.schedule(ste, periodInSec * 1000);
		logger.debug("Created timer expiring in " + periodInSec
				+ " seconds to send a timer event to the queue "
				+ timerEventQueue.getName());
	}

	/**
	 * Timer task that is scheduled after a period of time has elapsed and then,
	 * the task sends an event to a queue.
	 * 
	 * @author thomas
	 * @version 0.02
	 * 
	 */
	private class SendTimerEvent extends TimerTask {

		/**
		 * The queue to which the event should be sent.
		 */
		private EventQueueInternal timerEventQueue;

		/**
		 * The period of time in seconds.
		 */
		private int periodInSec;

		/**
		 * Constructor.
		 * 
		 * @param periodInSec
		 *            The period of time in seconds.
		 * @param timerEventQueue
		 *            The event queue to which the event is sent.
		 */
		private SendTimerEvent(int periodInSec,
				EventQueueInternal timerEventQueue) {
			this.periodInSec = periodInSec;
			this.timerEventQueue = timerEventQueue;
		}

		/**
		 * This method is scheduled for execution after the specified period of
		 * time has elapsed. It sends a timer event to the specified event
		 * queue.
		 */
		@Override
		public void run() {
			eurema.Event timerEvent =
					Metamodel.INSTANCE.getEuremaFactory().createEvent();
			timerEvent.setName("Period of " + this.periodInSec
					+ " seconds has elapsed.");
			eurema.EventType timerEventType =
					EuremaEventTypeHierarchy.INSTANCE
							.getEEventType(EuremaEventType.TIMER);
			timerEvent.setType(timerEventType);
			this.timerEventQueue.add(timerEvent);
			logger.debug("A timer event notifying about the expiration of "
					+ this.periodInSec + " seconds has been sent to the queue "
					+ this.timerEventQueue.getName());
		}
	}

}
