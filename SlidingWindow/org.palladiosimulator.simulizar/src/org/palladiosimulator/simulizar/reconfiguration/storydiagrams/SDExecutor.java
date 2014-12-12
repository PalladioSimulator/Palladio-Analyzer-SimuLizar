package org.palladiosimulator.simulizar.reconfiguration.storydiagrams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmPackage;
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
import de.uka.ipd.sdq.pcm.allocation.AllocationPackage;
import de.uka.ipd.sdq.pcm.repository.RepositoryPackage;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage;
import de.uka.ipd.sdq.pcm.system.SystemPackage;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Story diagram executor helper class that supports executing SDs against the PCM model@runtime.
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
    private static final EClass PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS = PrmPackage.eINSTANCE.getPRMModel();

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

    private final List<Variable<EClassifier>> staticParameters;

    private final Collection<Activity> activities;

    private final StoryDrivenEclipseInterpreter sdmInterpreter;

    private final Collection<Activity> storyDiagrams;
    private final PCMResourceSetPartition globalPcmResourceSetPartition;
    private final PRMModel prmModel;

    /**
     * Constructor of the SD Executor.
     * 
     * @param modelAccessFactory
     *            the model access factory used to access the SD, PCM@runtime and PRM models.
     */
    public SDExecutor(final IModelAccess modelAccessFactory) {
        super();
        this.storyDiagrams = modelAccessFactory.getStoryDiagrams();
        this.globalPcmResourceSetPartition = modelAccessFactory.getGlobalPCMModel();
        this.prmModel = modelAccessFactory.getPRMModel();
        try {
            this.sdmInterpreter = new StoryDrivenEclipseInterpreter(this.getClass().getClassLoader());
        } catch (final SDMException e) {
            throw new RuntimeException("Unable to inialise SD interpreter engine", e);
        }
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
            final Activity activityWithBindings = ActivityLoader.createBindings(activity, new String[] {
                    USAGE_MODEL, SYSTEM_MODEL, REPOSITORY_MODEL, ALLOCATION_MODEL, RESOURCE_ENVIRONMENT_MODEL,
                    PRM_MODEL, MONITORED_ELEMENT
            }, new EClassifier[] {
                    USAGE_MODEL_ECLASS, SYSTEM_MODEL_ECLASS, REPOSITORY_MODEL_ECLASS, ALLOCATION_MODEL_ECLASS,
                    RESOURCE_ENVIRONMENT_MODEL_ECLASS, PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS, EOBJECT_ECLASS
            });

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
                PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS, this.prmModel);
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
        this.sdmInterpreter.executeActivity(activity, parameters);
        // TODO: Get info on activity success?
        return false;
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
        final Variable<EClassifier> monitoredElementParameter = new Variable<EClassifier>(MONITORED_ELEMENT,
                EOBJECT_ECLASS, monitoredElement);
        final List<Variable<EClassifier>> paramterList = new ArrayList<Variable<EClassifier>>();
        paramterList.addAll(this.staticParameters);
        paramterList.add(monitoredElementParameter);
        boolean result = false;
        for (final Activity activity : this.activities) {
            try {

                result |= this.execute(activity, paramterList);

            } catch (final SDMException e) {
                LOGGER.error("SD failed", e);
                throw new PCMModelInterpreterException("SD interpretation failed", e);
            }
        }
        return result;
    }
}
