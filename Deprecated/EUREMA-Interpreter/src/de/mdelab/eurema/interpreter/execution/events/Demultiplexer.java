package de.mdelab.eurema.interpreter.execution.events;

import java.util.List;

/**
 * Demultiplexes events from the global sensor event queue to local event
 * queues. Layer 1 modules with triggering conditions have individual event
 * queues and schedulers.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface Demultiplexer extends Runnable {

	/**
	 * Returns an unmodifiable list of all sensor events that have occurred over
	 * time.
	 * 
	 * @return an unmodifiable list of all sensor events.
	 */
	public List<eurema.Event> getAllSensorEvents();

	/**
	 * Shuts down the demultiplexer.
	 */
	public void shutDown();

}
