package de.mdelab.eurema.interpreter.execution.events;

import de.mdelab.eurema.interpreter.EventQueue;

/**
 * Blocking event queue for internal usage by the EUREMA interpreter. Provides
 * direct access to the event queue.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface EventQueueInternal extends EventQueue {

	/**
	 * The name of the event queue.
	 * 
	 * @return the name of the event queue.
	 */
	public String getName();

	/**
	 * As specified by {@code BlockingQueue#take()}: Retrieves and removes the
	 * head of this queue, waiting if necessary until an element becomes
	 * available.
	 * 
	 * @return the head of this queue
	 * @throws InterruptedException
	 *             if interrupted while waiting
	 */
	public eurema.Event take() throws InterruptedException;
}
