package de.upb.pcm.interpreter.metrics;


import org.eclipse.emf.ecore.EObject;

import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.TemporalCharacterization;
import de.upb.pcm.prm.PCMModelElementMeasurement;
import de.upb.pcm.prm.PRMModel;


/**
 * Recorder for saving measurement of a measurement specification and pcm model element in prm
 * model. Can be used as base class for aggregators or performance metrics.
 * 
 * @author Joachim Meyer
 * 
 */
public abstract class PRMRecorder
{
   private PCMModelElementMeasurement pcmModelElementMeasurement;

   private final ModelHelper modelHelper;

   private final MeasurementSpecification measurementSpecification;

   private final PRMModel prmModel;

   private final TemporalCharacterization temporalCharacterization;


   /**
    * Constructor
    * 
    * @param measurementSpecification the measurement specification.
    * @param modelHelper the model helper.
    * @param pcmModelElementMeasurement the prm PCMModelElementMeasurement.
    */
   public PRMRecorder(final MeasurementSpecification measurementSpecification, final ModelHelper modelHelper,
         final PCMModelElementMeasurement pcmModelElementMeasurement)
   {
      super();
      this.pcmModelElementMeasurement = getPCMModelElementMeasurement();
      this.modelHelper = modelHelper;
      this.prmModel = modelHelper.getGlobalPRMModel();
      this.measurementSpecification = measurementSpecification;
      this.temporalCharacterization = measurementSpecification.getTemporalRestriction();
      this.pcmModelElementMeasurement = pcmModelElementMeasurement;
   }


   /**
    * Add measurement for measurement specification and PCMModelElementMeasurement to prm model.
    * 
    * @param value the measurement value.
    */
   protected void addToPRM(final double value)
   {
      if (this.pcmModelElementMeasurement.getPcmModelElement() == null)
      {
         this.pcmModelElementMeasurement.setPcmModelElement(getMonitoredPCMModellElement());
      }
      this.prmModel.getPcmModelElementMeasurements().remove(this.pcmModelElementMeasurement);
      this.pcmModelElementMeasurement.setMeasurementValue(value);
      this.pcmModelElementMeasurement.setMeasurementSpecification(this.measurementSpecification);
      this.prmModel.getPcmModelElementMeasurements().add(this.pcmModelElementMeasurement);
   }


   /**
    * @return returns the measurementSpecification.
    */
   protected MeasurementSpecification getMeasurementSpecification()
   {
      return this.measurementSpecification;
   }


   /**
    * @return returns the modelHelper.
    */
   protected ModelHelper getModelHelper()
   {
      return this.modelHelper;
   }


   /**
    * Gets the monitored pcm model element.
    * 
    * @return the monitored pcm model element.
    */
   protected abstract EObject getMonitoredPCMModellElement();


   /**
    * @return returns the pcmModelElementMeasurement.
    */
   protected final PCMModelElementMeasurement getPCMModelElementMeasurement()
   {
      return this.pcmModelElementMeasurement;
   }


   /**
    * @return returns the prmModel.
    */
   protected PRMModel getPrmModel()
   {
      return this.prmModel;
   }


   /**
    * @return returns the temporalCharacterization.
    */
   protected TemporalCharacterization getTemporalCharacterization()
   {
      return this.temporalCharacterization;
   }


}
