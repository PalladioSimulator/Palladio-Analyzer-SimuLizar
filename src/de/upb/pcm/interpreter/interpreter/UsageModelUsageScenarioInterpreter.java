package de.upb.pcm.interpreter.interpreter;


import javax.activation.UnsupportedDataTypeException;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.metrics.aggregators.ResponseTimeAggregator;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.switches.UsageModelUsageScenarioSwitch;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PerformanceMetricEnum;
import de.upb.pcm.prm.PrmFactory;


/**
 * Interpreter for usage scenario in usage model. Class implements an IScenarioRunner and can be
 * used in SimuCom workload drivers.
 * 
 * @author Joachim Meyer
 * 
 */
public class UsageModelUsageScenarioInterpreter extends AbstractPCMModelInterpreter implements IScenarioRunner
{

   /**
    * Constructor
    * 
    * @param contex the interpreter default context for the pcm model interpreter, may be null.
    * @param modelHelper the model helper.
    */
   public UsageModelUsageScenarioInterpreter(final InterpreterDefaultContext context, final ProbeSpecContext probeSpecContext, final ModelHelper modelHelper)
   {
      super(context, probeSpecContext, modelHelper);
   }


   /**
    * @see de.upb.pcm.interpreter.access.AbstractPCMModelAccess#getModel()
    */
   @Override
   public UsageModel getModel()
   {
      if (this.getSimProcess() != null)
      {
         return getModelHelper().getLocalPCMModels(this.getSimProcess()).getUsageModel();
      }
      return getModelHelper().getGlobalPCMModels().getUsageModel();
   }


   /**
    * @see de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter#getModelSwitch()
    */
   @Override
   protected UsageModelUsageScenarioSwitch<Object> getModelSwitch()
   {
      return new UsageModelUsageScenarioSwitch<Object>(this);
   }


   /**
    * Initializes response time measurement.
    * 
    * @param modelElement the model element to be measured.
    * @param calculatorName the name of the response time calculator.
    * @param startProbeId start probe id.
    * @param stopProbeId stop probe id.
    */
   private void initReponseTimeMeasurement(final EObject modelElement, final String calculatorName,
         final String startProbeId, final String stopProbeId)
   {
      final PMSAccess pmsAccess = (PMSAccess) getModelHelper().getModelAccessFactory().getPMSModelAccess();
      MeasurementSpecification measurementSpecification;
      if ((measurementSpecification = pmsAccess.isMonitored(modelElement, PerformanceMetricEnum.RESPONSE_TIME)) != null)
      {

         final Calculator calculator = this.getPCMInterpreterProbeSpecUtil().createResponseTimeCalculator(startProbeId,
               stopProbeId, calculatorName, calculatorName, measurementSpecification, modelElement,
               getModelHelper().getSimuComModel());

         if (calculator != null)
         {
            try
            {
               new ResponseTimeAggregator(measurementSpecification, calculator, calculatorName, modelElement,
                     getModelHelper(), PrmFactory.eINSTANCE.createPCMModelElementMeasurement());
            }
            catch (final UnsupportedDataTypeException e)
            {
               e.printStackTrace();
            }
         }
      }


   }


   /**
    * @see de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner#scenarioRunner(de.uka.ipd.sdq.simucomframework.abstractSimEngine.SimProcess)
    */
   @Override
   public void scenarioRunner(final SimuComSimProcess thread)
   {
      InterpreterLogger.debug(logger, "Start scenario: " + getModelHelper().getSimuComModel());
      // create context for scenario
      final InterpreterDefaultContext context = new InterpreterDefaultContext(getModelHelper().getSimuComModel(),
            thread);
      this.setContext(context);

      InterpreterLogger.debug(logger, "Created context: " + context);

      // interpret usage scenario in local model
      this.interpret(getModel().getUsageScenario_UsageModel().get(0));

      InterpreterLogger.debug(logger, "Scenario finished: " + getModel().getUsageScenario_UsageModel().get(0));

   }


   /**
    * @see de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter#startInterpretation(org.eclipse.emf.ecore.EObject,
    *      de.upb.pcm.interpreter.interfaces.IPCMModelSwitch)
    */
   @Override
   protected void startInterpretation(final EObject startElement, final Object... o)
   {
      InterpreterLogger.debug(logger, "Start Interpretation of Usage Scenario: " + getModel());
      if (!(startElement instanceof UsageScenario))
      {
         throw new IllegalArgumentException("startElement must be of type UsageScenario");
      }

      /*
       * Measure Response Time of Usage Scenario Steps: Take time sample at the start and a time
       * sample at the end of the usage scenario
       */

      final String calculatorName = ((UsageScenario) startElement).getEntityName();
      final String startProbeId = calculatorName + "_resp1";
      final String stopProbeId = calculatorName + "_resp2";

      initReponseTimeMeasurement(startElement, calculatorName, startProbeId, stopProbeId);

      // ############## Measurement START ##############
      getPCMInterpreterProbeSpecUtil().takeCurrentTimeSample(startProbeId, calculatorName, getSimProcess());
      // ############## Measurement END ##############

      // start visitor for usage scenario
      getModelSwitch().doSwitch(startElement);

      // ############## Measurement START ##############
      getPCMInterpreterProbeSpecUtil().takeCurrentTimeSample(stopProbeId, calculatorName, getSimProcess());
      // ############## Measurement END ##############


      InterpreterLogger.debug(logger, "Finished Interpretation of Usage Scenario: " + getModel());


   }

}
