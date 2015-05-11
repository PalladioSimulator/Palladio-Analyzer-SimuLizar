package de.mdelab.eurema.interpreter.execution.module.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;

import de.mdelab.eurema.interpreter.Metamodel;
import de.mdelab.eurema.interpreter.execution.consistency.ConsistencyManagementFactory;
import de.mdelab.eurema.interpreter.execution.consistency.ExecutionPermissionManager;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventType;
import de.mdelab.eurema.interpreter.execution.events.EventQueueImpl;
import de.mdelab.eurema.interpreter.execution.events.EventQueueInternal;
import de.mdelab.eurema.interpreter.execution.events.EuremaEventTypeHierarchy;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;
import de.mdelab.eurema.interpreter.util.EuremaUtils;

/**
 * Scheduler for a module (wrapped by a {@code ModuleExecutable}) at layer 1 of
 * the layered architecture that senses a layer 0 module. The module directly
 * consumes events emitted by the adaptable software located at layer 0 or timer
 * events. This module has a triggering condition of a certain type, which is
 * reflected by subclasses of this class. Each module at layer 1 has its own
 * scheduler.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
// Events emitted by the adaptable software and forwarded by the demultiplexer
// must not be modified. They can just be processed (read) and removed from the
// local queues that are individual for each scheduler with its module
// executable.
public abstract class ModuleSchedulerImpl implements ModuleScheduler {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager
			.getLogger(ModuleSchedulerImpl.class);

	/**
	 * The executable module.
	 */
	protected ModuleExecutable<?, ?, ?, ?> moduleExecutable;

	/**
	 * Indicates whether the module scheduler should continue processing (
	 * <code>continueRunning == true</code>) or stop and shut down (
	 * <code>continueRunning == false</code>).
	 */
	private boolean continueRunning = true;

	/**
	 * The quiescence manager.
	 */
	private ExecutionPermissionManager executionPermissionManager;

	/**
	 * Constructor.
	 * 
	 * @param moduleExecutable
	 *            the module whose execution should be scheduled.
	 */
	public ModuleSchedulerImpl(ModuleExecutable<?, ?, ?, ?> moduleExecutable) {
		this.moduleExecutable = moduleExecutable;
		this.executionPermissionManager = ConsistencyManagementFactory.INSTANCE
				.getExecutionPermissionManager();
	}

	/**
	 * {@inheritDoc}
	 */
	public ModuleExecutable<?, ?, ?, ?> getExecutable() {
		return this.moduleExecutable;
	}

	/**
	 * {@inheritDoc}
	 */
	public void shutDown() {
		this.continueRunning = false;
	}

	/**
	 * Returns <code>true</code> if the module scheduler should continue
	 * processing or <code>false</code> if the scheduler should stop and shut
	 * down.
	 * 
	 * @return <code>true</code> if the module scheduler should continue
	 *         processing or <code>false</code> if the scheduler should stop and
	 *         shut down.
	 */
	protected boolean continueRunning() {
		return this.continueRunning;
	}

	/**
	 * Matches a sensor event emitted by the adaptable software against the
	 * triggering condition of the module that should be scheduled.
	 * 
	 * @param eSensorEvent
	 *            the sensor event emitted by the adaptable software
	 * @return <code>true</code> if the sensor event matches the triggering
	 *         condition, else <code>false</code>.
	 */
	protected boolean matchTriggeringCondition(eurema.Event eSensorEvent) {
		boolean matched = false;
		eurema.Trigger eTrigger = this.moduleExecutable.getETrigger();
		// match eSensorEvent against the event used in the condition
		matched = this.matchEvents(eSensorEvent, eTrigger.getEvents());
		if (matched) {
			logger.debug("Matching sensor event "
					+ EuremaUtils.eventToString(eSensorEvent)
					+ " ... Found a match in triggering condition "
					+ EuremaUtils.triggeringConditionToString(eTrigger));
		} else {
			logger.debug("Matching sensor event "
					+ EuremaUtils.eventToString(eSensorEvent)
					+ " ... No match found in triggering condition "
					+ EuremaUtils.triggeringConditionToString(eTrigger));
		}
		return matched;
	}

	/**
	 * Matches the sensor event emitted by the adaptable software against the
	 * events specified in the triggering condition of the module to be
	 * scheduled.
	 * 
	 * @param eSensorEvent
	 *            the sensor event emitted by the adaptable software
	 * @param eConditionEvents
	 *            the list of events specified in the triggering condition
	 * @return <code>true</code> if the sensor event matches any event in the
	 *         list of events specified in triggering condition, else
	 *         <code>false</code>.
	 */
	private boolean matchEvents(eurema.Event eSensorEvent,
			EList<eurema.Event> eConditionEvents) {
		eurema.EventType eSensorEventType = eSensorEvent.getType();

		// Multiple events in a condition are combined by OR. Thus, first
		// match wins.
		for (eurema.Event eConditionEvent : eConditionEvents) {
			eurema.EventType eConditionEventType = eConditionEvent.getType();
			// match types
			boolean typeMatched = eSensorEventType.equals(eConditionEventType);
			// no match; check sub types
			if (!typeMatched) {
				typeMatched = this.matchEventTypes(eSensorEventType,
						eConditionEventType.getSubTypes());
			}
			// types are matched; match event names
			if (typeMatched) {
				String eConditionEventName = eConditionEvent.getName();
				if (eConditionEventName != null
						&& !(eConditionEventName.equals(""))) {
					// the condition defines an event name. Match it against the
					// name of the sensor event
					String eSensorEventName = eSensorEvent.getName();
					if (eConditionEventName.equals(eSensorEventName)) {
						return true;
					}
				} else {
					// the condition defines no event name. Thus, just the
					// events types must match, and they have already been
					// matched.
					return true;
				}
			}
			// check next event in the condition to match
		}
		return false;
	}

	/**
	 * Matches the type of the sensor event emitted by the adaptable software
	 * against the types of events indicated by the second parameter of this
	 * method. The matching process considers the types of the events (second
	 * parameter) and their sub types in order to find a match.
	 * 
	 * @param eSensorEventType
	 *            the type of the sensor event emitted by the adaptable software
	 * @param eConditionEventTypes
	 *            the types of events against which the sensor event type should
	 *            be matched
	 * @return <code>true</code> if the sensor event type matches any event type
	 *         or its sub type in the list of event types indicated by the
	 *         second parameter, else <code>false</code>.
	 */
	private boolean matchEventTypes(eurema.EventType eSensorEventType,
			EList<eurema.EventType> eConditionEventTypes) {
		for (eurema.EventType eConditionEventType : eConditionEventTypes) {
			if (eSensorEventType.equals(eConditionEventType)) {
				return true;
			} else {
				// check sub types of the event type used in the triggering
				// condition.
				this.matchEventTypes(eSensorEventType,
						eConditionEventType.getSubTypes());
			}
		}
		return false;
	}

	/**
	 * Runs the module executable.
	 */
	protected void runExecutable() {

		// check for quiescence and acquire execution permission
		this.executionPermissionManager
				.waitForExecutionPermission(this.moduleExecutable);

		eurema.Module eModule = this.moduleExecutable.getEModule();
		eurema.Trigger eTrigger = this.moduleExecutable.getETrigger();
		if (eModule instanceof eurema.MegamodelModule) {
			// get the initial operation to run the megamodel module
			eurema.MegamodelModuleTrigger eMegamodelTrigger = (eurema.MegamodelModuleTrigger) eTrigger;
			eurema.InitialOperation eInitialOperation = eMegamodelTrigger
					.getInitialOperation();
			assert eInitialOperation != null;

			// run the executable
			logger.debug("Executing the megamodel module " + eModule.getName()
					+ " ...");
			@SuppressWarnings("unchecked")
			ModuleExecutable<eurema.MegamodelModule, eurema.MegamodelModuleTrigger, eurema.InitialOperation, eurema.FinalOperation> megamodelModuleExecutable = (ModuleExecutable<eurema.MegamodelModule, eurema.MegamodelModuleTrigger, eurema.InitialOperation, eurema.FinalOperation>) this.moduleExecutable;
			eurema.FinalOperation result = megamodelModuleExecutable
					.run(eInitialOperation);
			logger.debug("Megamodel module " + eModule.getName()
					+ " has been executed and terminated in state "
					+ result.getName() + ".");
		} else if (eModule instanceof eurema.SoftwareModule) {
			// TODO trigger the legacy adaptation module
		}

		// release execution permission and notify quiescence manager about
		// termination of execution
		this.executionPermissionManager
				.releaseExecutionPermission(this.moduleExecutable);
	}

	/**
	 * Creates a timer event.
	 * 
	 * @param eventName
	 *            name of the event
	 * @return the created timer event.
	 */
	protected eurema.Event createTimerEvent(String eventName) {
		eurema.Event initialTimerEvent = Metamodel.INSTANCE.getEuremaFactory()
				.createEvent();
		initialTimerEvent.setType(EuremaEventTypeHierarchy.INSTANCE
				.getEEventType(EuremaEventType.TIMER));
		initialTimerEvent.setName(eventName);
		return initialTimerEvent;
	}

	/**
	 * Creates a local sensor event queue for the module executable.
	 * 
	 * @return the created local sensor event queue.
	 */
	protected EventQueueInternal createLocalSensorEventQueue() {
		String queueName = "EUREMA_SensorEventQueue_"
				+ moduleExecutable.getEModule().getName();
		return new EventQueueImpl(queueName);
	}

	/**
	 * Creates a local timer event queue for the module executable.
	 * 
	 * @return the created local timer event queue.
	 */
	protected EventQueueInternal createLocalTimerEventQueueName() {
		String queueName = "EUREMA_TimerEventQueue_"
				+ this.moduleExecutable.getEModule().getName();
		return new EventQueueImpl(queueName);
	}
}
