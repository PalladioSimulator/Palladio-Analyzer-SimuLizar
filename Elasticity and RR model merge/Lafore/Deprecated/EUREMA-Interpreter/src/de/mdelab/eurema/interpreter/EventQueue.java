package de.mdelab.eurema.interpreter;

/**
 * Event queue to which the adaptable software adds sensor events.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface EventQueue {

	/**
	 * Adds a sensor event to the event queue.
	 * 
	 * @param event
	 *            the sensor event
	 * @return as specified by {@code Collection#add(Object)}
	 */
	public boolean add(eurema.Event event);

}
