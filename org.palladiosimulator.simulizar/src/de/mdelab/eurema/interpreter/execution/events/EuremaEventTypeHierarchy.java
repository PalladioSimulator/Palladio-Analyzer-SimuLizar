package de.mdelab.eurema.interpreter.execution.events;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.Metamodel;

/**
 * The default hierarchy of all event types predefined by EUREMA.
 * 
 * @author thomas vogel
 * @version 0.03
 * 
 */
public class EuremaEventTypeHierarchy {

	/**
	 * The singleton instance of the event type hierarchy.
	 */
	public final static EuremaEventTypeHierarchy INSTANCE = new EuremaEventTypeHierarchy();

	/**
	 * Indicates whether the default event type hierarchy has been initialized
	 * or not. It can only be initialized once.
	 */
	private boolean initialized = false;

	/**
	 * EUREMA event type
	 */
	private eurema.EventType eEurema;
	/**
	 * EXECUTION event type
	 */
	private eurema.EventType eExecution;
	/**
	 * QUIESCENCE event type
	 */
	private eurema.EventType eQuiescence;
	/**
	 * FLD event type
	 */
	private eurema.EventType eFld;
	/**
	 * BEFORE event type
	 */
	private eurema.EventType eBefore;
	/**
	 * AFTER event type
	 */
	private eurema.EventType eAfter;
	/**
	 * ON_TRANSITION event type
	 */
	private eurema.EventType eOnTransition;
	/**
	 * LD event type
	 */
	private eurema.EventType eLd;
	/**
	 * CHANGE event type
	 */
	private eurema.EventType eChange;
	/**
	 * TIMER event type
	 */
	private eurema.EventType eTimer;

	/**
	 * Constructor.
	 */
	private EuremaEventTypeHierarchy() {

	}

	/**
	 * Initializes the default event typer hierarchy, which can only be done
	 * once. Multiple initializations result in exceptions.
	 * 
	 * @return the root event type of the event type hierarchy prescribed by
	 *         EUREMA.
	 */
	public eurema.EventType initialize() {
		if (this.initialized) {
			throw new EuremaInterpreterException(
					"Failure in initializing the EUREMA event type hierarchy: the hierarchy has already been initialized.");
		}

		eurema.EuremaFactory eFactory = Metamodel.INSTANCE.getEuremaFactory();

		// EUREMA event type
		this.eEurema = eFactory.createEventType();
		this.eEurema.setType(EuremaEventType.EUREMA.getType());

		// EUREMA.EXECUTION event type
		this.eExecution = eFactory.createEventType();
		this.eExecution.setType(EuremaEventType.EXECUTION.getType());
		this.eExecution.setSuperType(this.eEurema);

		// EUREMA.EXECUTION.FLD
		this.eFld = eFactory.createEventType();
		this.eFld.setType(EuremaEventType.FLD.getType());
		this.eFld.setSuperType(this.eExecution);

		// EUREMA.EXECUTION.FLD.ON_TRANSITION
		this.eOnTransition = eFactory.createEventType();
		this.eOnTransition.setType(EuremaEventType.ON_TRANSITION.getType());
		this.eOnTransition.setSuperType(this.eFld);

		// EUREMA.EXECUTION.FLD.AFTER
		this.eAfter = eFactory.createEventType();
		this.eAfter.setType(EuremaEventType.AFTER.getType());
		this.eAfter.setSuperType(this.eFld);

		// EUREMA.EXECUTION.FLD.BEFORE
		this.eBefore = eFactory.createEventType();
		this.eBefore.setType(EuremaEventType.BEFORE.getType());
		this.eBefore.setSuperType(this.eFld);

		// EUREMA.EXECUTION.LD
		this.eLd = eFactory.createEventType();
		this.eLd.setType(EuremaEventType.LD.getType());
		this.eLd.setSuperType(this.eExecution);

		// EUREMA.EXECUTION.LD.CHANGE
		this.eChange = eFactory.createEventType();
		this.eChange.setType(EuremaEventType.CHANGE.getType());
		this.eChange.setSuperType(this.eLd);

		// EUREMA.EXECUTION.LD.TIMER
		this.eTimer = eFactory.createEventType();
		this.eTimer.setType(EuremaEventType.TIMER.getType());
		this.eTimer.setSuperType(this.eLd);

		// EUREMA.EXECUTION.QUIESCENCE
		this.eQuiescence = eFactory.createEventType();
		this.eQuiescence.setType(EuremaEventType.QUIESCENCE.getType());
		this.eQuiescence.setSuperType(this.eExecution);

		this.initialized = true;
		
		return this.eEurema;
	}

	/**
	 * Returns the {@code eurema.EventType} of the given {@code EuremaEventType}
	 * .
	 * 
	 * @param type
	 *            the {@code EuremaEventType}
	 * @return the {@code eurema.EventType}
	 */
	public eurema.EventType getEEventType(EuremaEventType type) {
		switch (type) {
		case AFTER:
			return this.eAfter;
		case BEFORE:
			return this.eBefore;
		case CHANGE:
			return this.eChange;
		case EUREMA:
			return this.eEurema;
		case EXECUTION:
			return this.eExecution;
		case FLD:
			return this.eFld;
		case LD:
			return this.eLd;
		case ON_TRANSITION:
			return this.eOnTransition;
		case QUIESCENCE:
			return this.eQuiescence;
		case TIMER:
			return this.eTimer;
		default:
			return null;
		}
	}

	/**
	 * Returns a String representation of an event.
	 * 
	 * @param eEvent
	 *            the event.
	 * @return the String representation of the event.
	 */
	public String getEEventAsString(eurema.Event eEvent) {
		StringBuffer sb = new StringBuffer(eEvent.getType().getType());
		String eEventName = eEvent.getName();
		if (eEventName != null && !(eEventName.equals(""))) {
			sb.append("[" + eEventName + "]");
		}
		return sb.toString();
	}

}
