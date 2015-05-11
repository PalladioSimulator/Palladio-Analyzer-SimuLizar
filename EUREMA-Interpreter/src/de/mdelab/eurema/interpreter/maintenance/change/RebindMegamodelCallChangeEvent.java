package de.mdelab.eurema.interpreter.maintenance.change;

/**
 * Change event about the rebinding of a megamodel call, that is, the target of
 * the call is changed to another megamodel module.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class RebindMegamodelCallChangeEvent extends UpdateChangeEvent {

	/**
	 * The megamodel call whose target is rebound.
	 */
	private eurema.MegamodelCall megamodelCall;

	/**
	 * The old target of the megamodel call.
	 */
	private eurema.MegamodelModule oldTarget;

	/**
	 * The new target of the megamodel call.
	 */
	private eurema.MegamodelModule newTarget;

	/**
	 * Constructor.
	 * 
	 * @param megamodelCall
	 *            The megamodel call whose target is rebound.
	 * @param oldTarget
	 *            The old target of the megamodel call.
	 * @param newTarget
	 *            The new target of the megamodel call.
	 */
	public RebindMegamodelCallChangeEvent(eurema.MegamodelCall megamodelCall,
			eurema.MegamodelModule oldTarget, eurema.MegamodelModule newTarget) {
		super();
		this.megamodelCall = megamodelCall;
		this.oldTarget = oldTarget;
		this.newTarget = newTarget;
	}

	/**
	 * @return The megamodel call whose target is rebound.
	 */
	public eurema.MegamodelCall getMegamodelCall() {
		return megamodelCall;
	}

	/**
	 * @return The old target of the megamodel call.
	 */
	public eurema.MegamodelModule getOldTarget() {
		return oldTarget;
	}

	/**
	 * @return The new target of the megamodel call.
	 */
	public eurema.MegamodelModule getNewTarget() {
		return newTarget;
	}

}
