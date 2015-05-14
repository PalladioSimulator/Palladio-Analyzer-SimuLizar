package de.mdelab.eurema.interpreter.execution.events;

/**
 * Enumeration of all event types predefined by EUREMA.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public enum EuremaEventType {

	/**
	 * EUREMA
	 */
	EUREMA("EUREMA"),
	/**
	 * EXECUTION
	 */
	EXECUTION("EXECUTION"),
	/**
	 * QUIESCENCE
	 */
	QUIESCENCE("QUIESCENCE"),
	/**
	 * FLD
	 */
	FLD("FLD"),
	/**
	 * BEFORE
	 */
	BEFORE("BEFORE"),
	/**
	 * AFTER
	 */
	AFTER("AFTER"),
	/**
	 * ON_TRANSITION
	 */
	ON_TRANSITION("ON_TRANSITION"),
	/**
	 * LD
	 */
	LD("LD"),
	/**
	 * CHANGE
	 */
	CHANGE("CHANGE"),
	/**
	 * TIMER
	 */
	TIMER("TIMER");

	/**
	 * Event type as a String.
	 */
	private String type;

	/**
	 * Constructor.
	 * 
	 * @param type
	 *            event type as a String.
	 */
	private EuremaEventType(String type) {
		this.type = type;
	}

	/**
	 * Returns the event type as a String.
	 * 
	 * @return the event type as a String.
	 */
	public String getType() {
		return this.type;
	}
}
