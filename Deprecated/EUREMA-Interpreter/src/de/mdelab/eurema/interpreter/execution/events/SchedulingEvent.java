package de.mdelab.eurema.interpreter.execution.events;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Events used for scheduling modules. This implementation allows sorting of
 * events based on priority (events of EUREMA-defined types have a higher
 * priority than events of user-defined types. See also the default event type
 * hierarchy {@code EventTypeHierarchy} defining a inheritance tree of event
 * types while a subtree is dedicated to EUREMA event types. The root of this
 * subtree is the 'EUREMA' event type ({@code EuremaEventType#EUREMA})) and if
 * two events have the same priority, <i>FIFO</i> is applied.
 * 
 * <p>
 * See:
 * <code>http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/PriorityBlockingQueue.html</code>
 * </P>
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public class SchedulingEvent implements Comparable<SchedulingEvent> {

	/**
	 * Atomic long to realize the priority queue.
	 */
	private final static AtomicLong seq = new AtomicLong();
	/**
	 * Same priority, use FIFO. Therefore, a sequence number is used.
	 */
	private final long seqNum;
	/**
	 * The contained {@code eurema.Event}.
	 */
	private eurema.Event event;

	/**
	 * Constructor.
	 * 
	 * @param event
	 *            The contained {@code eurema.Event}.
	 */
	public SchedulingEvent(eurema.Event event) {
		this.seqNum = seq.getAndIncrement();
		this.event = event;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * EUREMA events have a higher priority than non-EUREMA events.
	 */
	@Override
	public int compareTo(SchedulingEvent other) {
		eurema.Event otherEvent = other.getEEvent();
		eurema.EventType otherEventType =
				(eurema.EventType) otherEvent.getType();
		boolean isOtherEventTypeAnEuremaEventType =
				this.isEuremaEventType(otherEventType);
		eurema.EventType thisEventType =
				(eurema.EventType) this.event.getType();
		boolean isThisEventTypeAnEuremaEventType =
				this.isEuremaEventType(thisEventType);

		int result = 0;
		if ((isOtherEventTypeAnEuremaEventType & isThisEventTypeAnEuremaEventType)
				|| (!isOtherEventTypeAnEuremaEventType & !isThisEventTypeAnEuremaEventType)) {
			result = 0;
			// same priority, use FIFO
			if (otherEvent != this.event) {
				result = (this.seqNum < other.seqNum ? -1 : 1);
			}
		} else if (isOtherEventTypeAnEuremaEventType
				& !isThisEventTypeAnEuremaEventType) {
			result = -1;
		} else if (!isOtherEventTypeAnEuremaEventType
				& isThisEventTypeAnEuremaEventType) {
			result = 1;
		}
		return result;
	}

	/**
	 * Returns the contained {@code eurema.Event}.
	 * 
	 * @return the contained {@code eurema.Event}.
	 */
	public eurema.Event getEEvent() {
		return this.event;
	}

	/**
	 * Checks whether an event type is an EUREMA event type predefined in the
	 * event type hierarchy (cf. {@code EventTypeHierarchy} and
	 * {@code EuremaEventType}).
	 * 
	 * @param eventType
	 *            the event type to be checked.
	 * @return <code>true</code> if the event type is an EUREMA event type, else
	 *         <code>false</code>.
	 */
	private boolean isEuremaEventType(eurema.EventType eventType) {
		String type = eventType.getType();
		boolean isEuremaEvent = type.equals(EuremaEventType.EUREMA.getType());
		if (!isEuremaEvent) {
			eurema.EventType superType = eventType.getSuperType();
			if (superType != null) {
				isEuremaEvent = this.isEuremaEventType(superType);
			}
		}
		return isEuremaEvent;
	}

}
