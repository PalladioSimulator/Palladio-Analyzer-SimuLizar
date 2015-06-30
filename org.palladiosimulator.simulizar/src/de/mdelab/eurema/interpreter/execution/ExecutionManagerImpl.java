package de.mdelab.eurema.interpreter.execution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.EventQueue;
import de.mdelab.eurema.interpreter.architecture.ArchitectureManager;
import de.mdelab.eurema.interpreter.execution.events.Demultiplexer;
import de.mdelab.eurema.interpreter.execution.events.DemultiplexerImpl;
import de.mdelab.eurema.interpreter.execution.events.EventQueueImpl;
import de.mdelab.eurema.interpreter.execution.events.EventQueueInternal;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;
import de.mdelab.eurema.interpreter.execution.module.executor.megamodel.ElementExecutorFactory;
import de.mdelab.eurema.interpreter.execution.module.executor.megamodel.MegamodelModuleExecutableImpl;
import de.mdelab.eurema.interpreter.execution.module.executor.software.SoftwareModuleExecutableImpl;
import de.mdelab.eurema.interpreter.execution.module.scheduler.EventDrivenModuleScheduler;
import de.mdelab.eurema.interpreter.execution.module.scheduler.EventDrivenModuleSchedulerImpl;
import de.mdelab.eurema.interpreter.execution.module.scheduler.EventPeriodicModuleSchedulerImpl;
import de.mdelab.eurema.interpreter.execution.module.scheduler.ModuleScheduler;
import de.mdelab.eurema.interpreter.execution.module.scheduler.PeriodicModuleSchedulerImpl;

