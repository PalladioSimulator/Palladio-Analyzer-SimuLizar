package de.mdelab.eurema.interpreter.maintenance;

import java.util.LinkedList;
import java.util.List;

import de.mdelab.eurema.interpreter.maintenance.change.UpdateChangeEvent;

/**
 * Queue of events about changes of the LD due to maintenance, that is, due to
 * enacting an update developed offline to the self-adaptive system.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class UpdateChangeEventQueue {

	/**
	 * The instance to obtain the queues.
	 */
	public static final UpdateChangeEventQueue INSTANCE = new UpdateChangeEventQueue();

	/**
	 * The queue of all change events in the order as they occurred.
	 */
	private List<UpdateChangeEvent> queue;

	/**
	 * Constructor.
	 */
	private UpdateChangeEventQueue() {
		this.queue = new LinkedList<>();
	}

	/**
	 * @return the queue of all change events in the order as they have
	 *         occurred.
	 */
	public List<UpdateChangeEvent> getQueue() {
		return this.queue;
	}
	
	/**
	 * Clears the queue of all change events.
	 */
	public void clearQueue() {
		this.queue.clear();
	}

}
