package de.upb.pcm.interpreter.switches;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.activation.UnsupportedDataTypeException;

import org.eclipse.emf.common.util.EList;

import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.Parameter;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition;
import de.uka.ipd.sdq.pcm.seff.BranchAction;
import de.uka.ipd.sdq.pcm.seff.CollectionIteratorAction;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.ForkAction;
import de.uka.ipd.sdq.pcm.seff.ForkedBehaviour;
import de.uka.ipd.sdq.pcm.seff.InternalAction;
import de.uka.ipd.sdq.pcm.seff.LoopAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;
import de.uka.ipd.sdq.pcm.seff.SetVariableAction;
import de.uka.ipd.sdq.pcm.seff.StartAction;
import de.uka.ipd.sdq.pcm.seff.performance.ParametricResourceDemand;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.fork.ForkExecutor;
import de.uka.ipd.sdq.simucomframework.fork.ForkedBehaviourProcess;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.upb.pcm.interpreter.access.AllocationAccess;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.exceptions.SimulatedStackAccessException;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter;
import de.upb.pcm.interpreter.metrics.aggregators.ResponseTimeAggregator;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.simulation.InterpreterSimulatedStack;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.interpreter.utils.TransitionDeterminer;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PerformanceMetricEnum;
import de.upb.pcm.prm.PrmFactory;


/**
 * Switch for RFSEFFs
 * 
 * @author Joachim Meyer
 * 
 * @param <T> return type of switch methods.
 */
public class RDSeffSwitch<T> extends AbstractSeffSwitch<T>
{


   private final TransitionDeterminer transitionDeterminer;


