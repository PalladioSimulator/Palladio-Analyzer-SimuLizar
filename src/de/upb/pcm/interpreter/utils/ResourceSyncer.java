package de.upb.pcm.interpreter.utils;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.resourcetype.SchedulingPolicy;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SchedulingStrategy;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.access.PMSAccess;
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
public class ResourceSyncer {
	protected static final Logger logger = Logger
			.getLogger(ResourceSyncer.class.getName());

	private final SimuComModel simuComModel;

	private final IModelAccessFactory modelAccessFactory;
	
	/**
	 * Constructor
	 * 
	 * @param simuComModel
	 *            the SimuCom model.
	 * @param modelHelper
	 *            the model helper.
	 */
	public ResourceSyncer(final SimuComModel simuComModel,
			final IModelAccessFactory modelAccessFactory) {
		super();
		this.simuComModel = simuComModel;
		this.modelAccessFactory = modelAccessFactory;
		ResourceEnvironment resourceEnvironment = modelAccessFactory.getGlobalPCMAccess().getModel().getResourceEnvironment();
		resourceEnvironment.eAdapters().add(new EContentAdapter(){

			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				InterpreterLogger.info(logger, "Resource environment changed by reconfiguration - Resync simulated resources");
				ResourceSyncer.this.syncResourceEnvironment();
			}
			
		});
	}

	/**
	 * @return returns the simuComModel.
	 */
	private SimuComModel getSimuComModel() {
		return this.simuComModel;
	}

	/**
	 * Checks whether simulated resource (by type id) already exists in given
	 * simulated resource container.
	 * 
	 * @param simulatedResourceContainer
	 *            the simulated resource container.
	 * @param typeId
	 *            id of the resource.
	 * @return the ScheduledResource.
	 */
	private ScheduledResource resourceAlreadyExist(
			final SimulatedResourceContainer simulatedResourceContainer,
			final String typeId) {
		// Resource already exists?
		for (final AbstractScheduledResource abstractScheduledResource : simulatedResourceContainer
				.getActiveResources()) {
			if (abstractScheduledResource.getName().equals(typeId)) {

				return (ScheduledResource) abstractScheduledResource;

			}
		}
		return null;
	}

	/**
	 * Sync resources in resource container. If simulated resource already
	 * exists in SimuCom, setProcessingRate will be updated.
	 * 
	 * @param resourceContainer
	 *            the resource container.
	 * @param simulatedResourceContainer
	 *            the corresponding simulated resource container in SimuCom.
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
         
         String schedulingStrategy = schedulingPolicy.getId();
         if (schedulingStrategy.equals("ProcessorSharing"))
        	 schedulingStrategy = SchedulingStrategy.PROCESSOR_SHARING;
         else if (schedulingStrategy.equals("FCFS"))
        	 schedulingStrategy = SchedulingStrategy.FCFS;
         else if (schedulingStrategy.equals("Delay"))
        	 schedulingStrategy = SchedulingStrategy.DELAY;

         final int numberOfReplicas = processingResource.getNumberOfReplicas();
         final ScheduledResource scheduledResource = resourceAlreadyExist(simulatedResourceContainer, typeId);
         if (scheduledResource != null)
         {
        	 scheduledResource.setProcessingRate(processingRate);
         }
         else
         {
            simulatedResourceContainer.addActiveResource(
            	  typeId, new String[]{}, resourceContainer.getId(), processingResource.getActiveResourceType_ActiveResourceSpecification().getEntityName(),  // TODO: Check if this is correct?
            	  description, processingRate, mttf, mttr, units,
                  schedulingStrategy, numberOfReplicas, true);
            InterpreterLogger.debug(logger, "Added ActiveResource. TypeID: " + typeId + ", Description: " + description
                  + ", ProcessingRate: " + processingRate + ", MTTF: " + mttf + ", MTTR: " + mttr + ", Units: " + units
                  + ", SchedulingStrategy: " + schedulingStrategy + ", NumberOfReplicas: " + numberOfReplicas);


            // is monitored?
            final PMSAccess pmsAccess = this.modelAccessFactory.getPMSModelAccess();
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
                           abstractScheduledResource, getSimuComModel(), measurementSpecification,
                           resourceContainerMeasurement, resourceContainer, processingResource, modelAccessFactory);
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
	public void syncResourceEnvironment() {

		// TODO this is only a draft
		InterpreterLogger
				.debug(logger,
						"Synchronise ResourceContainer and Simulated ResourcesContainer");
		// add resource container, if not done already
		for (final ResourceContainer resourceContainer : this.modelAccessFactory.
				getGlobalPCMAccess().getModel().getResourceEnvironment()
				.getResourceContainer_ResourceEnvironment()) {
			final String resourceContainerId = resourceContainer.getId();

			SimulatedResourceContainer simulatedResourceContainer;
			if (getSimuComModel().getResourceRegistry()
					.containsResourceContainer(resourceContainerId)) {
				simulatedResourceContainer = (SimulatedResourceContainer) getSimuComModel()
						.getResourceRegistry().getResourceContainer(
								resourceContainerId);
				InterpreterLogger.debug(logger,
						"SimulatedResourceContainer already exists: "
								+ simulatedResourceContainer);

				// now sync active resources
				this.syncActiveResources(resourceContainer,
						simulatedResourceContainer);
			} else {
				// create
				simulatedResourceContainer = (SimulatedResourceContainer) getSimuComModel()
						.getResourceRegistry().createResourceContainer(
								resourceContainerId);
				InterpreterLogger.debug(logger,
						"Added SimulatedResourceContainer: ID: "
								+ resourceContainerId + " "
								+ simulatedResourceContainer);

				// now sync active resources
				this.syncActiveResources(resourceContainer,
						simulatedResourceContainer);
			}

		}
		InterpreterLogger.debug(logger, "Synchronisation done");
		// TODO remove unused
	}

}
