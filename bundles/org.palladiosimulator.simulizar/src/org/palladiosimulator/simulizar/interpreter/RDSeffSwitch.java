package org.palladiosimulator.simulizar.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.palladiosimulator.analyzer.completions.DelegatingExternalCallAction;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.AcquireAction;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.CollectionIteratorAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ForkAction;
import org.palladiosimulator.pcm.seff.ForkedBehaviour;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ReleaseAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.SetVariableAction;
import org.palladiosimulator.pcm.seff.util.SeffSwitch;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.exceptions.SimulatedStackAccessException;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory.RDSeffElementDispatcher;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.RDSEFFElementPassedEvent;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultHandler;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResumptionPolicy;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;
import org.palladiosimulator.simulizar.utils.TransitionDeterminer;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.fork.ForkExecutor;
import de.uka.ipd.sdq.simucomframework.fork.ForkedBehaviourProcess;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * Switch for RFSEFFs. This visitor is responsible for traversing RDSEFF behaviours.
 *
 * @author Joachim Meyer, Steffen Becker, Sebastian Lehrig
 *
 */
public class RDSeffSwitch extends SeffSwitch<InterpreterResult> {
    @AssistedFactory
    public interface Factory extends RDSeffSwitchContributionFactory {
        @Override
        RDSeffSwitch createRDSeffSwitch(final InterpreterDefaultContext context,
                RDSeffElementDispatcher parentSwitch);
    }

    public static final Boolean SUCCESS = true;
    private static final Logger LOGGER = Logger.getLogger(RDSeffSwitch.class);

    private RDSeffElementDispatcher parentSwitch;
    private final TransitionDeterminer transitionDeterminer;
    private final InterpreterDefaultContext context;

    private final SimulatedBasicComponentInstance basicComponentInstance;
    private final IResourceTableManager resourceTableManager;
    private final ComposedStructureInnerSwitch.Factory composedSwitchFactory;
    private final ComposedRDSeffSwitchFactory rdseffSwitchFactory;
    private final EventDispatcher eventHelper;
    private final InterpreterResultHandler issueHandler;
    private final InterpreterResultMerger resultMerger;

