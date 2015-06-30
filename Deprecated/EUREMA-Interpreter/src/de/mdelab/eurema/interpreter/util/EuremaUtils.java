package de.mdelab.eurema.interpreter.util;

import org.eclipse.emf.common.util.EList;

/**
 * Utility functionality for EUREMA.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class EuremaUtils {

	/**
	 * Returns a String representation of the triggering condition of the module
	 * to be scheduled.
	 * 
	 * @param eTrigger
	 *            the triggering condition
	 * 
	 * @return a String representation of the triggering condition of the module
	 *         to be scheduled.
	 */
	public static String triggeringConditionToString(eurema.Trigger eTrigger) {
		StringBuffer condition = new StringBuffer("");

		// <events>
		EList<eurema.Event> events = eTrigger.getEvents();
		int numberOfEvents = events.size();
		int counter = 0;
		for (eurema.Event eEvent : events) {
			counter++;
			condition.append(EuremaUtils.eventToString(eEvent));
			if (counter < numberOfEvents) {
				condition.append(",");
			} else {
				condition.append(";");
			}
		}

		// <period>
		int period = eTrigger.getPeriod();
		if (period > 0) {
			condition.append(period + "s;");
		}

		// MegamodelModuleTrigger
		if (eTrigger instanceof eurema.MegamodelModuleTrigger) {
			eurema.MegamodelModuleTrigger megamodelModuleTrigger = (eurema.MegamodelModuleTrigger) eTrigger;
			eurema.InitialOperation eInitialOperation = megamodelModuleTrigger
					.getInitialOperation();
			condition.append(eInitialOperation.getName() + ";");
		} else if (eTrigger instanceof eurema.SoftwareModuleTrigger) {
			// SoftwareModuleTrigger
			eurema.SoftwareModuleTrigger softwareModuleTrigger = (eurema.SoftwareModuleTrigger) eTrigger;
			if (softwareModuleTrigger.isIsNative()) {
				condition.append("native");
			} else {
				condition.append(softwareModuleTrigger.getModule()
						.getImplementation()
						+ "."
						+ softwareModuleTrigger.getExecutionMethod() + ";");
			}
		} else {
			condition.append("UNKNOWN TRIGGER.");
		}
		return condition.toString();
	}

	/**
	 * Returns a String representation of the event
	 * 
	 * @param eEvent
	 *            the event
	 * @return the String representation of the event
	 */
	public static String eventToString(eurema.Event eEvent) {
		StringBuffer eventString = new StringBuffer(eEvent.getType().getType());
		if (eEvent.getName() != null && !(eEvent.getName().equals(""))) {
			eventString.append("[" + eEvent.getName() + "]");
		}
		return eventString.toString();
	}

	/**
	 * Returns the fully qualified name of a module, that is,
	 * <tt>MegamodelModule.name:MegamodelModule.megamodel.name</tt> or
	 * <tt>SoftwareModule.name:SoftwareModule.software.name</tt>.
	 * 
	 * @param module
	 *            the module
	 * @return the fully qualified name of the module
	 */
	public static String getFqModuleName(eurema.Module module) {
		String name = "";
		if (module != null) {
			name = module.getName() + ":";
			if (module instanceof eurema.MegamodelModule) {
				eurema.Megamodel megamodel = ((eurema.MegamodelModule) module)
						.getMegamodel();
				if (megamodel != null) {
					name = name + megamodel.getName();
				}
			} else if (module instanceof eurema.SoftwareModule) {
				eurema.Software software = ((eurema.SoftwareModule) module)
						.getSoftware();
				if (software != null) {
					name = name + software.getName();
				}
			}
			// name = name + " (ID = " + module.getUid() + ")";
		} else {
			name = "null";
		}
		return name;
	}
}
