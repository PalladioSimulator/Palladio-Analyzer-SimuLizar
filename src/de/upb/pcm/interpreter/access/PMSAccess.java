package de.upb.pcm.interpreter.access;


import org.eclipse.emf.ecore.EObject;

import de.upb.pcm.interpreter.access.internal.ModelHelper;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PMSModel;
import de.upb.pcm.pms.PerformanceMeasurement;
import de.upb.pcm.pms.PerformanceMetricEnum;


/**
 * Access class for pms model.
 * 
 * @author Joachim Meyer
 */
public class PMSAccess extends AbstractModelAccess<PMSModel>
{


   /**
    * Constructor
    * 
    * @param modelHelper the model helper.
    */
   public PMSAccess(final ModelHelper modelHelper)
   {
      super(modelHelper);
   }

   /**
    * @return the pms model.
    */
   @Override
   public PMSModel getModel()
   {
      return getModelHelper().getGlobalPMSModel();
   }

   /**
    * Method checks if given element should be monitored with given performance metric. If yes, it
    * will return the corresponding MeasurementSpecification, otherwise null.
    * 
    * @param element the element to be checked.
    * @param performanceMetric the performance metric.
    * @return the MeasurementSpecification, if element should be monitored according to given
    *         performance metric, otherwise null
    */
   public MeasurementSpecification isMonitored(EObject element, PerformanceMetricEnum performanceMetric)
   {
      if (getModelHelper().pmsModelExists())
      {
         for (PerformanceMeasurement performanceMeasurement : this.getModel().getPerformanceMeasurements())
         {
            if (performanceMeasurement.getPcmElementClassifier().getClassifierID() == element.eClass()
                  .getClassifierID())
            {
               for (MeasurementSpecification measurementSpecification : performanceMeasurement
                     .getMeasurementSpecification())
               {
                  if (measurementSpecification.getPerformanceMetric() == performanceMetric)
                  {
                     return measurementSpecification;
                  }
               }

            }
         }
      }

      return null;
   }
}
