package de.upb.pcm.interpreter.sdinterpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.interpreter.eclipse.StoryDrivenEclipseInterpreter;

import de.mdelab.sdm.interpreter.core.SDMException;
import de.mdelab.sdm.interpreter.core.notifications.OutputStreamNotificationReceiver;
import de.mdelab.sdm.interpreter.core.variables.Variable;
import de.uka.ipd.sdq.pcm.allocation.AllocationPackage;
import de.uka.ipd.sdq.pcm.repository.RepositoryPackage;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage;
import de.uka.ipd.sdq.pcm.system.SystemPackage;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.PCMModels;
import de.upb.pcm.prm.PrmPackage;

/**
 * Story diagram executor.
 * 
 */
public class SDExecutor {
	private final static Logger logger = Logger.getLogger(SDExecutor.class);
	/**
    * 
    */
	private static final EClass EOBJECT_ECLASS = EcorePackage.eINSTANCE
			.getEObject();

	/**
    * 
    */
	private static final String MONITORED_ELEMENT = "monitoredElement";

	/**
    * 
    */
	private static final EClass PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS = PrmPackage.eINSTANCE
			.getPRMModel();

	/**
    * 
    */
	private static final EClass RESOURCE_ENVIRONMENT_MODEL_ECLASS = ResourceenvironmentPackage.eINSTANCE
			.getResourceEnvironment();

	/**
    * 
    */
	private static final EClass ALLOCATION_MODEL_ECLASS = AllocationPackage.eINSTANCE
			.getAllocation();

	/**
    * 
    */
	private static final EClass REPOSITORY_MODEL_ECLASS = RepositoryPackage.eINSTANCE
			.getRepository();

	/**
    * 
    */
	private static final EClass SYSTEM_MODEL_ECLASS = SystemPackage.eINSTANCE
			.getSystem();

	/**
    * 
    */
	private static final EClass USAGE_MODEL_ECLASS = UsagemodelPackage.eINSTANCE
			.getUsageModel();

	/**
    * 
    */
	private static final String PRM_MODEL = "prmModel";

	/**
    * 
    */
	private static final String RESOURCE_ENVIRONMENT_MODEL = "resourceEnvironmentModel";

	/**
    * 
    */
	private static final String ALLOCATION_MODEL = "allocationModel";

	/**
    * 
    */
	private static final String REPOSITORY_MODEL = "repositoryModel";

	/**
    * 
    */
	private static final String SYSTEM_MODEL = "systemModel";

	/**
    * 
    */
	private static final String USAGE_MODEL = "usageModel";

	private final List<Variable<EClassifier>> parameters;

	private final List<Activity> activities;

	private StoryDrivenEclipseInterpreter sdmInterpreter;

	private IModelAccessFactory modelAccessFactory;

	/**
	 * Constructor
	 * 
	 * @param modelHelper
	 *            the model helper.
	 * @throws SDMException
	 */
	public SDExecutor(final IModelAccessFactory modelAccessFactory) {
		this.modelAccessFactory = modelAccessFactory;
		sdmInterpreter = null;
		try {
			sdmInterpreter = new StoryDrivenEclipseInterpreter(getClass().getClassLoader());
		} catch (SDMException e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			sdmInterpreter.getNotificationEmitter().addNotificationReceiver(
					new OutputStreamNotificationReceiver(sdmInterpreter.getFacadeFactory()));
		}
		this.parameters = createParameter();
		this.activities = createBindingsForActivities();
	}

	/**
	 * Creates parameter bindings for all activities of the sdm models.
	 * 
	 * @return list of activities with bound parameters.
	 */
	private List<Activity> createBindingsForActivities() {
		final List<Activity> ActivitiesFromModels = this.modelAccessFactory.getSDAccess().getModel();
		final List<Activity> vector = new Vector<Activity>();

		for (final Activity activity : ActivitiesFromModels) {
			final Activity activityWithBindings = ActivityLoader.INSTANCE
					.createBindings(activity, new String[] { USAGE_MODEL,
							SYSTEM_MODEL, REPOSITORY_MODEL, ALLOCATION_MODEL,
							RESOURCE_ENVIRONMENT_MODEL, PRM_MODEL,
							MONITORED_ELEMENT }, new EClassifier[] {
							USAGE_MODEL_ECLASS, SYSTEM_MODEL_ECLASS,
							REPOSITORY_MODEL_ECLASS, ALLOCATION_MODEL_ECLASS,
							RESOURCE_ENVIRONMENT_MODEL_ECLASS,
							PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS,
							EOBJECT_ECLASS });

			vector.add(activityWithBindings);
		}

		return vector;
	}

	private List<Variable<EClassifier>> createParameter() {
		final PCMModels globalPCMModel = modelAccessFactory.getGlobalPCMAccess().getModel();
		final List<Variable<EClassifier>> parameters = new ArrayList<Variable<EClassifier>>();
		final Variable<EClassifier> usageModelParameter = new Variable<EClassifier>(
				USAGE_MODEL, USAGE_MODEL_ECLASS, globalPCMModel.getUsageModel());
		final Variable<EClassifier> systemModelParameter = new Variable<EClassifier>(
				SYSTEM_MODEL, SYSTEM_MODEL_ECLASS, globalPCMModel.getSystem());
		final Variable<EClassifier> repositoryModelParameter = new Variable<EClassifier>(
				REPOSITORY_MODEL, REPOSITORY_MODEL_ECLASS, globalPCMModel.getRepository());
		final Variable<EClassifier> allocationModelParameter = new Variable<EClassifier>(
				ALLOCATION_MODEL, ALLOCATION_MODEL_ECLASS, globalPCMModel.getAllocation());
		final Variable<EClassifier> resourceEnvironmentModelParameter = new Variable<EClassifier>(
				RESOURCE_ENVIRONMENT_MODEL, RESOURCE_ENVIRONMENT_MODEL_ECLASS,
				globalPCMModel.getResourceEnvironment());
		final Variable<EClassifier> prmModelParameter = new Variable<EClassifier>(
				PRM_MODEL, PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS,
				modelAccessFactory.getPRMModelAccess().getModel());
		parameters.add(usageModelParameter);
		parameters.add(systemModelParameter);
		parameters.add(repositoryModelParameter);
		parameters.add(allocationModelParameter);
		parameters.add(resourceEnvironmentModelParameter);
		parameters.add(prmModelParameter);
		return parameters;
	}

	private boolean execute(final Activity activity,
			final List<Variable<EClassifier>> parameters) throws SDMException {
		sdmInterpreter.executeActivity(activity, parameters);
		// TODO: Get info on activity success
		return false;
	}

	/**
	 * Executes all activities for the given monitored element.
	 * 
	 * @param monitoredElement
	 *            the pcm model element to be monitored.
	 */
	public boolean executeActivities(final EObject monitoredElement) {
		final Variable<EClassifier> monitoredElementParameter = new Variable<EClassifier>(
				MONITORED_ELEMENT, EOBJECT_ECLASS, monitoredElement);
		this.parameters.add(monitoredElementParameter);
		boolean result = false;
		for (final Activity activity : activities) {
			try {

				result  |= execute(activity, parameters);

			} catch (final SDMException e) {
				InterpreterLogger.info(logger, "SD failed: "+e);
			}
		}
		// remove parameter again
		// TODO not nice
		this.parameters.remove(monitoredElementParameter);
		return result;
	}
}
