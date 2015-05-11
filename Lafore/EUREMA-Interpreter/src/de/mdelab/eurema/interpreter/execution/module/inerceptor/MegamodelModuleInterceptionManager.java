package de.mdelab.eurema.interpreter.execution.module.inerceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;

/**
 * Manager that checks whether the execution of a megamodel module should be
 * intercepted to execute other modules that senses (and potentially effects)
 * the intercepted module.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class MegamodelModuleInterceptionManager {

	/**
	 * The Megamodel Module Interception manager.
	 */
	public final static MegamodelModuleInterceptionManager INSTANCE =
			new MegamodelModuleInterceptionManager();

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager
			.getLogger(MegamodelModuleInterceptionManager.class);

	/**
	 * The global execution context.
	 */
	private GlobalExecutionContext globalExecutionContext;

	/**
	 * Flag indicating whether this manager has been initialized or not.
	 */
	private boolean initialized = false;

	/**
	 * Constructor.
	 */
	private MegamodelModuleInterceptionManager() {

	}

	/**
	 * Initializes the megamodel module interception manager.
	 * 
	 * @param globalExecutionContext
	 *            the global execution execution context of the EUREMA
	 *            interpreter.
	 */
	public void initialize(GlobalExecutionContext globalExecutionContext) {
		if (!this.initialized) {
			this.globalExecutionContext = globalExecutionContext;
			this.initialized = true;
		} else {
			throw new EuremaInterpreterException(
					"The Megamodel Module Interception Manager has already been initialized.");
		}
	}

	/**
	 * 
	 * Checks whether the <code>interceptedModule</code> should actually be
	 * intercepted to execute another module. The <code>interceptedModule</code>
	 * has emitted the <code>interceptionEvent</code> and this method checks the
	 * EUREMA model whether there are other modules that sense the
	 * <code>interceptedModule</code> and whose triggering condition match the
	 * <code>interceptionEvent</code>. For each match, the
	 * <code>interceptedModule</code> is actually intercepted and the
	 * corresponding other module is executed.
	 * 
	 * Interception events have the following form:
	 * <tt>BEFORE[operationName]</tt>, <tt>AFTER[operationName]</tt>, or
	 * <tt>ON_TRANSITION(transitionName)</tt>.
	 * 
	 * @param interceptedModule
	 *            the megamodel module that might be intercepted
	 * @param interceptionEvent
	 *            the interception event emitted by the
	 *            <code>interceptedModule</code>
	 */
	public void executeInterceptorModules(
			eurema.MegamodelModule interceptedModule,
			eurema.Event interceptionEvent) {
		// check if the inteceptingModule is sensed by another module
		EList<eurema.Sensing> eSensingRelationships =
				interceptedModule.getSensedBy();
		for (eurema.Sensing eSensing : eSensingRelationships) {
			eurema.Trigger eTrigger = eSensing.getTrigger();
			// match interception event against triggering condition, that is,
			// the interception event must match one of the events used in the
			// triggering condition.
			boolean matched = false;
			for (eurema.Event eTriggerEvent : eTrigger.getEvents()) {
				if (eTriggerEvent.getName().equals(interceptionEvent.getName())
						& eTriggerEvent.getType().getType()
								.equals(interceptionEvent.getType().getType())) {
					// event name and type match!
					matched = true;

				}
			}

			if (matched) {
				eurema.Module interceptingModule = eSensing.getSource();
				ModuleExecutable<?, ?, ?, ?> interceptingModuleExecutable =
						this.globalExecutionContext
								.getExecutable(interceptingModule);

				if (interceptingModule instanceof eurema.MegamodelModule) {
					// get the initial operation to run the megamodel module
					eurema.InitialOperation eInitialOperation =
							((eurema.MegamodelModuleTrigger) eTrigger)
									.getInitialOperation();

					// run the executable
					logger.debug("Intecepting the megamodel module "
							+ interceptedModule.getName() + " with the event "
							+ interceptionEvent.getType().getType() + "["
							+ interceptionEvent.getName()
							+ "] to execute the megamodel module "
							+ interceptingModule.getName()
							+ " with the initial operation "
							+ eInitialOperation.getName() + " ...");

					@SuppressWarnings("unchecked")
					ModuleExecutable<eurema.MegamodelModule, eurema.MegamodelModuleTrigger, eurema.InitialOperation, eurema.FinalOperation> megamodelModuleExecutable =
							(ModuleExecutable<eurema.MegamodelModule, eurema.MegamodelModuleTrigger, eurema.InitialOperation, eurema.FinalOperation>) interceptingModuleExecutable;
					eurema.FinalOperation result =
							megamodelModuleExecutable.run(eInitialOperation);
					logger.debug("Megamodel module "
							+ interceptingModule.getName()
							+ " has been executed and terminated in state "
							+ result.getName() + ".");
				} else if (interceptedModule instanceof eurema.SoftwareModule) {
					// TODO trigger the legacy adaptation module
				}

			}

		}

	}

}
