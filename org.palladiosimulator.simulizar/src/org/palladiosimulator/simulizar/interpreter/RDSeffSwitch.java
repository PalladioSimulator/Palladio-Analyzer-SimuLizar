package org.palladiosimulator.simulizar.interpreter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.simulizar.access.AllocationAccess;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.exceptions.SimulatedStackAccessException;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.RDSEFFElementPassedEvent;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;
import org.palladiosimulator.simulizar.utils.TransitionDeterminer;

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
import de.uka.ipd.sdq.pcm.seff.seff_performance.ParametricResourceDemand;
import de.uka.ipd.sdq.pcm.seff.util.SeffSwitch;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.fork.ForkExecutor;
import de.uka.ipd.sdq.simucomframework.fork.ForkedBehaviourProcess;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * Switch for RFSEFFs. This visitor is responsible for traversing RDSEFF behaviours.
 * 
 * @author Joachim Meyer, Steffen Becker
 * 
 */
class RDSeffSwitch extends SeffSwitch<SimulatedStackframe<Object>> {
    private final static Logger logger = Logger.getLogger(RDSeffSwitch.class);
    private final TransitionDeterminer transitionDeterminer;
    private final InterpreterDefaultContext context;
    private final IModelAccessFactory modelAccessFactory;

    private final SimulatedStackframe<Object> resultStackFrame;

