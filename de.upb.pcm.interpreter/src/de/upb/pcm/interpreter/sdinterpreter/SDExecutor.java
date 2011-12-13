package de.upb.pcm.interpreter.sdinterpreter;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.storydriven.modeling.activities.Activity;
import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;

import de.mdelab.sdm.interpreter.common.MainInterpreterFactory;
import de.mdelab.sdm.interpreter.common.SDMInterpreter;
import de.mdelab.sdm.interpreter.common.SDMInterpreterException;
import de.mdelab.sdm.interpreter.common.adapters.IMainAdapterFactory;
import de.mdelab.sdm.interpreter.common.expressions.EclipseBasedExpressionInterpreterFactory;
import de.mdelab.sdm.interpreter.common.expressions.ExpressionInterpreterFactory;
import de.mdelab.sdm.interpreter.common.tasks.notifying.NotifyingMainInterpreterFactory;
import de.mdelab.sdm.interpreter.common.tasks.notifying.OutputStreamNotificationReceiver;
import de.mdelab.sdm.interpreter.common.variables.Parameter;
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
public class SDExecutor
{
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

   private final ModelHelper modelHelper;

   private final List<Parameter> parameters;

   private final List<Activity> activities;

   private final SDMInterpreter sdmInterpreter;


   /**
    * Constructor
    * 
    * @param modelHelper the model helper.
    */
   public SDExecutor(final ModelHelper modelHelper)
   {
      sdmInterpreter = new SDMInterpreter(getMainInterpreterFactory(), getMainAdapterFactory(), getClass()
            .getClassLoader());
      this.modelHelper = modelHelper;
      this.parameters = createParameter();
      this.activities = createBindingsForActivities();
   }


   /**
    * Creates parameter bindings for all activities of the sdm models.
    * 
    * @return list of activities with bound parameters.
    */
   private List<Activity> createBindingsForActivities()
   {
      final List<Activity> ActivitiesFromModels = modelHelper.getSDMModels();
      final List<Activity> vector = new Vector<Activity>();

      for (final Activity activity : ActivitiesFromModels)
      {
         final Activity activityWithBindings = ActivityLoader.INSTANCE.createBindings(activity, new String[] {
               USAGE_MODEL, SYSTEM_MODEL, REPOSITORY_MODEL, ALLOCATION_MODEL, RESOURCE_ENVIRONMENT_MODEL, PRM_MODEL,
               MONITORED_ELEMENT }, new EClassifier[] { USAGE_MODEL_ECLASS, SYSTEM_MODEL_ECLASS,
               REPOSITORY_MODEL_ECLASS, ALLOCATION_MODEL_ECLASS, RESOURCE_ENVIRONMENT_MODEL_ECLASS,
               PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS, EOBJECT_ECLASS });

         vector.add(activityWithBindings);
      }

      return vector;
   }


   private List<Parameter> createParameter()
   {
      final List<Parameter> parameters = new ArrayList<Parameter>();
      final Parameter usageModelParameter = new Parameter(USAGE_MODEL, USAGE_MODEL_ECLASS, modelHelper
            .getGlobalPCMModels().getUsageModel());
      final Parameter systemModelParameter = new Parameter(SYSTEM_MODEL, SYSTEM_MODEL_ECLASS, modelHelper
            .getGlobalPCMModels().getSystem());
      final Parameter repositoryModelParameter = new Parameter(REPOSITORY_MODEL, REPOSITORY_MODEL_ECLASS, modelHelper
            .getGlobalPCMModels().getRepository());
      final Parameter allocationModelParameter = new Parameter(ALLOCATION_MODEL, ALLOCATION_MODEL_ECLASS, modelHelper
            .getGlobalPCMModels().getAllocation());
      final Parameter resourceEnvironmentModelParameter = new Parameter(RESOURCE_ENVIRONMENT_MODEL,
            RESOURCE_ENVIRONMENT_MODEL_ECLASS, modelHelper.getGlobalPCMModels().getResourceEnvironment());
      final Parameter prmModelParameter = new Parameter(PRM_MODEL, PALLADIO_RUNTIME_MEASUREMENT_MODEL_ECLASS,
            modelHelper.getGlobalPRMModel());
      parameters.add(usageModelParameter);
      parameters.add(systemModelParameter);
      parameters.add(repositoryModelParameter);
      parameters.add(allocationModelParameter);
      parameters.add(resourceEnvironmentModelParameter);
      parameters.add(prmModelParameter);
      return parameters;
   }


   private void execute(final Activity activity, final List<Parameter> parameters) throws SDMInterpreterException
   {
      sdmInterpreter.executeActivity(activity, parameters);
   }


   /**
    * Executes all activities for the given monitored element.
    * 
    * @param monitoredElement the pcm model element to be monitored.
    */
   public void executeActivities(final EObject monitoredElement)
   {
      final Parameter monitoredElementParameter = new Parameter(MONITORED_ELEMENT, EOBJECT_ECLASS, monitoredElement);
      this.parameters.add(monitoredElementParameter);
      for (final Activity activity : activities)
      {
         try
         {

            execute(activity, parameters);

         }
         catch (final SDMInterpreterException e)
         {
            e.printStackTrace();
         }
      }
      // remove parameter again
      // TODO not nice
      this.parameters.remove(monitoredElementParameter);
      modelHelper.syncResourceEnvironment();
   }


   private ExpressionInterpreterFactory getExpressionInterpreterAdapterFactory()
   {
      return new EclipseBasedExpressionInterpreterFactory();
   }


   private IMainAdapterFactory getMainAdapterFactory()
   {
      return new SDMainAdapterFactory();
   }


   private MainInterpreterFactory getMainInterpreterFactory()
   {
      return new NotifyingMainInterpreterFactory(getExpressionInterpreterAdapterFactory(),
            new OutputStreamNotificationReceiver());
   }

}
