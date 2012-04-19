package de.upb.pcm.interpreter.sdinterpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.interpreter.eclipse.StoryDrivenEclipseInterpreter;

import de.mdelab.sdm.interpreter.core.SDMException;
import de.mdelab.sdm.interpreter.core.variables.Variable;
import de.uka.ipd.sdq.pcm.allocation.AllocationPackage;
import de.uka.ipd.sdq.pcm.repository.RepositoryPackage;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage;
import de.uka.ipd.sdq.pcm.system.SystemPackage;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.prm.PrmPackage;

/**
 * Story diagram executor.
 * 
 */
public class SDExecutor {
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

	private final ModelHelper modelHelper;

	private final List<Variable<EClassifier>> parameters;

	private final List<Activity> activities;

	private StoryDrivenEclipseInterpreter sdmInterpreter;

	/**
	 * Constructor
	 * 
	 * @param modelHelper
	 *            the model helper.
	 * @throws SDMException
	 */
	public SDExecutor(final ModelHelper modelHelper) {
		sdmInterpreter = null;
		try {
			sdmInterpreter = new StoryDrivenEclipseInterpreter(getClass().getClassLoader());
		} catch (SDMException e) {
			e.printStackTrace();
		}
		this.modelHelper = modelHelper;
		this.parameters = createParameter();
		this.activities = createBindingsForActivities();
	}

	/**
	 * Creates parameter bindings for all activities of the sdm models.
	 * 
	 * @return list of activities with bound parameters.
	 */
	private List<Activity> createBindingsForActivities() {
		final List<Activity> ActivitiesFromModels = modelHelper.getSDMModels();
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
		final List<Variable<EClassifier>> parameters = new ArrayList<Variable<EClassifier>>();
		final Variable<EClassifier> usageModelParameter = new Variable<EClassifier>(
				USAGE_MODEL, USAGE_MODEL_ECLASS, modelHelper
						.getGlobalPCMModels().getUsageModel());
		final Variable<EClassifier> systemModelParameter = new Variable<EClassifier>(
				SYSTEM_MODEL, SYSTEM_MODEL_ECLASS, modelHelper
						.getGlobalPCMModels().getSystem());
		final Variable<EClassifier> repositoryModelParameter = new Variable<EClassifier>(
				REPOSITORY_MODEL, REPOSITORY_MODEL_ECLASS, modelHelper
						.getGlobalPCMModels().getRepository());
		final Variable<EClassifier> allocationModelParameter = new Variable<EClassifier>(
				ALLOCATION_MODEL, ALLOCATION_MODEL_ECLASS, modelHelper
						.getGlobalPCMModels().getAllocation());
		final Variable<EClassifier> resourceEnvironmentModelParameter = new Variable<EClassifier>(
				RESOURCE_ENVIRONMENT_MODEL, RESOURCE_ENVIRONMENT_MODEL_ECLASS,
				modelHelper.getGlobalPCMModels().getResourceEnvironment());
		final Variable<EClassifier> prmModelParameter = new Variable<EClassifier>(
				PRM_MODEL, PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS,
				modelHelper.getGlobalPRMModel());
		parameters.add(usageModelParameter);
		parameters.add(systemModelParameter);
		parameters.add(repositoryModelParameter);
		parameters.add(allocationModelParameter);
		parameters.add(resourceEnvironmentModelParameter);
		parameters.add(prmModelParameter);
		return parameters;
	}

	private void execute(final Activity activity,
			final List<Variable<EClassifier>> parameters) throws SDMException {
		sdmInterpreter.executeActivity(activity, parameters);
	}

	/**
	 * Executes all activities for the given monitored element.
	 * 
	 * @param monitoredElement
	 *            the pcm model element to be monitored.
	 */
	public void executeActivities(final EObject monitoredElement) {
		final Variable<EClassifier> monitoredElementParameter = new Variable<EClassifier>(
				MONITORED_ELEMENT, EOBJECT_ECLASS, monitoredElement);
		this.parameters.add(monitoredElementParameter);
		for (final Activity activity : activities) {
			try {

				execute(activity, parameters);

			} catch (final SDMException e) {
				e.printStackTrace();
			}
		}
		// remove parameter again
		// TODO not nice
		this.parameters.remove(monitoredElementParameter);
		modelHelper.syncResourceEnvironment();
	}
}