    /**
     * Constructor.
     * 
     * @param modelInterpreter
     *            the corresponding pcm model interpreter holding this switch.
     * @param assemblyContext
     *            the assembly context of the component of the SEFF.
     */
    public RDSeffSwitch(final InterpreterDefaultContext context, final IModelAccessFactory interpreterFactory,
            final AssemblyContext assemblyContext) {
        super();
        this.modelAccessFactory = interpreterFactory;
        this.context = context;
        this.transitionDeterminer = new TransitionDeterminer(context);
        this.resultStackFrame = new SimulatedStackframe<Object>();
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseBranchAction(de.uka.ipd.sdq.pcm.seff.BranchAction)
     */
    @Override
    public SimulatedStackframe<Object> caseBranchAction(final BranchAction object) {
        final EList<AbstractBranchTransition> abstractBranchTransitions = object.getBranches_Branch();
        if (abstractBranchTransitions.isEmpty()) {
            return super.caseBranchAction(object);
        }

        final AbstractBranchTransition branchTransition = this.transitionDeterminer
                .determineTransition(abstractBranchTransitions);

        /*
         * In case of a guarded transition, it must not necessarily be the case, that any branch
         * condition evaluated to true.
         */

        if (branchTransition == null) {
            logger.error("No branch's condition evaluated to true, no branch selected: " + object);
            throw new PCMModelInterpreterException("No branch transition was active. This is not allowed.");
        } else {
            this.doSwitch(branchTransition.getBranchBehaviour_BranchTransition());
        }

        return null;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseCollectionIteratorAction(de.uka.ipd.sdq.pcm.seff.CollectionIteratorAction)
     */
    @Override
    public SimulatedStackframe<Object> caseCollectionIteratorAction(final CollectionIteratorAction object) {
        this.iterateOverCollection(object, object.getParameter_CollectionIteratorAction());

        return null;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseExternalCallAction(de.uka.ipd.sdq.pcm.seff.ExternalCallAction)
     */
    @Override
    public SimulatedStackframe<Object> caseExternalCallAction(final ExternalCallAction externalCall) {
        this.context.getEventNotificationHelper().firePassedEvent(
                new RDSEFFElementPassedEvent<ExternalCallAction>(externalCall, EventType.BEGIN, this.context
                        .getThread(), this.context.getAssemblyContextStack().peek()));

        final ComposedStructureInnerSwitch composedStructureSwitch = new ComposedStructureInnerSwitch(this.context,
                this.modelAccessFactory, externalCall.getCalledService_ExternalService(),
                externalCall.getRole_ExternalService());

        // create new stack frame for input parameter
        SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                externalCall.getInputVariableUsages__CallAction());
        final AssemblyContext myContext = this.context.getAssemblyContextStack().pop();
        final SimulatedStackframe<Object> outputFrame = composedStructureSwitch.doSwitch(myContext);
        this.context.getAssemblyContextStack().push(myContext);
        this.context.getStack().removeStackFrame();
        SimulatedStackHelper.addParameterToStackFrame(outputFrame,
                externalCall.getReturnVariableUsage__CallReturnAction(), this.context.getStack().currentStackFrame());

        this.context.getEventNotificationHelper().firePassedEvent(
                new RDSEFFElementPassedEvent<ExternalCallAction>(externalCall, EventType.END, this.context.getThread(),
                        this.context.getAssemblyContextStack().peek()));

        return null;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseForkAction(de.uka.ipd.sdq.pcm.seff.ForkAction)
     */
    @Override
    public SimulatedStackframe<Object> caseForkAction(final ForkAction object) {
        /*
         * Component developers can use a SynchronisationPoint to join synchronously
         * ForkedBehaviours and specify a result of the computations with its attached
         * VariableUsages.
         * 
         * For ForkedBehaviours attached to the SynchronizationPoint, it will be possible to return
         * results of their computations to the initiating ForkAction in future versions of the PCM.
         * Happe (2008) currently defines the necessary meta-model changes.
         * 
         * THIS IS CURRENTLY NOT SUPPORTED BY THE INTERPRETER
         */

        // get asynced processes
        final List<ForkedBehaviourProcess> asyncProcesses = this.getProcesses(
                object.getAsynchronousForkedBehaviours_ForkAction(), true);

        // get synced processes
        final List<ForkedBehaviourProcess> syncProcesses = this.determineSyncedProcesses(object);

        // combine both
        final List<ForkedBehaviourProcess> combinedProcesses = this.combineProcesses(asyncProcesses, syncProcesses);

        // create and start fork executor
        final ForkExecutor forkExecutor = new ForkExecutor(this.context.getThread(),
                combinedProcesses.toArray(new ForkedBehaviourProcess[0]));

        forkExecutor.run();

        return null;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseInternalAction(de.uka.ipd.sdq.pcm.seff.InternalAction)
     */
    @Override
    public SimulatedStackframe<Object> caseInternalAction(final InternalAction internalAction) {
        final AllocationAccess allocationReader = this.modelAccessFactory.getAllocationAccess(this.context);

        final AllocationContext allocationContext = allocationReader.getAllocationContext(this.context
                .getAssemblyContextStack().peek());

        final ResourceContainer resourceContainer = allocationContext.getResourceContainer_AllocationContext();

        for (final ParametricResourceDemand parametricResourceDemand : internalAction.getResourceDemand_Action()) {

            final ResourceRegistry resourceRegistry = this.context.getModel().getResourceRegistry();
            final String idRequiredResourceType = parametricResourceDemand
                    .getRequiredResource_ParametricResourceDemand().getId();
            final String specification = parametricResourceDemand.getSpecification_ParametericResourceDemand()
                    .getSpecification();
            final SimulatedStackframe<Object> currentStackFrame = this.context.getStack().currentStackFrame();
            final Double value = StackContext.evaluateStatic(specification, Double.class, currentStackFrame);

            resourceRegistry.getResourceContainer(resourceContainer.getId()).loadActiveResource(
                    this.context.getThread(), idRequiredResourceType, value);

        }
        return null;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseLoopAction(de.uka.ipd.sdq.pcm.seff.LoopAction)
     */
    @Override
    public SimulatedStackframe<Object> caseLoopAction(final LoopAction object) {
        final PCMRandomVariable iterationCount = object.getIterationCount_LoopAction();
        final String stoex = iterationCount.getSpecification();

        // we expect an int here
        final int numberOfLoops = StackContext.evaluateStatic(stoex, Integer.class, this.context.getStack()
                .currentStackFrame());

        if (logger.isDebugEnabled()) {
            logger.debug("Determined number of loops: " + numberOfLoops + " " + object);
        }
        
        // interpret behavior the given number of times
        this.interpretLoop(object, numberOfLoops);

        return null;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseResourceDemandingBehaviour(de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour)
     */
    @Override
    public SimulatedStackframe<Object> caseResourceDemandingBehaviour(final ResourceDemandingBehaviour object) {
        final int stacksize = this.context.getStack().size();

        // interpret start action
        for (final AbstractAction abstractAction : object.getSteps_Behaviour()) {
            if (abstractAction instanceof StartAction) {
                this.doSwitch(abstractAction);
                break;
            }
        }

        if (this.context.getStack().size() != stacksize) {
            throw new PCMModelInterpreterException("Interpreter did not pop all pushed stackframes");
        }

        return this.resultStackFrame;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseSetVariableAction(de.uka.ipd.sdq.pcm.seff.SetVariableAction)
     */
    @Override
    public SimulatedStackframe<Object> caseSetVariableAction(final SetVariableAction object) {
        SimulatedStackHelper.addParameterToStackFrame(this.context.getStack().currentStackFrame(),
                object.getLocalVariableUsages_SetVariableAction(), this.resultStackFrame);
        /*
         * Special attention has to be paid if the random variable to set is an INNER
         * characterisation. In this case, a late evaluating random variable has to be stored with
         * the current stack frame as evaluation context (cf. section 4.4.2).
         * 
         * Why?
         */
        return null;
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
    // while ((currentAction = currentAction.getSuccessor_AbstractAction()) !=
    // null)
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
    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.pcm.seff.util.SeffSwitch#caseAbstractAction(de.uka.ipd.sdq.pcm.seff.AbstractAction)
     */
    @Override
    public SimulatedStackframe<Object> caseAbstractAction(final AbstractAction object) {
        if (object.getSuccessor_AbstractAction() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Interpret "+object.getSuccessor_AbstractAction().eClass().getName()+": " + object);
            }
            this.doSwitch(object.getSuccessor_AbstractAction());
            if (logger.isDebugEnabled()) {
                logger.debug("Interpret "+object.getSuccessor_AbstractAction().eClass().getName()+": " + object);
            }
        }
        return null;
    }

    /**
     * Combines synced and asynced processes in a combined list.
     * 
     * @param asyncProcesses
     *            list of asynced processes.
     * @param syncProcesses
     *            list of synced processes.
     * @return combined list.
     */
    private List<ForkedBehaviourProcess> combineProcesses(final List<ForkedBehaviourProcess> asyncProcesses,
            final List<ForkedBehaviourProcess> syncProcesses) {
        final List<ForkedBehaviourProcess> combinedProcesses = new Vector<ForkedBehaviourProcess>();
        combinedProcesses.addAll(asyncProcesses);
        combinedProcesses.addAll(syncProcesses);
        return combinedProcesses;
    }

    /**
     * Determines the synced processes in a fork action.
     * 
     * @param object
     *            the fork action.
     * @return a list with synced processes.
     */
    private List<ForkedBehaviourProcess> determineSyncedProcesses(final ForkAction object) {
        List<ForkedBehaviourProcess> syncProcesses = new ArrayList<ForkedBehaviourProcess>();

        if (object.getSynchronisingBehaviours_ForkAction() != null) {
            syncProcesses = this.getProcesses(object.getSynchronisingBehaviours_ForkAction()
                    .getSynchronousForkedBehaviours_SynchronisationPoint(), false);
        }
        return syncProcesses;
    }

    /**
     * Creates a list of sync and async processes for given behaviors.
     * 
     * @param forkedBehaviours
     *            the forked behaviors, independent of their sync or async character.
     * @param isAsync
     *            true if processes shall be async, otherwise false.
     * @return a list of configured forked behavior processes.
     */
    private List<ForkedBehaviourProcess> getProcesses(final List<ForkedBehaviour> forkedBehaviours,
            final boolean isAsync) {
        final List<ForkedBehaviourProcess> syncProcesses = new LinkedList<ForkedBehaviourProcess>();

        // for each create process, and add to array of processes

        for (final ForkedBehaviour forkedBehaviour : forkedBehaviours) {

            syncProcesses.add(new ForkedBehaviourProcess(this.context, this.context.getAssemblyContextStack().peek()
                    .getId(), isAsync) {

                @Override
                protected void executeBehaviour() {

                    /*
                     * The forked behavior process has its own copied stack in its context, for type
                     * reasons we need an InterpreterDefaultContext. Thus we have to copy the
                     * context including its stack.
                     */
                    final RDSeffSwitch seffInterpreter = new RDSeffSwitch(new InterpreterDefaultContext(
                            (InterpreterDefaultContext) this.ctx), RDSeffSwitch.this.modelAccessFactory,
                            RDSeffSwitch.this.context.getAssemblyContextStack().peek());

                    if (logger.isDebugEnabled()) {
                        logger.debug("Created new RDSeff interpreter for "
                            + ((this.isAsync()) ? "asynced" : "synced") + " forked baviour: " + this);
                    }
                    seffInterpreter.doSwitch(forkedBehaviour);
                }

            });
        }
        return syncProcesses;
    }

    /**
     * Interpret inner path of loop the given times
     * 
     * @param object
     *            the LoopAction.
     * @param numberOfLoops
     *            number of loops.
     */
    private void interpretLoop(final LoopAction object, final int numberOfLoops) {
        for (int i = 0; i < numberOfLoops; i++) {
            if (logger.isDebugEnabled()) {
                logger.debug("Interpret loop number " + i + ": " + object);
            }
            this.doSwitch(object.getBodyBehaviour_Loop());
            if (logger.isDebugEnabled()) {
                logger.debug("Finished loop number " + i + ": " + object);
            }
        }
    }

    /**
     * Iterates over collection of given CollectionIteratorAction.
     * 
     * @param object
     *            the CollectionIteratorAction.
     * @param parameterthe
     *            parameter of the collection.
     * @return
     */
    private void iterateOverCollection(final CollectionIteratorAction object, final Parameter parameter) {
        // TODO make better
        final String idNumberOfLoops = parameter.getParameterName() + ".NUMBER_OF_ELEMENTS";

        // get number of loops
        final int numberOfLoops = StackContext.evaluateStatic(idNumberOfLoops, Integer.class, this.context.getStack()
                .currentStackFrame());

        if (logger.isDebugEnabled()) {
            logger.debug("Determined number of loops: " + numberOfLoops + " " + object);
        }
        for (int i = 0; i < numberOfLoops; i++) {
            if (logger.isDebugEnabled()) {
                logger.debug("Interpret loop number " + i + ": " + object);
            }

            // create new stack frame for value characterizations of inner
            // collection variable
            final SimulatedStackframe<Object> innerVariableStackFrame = this.context.getStack()
                    .createAndPushNewStackFrame(this.context.getStack().currentStackFrame());

            /*
             * evaluate value characterization of inner collection variable, store them on created
             * top most stack frame. Add a . at the end of the parameter name because otherwise if
             * we search for parameter name "ab" we also get variables called "abc"
             */
            // TODO the point is not nice
            this.context.evaluateInner(innerVariableStackFrame, parameter.getParameterName() + ".");

            if (logger.isDebugEnabled()) {
                logger.debug("Created new stackframe with evaluated inner collection variables: "
                    + innerVariableStackFrame);
            }

            /*
             * now further access on inner variables are caught in the current top most frame. In
             * other words, they are currently overridden with their evaluated values. This has the
             * effect that actions within the iterator use the same evaluated values. This is very
             * important in case of EvaluationProxys which should not be reevaluated for each action
             * within an iteration.
             */

            this.doSwitch(object.getBodyBehaviour_Loop());

            // remove stack frame for value characterisations of inner
            // collection variable
            if (this.context.getStack().currentStackFrame() != innerVariableStackFrame) {
                throw new SimulatedStackAccessException(
                        "Inner value characterisations of inner collection variable expected");
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Remove stack frame: " + innerVariableStackFrame);
            }
            this.context.getStack().removeStackFrame();
            if (logger.isDebugEnabled()) { 
                logger.debug("Finished loop number " + i + ": " + object);
            }
        }
    }
}
