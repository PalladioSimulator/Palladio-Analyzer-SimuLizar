package de.upb.pcm.interpreter.metrics;


import java.util.ArrayList;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.IStateListener;
import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.pms.Intervall;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.prm.ResourceContainerMeasurement;


/**
 * Utilization performance metric for resources, based on queue length at resources. Interval starts
 * at first change of queue length. Interval limited to simple interval.
 * 
 * @author Joachim Meyer
 * 
 */
public class ResourceStateListener implements IStateListener
{

   private double start = 0.0;

   private double lastSimulationTime = 0.0;

   private final ArrayList<Double> measurements = new ArrayList<Double>();

   private boolean lastTimeNull = false;

   private final ModelHelper modelHelper;

   private final double timeIntervall;

   private final ResourceContainerMeasurement resourceContainerMeasurement;

   private final ResourceContainer resourceContainer;

   private final ProcessingResourceSpecification processingResource;


   /**
    * Constructor
    * 
    * @param processingResourceType the processing resource type (pcm) of the resource.
    * @param abstractScheduledResource the corresponding SimuCom simulated resource of the resource.
    * @param modelHelper the model helper.
    * @param measurementSpecification the measurement specification of the resource container of the
    *           resource.
    * @param resourceContainerMeasurement the resource container measurement of the prm.
    * @param resourceContainer the pcm resource container of the resource.
    * @param processingResource the pcm processing resource specification of the resource.
    */
   public ResourceStateListener(final ProcessingResourceType processingResourceType,
         final AbstractScheduledResource abstractScheduledResource, final ModelHelper modelHelper,
         final MeasurementSpecification measurementSpecification,
         final ResourceContainerMeasurement resourceContainerMeasurement, final ResourceContainer resourceContainer,
         final ProcessingResourceSpecification processingResource)
   {
      super();
      this.modelHelper = modelHelper;
      this.timeIntervall = ((Intervall) measurementSpecification.getTemporalRestriction()).getIntervall();
      lastSimulationTime = modelHelper.getSimuComModel().getSimulationControl().getCurrentSimulationTime();
      this.resourceContainerMeasurement = resourceContainerMeasurement;
      this.resourceContainer = resourceContainer;
      this.processingResource = processingResource;
      abstractScheduledResource.addStateListener(this, 0);
   }


   /**
    * Add measurement for measurement specification and ResourceContainerMeasurement to prm model.
    * Sets the corresponding processing resource type.
    * 
    * @param value the measurement value.
    */
   protected void addToPRM(final double value)
   {
      modelHelper.getGlobalPRMModel().getPcmModelElementMeasurements().remove(this.resourceContainerMeasurement);
      this.resourceContainerMeasurement.setMeasurementValue(value);
      this.resourceContainerMeasurement.setProcessingResourceType(processingResource
            .getActiveResourceType_ActiveResourceSpecification());
      modelHelper.getGlobalPRMModel().getPcmModelElementMeasurements().add(this.resourceContainerMeasurement);
   }


   /**
    * @see de.uka.ipd.sdq.simucomframework.resources.IStateListener#stateChanged(int, int)
    */
   @Override
   public void stateChanged(final int queueLength, final int instanceId)
   {

      final double simulationTime = this.modelHelper.getSimuComModel().getSimulationControl()
            .getCurrentSimulationTime();
      if (lastTimeNull)
      {
         lastTimeNull = false;
         // calculate time of zero jobs
         final double idleTime = simulationTime - lastSimulationTime;
         this.measurements.add(idleTime);
      }
      if (simulationTime <= start + timeIntervall)
      {
         if (queueLength == 0)
         {
            lastTimeNull = true;
         }

      }
      else
      {
         start = simulationTime;
         final double utilization = 1 - (summArray(this.measurements) / timeIntervall);

         addToPRM(utilization);
         /*
          * Value changed, adapt (start sd interpreter), check first if sdm models exists. Reason:
          * SimuLizar only runs in Eclipse Indigo without SD Interpreter. No classes form the SD
          * Interpreter are allowed to be accesed in Indigo by the PCM Interpreter.
          */
         if (this.modelHelper.sdmModelsExists())
         {
            this.modelHelper.getSDExecutor().executeActivities(this.resourceContainer);
         }


         this.measurements.clear();
      }
      this.lastSimulationTime = simulationTime;

   }


   /**
    * Sums double values in the given list.
    * 
    * @param list list of double values.
    * @return the sum of all values.
    */
   private double summArray(final ArrayList<Double> list)
   {
      double sum = 0.0;
      for (final Double idleTime : list)
      {
         sum += idleTime;
      }
      return sum;
   }

}
