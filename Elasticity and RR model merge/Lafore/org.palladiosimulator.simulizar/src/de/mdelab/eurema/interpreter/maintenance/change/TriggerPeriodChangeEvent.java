package de.mdelab.eurema.interpreter.maintenance.change;

/**
 * Change event about a change of the period of a trigger.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class TriggerPeriodChangeEvent extends UpdateChangeEvent {

	/**
	 * The trigger whose period has changed.
	 */
	private eurema.Trigger trigger;

	/**
	 * The old value of the trigger's period.
	 */
	private int oldPeriod;

	/**
	 * The new value of the trigger's period.
	 */
	private int newPeriod;

	/**
	 * Constructor.
	 * 
	 * @param trigger
	 *            The trigger whose period has changed.
	 * @param oldPeriod
	 *            The old value of the trigger's period.
	 * @param newPeriod
	 *            The new value of the trigger's period.
	 */
	public TriggerPeriodChangeEvent(eurema.Trigger trigger, int oldPeriod,
			int newPeriod) {
		super();
		this.trigger = trigger;
		this.oldPeriod = oldPeriod;
		this.newPeriod = newPeriod;
	}

	/**
	 * @return The trigger whose period has changed.
	 */
	public eurema.Trigger getTrigger() {
		return this.trigger;
	}

	/**
	 * @return The old value of the trigger's period.
	 */
	public int getOldPeriod() {
		return this.oldPeriod;
	}

	/**
	 * @return The new value of the trigger's period.
	 */
	public int getNewPeriod() {
		return this.newPeriod;
	}

}
