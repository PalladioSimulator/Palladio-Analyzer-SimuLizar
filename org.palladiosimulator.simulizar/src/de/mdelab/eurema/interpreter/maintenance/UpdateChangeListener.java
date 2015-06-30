package de.mdelab.eurema.interpreter.maintenance;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.mdelab.eurema.interpreter.maintenance.change.LayerAddedChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.LayerRemovedChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.ModuleAddedChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.ModuleRemovedChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.RebindMegamodelCallChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.RebindModelOperationChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.TriggerEventChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.TriggerPeriodChangeEvent;
import de.mdelab.eurema.interpreter.maintenance.change.UpdateChangeEvent;
import de.mdelab.eurema.interpreter.util.EuremaUtils;

/**
 * Listener to observe changes made to the <i>Layer Diagram</i> (LD) by
 * maintenance, that is, an integration rule adjusts the LD to enact an update
 * developed offline.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class UpdateChangeListener extends EContentAdapter {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(UpdateChangeListener.class);

	/**
	 * Constructor.
	 */
	UpdateChangeListener() {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * Observes changes to the LD, maps them to {@link UpdateChangeEvent}s and
	 * adds them to the {@link UpdateChangeEventQueue}.
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		Object notifier = notification.getNotifier();

		if (notifier instanceof eurema.Architecture) {
			// Layer has been added or removed.
			this.createAndAddLayerChangeEvent((eurema.Architecture) notifier, notification);
		} else if (notifier instanceof eurema.Layer) {
			// Module has been added or removed.
			this.createAndAddModuleChangeEvent((eurema.Layer) notifier, notification);
		} else if (notifier instanceof eurema.MegamodelCall) {
			// Binding of the call to a megamodel module has changed.
			this.createAndAddRebindMegamodelCallChangeEvent((eurema.MegamodelCall) notifier, notification);
		} else if (notifier instanceof eurema.ModelOperation) {
			// Binding of the operation to a software module has changed.
			this.createAndAddRebindModelOperationChangeEvent((eurema.ModelOperation) notifier, notification);
		} else if (notifier instanceof eurema.Trigger) {
			// Trigger of a module has been modified.
			this.createAndAddTriggerChangeEvent((eurema.Trigger) notifier, notification);
		}

	}

	/**
	 * Creates and adds events about the removal or addition of layers from or
	 * to the architecture.
	 * 
	 * @param notifier
	 *            the architecture notifying about the change
	 * @param notification
	 *            the notification of the change
	 */
	@SuppressWarnings("unchecked")
	private void createAndAddLayerChangeEvent(eurema.Architecture notifier, Notification notification) {
		int featureID = notification.getFeatureID(eurema.Architecture.class);
		if (featureID == eurema.EuremaPackage.ARCHITECTURE__LAYERS) {
			// layer is added or removed from the architecture
			int eventType = notification.getEventType();
			if (eventType == Notification.REMOVE) {
				Object oldValue = notification.getOldValue();
				eurema.Layer removedLayer = (eurema.Layer) oldValue;
				LayerRemovedChangeEvent event = new LayerRemovedChangeEvent(removedLayer);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
				logger.debug("Layer #" + removedLayer.getNumber() + " called \"" + removedLayer.getName()
						+ "\" has been removed from the architecture.");
			} else if (eventType == Notification.REMOVE_MANY) {
				Object oldValues = notification.getOldValue();
				List<eurema.Layer> removedLayers = (List<eurema.Layer>) oldValues;
				for (eurema.Layer removedLayer : removedLayers) {
					LayerRemovedChangeEvent event = new LayerRemovedChangeEvent(removedLayer);
					UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
					logger.debug("Layer #" + removedLayer.getNumber() + " called \"" + removedLayer.getName()
							+ "\" has been removed from the architecture.");
				}
			} else if (eventType == Notification.ADD) {
				Object newValue = notification.getNewValue();
				eurema.Layer addedLayer = (eurema.Layer) newValue;
				LayerAddedChangeEvent event = new LayerAddedChangeEvent(addedLayer);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
				logger.debug("Layer #" + addedLayer.getNumber() + " called \"" + addedLayer.getName()
						+ "\" has been added to the architecture.");
			} else if (eventType == Notification.ADD_MANY) {
				Object newValues = notification.getNewValue();
				List<eurema.Layer> addedLayers = (List<eurema.Layer>) newValues;
				for (eurema.Layer addedLayer : addedLayers) {
					LayerAddedChangeEvent event = new LayerAddedChangeEvent(addedLayer);
					UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
					logger.debug("Layer #" + addedLayer.getNumber() + " called \"" + addedLayer.getName()
							+ "\" has been added to the architecture.");
				}
			}
		}
	}

	/**
	 * Creates and adds events about the removal or addition of modules from or
	 * to a layer of the architecture.
	 * 
	 * @param notifier
	 *            the layer from/to which a module has been removed/added and
	 *            that notifies about the change
	 * @param notification
	 *            the notification of the change
	 */
	@SuppressWarnings("unchecked")
	private void createAndAddModuleChangeEvent(eurema.Layer notifier, Notification notification) {
		int featureID = notification.getFeatureID(eurema.Layer.class);
		if (featureID == eurema.EuremaPackage.LAYER__MODULES) {
			// module is added or removed from the layer
			int eventType = notification.getEventType();
			boolean isRemoved;
			if (eventType == Notification.REMOVE) {
				isRemoved = true;
				Object oldValue = notification.getOldValue();
				eurema.Module removedModule = (eurema.Module) oldValue;
				ModuleRemovedChangeEvent event = new ModuleRemovedChangeEvent(removedModule, notifier);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
				this.logModuleChange(notifier, removedModule, isRemoved);
			} else if (eventType == Notification.REMOVE_MANY) {
				isRemoved = true;
				Object oldValues = notification.getOldValue();
				List<eurema.Module> removedModules = (List<eurema.Module>) oldValues;
				for (eurema.Module removedModule : removedModules) {
					ModuleRemovedChangeEvent event = new ModuleRemovedChangeEvent(removedModule, notifier);
					UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
					this.logModuleChange(notifier, removedModule, isRemoved);
				}
			} else if (eventType == Notification.ADD) {
				isRemoved = false;
				Object newValue = notification.getNewValue();
				eurema.Module addedModule = (eurema.Module) newValue;
				ModuleAddedChangeEvent event = new ModuleAddedChangeEvent(addedModule, notifier);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
				this.logModuleChange(notifier, addedModule, isRemoved);
			} else if (eventType == Notification.ADD_MANY) {
				isRemoved = false;
				Object newValues = notification.getNewValue();
				List<eurema.Module> addedModules = (List<eurema.Module>) newValues;
				for (eurema.Module addedModule : addedModules) {
					ModuleAddedChangeEvent event = new ModuleAddedChangeEvent(addedModule, notifier);
					UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
					this.logModuleChange(notifier, addedModule, isRemoved);
				}
			}
		}
	}

	/**
	 * Logs the removal or addition of a module.
	 * 
	 * @param notifier
	 *            the layer from/to which a module is removed/added.
	 * @param module
	 *            the removed/added module
	 * @param isRemoved
	 *            <code>true</code> if the module has been removed from the
	 *            layer, <code>false</code> if the module has been added to the
	 *            layer.
	 */
	private void logModuleChange(eurema.Layer notifier, eurema.Module module, boolean isRemoved) {
		String verb = "";
		if (isRemoved) {
			verb = "removed from";
		} else {
			verb = "added to";
		}

		if (module instanceof eurema.MegamodelModule) {
			logger.debug("MegamodelModule \"" + EuremaUtils.getFqModuleName(module) + "\" (ID = " + module.getUid()
					+ ") has been " + verb + " the layer #" + notifier.getNumber() + " \"" + notifier.getName()
					+ "\".");
		} else if (module instanceof eurema.SoftwareModule) {
			logger.debug("SoftwareModule \"" + EuremaUtils.getFqModuleName(module) + "\" (ID = " + module.getUid()
					+ ") has been " + verb + " the layer #" + notifier.getNumber() + " \"" + notifier.getName()
					+ "\".");
		}
	}

	/**
	 * Creates and adds events about rebinding a megamodel call to a megamodel
	 * module.
	 * 
	 * @param notifier
	 *            the megamodel call that is rebound.
	 * @param notification
	 *            the event notifying about the rebinding.
	 */
	private void createAndAddRebindMegamodelCallChangeEvent(eurema.MegamodelCall notifier, Notification notification) {
		int featureID = notification.getFeatureID(eurema.MegamodelCall.class);
		if (featureID == eurema.EuremaPackage.MEGAMODEL_CALL__BINDING) {
			// new target of the binding has been set
			int eventType = notification.getEventType();
			if (eventType == Notification.SET) {
				Object oldTarget = notification.getOldValue();
				eurema.MegamodelModule oldModule = (eurema.MegamodelModule) oldTarget;
				Object newTarget = notification.getNewValue();
				eurema.MegamodelModule newModule = (eurema.MegamodelModule) newTarget;
				RebindMegamodelCallChangeEvent event = new RebindMegamodelCallChangeEvent(notifier, oldModule,
						newModule);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
				logger.debug("The MegamodelCall \"" + notifier.getName() + "\" of the MegamodelModule \""
						+ EuremaUtils.getFqModuleName(notifier.getMegamodel().getModule())
						+ "\" has been rebound from the MegamodelModule \"" + EuremaUtils.getFqModuleName(oldModule)
						+ "\" to the MegamodelModule \"" + EuremaUtils.getFqModuleName(newModule) + "\".");
			}
		}
	}

	/**
	 * Creates and adds events about rebinding a model operation to a software
	 * module.
	 * 
	 * @param notifier
	 *            the model operation that is rebound.
	 * @param notification
	 *            the event notifying about the rebinding.
	 */
	private void createAndAddRebindModelOperationChangeEvent(eurema.ModelOperation notifier,
			Notification notification) {
		int featureID = notification.getFeatureID(eurema.ModelOperation.class);
		if (featureID == eurema.EuremaPackage.MODEL_OPERATION__BINDING) {
			// new target of the binding has been set
			int eventType = notification.getEventType();
			if (eventType == Notification.SET) {
				Object oldTarget = notification.getOldValue();
				eurema.SoftwareModule oldModule = (eurema.SoftwareModule) oldTarget;
				Object newTarget = notification.getNewValue();
				eurema.SoftwareModule newModule = (eurema.SoftwareModule) newTarget;
				RebindModelOperationChangeEvent event = new RebindModelOperationChangeEvent(notifier, oldModule,
						newModule);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
				logger.debug("The ModelOperation \"" + notifier.getName() + "\" of the MegamodelModule \""
						+ EuremaUtils.getFqModuleName(notifier.getMegamodel().getModule())
						+ "\" has been rebound from the SoftwareModule \"" + EuremaUtils.getFqModuleName(oldModule)
						+ "\" to the SoftwareModule \"" + EuremaUtils.getFqModuleName(newModule) + "\".");
			}
		}
	}

	/**
	 * Creates and adds events about changes of a trigger.
	 * 
	 * @param notifier
	 *            the trigger that has been changed.
	 * @param notification
	 *            the notification of the change.
	 */
	@SuppressWarnings("unchecked")
	private void createAndAddTriggerChangeEvent(eurema.Trigger notifier, Notification notification) {
		int featureID = notification.getFeatureID(eurema.Trigger.class);
		if (featureID == eurema.EuremaPackage.TRIGGER__PERIOD) {
			// period of the trigger has been changed
			int eventType = notification.getEventType();
			if (eventType == Notification.SET) {
				int oldPeriod = notification.getOldIntValue();
				int newPeriod = notification.getNewIntValue();
				TriggerPeriodChangeEvent event = new TriggerPeriodChangeEvent(notifier, oldPeriod, newPeriod);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);

				// log the change
				if (notifier instanceof eurema.MegamodelModuleTrigger) {
					eurema.MegamodelModuleTrigger mmTrigger = (eurema.MegamodelModuleTrigger) notifier;
					logger.debug(
							"The trigger of the MegamodelModule \"" + EuremaUtils.getFqModuleName(mmTrigger.getModule())
									+ "\" has changed its period from " + oldPeriod + " to " + newPeriod + ".");
				} else if (notifier instanceof eurema.SoftwareModuleTrigger) {
					eurema.SoftwareModuleTrigger swTrigger = (eurema.SoftwareModuleTrigger) notifier;
					logger.debug(
							"The trigger of the SoftwareModule \"" + EuremaUtils.getFqModuleName(swTrigger.getModule())
									+ "\" has changed its period from " + oldPeriod + " to " + newPeriod + ".");
				}
			}
		} else if (featureID == eurema.EuremaPackage.TRIGGER__EVENTS) {
			// events have been added or removed from the trigger
			int eventType = notification.getEventType();
			TriggerEventChangeEvent event = null;
			if (eventType == Notification.REMOVE) {
				Object oldValue = notification.getOldValue();
				eurema.Event removedEvent = (eurema.Event) oldValue;
				List<eurema.Event> removedEvents = new LinkedList<>();
				removedEvents.add(removedEvent);
				event = new TriggerEventChangeEvent(notifier, Collections.<eurema.Event> emptyList(), removedEvents);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
			} else if (eventType == Notification.REMOVE_MANY) {
				Object oldValues = notification.getOldValue();
				List<eurema.Event> oldValueList = (List<eurema.Event>) oldValues;
				List<eurema.Event> removedEvents = new LinkedList<>();
				removedEvents.addAll(oldValueList);
				event = new TriggerEventChangeEvent(notifier, Collections.<eurema.Event> emptyList(), removedEvents);
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
			} else if (eventType == Notification.ADD) {
				Object newValue = notification.getNewValue();
				eurema.Event addedEvent = (eurema.Event) newValue;
				List<eurema.Event> addedEvents = new LinkedList<>();
				addedEvents.add(addedEvent);
				event = new TriggerEventChangeEvent(notifier, addedEvents, Collections.<eurema.Event> emptyList());
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
			} else if (eventType == Notification.ADD_MANY) {
				Object newValues = notification.getNewValue();
				List<eurema.Event> newValueList = (List<eurema.Event>) newValues;
				List<eurema.Event> addedEvents = new LinkedList<>();
				addedEvents.addAll(newValueList);
				event = new TriggerEventChangeEvent(notifier, addedEvents, Collections.<eurema.Event> emptyList());
				UpdateChangeEventQueue.INSTANCE.getQueue().add(event);
			}

			// log the change
			if (event != null) {
				eurema.Trigger trigger = event.getTrigger();
				StringBuffer sb = new StringBuffer("The trigger of the ");
				if (trigger instanceof eurema.MegamodelModuleTrigger) {
					eurema.MegamodelModule mmModule = ((eurema.MegamodelModuleTrigger) trigger).getModule();
					sb.append("MegamodelModule \"" + EuremaUtils.getFqModuleName(mmModule) + "\" ");
				} else if (trigger instanceof eurema.SoftwareModuleTrigger) {
					eurema.SoftwareModule swModule = ((eurema.SoftwareModuleTrigger) trigger).getModule();
					sb.append("SoftwareModule \"" + EuremaUtils.getFqModuleName(swModule) + "\" ");
				}
				sb.append("has been changed.\n The following events have been removed from the trigger:\n");
				for (eurema.Event e : event.getRemovedEvents()) {
					sb.append("- " + EuremaUtils.eventToString(e) + "\n");
				}
				sb.append("The following events have been added to the trigger:\n");
				for (eurema.Event e : event.getAddedEvents()) {
					sb.append("- " + EuremaUtils.eventToString(e) + "\n");
				}
				sb.append("The trigger is now specified as follows:\n\t\t");
				sb.append(EuremaUtils.triggeringConditionToString(trigger));
				logger.debug(sb.toString());
			}
		}
	}

}
