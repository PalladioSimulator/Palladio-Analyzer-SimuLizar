package de.upb.pcm.interpreter.utils;


import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourcetype.SchedulingPolicy;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SchedulingStrategy;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.exceptions.PCMModelLoadException;
import de.upb.pcm.interpreter.metrics.ResourceStateListener;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PerformanceMetricEnum;
import de.upb.pcm.prm.PrmFactory;
import de.upb.pcm.prm.ResourceContainerMeasurement;


/**
 * Class to sync resource environment model with SimuCom. UGLY DRAFT!
 * 
 * @author Joachim Meyer
 * 
 */
public class ResourceSyncer
{
   protected static final Logger logger = Logger.getLogger(ResourceSyncer.class.getName());

   private final SimuComModel simuComModel;

   private final ModelHelper modelHelper;


   /**
    * Constructor
    * 
    * @param simuComModel the SimuCom model.
    * @param modelHelper the model helper.
    */
   protected ResourceSyncer(final SimuComModel simuComModel, final ModelHelper modelHelper)
   {
      super();
      this.simuComModel = simuComModel;
      this.modelHelper = modelHelper;
   }


   /**
    * @return returns the modelHelper.
    */
   private ModelHelper getModelHelper()
   {
      return this.modelHelper;
   }


   /**
    * @return returns the simuComModel.
    */
   private SimuComModel getSimuComModel()
   {
      return this.simuComModel;
   }


   /**
    * Checks whether simulated resource (by type id) already exists in given simulated resource
    * container.
    * 
    * @param simulatedResourceContainer the simulated resource container.
    * @param typeId id of the resource.
    * @return the ScheduledResource.
    */
   private ScheduledResource resourceAlreadyExist(final SimulatedResourceContainer simulatedResourceContainer,
         final String typeId)
   {
      // Resource already exists?
      for (final AbstractScheduledResource abstractScheduledResource : simulatedResourceContainer.getActiveResources())
      {
         if (abstractScheduledResource.getName().equals(typeId))
         {

            return (ScheduledResource) abstractScheduledResource;

         }
      }
      return null;
   }


