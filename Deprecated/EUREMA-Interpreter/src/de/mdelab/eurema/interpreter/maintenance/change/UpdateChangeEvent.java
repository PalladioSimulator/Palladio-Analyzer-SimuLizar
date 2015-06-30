package de.mdelab.eurema.interpreter.maintenance.change;

import java.util.UUID;

/**
 * Event that represents a change of the <i>Layer Diagram</i> (LD) caused by
 * maintenance, that is, enacting an update developed offline to the
 * self-adaptive system.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class UpdateChangeEvent {

	/**
	 * Identifier of the change event.
	 */
	private String id;

	/**
	 * Time stamp when the change occurred.
	 */
	private long timestamp;

	/**
	 * Constructs a change event.
	 */
	public UpdateChangeEvent() {
		super();
		this.timestamp = System.currentTimeMillis();
		this.id = "UpdateChangeEvent_" + this.timestamp + "_"
				+ UUID.randomUUID().toString();

	}

	/**
	 * @return the id of the change event.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the timestamp when the change has occurred.
	 */
	public long getTimestamp() {
		return this.timestamp;
	}

}
