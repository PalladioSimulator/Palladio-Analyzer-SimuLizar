package de.mdelab.eurema.interpreter.execution.timer;

import de.mdelab.eurema.interpreter.execution.events.EventQueueInternal;

/**
 * Timer service for the EUREMA interpreter.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface TimerService {

	/**
	 * The timer service instance.
	 */
	public final TimerService INSTANCE = new TimerServiceImpl();

	/**
	 * Creates a timer that sends a timer event (the event is an EUREMA event of
	 * type {@code EventTypeHierarchy#getETimer()}; cf. also
	 * {@code EuremaEventType#TIMER}) to the <code>eventQueue</code> after
	 * <code>periodInSec</code> seconds have expired.
	 * 
	 * @param periodInSec
	 *            the period in seconds to elapse before an event is sent to the
	 *            queue.
	 * @param eventQueue
	 *            the queue to which the event is sent when
	 *            <code>periodInSec</code> seconds have elapsed.
	 */
	public void createTimer(int periodInSec, EventQueueInternal eventQueue);

}
