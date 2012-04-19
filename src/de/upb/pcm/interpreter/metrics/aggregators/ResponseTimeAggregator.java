package de.upb.pcm.interpreter.metrics.aggregators;


import java.util.Vector;

import javax.activation.UnsupportedDataTypeException;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.metrics.PRMRecorder;
import de.upb.pcm.pms.Intervall;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.StatisticalCharacterizationEnum;
import de.upb.pcm.prm.PCMModelElementMeasurement;


/**
 * The aggregator "Response time".
 * 
 * @author Joachim Meyer
 * 
 */
public class ResponseTimeAggregator extends PRMRecorder implements ICalculatorListener
{


   private final Vector<Double> responseTimes;

   private final IStatisticalCharacterization aggregator;

   private double lastSimulationTime;

   private final EObject monitoredElement;


   /**
    * Constructor
    * 
    * @param measurementSpecification the measurement specification.
    * @param responseTimeCalculator the response time calculator of the probe specification
    *           framework.
    * @param measurementId id of the measurement.
    * @param monitoredElement the pcm model element to be monitored.
    * @param modelHelper the model helper.
    * @param pcmModelElementMeasurement the PCMModelElementMeasurement from the prm model.
    * @throws UnsupportedDataTypeException if statistical characterization is not supported.
    */
   public ResponseTimeAggregator(
		   final IModelAccessFactory modelAccessFactory,
		   final MeasurementSpecification measurementSpecification,
		   final Calculator responseTimeCalculator, 
		   final String measurementId, 
		   final EObject monitoredElement,
           final PCMModelElementMeasurement pcmModelElementMeasurement,
           double baseSimulationTime)
         throws UnsupportedDataTypeException
   {
      super(modelAccessFactory, measurementSpecification, pcmModelElementMeasurement);
      this.responseTimes = new Vector<Double>();
      if (measurementSpecification.getStatisticalCharacterization() == StatisticalCharacterizationEnum.ARITHMETIC_MEAN)
      {
         this.aggregator = new ArithmeticMean();
      }
      else
      {
         throw new UnsupportedDataTypeException("Only Arithmetic Mean is currently supported");
      }
      if (!(measurementSpecification.getTemporalRestriction() instanceof Intervall))
      {
         throw new UnsupportedDataTypeException("Only Intervall is currently supported");
      }
      this.lastSimulationTime = baseSimulationTime;
      this.monitoredElement = monitoredElement;

      responseTimeCalculator.addCalculatorListener(this);
   }


   /**
    * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener#calculated(java.util.Vector)
    */
   @Override
   public void calculated(final Vector<Measure<?, ? extends Quantity>> resultTuple)
   {

      final double simulationTime = (Double) resultTuple.get(1).getValue();
      if (this.getMeasurementSpecification().getTemporalRestriction() instanceof Intervall)
      {
         final Intervall intervall = (Intervall) this.getMeasurementSpecification().getTemporalRestriction();
         if (simulationTime - this.lastSimulationTime >= intervall.getIntervall())
         {
            // calculate StatisticalCharacterization
            final double statisticalCharacterization = this.aggregator
                  .calculateStatisticalCharaterization(this.responseTimes);
            addToPRM(statisticalCharacterization);
            /*
             * Value changed, adapt (start sd interpreter), check first if sdm models exists.
             * Reason: SimuLizar only runs in Eclipse Indigo without SD Interpreter. No classes form
             * the SD Interpreter are allowed to be accesed in Indigo by the PCM Interpreter.
             */
            // TODO: Refactor this, this should not be here at all!
            //if (this.getModelHelper().sdmModelsExists())
            //{
            //   this.getModelHelper().getSDExecutor().executeActivities(this.getMonitoredPCMModellElement());
            //}

            this.lastSimulationTime = simulationTime;
            this.responseTimes.clear();

         }
      }

      responseTimes.add((Double) resultTuple.get(0).getValue());

   }


   /**
    * @see de.upb.pcm.interpreter.metrics.PRMRecorder#getMonitoredPCMModellElement()
    */
   @Override
   protected EObject getMonitoredPCMModellElement()
   {
      return this.monitoredElement;
   }

}
