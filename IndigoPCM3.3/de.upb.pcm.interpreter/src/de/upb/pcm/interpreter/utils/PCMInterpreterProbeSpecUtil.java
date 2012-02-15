package de.upb.pcm.interpreter.utils;


import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.probespec.framework.ISampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.CalculatorRegistry;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory;
import de.uka.ipd.sdq.probespec.framework.calculator.ResponseTimeCalculator;
import de.uka.ipd.sdq.probespec.framework.probes.IProbeStrategy;
import de.uka.ipd.sdq.probespec.framework.utils.ProbeSpecUtils;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.processes.SimulatedProcess;
import de.upb.pcm.pms.MeasurementSpecification;


/**
 * Util for the Probe Specification Framework.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMInterpreterProbeSpecUtil
{

   protected static final Logger logger = Logger.getLogger(PCMInterpreterProbeSpecUtil.class.getName());

   final ProbeSpecContext probeSpecContext;
   final ISampleBlackboard blackboard;

   private final ModelHelper modelHelper;


   /**
    * Constructor
    * 
    * @param modelHelper the model helper.
    */
   public PCMInterpreterProbeSpecUtil(final ProbeSpecContext probeSpecContext, final ModelHelper modelHelper)
   {
      super();
      this.modelHelper = modelHelper;
      this.probeSpecContext = probeSpecContext;
      this.blackboard = probeSpecContext.getSampleBlackboard();

   }


   /**
    * Creates a response time calculator in the probe specification framework.
    * 
    * @param startProbeId id of the start probe.
    * @param stopProbeId id of the stop probe.
    * @param measurementId id of this measurement.
    * @param calculatorName name of the response time calculator
    * @param measurementSpecification the corresponding measurement specification from the pms
    *           model.
    * @param pcmElement the pcm element to be measured.
    * @param simuComModel the SimuCom model.
    * @return the created calculator, null if calculator already exists.
    */
   public Calculator createResponseTimeCalculator(final String startProbeId, final String stopProbeId,
         final String measurementId, final String calculatorName,
         final MeasurementSpecification measurementSpecification, final EObject pcmElement,
         final SimuComModel simuComModel)
   {

      ResponseTimeCalculator responseTimeCalculator = null;
      final CalculatorRegistry calculatorRegistry = probeSpecContext.getCalculatorRegistry();
      // only register each calculator once
      if (calculatorRegistry.getCalculatorForId(measurementId) == null)
      {
         final ICalculatorFactory calculatorFactory = probeSpecContext.getCalculatorFactory();

         final Calculator responseCalculator = calculatorFactory.buildResponseTimeCalculator(calculatorName,
               probeSpecContext.obtainProbeSetId(startProbeId), probeSpecContext.obtainProbeSetId(stopProbeId));

         calculatorRegistry.registerCalculator(measurementId, responseCalculator);

         InterpreterLogger.debug(logger, "Created Response Time Calculator. StartProbeId: " + startProbeId
               + ", StopProbeId: " + stopProbeId);

         // get created calculator
         responseTimeCalculator = (ResponseTimeCalculator) calculatorRegistry.getCalculatorForId(measurementId);

         return responseTimeCalculator;

      }
      return null;

   }


   /**
    * @return returns the blackboard.
    */
   private ISampleBlackboard getBlackboard()
   {
      return this.blackboard;
   }


   /**
    * Takes current time sample.
    * 
    * @param probeID the start or stop probe id.
    * @param measurementId id of the measurement.
    * @param simProcess the sim process.
    */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public void takeCurrentTimeSample(final String probeID, final String measurementId, final SimulatedProcess simProcess)
   {
      final IProbeStrategy timeStrategy = probeSpecContext.getProbeStrategyRegistry()
            .getProbeStrategy(ProbeType.CURRENT_TIME, null);

      final ProbeSample probeSample = timeStrategy.takeSample(probeID, this.modelHelper.getSimuComModel()
            .getSimulationControl());

      final ProbeSetSample probeSampleSet = ProbeSpecUtils.buildProbeSetSample(probeSample,
            simProcess.getRequestContext(), measurementId, ProbeSpecContext.instance().obtainProbeSetId(probeID));

      InterpreterLogger.debug(logger, "Took probe " + probeID + " of " + measurementId);

      getBlackboard().addSample(probeSampleSet);
   }


}
