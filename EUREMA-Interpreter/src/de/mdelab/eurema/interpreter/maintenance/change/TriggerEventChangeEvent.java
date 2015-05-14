package de.mdelab.eurema.interpreter.maintenance.change;

import java.util.List;

/**
 * Change event about the removal or addition of events from or to a trigger.
 * 
 * @author thomas vogel
 * @version 0.1
 * 
 */
public class TriggerEventChangeEvent extends UpdateChangeEvent {

	/**
	 * The trigger from/to which events are removed/added.
	 */
	private eurema.Trigger trigger;

	/**
	 * Events added to the trigger.
	 */
	private List<eurema.Event> addedEvents;

	/**
	 * Events removed from the trigger.
	 */
	private List<eurema.Event> removedEvents;

	/**
	 * Constructor.
	 * 
	 * @param trigger
	 *            The trigger from/to which events are removed/added.
	 * @param addedEvents
	 *            Events added to the trigger.
	 * @param removedEvents
	 *            Events removed from the trigger.
	 */
	public TriggerEventChangeEvent(eurema.Trigger trigger,
			List<eurema.Event> addedEvents, List<eurema.Event> removedEvents) {
		super();
		this.trigger = trigger;
		this.addedEvents = addedEvents;
		this.removedEvents = removedEvents;
	}

	/**
	 * @return The trigger from/to which events are removed/added.
	 */
	public eurema.Trigger getTrigger() {
		return this.trigger;
	}

	/**
	 * @return Events added to the trigger.
	 */
	public List<eurema.Event> getAddedEvents() {
		return this.addedEvents;
	}

	/**
	 * @return Events removed from the trigger.
	 */
	public List<eurema.Event> getRemovedEvents() {
		return this.removedEvents;
	}

}
