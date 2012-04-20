package de.upb.pcm.interpreter.switches;

import javax.activation.UnsupportedDataTypeException;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.usagemodel.AbstractUserAction;
import de.uka.ipd.sdq.pcm.usagemodel.Branch;
import de.uka.ipd.sdq.pcm.usagemodel.BranchTransition;
import de.uka.ipd.sdq.pcm.usagemodel.Delay;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.Loop;
import de.uka.ipd.sdq.pcm.usagemodel.ScenarioBehaviour;
import de.uka.ipd.sdq.pcm.usagemodel.Start;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.interfaces.IPCMModelSwitch;
import de.upb.pcm.interpreter.interpreter.RepositoryInterpreter;
import de.upb.pcm.interpreter.metrics.aggregators.ResponseTimeAggregator;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.PCMInterpreterProbeSpecUtil;
import de.upb.pcm.interpreter.utils.TransitionDeterminer;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PerformanceMetricEnum;
import de.upb.pcm.prm.PrmFactory;

/**
 * Switch for Usage Scenario in Usage Model
 * 
 * @author Joachim Meyer
 * 
 * @param <T> return type of switch methods.
 */

public class UsageModelUsageScenarioSwitch<T> extends UsagemodelSwitch<T> implements IPCMModelSwitch<T>
{
   protected static final Logger logger = Logger.getLogger(UsageModelUsageScenarioSwitch.class.getName());

   private final InterpreterDefaultContext context;
   private final TransitionDeterminer transitionDeterminer;
   private final IModelAccessFactory modelAccessFactory;
   private final PCMInterpreterProbeSpecUtil probeSpecUtil;

   /**
    * Constructor
    * 
    * @param modelInterpreter the corresponding pcm model interpreter holding this switch..
    */
   public UsageModelUsageScenarioSwitch(final InterpreterDefaultContext context,
		   final IModelAccessFactory modelAccessFactory,
		   final PCMInterpreterProbeSpecUtil probeSpecUtil)
   {
	   this.context = context;
	   this.modelAccessFactory = modelAccessFactory;
      transitionDeterminer = new TransitionDeterminer(context);
      this.probeSpecUtil = probeSpecUtil;
   }


