package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.modelaccess.StoryDiagramModelAccess;
import org.storydriven.core.expressions.Expression;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityEdge;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.interpreter.eclipse.StoryDrivenEclipseInterpreter;
import org.storydriven.storydiagrams.patterns.AbstractLinkVariable;
import org.storydriven.storydiagrams.patterns.AbstractVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;

import de.mdelab.sdm.interpreter.core.SDMException;
import de.mdelab.sdm.interpreter.core.notifications.OutputStreamNotificationReceiver;
import de.mdelab.sdm.interpreter.core.variables.Variable;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.allocation.AllocationPackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcm.system.SystemPackage;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;

/**
 * 
 * @author Joachim Meyer
 * @author Matthias Becker
 * 
 *         Story Diagram executor helper class that supports executing StoryDiagram reconfiguration
 *         rules.
 *
 */
public class SDExecutor {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SDExecutor.class);

    /**
    * 
    */
    private static final EClass EOBJECT_ECLASS = EcorePackage.eINSTANCE.getEObject();

    /**
    * 
    */
    private static final String MONITORED_ELEMENT = "monitoredElement";

    /**
    * 
    */
    private static final EClass PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS = RuntimeMeasurementPackage.eINSTANCE
            .getRuntimeMeasurementModel();

    /**
    * 
    */
    private static final EClass RESOURCE_ENVIRONMENT_MODEL_ECLASS = ResourceenvironmentPackage.eINSTANCE
            .getResourceEnvironment();

    /**
    * 
    */
    private static final EClass ALLOCATION_MODEL_ECLASS = AllocationPackage.eINSTANCE.getAllocation();

    /**
    * 
    */
    private static final EClass REPOSITORY_MODEL_ECLASS = RepositoryPackage.eINSTANCE.getRepository();

    /**
    * 
    */
    private static final EClass SYSTEM_MODEL_ECLASS = SystemPackage.eINSTANCE.getSystem();

    /**
    * 
    */
    private static final EClass USAGE_MODEL_ECLASS = UsagemodelPackage.eINSTANCE.getUsageModel();

    /**
    * 
    */
    private static final String PRM_MODEL = "runtimeMeasurementModel";

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

    private static final String RETURN_VALUE = "returnValue";
    private static final EClass BOOLEAN_ECLASS = EcorePackage.eINSTANCE.getEBoolean().eClass();

    /**
    * 
    */
    private static final String USAGE_MODEL = "usageModel";

    private final List<Variable<EClassifier>> staticParameters;

    private final Collection<Activity> activities;

    private final StoryDrivenEclipseInterpreter sdmInterpreter;

    private final Collection<Activity> storyDiagrams;
    private final PCMResourceSetPartition globalPcmResourceSetPartition;
    private final RuntimeMeasurementModel runtimeMeasurementModel;
    private final SDReconfigurationNotificationReceiver<Activity, ActivityNode, ActivityEdge, StoryPattern, AbstractVariable, AbstractLinkVariable, EClassifier, EStructuralFeature, Expression> sdNotificationReceiver;

    /**
     * Constructor of the SD Executor.
     * 
     * @param modelAccessFactory
     *            the model access factory used to access the SD, PCM@runtime and RuntimeMeasurement
     *            models.
     */
    public SDExecutor(final StoryDiagramModelAccess modelAccessFactory) {
        super();
        this.storyDiagrams = modelAccessFactory.getStoryDiagrams();
        this.globalPcmResourceSetPartition = modelAccessFactory.getGlobalPCMModel();
        this.runtimeMeasurementModel = modelAccessFactory.getRuntimeMeasurementModel();
        try {
            this.sdmInterpreter = new StoryDrivenEclipseInterpreter(this.getClass().getClassLoader());
        } catch (final SDMException e) {
            throw new RuntimeException("Unable to inialise SD interpreter engine", e);
        }
        this.sdNotificationReceiver = new SDReconfigurationNotificationReceiver<Activity, ActivityNode, ActivityEdge, StoryPattern, AbstractVariable, AbstractLinkVariable, EClassifier, EStructuralFeature, Expression>(
                this.sdmInterpreter.getFacadeFactory());
        this.sdmInterpreter.getNotificationEmitter().addNotificationReceiver(this.sdNotificationReceiver);

        if (LOGGER.isDebugEnabled()) {
            this.sdmInterpreter
                    .getNotificationEmitter()
                    .addNotificationReceiver(
                            new OutputStreamNotificationReceiver<Activity, ActivityNode, ActivityEdge, StoryPattern, AbstractVariable, AbstractLinkVariable, EClassifier, EStructuralFeature, Expression>(
                                    this.sdmInterpreter.getFacadeFactory()));
        }

        this.staticParameters = this.createParameter();
        this.activities = this.createBindingsForActivities();
    }

    /**
     * Creates parameter bindings for all activities of the sdm models.
     * 
     * @return list of activities with bound parameters.
     */
    private Collection<Activity> createBindingsForActivities() {
        final Collection<Activity> ActivitiesFromModels = this.storyDiagrams;
        final Collection<Activity> result = new LinkedList<Activity>();

        for (final Activity activity : ActivitiesFromModels) {
            final Activity activityWithBindings = ActivityLoader.createBindings(activity, new String[] { USAGE_MODEL,
                    SYSTEM_MODEL, REPOSITORY_MODEL, ALLOCATION_MODEL, RESOURCE_ENVIRONMENT_MODEL, PRM_MODEL,
                    MONITORED_ELEMENT }, new EClassifier[] { USAGE_MODEL_ECLASS, SYSTEM_MODEL_ECLASS,
                    REPOSITORY_MODEL_ECLASS, ALLOCATION_MODEL_ECLASS, RESOURCE_ENVIRONMENT_MODEL_ECLASS,
                    PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS, EOBJECT_ECLASS });

            result.add(activityWithBindings);
        }

        return result;
    }

    /**
     * Created the parameters used within the StoryDiagrams
     * 
     * @return List<Variable<EClassifier>> list of variables and their according EClassifiers
     */
    private List<Variable<EClassifier>> createParameter() {
        final List<Variable<EClassifier>> parameters = new ArrayList<Variable<EClassifier>>();
        final Variable<EClassifier> usageModelParameter = new Variable<EClassifier>(USAGE_MODEL, USAGE_MODEL_ECLASS,
                this.globalPcmResourceSetPartition.getUsageModel());
        final Variable<EClassifier> systemModelParameter = new Variable<EClassifier>(SYSTEM_MODEL, SYSTEM_MODEL_ECLASS,
                this.globalPcmResourceSetPartition.getAllocation().getSystem_Allocation());
        // final Variable<EClassifier> repositoryModelParameter = new
        // Variable<EClassifier>(REPOSITORY_MODEL,
        // REPOSITORY_MODEL_ECLASS, globalPCMModel.getRepository());
        final Variable<EClassifier> allocationModelParameter = new Variable<EClassifier>(ALLOCATION_MODEL,
                ALLOCATION_MODEL_ECLASS, this.globalPcmResourceSetPartition.getAllocation());
        final Variable<EClassifier> resourceEnvironmentModelParameter = new Variable<EClassifier>(
                RESOURCE_ENVIRONMENT_MODEL, RESOURCE_ENVIRONMENT_MODEL_ECLASS, this.globalPcmResourceSetPartition
                        .getAllocation().getTargetResourceEnvironment_Allocation());
        final Variable<EClassifier> prmModelParameter = new Variable<EClassifier>(PRM_MODEL,
                PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS, this.runtimeMeasurementModel);
        parameters.add(usageModelParameter);
        parameters.add(systemModelParameter);
        // parameters.add(repositoryModelParameter);
        parameters.add(allocationModelParameter);
        parameters.add(resourceEnvironmentModelParameter);
        parameters.add(prmModelParameter);
        return parameters;
    }

    /**
     * Executes one activities for the given monitored element.
     * 
     * @param activity
     *            Activities in the StoryDiagram
     * @param parameters
     *            Parameters for the StoryDiagram execution
     * @return always returns false
     * @throws SDMException
     *             in case the SD Activity could not be executed
     */
    private boolean execute(final Activity activity, final List<Variable<EClassifier>> parameters) throws SDMException {
        this.sdNotificationReceiver.reset();
        final Map<String, Variable<EClassifier>> result = this.sdmInterpreter.executeActivity(activity, parameters);
        // TODO: Get info on activity success?
        if (this.sdNotificationReceiver.applicationSuccessful()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Executes all activities for the given monitored element.
     * 
     * @param monitoredElement
     *            the pcm model element to be monitored.
     * @return true if at least one reconfiguration's check was positive and it reconfigured the
     *         model.
     */
    public boolean executeActivities(final EObject monitoredElement) {
        final EObject returnvalue = EcoreFactory.eINSTANCE.create(BOOLEAN_ECLASS);
        final Variable<EClassifier> monitoredElementParameter = new Variable<EClassifier>(MONITORED_ELEMENT,
                EOBJECT_ECLASS, monitoredElement);
        final Variable<EClassifier> returnValueParameter = new Variable<EClassifier>(RETURN_VALUE, BOOLEAN_ECLASS,
                returnvalue);
        final List<Variable<EClassifier>> paramterList = new ArrayList<Variable<EClassifier>>();
        paramterList.addAll(this.staticParameters);
        paramterList.add(monitoredElementParameter);
        paramterList.add(returnValueParameter);
        boolean result = false;
        for (final Activity activity : this.activities) {
            try {
                LOGGER.debug("Trying to execute Story Diagram " + activity.getName() + ".");
                result |= this.execute(activity, paramterList);

            } catch (final SDMException e) {
                LOGGER.error("SD failed", e);
                throw new RuntimeException("SD interpretation failed", e);
            }
        }
        return result;
    }
}