    /**
     * @see RDSeffSwitchFactory#create(InterpreterDefaultContext, SimulatedBasicComponentInstance, ComposedSwitch)
     */
    @AssistedInject
    RDSeffSwitch(@Assisted final InterpreterDefaultContext context, @Assisted RDSeffElementDispatcher parentSwitch,
            IResourceTableManager resourceTableManager,
            ComponentInstanceRegistry componentInstanceRegistry,
            ComposedStructureInnerSwitch.Factory composedSwitchFactory,
            ComposedRDSeffSwitchFactory rdseffSwitchFactory, 
            EventDispatcher eventHelper,
            InterpreterResultHandler issueHandler,
            InterpreterResultMerger resultMerger) {
        super();
        this.context = context;
        this.composedSwitchFactory = composedSwitchFactory;
        this.rdseffSwitchFactory = rdseffSwitchFactory;
        this.eventHelper = eventHelper;
        this.issueHandler = issueHandler;
        this.resultMerger = resultMerger;
        this.transitionDeterminer = new TransitionDeterminer(context);
        this.basicComponentInstance = Optional.ofNullable(componentInstanceRegistry.getComponentInstance(context.computeFQComponentID()))
                .filter(SimulatedBasicComponentInstance.class::isInstance)
                .map(SimulatedBasicComponentInstance.class::cast)
                .orElseThrow(() -> new IllegalStateException("RDSeffSwitch requires an SimulatedBasicComponentInstance to be initialized already."));
        this.resourceTableManager = resourceTableManager;
        this.parentSwitch = parentSwitch;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseResourceDemandingBehaviour(org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour)
     */
    @Override
    public InterpreterResult caseResourceDemandingBehaviour(final ResourceDemandingBehaviour object) {
        final int stacksize = this.context.getStack().size();

        AbstractAction currentAction = null;
        // interpret start action
        for (final AbstractAction abstractAction : object.getSteps_Behaviour()) {
            if (abstractAction.eClass() == SeffPackage.eINSTANCE.getStartAction()) {
                this.firePassedEvent(abstractAction, EventType.BEGIN);
                currentAction = abstractAction.getSuccessor_AbstractAction();
                this.firePassedEvent(abstractAction, EventType.END);
                break;
            }
        }
        if (currentAction == null) {
            throw new PCMModelInterpreterException("RDSEFF is invalid, it misses a start action");
        }

        InterpreterResult result = InterpreterResult.OK;
        while (issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE
                && currentAction.eClass() != SeffPackage.eINSTANCE.getStopAction()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Interpret " + currentAction.eClass().getName() + ": " + currentAction);
            }
            this.firePassedEvent(currentAction, EventType.BEGIN);
            result = resultMerger.merge(result, parentSwitch.doSwitch(currentAction));
            this.firePassedEvent(currentAction, EventType.END);
            currentAction = currentAction.getSuccessor_AbstractAction();
        }

        if (this.context.getStack().size() != stacksize) {
            throw new PCMModelInterpreterException("Interpreter did not pop all pushed stackframes");
        }

        return result;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseAbstractAction(org.palladiosimulator.pcm.seff.AbstractAction)
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.pcm.seff.util.SeffSwitch#caseAbstractAction(org.palladiosimulator.pcm.
     * seff.AbstractAction )
     */
    @Override
    public InterpreterResult caseAbstractAction(final AbstractAction object) {
        throw new UnsupportedOperationException(
                "SEFF Interpreter tried to interpret unsupported action type: " + object.eClass().getName());
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseInternalAction(org.palladiosimulator.pcm.seff.InternalAction)
     */
    @Override
    public InterpreterResult caseInternalAction(final InternalAction internalAction) {
        var result = InterpreterResult.OK;
        result = resultMerger.merge(result, invokeRecursiveAndHandleFailure(internalAction.getResourceDemand_Action()));
        result = resultMerger.merge(result, invokeRecursiveAndHandleFailure(internalAction.getInfrastructureCall__Action()));
        // We include the following, once failure simulation has been fully integrated
        //result = resultMerger.merge(invokeRecursiveAndHandleFailure(internalAction.getInternalFailureOccurrenceDescriptions__InternalAction());
        result = resultMerger.merge(result, invokeRecursiveAndHandleFailure(internalAction.getResourceCall__Action()));
        return result;
    }
    
    private InterpreterResult invokeRecursiveAndHandleFailure(Collection<? extends EObject> nestedElements) {
        var result = InterpreterResult.OK;
        for (var element: nestedElements) {
            result = resultMerger.merge(result, this.parentSwitch.doSwitch(element));
        }
        return result;
    }


    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseExternalCallAction(org.palladiosimulator.pcm.seff.ExternalCallAction)
     */
    @Override
    public InterpreterResult caseExternalCallAction(final ExternalCallAction externalCall) {
        final ComposedStructureInnerSwitch composedStructureSwitch = composedSwitchFactory.create(this.context,
                externalCall.getCalledService_ExternalService(), externalCall.getRole_ExternalService());

        if (externalCall instanceof DelegatingExternalCallAction) {
            final SimulatedStackframe<Object> currentFrame = this.context.getStack().currentStackFrame();
            final SimulatedStackframe<Object> callFrame = SimulatedStackHelper.createAndPushNewStackFrame(
                    this.context.getStack(), externalCall.getInputVariableUsages__CallAction(), currentFrame);
            callFrame.addVariables(this.context.getCurrentResultFrame());
        } else {
            // create new stack frame for input parameter
            SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                    externalCall.getInputVariableUsages__CallAction());
        }
        final AssemblyContext myContext = this.context.getAssemblyContextStack().pop();
        context.getResultFrameStack().push(new SimulatedStackframe<>());
        var result = composedStructureSwitch.doSwitch(myContext);
        this.context.getAssemblyContextStack().push(myContext);
        this.context.getStack().removeStackFrame();

        SimulatedStackHelper.addParameterToStackFrame(context.getResultFrameStack().pop(),
                externalCall.getReturnVariableUsage__CallReturnAction(), this.context.getStack().currentStackFrame());

        return result;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseBranchAction(org.palladiosimulator.pcm.seff.BranchAction)
     */
    @Override
    public InterpreterResult caseBranchAction(final BranchAction object) {
        final EList<AbstractBranchTransition> abstractBranchTransitions = object.getBranches_Branch();
        if (abstractBranchTransitions.isEmpty()) {
            throw new PCMModelInterpreterException("Empty branch action is not allowed");
        }

        if (LOGGER.isDebugEnabled()) {
            final StringBuilder sb = new StringBuilder();

            sb.append("Branch \"");
            sb.append(object.getEntityName());
            sb.append("\" [ID: ");
            sb.append(object.getId());
            sb.append("\"] with ");
            sb.append(object.getBranches_Branch().size());
            sb.append(" branches.");

            LOGGER.debug(sb.toString());
        }
        final AbstractBranchTransition branchTransition = this.transitionDeterminer
                .determineTransition(abstractBranchTransitions);

        /*
         * In case of a guarded transition, it must not necessarily be the case, that any branch
         * condition evaluated to true.
         */

        if (branchTransition == null) {
            LOGGER.error("No branch's condition evaluated to true, no branch selected: " + object);
            throw new PCMModelInterpreterException("No branch transition was active. This is not allowed.");
        } else {
            return parentSwitch.doSwitch(branchTransition.getBranchBehaviour_BranchTransition());
        }
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseCollectionIteratorAction(org.palladiosimulator.pcm.seff.CollectionIteratorAction)
     */
    @Override
    public InterpreterResult caseCollectionIteratorAction(final CollectionIteratorAction object) {
        return this.iterateOverCollection(object, object.getParameter_CollectionIteratorAction());
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseForkAction(org.palladiosimulator.pcm.seff.ForkAction)
     */
    @Override
    public InterpreterResult caseForkAction(final ForkAction object) {
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
        final List<ForkedBehaviourProcess> asyncProcesses = this
                .getProcesses(object.getAsynchronousForkedBehaviours_ForkAction(), true, resourceTableManager);

        // get synced processes
        final List<ForkedBehaviourProcess> syncProcesses = this.determineSyncedProcesses(object, resourceTableManager);

        // combine both
        final List<ForkedBehaviourProcess> combinedProcesses = this.combineProcesses(asyncProcesses, syncProcesses);

        // create and start fork executor
        final ForkExecutor forkExecutor = new ForkExecutor(this.context.getThread(),
                combinedProcesses.toArray(new ForkedBehaviourProcess[0]));

        forkExecutor.run();

        return InterpreterResult.OK;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseLoopAction(org.palladiosimulator.pcm.seff.LoopAction)
     */
    @Override
    public InterpreterResult caseLoopAction(final LoopAction object) {
        final PCMRandomVariable iterationCount = object.getIterationCount_LoopAction();
        final String stoex = iterationCount.getSpecification();

        // we expect an int here
        final int numberOfLoops = StackContext.evaluateStatic(stoex, Integer.class,
                this.context.getStack().currentStackFrame());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Determined number of loops: " + numberOfLoops + " " + object);
        }

        // interpret behavior the given number of times
        return this.interpretLoop(object, numberOfLoops);
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseSetVariableAction(org.palladiosimulator.pcm.seff.SetVariableAction)
     */
    @Override
    public InterpreterResult caseSetVariableAction(final SetVariableAction object) {
        SimulatedStackHelper.addParameterToStackFrame(this.context.getStack().currentStackFrame(),
                object.getLocalVariableUsages_SetVariableAction(), context.getCurrentResultFrame());
        /*
         * Special attention has to be paid if the random variable to set is an INNER
         * characterisation. In this case, a late evaluating random variable has to be stored with
         * the current stack frame as evaluation context (cf. section 4.4.2).
         *
         * Why?
         */
        return InterpreterResult.OK;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.pcm.seff.util.SeffSwitch#caseAcquireAction(org.palladiosimulator.pcm.
     * seff.AcquireAction )
     */
    @Override
    public InterpreterResult caseAcquireAction(final AcquireAction acquireAction) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Process " + this.context.getThread().getId() + " tries to acquire "
                    + acquireAction.getPassiveresource_AcquireAction().getEntityName());
        }
        this.basicComponentInstance.acquirePassiveResource(acquireAction.getPassiveresource_AcquireAction(),
                this.context, this.context.getModel().getConfiguration().getSimulateFailures(),
                acquireAction.getTimeoutValue());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Process " + this.context.getThread().getId() + " successfully acquired "
                    + acquireAction.getPassiveresource_AcquireAction().getEntityName());
        }
        return InterpreterResult.OK;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.pcm.seff.util.SeffSwitch#caseReleaseAction(org.palladiosimulator.pcm.
     * seff.ReleaseAction )
     */
    @Override
    public InterpreterResult caseReleaseAction(final ReleaseAction releaseAction) {
        this.basicComponentInstance.releasePassiveResource(releaseAction.getPassiveResource_ReleaseAction(),
                this.context);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Process " + this.context.getThread().getId() + " released "
                    + releaseAction.getPassiveResource_ReleaseAction().getEntityName());
        }
        return InterpreterResult.OK;
    }

    /**
     * @param abstractAction
     * @param eventType
     */
    private <T extends AbstractAction> void firePassedEvent(final T abstractAction, final EventType eventType) {
        eventHelper.firePassedEvent(new RDSEFFElementPassedEvent<T>(
                abstractAction, eventType, this.context, this.context.getAssemblyContextStack().peek()));
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
        final List<ForkedBehaviourProcess> combinedProcesses = new LinkedList<ForkedBehaviourProcess>();
        combinedProcesses.addAll(asyncProcesses);
        combinedProcesses.addAll(syncProcesses);
        return Collections.synchronizedList(combinedProcesses);
    }

    /**
     * Determines the synced processes in a fork action.
     *
     * @param object
     *            the fork action.
     * @return a list with synced processes.
     */
    private List<ForkedBehaviourProcess> determineSyncedProcesses(final ForkAction object, IResourceTableManager resourceTableManager) {
        List<ForkedBehaviourProcess> syncProcesses = new ArrayList<ForkedBehaviourProcess>();

        if (object.getSynchronisingBehaviours_ForkAction() != null) {
            syncProcesses = this.getProcesses(object.getSynchronisingBehaviours_ForkAction()
                    .getSynchronousForkedBehaviours_SynchronisationPoint(), false, resourceTableManager);
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
            final boolean isAsync, IResourceTableManager resourceTableManager) {
        final List<ForkedBehaviourProcess> processes = new LinkedList<ForkedBehaviourProcess>();

        // for each create process, and add to array of processes

        for (final ForkedBehaviour forkedBehaviour : forkedBehaviours) {
            @SuppressWarnings("unchecked")
            final Stack<AssemblyContext> parentAssemblyContextStack = (Stack<AssemblyContext>) this.context
            .getAssemblyContextStack().clone();
            processes.add(new ForkedBehaviourProcess(this.context,
                    this.context.getAssemblyContextStack().peek().getId(), isAsync, resourceTableManager) {

                @Override
                protected void executeBehaviour() {

                    /*
                     * The forked behavior process has its own copied stack in its context, for type
                     * reasons we need an InterpreterDefaultContext. Thus we have to copy the
                     * context including its stack.
                     */
                    final InterpreterDefaultContext seffContext = new InterpreterDefaultContext(this.forkContext,
                            RDSeffSwitch.this.context.getPCMPartitionManager(), true,
                            RDSeffSwitch.this.context.getLocalPCMModelAtContextCreation());
                    seffContext.getAssemblyContextStack().addAll(parentAssemblyContextStack);
                    final var seffInterpreter = rdseffSwitchFactory.createRDSeffSwitch(seffContext);

                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Created new RDSeff interpreter for " + ((this.isAsync()) ? "asynced" : "synced")
                                + " forked baviour: " + this);
                    }
                    // no use of parentSwitch.doSwitch() because we want the inner switches
                    seffInterpreter.doSwitch(forkedBehaviour);
                }

            });
        }
        return processes;
    }

    /**
     * Interpret inner path of loop the given times
     *
     * @param object
     *            the LoopAction.
     * @param numberOfLoops
     *            number of loops.
     */
    private InterpreterResult interpretLoop(final LoopAction object, final int numberOfLoops) {
        InterpreterResult result = InterpreterResult.OK;
        for (int i = 0; issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE && 
                i < numberOfLoops; i++) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Interpret loop number " + i + ": " + object);
            }
            result = resultMerger.merge(result, parentSwitch.doSwitch(object.getBodyBehaviour_Loop()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Finished loop number " + i + ": " + object);
            }
        }
        return result;
    }

    /**
     * Iterates over collection of given CollectionIteratorAction.
     *
     * @param object
     *            the CollectionIteratorAction.
     * @param parameter
     *            parameter of the collection.
     * @return
     */
    private InterpreterResult iterateOverCollection(final CollectionIteratorAction object, final Parameter parameter) {
        // TODO make better
        final String idNumberOfLoops = parameter.getParameterName() + ".NUMBER_OF_ELEMENTS";

        // get number of loops
        final int numberOfLoops = StackContext.evaluateStatic(idNumberOfLoops, Integer.class,
                this.context.getStack().currentStackFrame());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Determined number of loops: " + numberOfLoops + " " + object);
        }
        InterpreterResult result = InterpreterResult.OK;
        for (int i = 0; issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE && 
                i < numberOfLoops; i++) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Interpret loop number " + i + ": " + object);
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

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                        "Created new stackframe with evaluated inner collection variables: " + innerVariableStackFrame);
            }

            /*
             * now further access on inner variables are caught in the current top most frame. In
             * other words, they are currently overridden with their evaluated values. This has the
             * effect that actions within the iterator use the same evaluated values. This is very
             * important in case of EvaluationProxys which should not be reevaluated for each action
             * within an iteration.
             */

            result = resultMerger.merge(result, parentSwitch.doSwitch(object.getBodyBehaviour_Loop()));

            // remove stack frame for value characterisations of inner
            // collection variable
            if (this.context.getStack().currentStackFrame() != innerVariableStackFrame) {
                throw new SimulatedStackAccessException(
                        "Inner value characterisations of inner collection variable expected");
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Remove stack frame: " + innerVariableStackFrame);
            }
            this.context.getStack().removeStackFrame();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Finished loop number " + i + ": " + object);
            }
        }
        return result;
    }
}
