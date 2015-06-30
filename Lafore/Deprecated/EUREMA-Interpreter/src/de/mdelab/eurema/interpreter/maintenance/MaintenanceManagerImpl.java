package de.mdelab.eurema.interpreter.maintenance;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import de.mdelab.eurema.interpreter.architecture.ArchitectureManager;
import de.mdelab.eurema.interpreter.execution.consistency.ConsistencyManagementFactory;
import de.mdelab.eurema.interpreter.execution.consistency.QuiescenceListener;
import de.mdelab.eurema.interpreter.execution.consistency.QuiescenceManager;
import de.mdelab.eurema.interpreter.execution.consistency.QuiescenceState;
import de.mdelab.eurema.interpreter.models.ModelRepository;

/**
 * Implementation of the {@code MaintenanceManager} that uses the <i>Story
 * Diagram Interpreter</i> to execute story patterns that define integration
 * rules.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class MaintenanceManagerImpl implements MaintenanceManager,
		QuiescenceListener {

	/**
	 * The update that is currently executed or <code>null</code> if there is
	 * currently no update in progress of execution.
	 */
	private Update update;

	/**
	 * The quiescence manager.
	 */
	private QuiescenceManager quiescenceManager;

	/**
	 * The Story Diagram Interpreter.
	 */
	// TODO Integrate SDM Interpreter
	// private SDESDMInterpreter storyDiagramInterpreter;

	// TODO must be a singleton!
	private MaintenanceManagerImpl() {
		// setup the quiescence manager
		this.quiescenceManager = ConsistencyManagementFactory.INSTANCE
				.getQuiescenceManager();
		this.quiescenceManager.addQuiescenceListener(this);

		// set up Story Diagram Interpreter
		this.setUpStoryDiagramInterpreter();

		// create and add listener to the LD to observe changes of it
		UpdateChangeListener listener = new UpdateChangeListener();
		ArchitectureManager.INSTANCE.getArchitecture().eAdapters()
				.add(listener);

		// TODO implement processors that map changes observed by the listener
		// to the interpreter's data structures used for executing EUREMA
		// models.

	}

	/**
	 * Sets up the Story Diagram Interpreter.
	 */
	// TODO implement SDM Interpreter set up
	private void setUpStoryDiagramInterpreter() {
		// this.storyDiagramInterpreter = new SDESDMInterpreter(
		// new SDEExpressionInterpreterManager(this.getClass()
		// .getClassLoader()));
		// try {
		// // To evaluate OCL expressions in Story Diagrams
		// this.storyDiagramInterpreter.getExpressionInterpreterManager()
		// .registerExpressionInterpreter(
		// new OCLExpressionInterpreter<Expression>(), "OCL",
		// "1.0");
		// } catch (SDMException e) {
		// throw new EuremaInterpreterException(
		// "Failure in setting up the Story Diagram Interpreter", e);
		// }
		throw new UnsupportedOperationException(
				"SDM Interpreter set up not implemented yet.");
	}

	/**
	 * {@inheritDoc}
	 */
	// only one update at one point in time! => synchronized method
	@Override
	public synchronized boolean uploadAndExecuteUpdate(String updateName,
			Resource integrationRule, Resource fld, List<Resource> runtimeModels) {

		if (this.update == null) {
			// there is no update in progress
			// => execute the uploaded update
			this.update = new Update(updateName, integrationRule, fld,
					runtimeModels);

			// initiate quiescence
			assert this.quiescenceManager.getCurrentState() == QuiescenceState.OFF;
			this.quiescenceManager.activateQuiescence();

			// wait for quiescent state to perform the update. This is done by
			// the callback method void notifyStateChange(QuiescenceState
			// oldState, QuiescenceState newState)

			return true;
		} else {
			return false;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyStateChange(QuiescenceState oldState,
			QuiescenceState newState) {
		switch (oldState) {
		case OFF:
			switch (newState) {
			case BLOCKING:
				// Quiescence has been activated. This has been done after an
				// update has been uploaded. Do nothing.
				break;

			default:
				break;
			}

			break;

		case BLOCKING:
			switch (newState) {
			case QUIESCENT:
				// Quiescence has been reached. Execute the update.
				this.executeUpdate();
				break;
			case OFF:
				// Quiescence has been deactivated before quiescence has been
				// reached. Activate quiescence again:
				this.quiescenceManager.activateQuiescence();
				break;
			default:
				break;
			}

			break;

		case QUIESCENT:
			switch (newState) {
			case OFF:
				// Quiescence has been deactivated after the quiescence state
				// has been reached and the update has been performed. => Do
				// nothing.
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}

	}

	/**
	 * Executes the update.
	 */
	private void executeUpdate() {

		// add FLD and runtime models to the interpreter's model repository
		this.addModelsToRepository();

		// TODO Is something missing here?

		// execute the integration rule using the story diagram interpreter
		this.executeIntegrationRule();

		// The update has been performed. Quiescence can be deactivated.
		this.quiescenceManager.deactivateQuiescence();
		this.update = null;
	}

	/**
	 * Executes the integration rule to adjust the LD.
	 */
	// TODO offline adaptation: implement the execution of an integration rule
	// defined by a story pattern
	private void executeIntegrationRule() {

		// TODO Register all the meta model packages that you will use with
		// their nsURIs in the package registry as following:
		// resourceSet.getPackageRegistry().put(YourPackage.eNS_URI,
		// YourPackage.eINSTANCE);

		// // activity = story diagram to execute
		// Activity activity = null; // TODO obtain the activity
		// // parameters of the activity (variable is a triple: name, type, and
		// // value of a parameter
		// List<Variable<EClassifier>> params = null; // TODO obtain the parameters
		// try {
		// // execute the activity
		// Map<String, Variable<EClassifier>> result =
		// this.storyDiagramInterpreter
		// .executeActivity(activity, params);
		// // result is a map from names of the result parameters to parameters
		// // as a triple of name, type, and value.
		// } catch (SDMException e) {
		// e.printStackTrace();
		// }
		throw new UnsupportedOperationException(
				"Execution of integration rules defined by story patterns are not supported yet.");
	}

	/**
	 * Adds the FLD and the runtime models contained in the FLD to the
	 * interpreter's model repository.
	 */
	private void addModelsToRepository() {
		Resource fld = this.update.getFld();
		ModelRepository.INSTANCE.addModel(fld, "Integration Rule of Update: "
				+ this.update.getName());
		int i = 1;
		for (Resource rtModel : this.update.getRuntimeModels()) {
			ModelRepository.INSTANCE.addModel(rtModel, "Runtime Model #" + i
					+ " of Update: " + this.update.getName());
			i++;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean isUpdateInProgress() {
		return this.update != null;
	}

}