/**
 * Manages the execution of the EUREMA model.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class ExecutionManagerImpl implements ExecutionManager, GlobalExecutionContext {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(ExecutionManagerImpl.class);

	/**
	 * Queue to which the adaptable software adds events that are immediately
	 * processed by the demultiplexer.
	 */
	private EventQueueInternal globalSensorEventQueue;

	/**
	 * The demultiplexer that distributes sensor events of the
	 * {@code #globalSensorEventQueue} to the local event queues of layer 1
	 * modules.
	 */
	private Demultiplexer demultiplexer;

	/**
	 * Factory for executors of megamodel elements.
	 */
	private ElementExecutorFactory elementExecutorFactory;

	/**
	 * <code>true</code> if the execution manager has been initialized, else
	 * <code>false</code>
	 */
	private boolean initialized = false;

	/**
	 * Mapping of modules to their executables.
	 */
	private Map<eurema.Module, ModuleExecutable<?, ?, ?, ?>> modulesToExecutables;

	/**
	 * All schedulers for layer 1 modules.
	 */
	private List<ModuleScheduler> schedulers;

	/**
	 * Constructor.
	 */
	public ExecutionManagerImpl() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQueue initialize(eurema.RuntimeEnvironment eRuntimeEnvironment) {
		logger.debug("Initializing the execution manager ...");
		if (this.initialized) {
			throw new EuremaInterpreterException("Execution manager has already been initialized.");
		}
		this.globalSensorEventQueue = new EventQueueImpl("EUREMA_Global Sensor Event Queue");
		this.schedulers = new LinkedList<>();
		this.elementExecutorFactory = new ElementExecutorFactory(this);

		// set up the executables for all managing modules
		this.setUpModuleExecutables(eRuntimeEnvironment);

		// EventType-Scheduler subscription as used by Demultiplexer.
		Map<eurema.EventType, List<EventDrivenModuleScheduler>> eventSubscriptions = new HashMap<>();

		// set up the schedulers for layer 1 modules with triggers
		this.setUpModuleSchedulers(eventSubscriptions);
		// start the schedulers for layer 1 modules with triggers
		this.startModuleSchedulers();

		// set up the demultiplexer.
		this.setUpDemultiplexer(eventSubscriptions);
		// start the demultiplexer.
		this.startDemultiplexer();

		logger.debug("Execution manager has been successfully initialized.");
		this.initialized = true;
		// return queue for sensor events
		return this.globalSensorEventQueue;
	}

	/**
	 * Sets up an executable for each managing module.
	 * 
	 * @param eRuntimeEnvironment
	 *            the runtime environment of the EUREMA interpreter
	 */
	private void setUpModuleExecutables(eurema.RuntimeEnvironment eRuntimeEnvironment) {
		logger.debug("Setting up the executables for each managing module...");
		this.modulesToExecutables = new HashMap<>();
		List<eurema.Module> allManagingModules = ArchitectureManager.INSTANCE.getAllManagingModules();
		for (eurema.Module eModule : allManagingModules) {
			ModuleExecutable<?, ?, ?, ?> moduleExecutable = null;
			if (eModule instanceof eurema.MegamodelModule) {
				moduleExecutable = new MegamodelModuleExecutableImpl((eurema.MegamodelModule) eModule, this);
				// Connect eurema.ExecutionContext to the
				// eurema.RuntimeEnvironment
				moduleExecutable.getExecutionContext().setEnvironment(eRuntimeEnvironment);
			} else if (eModule instanceof eurema.SoftwareModule) {
				moduleExecutable = new SoftwareModuleExecutableImpl((eurema.SoftwareModule) eModule, this);
			}
			assert moduleExecutable != null;
			this.modulesToExecutables.put(eModule, moduleExecutable);
		}

	}

	/**
	 * Sets up the schedulers for all modules at layer 1 that sense adaptable
	 * software modules. Only layer 1 modules that sense a module at layer 0
	 * have a scheduler.
	 * 
	 * @param eventSubscriptions
	 *            the subscriptions mapping event types to schedulers that
	 *            interested in events of these types
	 */
	private void setUpModuleSchedulers(Map<eurema.EventType, List<EventDrivenModuleScheduler>> eventSubscriptions) {
		logger.debug("Setting up the schedulers for layer 1 modules...");

		List<eurema.Module> adaptableSoftwareModules = ArchitectureManager.INSTANCE.getAllManagedModules();
		// get all modules that sense the adaptable software
		for (eurema.Module eAdaptableSoftwareModule : adaptableSoftwareModules) {
			for (eurema.Sensing eSense : eAdaptableSoftwareModule.getSensedBy()) {
				ModuleScheduler scheduler = this.createScheduler(eSense);
				if (scheduler != null) {
					this.schedulers.add(scheduler);
					if (scheduler instanceof EventDrivenModuleScheduler) {
						// Recall: EventPeriodicModuleScheduler extends
						// EventDrivenModuleScheduler

						// The created scheduler is triggered by sensor events.
						// Subscribe the scheduler to the corresponding event
						// types as used in the triggering condition.

						this.addEventSubscription(((EventDrivenModuleScheduler) scheduler), eventSubscriptions);
					}
				}
			}
		}
	}

	/**
	 * Creates a scheduler for the layer 1 module that has the
	 * <code>eSensing</code> relationship to a layer 0 module. A scheduler is
	 * only if there is a triggering conditions attached to the sensing
	 * relationship. Otherwise, <code>null</code> is returned.
	 * 
	 * @param eSensing
	 *            the sensing relationship stating that the source of the
	 *            relationship, that is a layer 1 module, monitors the target of
	 *            the relationship, that is a layer 0 module.
	 * @return the created scheduler or <code>null</code>.
	 */
	private ModuleScheduler createScheduler(eurema.Sensing eSensing) {
		// the scheduler to be created
		ModuleScheduler scheduler = null;

		if (eSensing.getTrigger() != null) {
			// there is a triggering condition annotated to the sensing
			// relationship
			eurema.Trigger eTrigger = eSensing.getTrigger();

			// source module senses the target module (source and target
			// with respect to the sensing relationship)
			eurema.Module eModule = eSensing.getSource();
			// retrieve the executable of the source module
			ModuleExecutable<?, ?, ?, ?> moduleExecutable = this.getExecutable(eModule);

			// check which type of trigger is used
			if (eTrigger.getEvents() == null || eTrigger.getEvents().size() == 0) {
				// no events are used in the triggering condition
				if (eTrigger.getPeriod() == 0) {
					// no period is used in the triggering condition.
					// INVALID triggering condition: at least the events
					// or the period have to be specified.
					String msg = "Failure in setting up the execution manager. The module " + eModule.getName()
							+ " has an invalid triggering condition." + " It specified neither an event nor a period.";
					logger.debug(" |_ " + msg);
					throw new EuremaInterpreterException(msg);
				} else {
					// It is a PERIODIC triggering condition
					scheduler = new PeriodicModuleSchedulerImpl(moduleExecutable);
					logger.debug(" |_ Creating the scheduler for the periodic " + eModule.eClass().getName() + " "
							+ eModule.getName());
				}
			} else {
				// there are events used in the triggering condition
				if (eTrigger.getPeriod() == 0) {
					// No period is used in the triggering condition
					// => It is an EVENT_DRIVEN triggering condition
					scheduler = new EventDrivenModuleSchedulerImpl(moduleExecutable);
					logger.debug(" |_ Creating the scheduler for the event-driven " + eModule.eClass().getName() + " "
							+ eModule.getName());
				} else {
					// events and a period are used in the triggering condition
					// => It is an EVENT_PERIODIC triggering condition
					scheduler = new EventPeriodicModuleSchedulerImpl(moduleExecutable);
					logger.debug(" |_ Creating the scheduler for the event-periodic " + eModule.eClass().getName() + " "
							+ eModule.getName());
				}
			}

			return scheduler;

		} else {
			logger.debug("No triggering condition has been specified for the module " + eSensing.getSource().getName());
			return null;
		}
	}

	/**
	 * Adds a subscription of the <code>scheduler</code> for types of events
	 * that are used in the triggering condition of the module to be scheduled.
	 * A scheduler is interested in events of such type since they are used to
	 * control the execution of the module associated to the scheduler.
	 * 
	 * @param scheduler
	 *            the event-driven scheduler (that may additionally be driven by
	 *            time) that subscribes to events of a certain type.
	 * @param eventSubscriptions
	 *            the subscriptions mapping event types to schedulers that
	 *            interested in events of these types
	 */
	private void addEventSubscription(EventDrivenModuleScheduler scheduler,
			Map<eurema.EventType, List<EventDrivenModuleScheduler>> eventSubscriptions) {
		eurema.Trigger eTrigger = scheduler.getExecutable().getETrigger();
		EList<eurema.Event> eEvents = eTrigger.getEvents();
		for (eurema.Event eEvent : eEvents) {
			eurema.EventType eEventType = eEvent.getType();
			List<EventDrivenModuleScheduler> subscribers = eventSubscriptions.get(eEventType);
			if (subscribers == null) {
				subscribers = new LinkedList<>();
				eventSubscriptions.put(eEventType, subscribers);
			}
			subscribers.add(scheduler);
			logger.debug(
					"Added subscription of the scheduler for module " + scheduler.getExecutable().getEModule().getName()
							+ " to sensor events of type " + eEventType.getType());

		}
	}

	/**
	 * Starts all schedulers for the modules at layer 1.
	 */
	private void startModuleSchedulers() {
		logger.debug("Starting the schedulers for layer modules...");
		for (ModuleScheduler scheduler : this.schedulers) {
			logger.debug(" |_ Starting the scheduler for module " + scheduler.getExecutable().getEModule().getName()
					+ " ...");
			Thread thread = new Thread(scheduler);
			thread.start();
		}
	}

	/**
	 * Sets up the demultiplexer.
	 * 
	 * @param eventSubscriptions
	 *            the subscriptions mapping event types to schedulers that
	 *            interested in events of these types
	 */
	private void setUpDemultiplexer(Map<eurema.EventType, List<EventDrivenModuleScheduler>> eventSubscriptions) {
		logger.debug("Setting up the demultiplexer ...");
		this.demultiplexer = new DemultiplexerImpl(this.globalSensorEventQueue, eventSubscriptions);
	}

	/**
	 * Starts the demultiplexer.
	 */
	private void startDemultiplexer() {
		logger.debug("Starting the demultiplexer ...");
		Thread thread = new Thread(this.demultiplexer);
		thread.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ModuleExecutable<?, ?, ?, ?> getExecutable(eurema.Module eModule) {
		ModuleExecutable<?, ?, ?, ?> result = this.modulesToExecutables.get(eModule);
		assert result != null;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementExecutorFactory getElementExecutorFactory() {
		return this.elementExecutorFactory;
	}
}
