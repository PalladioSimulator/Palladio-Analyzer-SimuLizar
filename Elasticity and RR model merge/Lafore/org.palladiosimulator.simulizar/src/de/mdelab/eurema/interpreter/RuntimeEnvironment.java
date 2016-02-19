package de.mdelab.eurema.interpreter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.interpreter.architecture.ArchitectureManager;
import de.mdelab.eurema.interpreter.execution.ExecutionManager;
import de.mdelab.eurema.interpreter.execution.ExecutionManagerImpl;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;

/**
 * The runtime environment of the EUREMA interpreter.
 * 
 * @author thomas vogel
 * @version 0.03
 * 
 *          ModelAccess and Violation and Strategy Runtime models are added for
 *          LAFORE.
 * 
 */
class RuntimeEnvironment {

	IModelAccess access;
	RuntimeViolationsModel vRun;
	RuntimeStrategiesModel sRun;

	IModelAccess getModelAccess() {
		return access;
	}

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager.getLogger(RuntimeEnvironment.class);

	/**
	 * The {@code eurema.RuntimeEnvironment} instance in the EUREMA model.
	 */
	private final eurema.RuntimeEnvironment eRuntimeEnvironment;

	/**
	 * The execution manager.
	 */
	private final ExecutionManager executionManager;

	/**
	 * Constructor of the runtime environment
	 * 
	 * @param eRuntimeEnvironment
	 *            the {@code eurema.RuntimeEnvironment} instance in the EUREMA
	 *            model.
	 */
	RuntimeEnvironment(eurema.RuntimeEnvironment eRuntimeEnvironment) {
		this.eRuntimeEnvironment = eRuntimeEnvironment;
		this.executionManager = new ExecutionManagerImpl();
	}

	/**
	 * Executes an EUREMA model.
	 * 
	 * @param eArchitecture
	 *            the EUREMA architecture to be executed as defined by the
	 *            EUREMA model (<i>Layer Diagram</i> and <i>Feedback Loop
	 *            Diagrams</i>)
	 * @return The queue to which sensor events from the adaptable software have
	 *         to be added.
	 */

	EventQueue execute(eurema.Architecture eArchitecture, IModelAccess modAccess, RuntimeViolationsModel v,
			RuntimeStrategiesModel s) {
		logger.debug("Preparing the execution of the architecture defined in the EUREMA model " + eArchitecture);

		// validate the architecture before executing it
		// TODO Validate the EUREMA model
		logger.debug("The EUREMA model is currently not validated! (future revisions will address this issue)");

		// Connect the RuntimeEnvironment and Architecture element in the
		// EUREMA model
		this.eRuntimeEnvironment.setArchitecture(eArchitecture);

		// Initialize the architecture manager
		ArchitectureManager.INSTANCE.initialize(eArchitecture);

		// Initialize the execution manager
		EventQueue queue = this.executionManager.initialize(this.eRuntimeEnvironment, modAccess, v, s);

		// TODO something missing here -- after the architecture manager and
		// execution manager have been initialized?

		vRun = v;
		sRun = s;
		access = modAccess;
		return queue;
	}

}