   /**
    * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseBranch(de.uka.ipd.sdq.pcm.usagemodel.Branch)
    */
   @Override
   public T caseBranch(final Branch object)
   {
      InterpreterLogger.debug(logger, "Interpret Branch: " + object);

      // determine branch transition
      final BranchTransition branchTransition = transitionDeterminer.determineBranchTransition(object
            .getBranchTransitions_Branch());

      // interpret scenario behaviour of branch transition
      this.doSwitch(branchTransition.getBranchedBehaviour_BranchTransition());

      InterpreterLogger.debug(logger, "Finished Branch: " + object);
      return super.caseBranch(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseDelay(de.uka.ipd.sdq.pcm.usagemodel.Delay)
    */
   @Override
   public T caseDelay(final Delay object)
   {
      InterpreterLogger.debug(logger, "Interpret Delay: " + object);
      // determine delay
      final double delay = StackContext.evaluateStatic(object.getTimeSpecification_Delay().getSpecification(),
            Double.class);
      InterpreterLogger.debug(logger, "Start delay "
            + delay
            + " @ simulation time "
            + context.getModel().getSimulationControl()
                  .getCurrentSimulationTime());
      // hold simulation process
      this.context.getThread().hold(delay);
      InterpreterLogger.debug(logger, "Continue user @ simulation time "
            + this.context.getModel().getSimulationControl()
                  .getCurrentSimulationTime());
      InterpreterLogger.debug(logger, "Finished Delay: " + object);
      return super.caseDelay(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseEntryLevelSystemCall(de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall)
    */
   @Override
   public T caseEntryLevelSystemCall(final EntryLevelSystemCall entryLevelSystemCall)
   {
      InterpreterLogger.debug(logger, "Interpret EntryLevelSystemCall: " + entryLevelSystemCall);

      final RepositoryInterpreter repositoryInterpreter = modelAccessFactory.getRepositoryInterpreter(
            context);

      // create new stack frame for input parameter
      context.getStack()
            .createAndPushNewStackFrame(entryLevelSystemCall.getInputParameterUsages_EntryLevelSystemCall());

      /*
       * Measure Response Time of external calls: Take time sample at the start and a time sample at
       * the end of the called RDSEFF
       */

      final String calculatorName = "entry level call: " + entryLevelSystemCall.getEntityName() + " (id: "
            + entryLevelSystemCall.getId() + ")";
      final String startProbeId = calculatorName + "_resp1";
      final String stopProbeId = calculatorName + "_resp2";

      initReponseTimeMeasurement(entryLevelSystemCall, calculatorName, startProbeId, stopProbeId);

      // ############## Measurement START ##############
      this.probeSpecUtil.takeCurrentTimeSample(startProbeId, calculatorName,
            context.getThread());
      // ############## Measurement END ##############

      repositoryInterpreter.interpret(entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall(),
            entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall());

      // ############## Measurement START ##############
     this.probeSpecUtil.takeCurrentTimeSample(stopProbeId, calculatorName,
            context.getThread());
      // ############## Measurement END ##############


      InterpreterLogger.debug(logger, "Finished EntryLevelSystemCall: " + entryLevelSystemCall);
      return super.caseEntryLevelSystemCall(entryLevelSystemCall);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseLoop(de.uka.ipd.sdq.pcm.usagemodel.Loop)
    */
   @Override
   public T caseLoop(final Loop object)
   {
      InterpreterLogger.debug(logger, "Interpret Loop: " + object);
      // determine number of loops
      final int numberOfLoops = StackContext.evaluateStatic(object.getLoopIteration_Loop().getSpecification(),
            Integer.class);
      for (int i = 0; i < numberOfLoops; i++)
      {
         InterpreterLogger.debug(logger, "Interpret loop number " + i);
         this.doSwitch(object.getBodyBehaviour_Loop());
         InterpreterLogger.debug(logger, "Finished loop number " + i);

      }
      InterpreterLogger.debug(logger, "Finished Loop: " + object);
      return super.caseLoop(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseScenarioBehaviour(de.uka.ipd.sdq.pcm.usagemodel.ScenarioBehaviour)
    */
   @Override
   public T caseScenarioBehaviour(final ScenarioBehaviour object)
   {
      InterpreterLogger.debug(logger, "Interpret ScenarioBehaviour: " + object);

      // interpret start user action
      for (final AbstractUserAction abstractUserAction : object.getActions_ScenarioBehaviour())
      {
         if (abstractUserAction instanceof Start)
         {
            this.doSwitch(abstractUserAction);
            break;
         }

      }

      InterpreterLogger.debug(logger, "Finished ScenarioBehaviour: " + object);
      return super.caseScenarioBehaviour(object);
   }


   // /**
   // * @see
   // de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseStart(de.uka.ipd.sdq.pcm.usagemodel.Start)
   // */
   // @Override
   // public T caseStart(final Start object)
   // {
   // InterpreterLogger.debug(logger, "Interpret Start: " + object);
   //
   // AbstractUserAction currentAction = object;
   //
   // InterpreterLogger.debug(logger, "Follow action chain");
   // // follow action chain, beginning with start action
   // while ((currentAction = currentAction.getSuccessor()) != null)
   // {
   // this.doSwitch(currentAction);
   // }
   //
   // InterpreterLogger.debug(logger, "Finished start: " + object);
   // return super.caseStart(object);
   // }

   /**
    * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseAbstractUserAction(de.uka.ipd.sdq.pcm.usagemodel.AbstractUserAction)
    */
   @Override
   public T caseAbstractUserAction(final AbstractUserAction object)
   {
      if (object.getSuccessor() != null)
      {
         this.doSwitch(object.getSuccessor());

      }
      return super.caseAbstractUserAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseUsageScenario(de.uka.ipd.sdq.pcm.usagemodel.UsageScenario)
    */
   @Override
   public T caseUsageScenario(final UsageScenario object)
   {
      this.doSwitch(object.getScenarioBehaviour_UsageScenario());
      return super.caseUsageScenario(object);
   }

   /**
    * Initializes response time measurement.
    * 
    * @param entryLevelSystemCall the entryLevelSystemCall to be measured.
    * @param calculatorName the name of the response time calculator.
    * @param startProbeId start probe id.
    * @param stopProbeId stop probe id.
    */
   private void initReponseTimeMeasurement(final EntryLevelSystemCall entryLevelSystemCall,
         final String calculatorName, final String startProbeId, final String stopProbeId)
   {
      final PMSAccess pmsAccess = (PMSAccess) modelAccessFactory.getPMSModelAccess();
      MeasurementSpecification measurementSpecification;
      if ((measurementSpecification = pmsAccess.isMonitored(entryLevelSystemCall, PerformanceMetricEnum.RESPONSE_TIME)) != null)
      {
         final Calculator calculator = this.probeSpecUtil
               .createResponseTimeCalculator(startProbeId, stopProbeId, calculatorName, calculatorName,
                     measurementSpecification, entryLevelSystemCall,
                     context.getModel());

         if (calculator != null)
         {
            try
            {
               new ResponseTimeAggregator(this.modelAccessFactory, measurementSpecification, calculator, calculatorName, entryLevelSystemCall,
                     PrmFactory.eINSTANCE.createPCMModelElementMeasurement(),
                     this.context.getModel().getSimulationControl().getCurrentSimulationTime());
            }
            catch (final UnsupportedDataTypeException e)
            {
               e.printStackTrace();
            }
         }
      }
   }
}
