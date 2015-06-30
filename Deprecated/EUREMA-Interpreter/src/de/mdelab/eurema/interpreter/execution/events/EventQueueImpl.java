package de.mdelab.eurema.interpreter.execution.events;

import java.util.concurrent.PriorityBlockingQueue;

import de.mdelab.eurema.interpreter.EventQueue;

/**
 * Implementation of a blocking event queue.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class EventQueueImpl implements EventQueue, EventQueueInternal {

	/**
	 * The priority blocking event queue.
	 */
	private PriorityBlockingQueue<SchedulingEvent> queue;

	/**
	 * The name of the event queue.
	 */
	private String name;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the name of the event queue.
	 */
	public EventQueueImpl(String name) {
		this.queue = new PriorityBlockingQueue<SchedulingEvent>();
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(eurema.Event event) {
		boolean result = this.queue.add(new SchedulingEvent(event));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public eurema.Event take() throws InterruptedException {
		SchedulingEvent schedulingEvent = this.queue.take();
		return schedulingEvent.getEEvent();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}

}