   /**
    * Constructor
    * 
    * @param modelInterpreter the corresponding pcm model interpreter holding this switch.
    * @param assemblyContext the assembly context of the component of the SEFF.
    */
   public RDSeffSwitch(final AbstractPCMModelInterpreter modelInterpreter, final AssemblyContext assemblyContext)
   {
      super(modelInterpreter, assemblyContext);
      transitionDeterminer = new TransitionDeterminer(modelInterpreter.getModelHelper().getSimuComModel().getConfig(),
            modelInterpreter);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseBranchAction(de.uka.ipd.sdq.pcm.seff.BranchAction)
    */
   @Override
   public T caseBranchAction(final BranchAction object)
   {
      InterpreterLogger.debug(logger, "Interpret BranchAction: " + object);
      final EList<AbstractBranchTransition> abstractBranchTransitions = object.getBranches_Branch();
      if (abstractBranchTransitions.isEmpty())
      {
         return super.caseBranchAction(object);
      }

      final AbstractBranchTransition branchTransition = transitionDeterminer
            .determineTransition(abstractBranchTransitions);

      /*
       * In case of a guarded transition, it must not necessarily be the case, that any branch
       * condition evaluated to true.
       */

      if (branchTransition == null)
      {
         InterpreterLogger.debug(logger, "No branch's condition evaluated to true, no branch selected: " + object);
      }
      else
      {
         this.doSwitch(branchTransition.getBranchBehaviour_BranchTransition());
      }


      InterpreterLogger.debug(logger, "Finished BranchAction: " + object);
      return super.caseBranchAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseCollectionIteratorAction(de.uka.ipd.sdq.pcm.seff.CollectionIteratorAction)
    */
   @Override
   public T caseCollectionIteratorAction(final CollectionIteratorAction object)
   {
      InterpreterLogger.debug(logger, "Interpret CollectionIteratorAction: " + object);


      iterateOverCollection(object, object.getParameter_CollectionIteratorAction());

      InterpreterLogger.debug(logger, "Finished CollectionIteratorAction: " + object);

      return super.caseCollectionIteratorAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseExternalCallAction(de.uka.ipd.sdq.pcm.seff.ExternalCallAction)
    */
   @Override
   public T caseExternalCallAction(final ExternalCallAction object)
   {
      InterpreterLogger.debug(logger, "Interpret ExternalCallAction: " + object);

      final IModelAccessFactory modelAccessFactory = getModelInterpreter().getModelHelper().getModelAccessFactory();
      // interpret repository
      final AbstractPCMModelInterpreter repositoryInterpreter = modelAccessFactory.getPCMModelInterpreter(
            IModelAccessFactory.REPOSITORY_INTERPRETER, getModelInterpreter().getContext(), null);

      // create new stack frame for input parameter

      getModelInterpreter().getContext().getStack()
            .createAndPushNewStackFrame(object.getInputVariableUsages__CallAction());

      /*
       * Measure Response Time of external calls: Take time sample at the start and a time sample at
       * the end of the called RDSEFF
       */

      final String calculatorName = "call: " + object.getEntityName() + " (id: " + object.getId() + "), AssemblyCtx: "
            + getAssemblyContext().getEntityName() + "(id: " + getAssemblyContext().getId() + ")";
      final String startProbeId = calculatorName + "_resp1";
      final String stopProbeId = calculatorName + "_resp2";

      initReponseTimeMeasurement(object, calculatorName, startProbeId, stopProbeId);

      // ############## Measurement START ##############
      getModelInterpreter().getPCMInterpreterProbeSpecUtil().takeCurrentTimeSample(startProbeId, calculatorName,
            getModelInterpreter().getSimProcess());
      // ############## Measurement END ##############

      repositoryInterpreter.interpret(object.getRole_ExternalService(), object.getCalledService_ExternalService());


      // ############## Measurement START ##############
      getModelInterpreter().getPCMInterpreterProbeSpecUtil().takeCurrentTimeSample(stopProbeId, calculatorName,
            getModelInterpreter().getSimProcess());
      // ############## Measurement END ##############


      InterpreterLogger.debug(logger, "Finished ExternalCallAction: " + object);
      return super.caseExternalCallAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseForkAction(de.uka.ipd.sdq.pcm.seff.ForkAction)
    */
   @Override
   public T caseForkAction(final ForkAction object)
   {
      InterpreterLogger.debug(logger, "Interpret ForkAction: " + object);

      /*
       * Component developers can use a SynchronisationPoint to join synchronously ForkedBehaviours
       * and specify a result of the computations with its attached VariableUsages.
       * 
       * For ForkedBehaviours attached to the SynchronizationPoint, it will be possible to return
       * results of their computations to the initiating ForkAction in future versions of the PCM.
       * Happe (2008) currently defines the necessary meta-model changes.
       * 
       * THIS IS CURRENTLY NOT SUPPORTED BY THE INTERPRETER
       */

      // get asynced processes
      final List<ForkedBehaviourProcess> asyncProcesses = getProcesses(
            object.getAsynchronousForkedBehaviours_ForkAction(), true);

      // get synced processes
      final List<ForkedBehaviourProcess> syncProcesses = determineSyncedProcesses(object);

      // combine both
      final List<ForkedBehaviourProcess> combinedProcesses = combineProcesses(asyncProcesses, syncProcesses);

      // create and start fork executor
      final ForkExecutor forkExecutor = new ForkExecutor(getModelInterpreter().getSimProcess(),
            combinedProcesses.toArray(new ForkedBehaviourProcess[0]));

      InterpreterLogger.debug(logger, "Run fork executor: " + object);
      forkExecutor.run();
      InterpreterLogger.debug(logger, "Finished ForkAction: " + object);

      return super.caseForkAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseInternalAction(de.uka.ipd.sdq.pcm.seff.InternalAction)
    */
   @Override
   public T caseInternalAction(final InternalAction object)
   {
      InterpreterLogger.debug(logger, "Interpret InternalAction: " + object);

      final ModelHelper modelHelper = getModelInterpreter().getModelHelper();
      final AllocationAccess allocationReader = (AllocationAccess) modelHelper.getModelAccessFactory()
            .getPCMModelAccess(IModelAccessFactory.ALLOCATION_ACCESS, getModelInterpreter().getContext());

      final AllocationContext allocationContext = allocationReader.getAllocationContext(getAssemblyContext());

      final ResourceContainer resourceContainer = allocationContext.getResourceContainer_AllocationContext();

      for (final ParametricResourceDemand parametricResourceDemand : object.getResourceDemand_Action())
      {

         final ResourceRegistry resourceRegistry = modelHelper.getSimuComModel().getResourceRegistry();
         final String idParametricResourceDemand = parametricResourceDemand
               .getRequiredResource_ParametricResourceDemand().getId();
         final String specification = parametricResourceDemand.getSpecification_ParametericResourceDemand()
               .getSpecification();
         final SimulatedStackframe<Object> currentStackFrame = getModelInterpreter().getContext().getStack()
               .currentStackFrame();
         final Double value = StackContext.evaluateStatic(specification, Double.class, currentStackFrame);

         resourceRegistry.getResourceContainer(resourceContainer.getId()).loadActiveResource(
               getModelInterpreter().getSimProcess(), idParametricResourceDemand, value);


      }


      InterpreterLogger.debug(logger, "Finished InternalAction: " + object);

      return super.caseInternalAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseLoopAction(de.uka.ipd.sdq.pcm.seff.LoopAction)
    */
   @Override
   public T caseLoopAction(final LoopAction object)
   {
      InterpreterLogger.debug(logger, "Interpret LoopAction: " + object);
      final PCMRandomVariable iterationCount = object.getIterationCount_LoopAction();
      final String stoex = iterationCount.getSpecification();

      // we expect an int here
      final int numberOfLoops = StackContext.evaluateStatic(stoex, Integer.class, getModelInterpreter().getContext()
            .getStack().currentStackFrame());

      InterpreterLogger.debug(logger, "Determined number of loops: " + numberOfLoops + " " + object);

      // interpret behavior the given number of times
      interpretLoop(object, numberOfLoops);

      InterpreterLogger.debug(logger, "Finished LoopAction: " + object);
      return super.caseLoopAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseResourceDemandingBehaviour(de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour)
    */
   @Override
   public T caseResourceDemandingBehaviour(final ResourceDemandingBehaviour object)
   {
      InterpreterLogger.debug(logger, "Interpret ResourceDemandingBehaviour: " + object);

      // interpret start action
      for (final AbstractAction abstractAction : object.getSteps_Behaviour())
      {
         if (abstractAction instanceof StartAction)
         {
            this.doSwitch(abstractAction);
            break;
         }

      }

      InterpreterLogger.debug(logger, "Finished ResourceDemandingBehaviour: " + object);
      return super.caseResourceDemandingBehaviour(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseSetVariableAction(de.uka.ipd.sdq.pcm.seff.SetVariableAction)
    */
   @Override
   public T caseSetVariableAction(final SetVariableAction object)
   {
      InterpreterLogger.debug(logger, "Interpret SetVariableAction: " + object);
      final InterpreterSimulatedStack stack = getModelInterpreter().getContext().getStack();
      stack.addParameterToStackFrame(object.getLocalVariableUsages_SetVariableAction(), getTemporaryResultStackFrame());
      /*
       * Special attention has to be paid if the random variable to set is an INNER
       * characterisation. In this case, a late evaluating random variable has to be stored with the
       * current stack frame as evaluation context (cf. section 4.4.2).
       * 
       * Why?
       */
      InterpreterLogger.debug(logger, "Finished SetVariableAction: " + object);
      return super.caseSetVariableAction(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseStartAction(de.uka.ipd.sdq.pcm.seff.StartAction)
    */
   // @Override
   // public T caseStartAction(final StartAction object)
   // {
   // InterpreterLogger.debug(logger, "Interpret StartAction: " + object);
   //
   // AbstractAction currentAction = object;
   //
   // InterpreterLogger.debug(logger, "Follow action chain");
   // // follow action chain, beginning with start action
   // while ((currentAction = currentAction.getSuccessor_AbstractAction()) != null)
   // {
   // this.doSwitch(currentAction);
   // }
   //
   // InterpreterLogger.debug(logger, "Finished StartAction: " + object);
   // return super.caseStartAction(object);
   // }

   /**
    * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseAbstractAction(de.uka.ipd.sdq.pcm.seff.AbstractAction)
    */
   @Override
   public T caseAbstractAction(final AbstractAction object)
   {
      if (object.getSuccessor_AbstractAction() != null)
      {
         this.doSwitch(object.getSuccessor_AbstractAction());

      }
      return super.caseAbstractAction(object);
   }


   /**
    * Combines synced and asynced processes in a combined list.
    * 
    * @param asyncProcesses list of asynced processes.
    * @param syncProcesses list of synced processes.
    * @return combined list.
    */
   private List<ForkedBehaviourProcess> combineProcesses(final List<ForkedBehaviourProcess> asyncProcesses,
         final List<ForkedBehaviourProcess> syncProcesses)
   {
      final List<ForkedBehaviourProcess> combinedProcesses = new Vector<ForkedBehaviourProcess>();
      combinedProcesses.addAll(asyncProcesses);
      combinedProcesses.addAll(syncProcesses);
      return combinedProcesses;
   }


   /**
    * Determines the synced processes in a fork action.
    * 
    * @param object the fork action.
    * @return a list with synced processes.
    */
   private List<ForkedBehaviourProcess> determineSyncedProcesses(final ForkAction object)
   {
      List<ForkedBehaviourProcess> syncProcesses = new ArrayList<ForkedBehaviourProcess>();

      if (object.getSynchronisingBehaviours_ForkAction() != null)
      {
         syncProcesses = getProcesses(object.getSynchronisingBehaviours_ForkAction()
               .getSynchronousForkedBehaviours_SynchronisationPoint(), false);
      }
      return syncProcesses;
   }


   /**
    * Creates a list of sync and async processes for given behaviors.
    * 
    * @param forkedBehaviours the forked behaviors, independent of their sync or async character.
    * @param isAsync true if processes shall be async, otherwise false.
    * @return a list of configured forked behavior processes.
    */
   private List<ForkedBehaviourProcess> getProcesses(final List<ForkedBehaviour> forkedBehaviours, final boolean isAsync)
   {
      final List<ForkedBehaviourProcess> syncProcesses = new Vector<ForkedBehaviourProcess>();

      // for each create process, and add to array of processes

      for (final ForkedBehaviour forkedBehaviour : forkedBehaviours)
      {

         syncProcesses.add(new ForkedBehaviourProcess(getModelInterpreter().getContext(), getAssemblyContext().getId(),
               isAsync)
         {

            @Override
            protected void executeBehaviour()
            {

               /*
                * The forked behavior process has its own copied stack in its context, for type
                * reasons we need an InterpreterDefaultContext. Thus we have to copy the context
                * including its stack.
                */
               final IModelAccessFactory modelAccessFactory = getModelInterpreter().getModelHelper()
                     .getModelAccessFactory();

               final AbstractPCMModelInterpreter seffInterpreter = modelAccessFactory.getPCMModelInterpreter(
                     IModelAccessFactory.RDSEFF_INTERPRETER, new InterpreterDefaultContext(ctx), getAssemblyContext());

               InterpreterLogger.debug(logger, "Created new RDSeff interpreter for "
                     + ((isAsync()) ? "asynced" : "synced") + " forked baviour: " + this);
               seffInterpreter.interpret(forkedBehaviour);
            }

         });
      }
      return syncProcesses;
   }


   /**
    * Initializes response time measurement.
    * 
    * @param object the ExternalCallAction to be measured.
    * @param calculatorName the name of the response time calculator.
    * @param startProbeId start probe id.
    * @param stopProbeId stop probe id.
    */
   private void initReponseTimeMeasurement(final ExternalCallAction object, final String calculatorName,
         final String startProbeId, final String stopProbeId)
   {
      final IModelAccessFactory modelAccessFactory = getModelInterpreter().getModelHelper().getModelAccessFactory();
      final PMSAccess pmsAccess = (PMSAccess) modelAccessFactory.getPMSModelAccess();
      MeasurementSpecification measurementSpecification;
      if ((measurementSpecification = pmsAccess.isMonitored(object, PerformanceMetricEnum.RESPONSE_TIME)) != null)
      {

         final Calculator calculator = getModelInterpreter().getPCMInterpreterProbeSpecUtil()
               .createResponseTimeCalculator(startProbeId, stopProbeId, calculatorName, calculatorName,
                     measurementSpecification, object, getModelInterpreter().getModelHelper().getSimuComModel());

         if (calculator != null)
         {
            try
            {
               new ResponseTimeAggregator(measurementSpecification, calculator, calculatorName, object,
                     getModelInterpreter().getModelHelper(), PrmFactory.eINSTANCE.createPCMModelElementMeasurement());
            }
            catch (final UnsupportedDataTypeException e)
            {
               e.printStackTrace();
            }
         }
      }
   }


   /**
    * Interpret inner path of loop the given times
    * 
    * @param object the LoopAction.
    * @param numberOfLoops number of loops.
    */
   private void interpretLoop(final LoopAction object, final int numberOfLoops)
   {
      for (int i = 0; i < numberOfLoops; i++)
      {
         InterpreterLogger.debug(logger, "Interpret loop number " + i + ": " + object);
         this.doSwitch(object.getBodyBehaviour_Loop());
         InterpreterLogger.debug(logger, "Finished loop number " + i + ": " + object);
      }
   }


   /**
    * Iterates over collection of given CollectionIteratorAction.
    * 
    * @param object the CollectionIteratorAction.
    * @param parameterthe parameter of the collection.
    */
   private void iterateOverCollection(final CollectionIteratorAction object, final Parameter parameter)
   {
      // TODO make better
      final String idNumberOfLoops = parameter.getParameterName() + ".NUMBER_OF_ELEMENTS";

      // get number of loops
      final int numberOfLoops = StackContext.evaluateStatic(idNumberOfLoops, Integer.class, getModelInterpreter()
            .getContext().getStack().currentStackFrame());

      InterpreterLogger.debug(logger, "Determined number of loops: " + numberOfLoops + " " + object);
      for (int i = 0; i < numberOfLoops; i++)
      {
         InterpreterLogger.debug(logger, "Interpret loop number " + i + ": " + object);

         // create new stack frame for value characterizations of inner collection variable
         final SimulatedStackframe<Object> innerVariableStackFrame = getModelInterpreter().getContext().getStack()
               .createAndPushNewStackFrame(getModelInterpreter().getContext().getStack().currentStackFrame());

         /*
          * evaluate value characterization of inner collection variable, store them on created top
          * most stack frame. Add a . at the end of the parameter name because otherwise if we
          * search for parameter name "ab" we also get variables called "abc"
          */
         // TODO the point is not nice
         this.getModelInterpreter().getContext()
               .evaluateInner(innerVariableStackFrame, parameter.getParameterName() + ".");

         InterpreterLogger.debug(logger, "Created new stackframe with evaluated inner collection variables: "
               + innerVariableStackFrame);

         /*
          * now further access on inner variables are caught in the current top most frame. In other
          * words, they are currently overridden with their evaluated values. This has the effect
          * that actions within the iterator use the same evaluated values. This is very important
          * in case of EvaluationProxys which should not be reevaluated for each action within an
          * iteration.
          */

         this.doSwitch(object.getBodyBehaviour_Loop());

         // remove stack frame for value characterisations of inner collection variable
         if (getModelInterpreter().getContext().getStack().currentStackFrame() != innerVariableStackFrame)
         {
            throw new SimulatedStackAccessException(
                  "Inner value characterisations of inner collection variable expected");
         }

         InterpreterLogger.debug(logger, "Remove stack frame: " + innerVariableStackFrame);
         getModelInterpreter().getContext().getStack().removeStackFrame();


         InterpreterLogger.debug(logger, "Finished loop number " + i + ": " + object);
      }
   }
}