   /**
    * Sync resources in resource container. If simulated resource already exists in SimuCom,
    * setProcessingRate will be updated.
    * 
    * @param resourceContainer the resource container.
    * @param simulatedResourceContainer the corresponding simulated resource container in SimuCom.
    */
   private void syncActiveResources(final ResourceContainer resourceContainer,
         final SimulatedResourceContainer simulatedResourceContainer)
   {

      // add resources
      for (final ProcessingResourceSpecification processingResource : resourceContainer
            .getActiveResourceSpecifications_ResourceContainer())
      {
         final String resourceContainerName = resourceContainer.getEntityName();
         final String typeId = processingResource.getActiveResourceType_ActiveResourceSpecification().getId();

         final String description = resourceContainerName + " - "
               + processingResource.getActiveResourceType_ActiveResourceSpecification().getEntityName();
         final String processingRate = processingResource.getProcessingRate_ProcessingResourceSpecification()
               .getSpecification();
         // processingRate does not need to be evaluated, will be done in
         // simulatedResourceContainer
         final double mttf = processingResource.getMTTF();
         final double mttr = processingResource.getMTTR();
         // units not used in simulatedResourceContainer
         final String units = processingResource.getProcessingRate_ProcessingResourceSpecification().getSpecification();

         // SchedulingStrategy
         final SchedulingPolicy schedulingPolicy = processingResource.getSchedulingPolicy();
         // TODO: Check if this works...
         String schedulingStrategy = schedulingPolicy.getEntityName();
//         switch (schedulingPolicy)
//         {
//            case PROCESSOR_SHARING:
//               schedulingStrategy = SchedulingStrategy.PROCESSOR_SHARING;
//               break;
//            case DELAY:
//               schedulingStrategy = SchedulingStrategy.DELAY;
//               break;
//            case FCFS:
//               schedulingStrategy = SchedulingStrategy.FCFS;
//               break;
//            default:
//               throw new PCMModelLoadException("No equivalent SchedulingStrategy found for StrategyPolicy "
//                     + schedulingPolicy);
//         }

         final int numberOfReplicas = processingResource.getNumberOfReplicas();
         final ScheduledResource scheduledResource = resourceAlreadyExist(simulatedResourceContainer, typeId);
         if (scheduledResource != null)
         {
            // scheduledResource.setProcessingRate(processingRate);
         }
         else
         {
            simulatedResourceContainer.addActiveResource(
            	  typeId, new String[]{}, description, description,  // TODO: Check if this is correct?
            	  description, processingRate, mttf, mttr, units,
                  schedulingStrategy, numberOfReplicas, true);
            InterpreterLogger.debug(logger, "Added ActiveResource. TypeID: " + typeId + ", Description: " + description
                  + ", ProcessingRate: " + processingRate + ", MTTF: " + mttf + ", MTTR: " + mttr + ", Units: " + units
                  + ", SchedulingStrategy: " + schedulingStrategy + ", NumberOfReplicas: " + numberOfReplicas);


            // is monitored?
            final PMSAccess pmsAccess = (PMSAccess) getModelHelper().getModelAccessFactory().getPMSModelAccess();
            MeasurementSpecification measurementSpecification;
            if ((measurementSpecification = pmsAccess.isMonitored(resourceContainer, PerformanceMetricEnum.UTILIZATION)) != null)
            {
               final ResourceContainerMeasurement resourceContainerMeasurement = PrmFactory.eINSTANCE
                     .createResourceContainerMeasurement();
               resourceContainerMeasurement.setMeasurementSpecification(measurementSpecification);
               resourceContainerMeasurement.setPcmModelElement(resourceContainer);
               resourceContainerMeasurement.setProcessingResourceType(processingResource
                     .getActiveResourceType_ActiveResourceSpecification());

               // get created active resource
               for (AbstractScheduledResource abstractScheduledResource : simulatedResourceContainer
                     .getActiveResources())
               {
                  if (abstractScheduledResource.getName().equals(typeId))
                  {
                     new ResourceStateListener(processingResource.getActiveResourceType_ActiveResourceSpecification(),
                           abstractScheduledResource, modelHelper, measurementSpecification,
                           resourceContainerMeasurement, resourceContainer, processingResource);
                     break;
                  }

               }
            }


         }

      }
   }


   /**
    * Syncs resource environment model with SimuCom.
    */
   protected void syncResourceEnvironment()
   {

      // TODO this is only a draft
      InterpreterLogger.info(logger, "Synchronise ResourceContainer and Simulated ResourcesContainer");
      // add resource container, if not done already
      for (final ResourceContainer resourceContainer : getModelHelper().getGlobalPCMModels().getResourceEnvironment()
            .getResourceContainer_ResourceEnvironment())
      {
         final String resourceContainerId = resourceContainer.getId();


         SimulatedResourceContainer simulatedResourceContainer;
         if (getSimuComModel().getResourceRegistry().containsResourceContainer(resourceContainerId))
         {
            simulatedResourceContainer = (SimulatedResourceContainer) getSimuComModel().getResourceRegistry()
                  .getResourceContainer(resourceContainerId);
            InterpreterLogger.debug(logger, "SimulatedResourceContainer already exists: " + simulatedResourceContainer);

            // now sync active resources
            this.syncActiveResources(resourceContainer, simulatedResourceContainer);
         }
         else
         {
            // create
            simulatedResourceContainer = (SimulatedResourceContainer) getSimuComModel().getResourceRegistry()
                  .createResourceContainer(resourceContainerId);
            InterpreterLogger.debug(logger, "Added SimulatedResourceContainer: ID: " + resourceContainerId + " "
                  + simulatedResourceContainer);

            // now sync active resources
            this.syncActiveResources(resourceContainer, simulatedResourceContainer);
         }


      }
      InterpreterLogger.info(logger, "Synchronisation done");
      // TODO remove unused
   }


}
