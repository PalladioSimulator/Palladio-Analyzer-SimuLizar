package org.palladiosimulator.simulizar.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.analyzer.completions.DelegatingExternalCallAction;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationContext;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.entity.ResourceProvidedRole;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourcetype.ResourceInterface;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.ResourceSignature;
import org.palladiosimulator.pcm.resourcetype.ResourceType;
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
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;
import org.palladiosimulator.pcm.seff.util.SeffSwitch;
import org.palladiosimulator.simulizar.exceptions.PCMModelAccessException;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.exceptions.SimulatedStackAccessException;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.RDSEFFElementPassedEvent;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;
import org.palladiosimulator.simulizar.utils.TransitionDeterminer;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.fork.ForkExecutor;
import de.uka.ipd.sdq.simucomframework.fork.ForkedBehaviourProcess;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * Switch for RFSEFFs. This visitor is responsible for traversing RDSEFF behaviours.
 *
 * @author Joachim Meyer, Steffen Becker, Sebastian Lehrig
 *
 */
class RDSeffSwitch extends SeffSwitch<Object> implements IComposableSwitch {

    private static final Boolean SUCCESS = true;
    private static final Logger LOGGER = Logger.getLogger(RDSeffSwitch.class);

    private ComposedSwitch<Object> parentSwitch;
    private final TransitionDeterminer transitionDeterminer;
    private final InterpreterDefaultContext context;
    private final Allocation allocation;

    private final SimulatedStackframe<Object> resultStackFrame;

    private final SimulatedBasicComponentInstance basicComponentInstance;

    /**
     * Constructor.
     *
     * @param context
     *            Default context for the pcm interpreter.
     * @param basicComponentInstance
     *            Simulated component
     */
    public RDSeffSwitch(final InterpreterDefaultContext context,
            final SimulatedBasicComponentInstance basicComponentInstance) {
        super();
        this.context = context;
        this.allocation = context.getLocalPCMModelAtContextCreation().getAllocation();
        this.transitionDeterminer = new TransitionDeterminer(context);
        this.resultStackFrame = new SimulatedStackframe<Object>();
        this.basicComponentInstance = basicComponentInstance;
    }


    /**
     * Constructor.
     *
     * @param context
     *				Default context for the pcm interpreter.
     * @param basicComponentInstance
     *				Simulated component
     * @param parentSwitch
     *				The composed switch which is containing this switch
     */
    public RDSeffSwitch(final InterpreterDefaultContext context,
            final SimulatedBasicComponentInstance basicComponentInstance, ComposedSwitch<Object> parentSwitch) {
    	this(context, basicComponentInstance);
    	this.parentSwitch = parentSwitch;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseResourceDemandingBehaviour(org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour)
     */
    @Override
    public Object caseResourceDemandingBehaviour(final ResourceDemandingBehaviour object) {
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

        while (currentAction.eClass() != SeffPackage.eINSTANCE.getStopAction()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Interpret " + currentAction.eClass().getName() + ": " + currentAction);
            }
            this.firePassedEvent(currentAction, EventType.BEGIN);
            this.getParentSwitch().doSwitch(currentAction);
            this.firePassedEvent(currentAction, EventType.END);
            currentAction = currentAction.getSuccessor_AbstractAction();
        }

        if (this.context.getStack().size() != stacksize) {
            throw new PCMModelInterpreterException("Interpreter did not pop all pushed stackframes");
        }

        return this.resultStackFrame;
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
    public SimulatedStackframe<Object> caseAbstractAction(final AbstractAction object) {
        throw new UnsupportedOperationException(
                "SEFF Interpreter tried to interpret unsupported action type: " + object.eClass().getName());
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseInternalAction(org.palladiosimulator.pcm.seff.InternalAction)
     */
    @Override
    public Object caseInternalAction(final InternalAction internalAction) {
        if (internalAction.getResourceDemand_Action().size() > 0) {
            interpretResourceDemands(internalAction);
        }
        if (internalAction.getInfrastructureCall__Action().size() > 0) {
            interpretInfrastructureCalls(internalAction);
        }
        if (internalAction.getInternalFailureOccurrenceDescriptions__InternalAction().size() > 0) {
            interpretFailures(internalAction);
        }
        if (internalAction.getResourceCall__Action().size() > 0) {
            interpretResourceCall(internalAction);
        }
        return SUCCESS;
    }

    /**
     * @param internalAction
     */
    private void interpretInfrastructureCalls(final InternalAction internalAction) {
        for (final InfrastructureCall infrastructureCall : internalAction.getInfrastructureCall__Action()) {
            final SimulatedStackframe<Object> currentStackFrame = this.context.getStack().currentStackFrame();
            final int repetitions = StackContext.evaluateStatic(
                    infrastructureCall.getNumberOfCalls__InfrastructureCall().getSpecification(), Integer.class,
                    currentStackFrame);
            for (int i = 0; i < repetitions; i++) {
                final ComposedStructureInnerSwitch composedStructureSwitch = new ComposedStructureInnerSwitch(
                        this.context, infrastructureCall.getSignature__InfrastructureCall(),
                        infrastructureCall.getRequiredRole__InfrastructureCall());

                // create new stack frame for input parameter
                SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                        infrastructureCall.getInputVariableUsages__CallAction());
                final AssemblyContext myContext = this.context.getAssemblyContextStack().pop();
                composedStructureSwitch.doSwitch(myContext);
                this.context.getAssemblyContextStack().push(myContext);
                this.context.getStack().removeStackFrame();
            }
        }
    }

    /**
     * @param internalAction
     *
     */
    private void interpretFailures(final InternalAction internalAction) {
        if (this.context.getModel().getConfiguration().getSimulateFailures()) {
            throw new UnsupportedOperationException("Simulation of failures not yet supported by Simulizar");
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("A failure description is available in an action, but skipped due to configuration set to not simulate failures.");
            }
        }
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseExternalCallAction(org.palladiosimulator.pcm.seff.ExternalCallAction)
     */
    @Override
    public Object caseExternalCallAction(final ExternalCallAction externalCall) {
        final ComposedStructureInnerSwitch composedStructureSwitch = new ComposedStructureInnerSwitch(this.context,
                externalCall.getCalledService_ExternalService(), externalCall.getRole_ExternalService());

        if (externalCall instanceof DelegatingExternalCallAction) {
            final SimulatedStackframe<Object> currentFrame = this.context.getStack().currentStackFrame();
            final SimulatedStackframe<Object> callFrame = SimulatedStackHelper.createAndPushNewStackFrame(
                    this.context.getStack(), externalCall.getInputVariableUsages__CallAction(), currentFrame);
            callFrame.addVariables(this.resultStackFrame);
        } else {
            // create new stack frame for input parameter
            SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                    externalCall.getInputVariableUsages__CallAction());
        }
        final AssemblyContext myContext = this.context.getAssemblyContextStack().pop();
        final SimulatedStackframe<Object> outputFrame = composedStructureSwitch.doSwitch(myContext);
        this.context.getAssemblyContextStack().push(myContext);
        this.context.getStack().removeStackFrame();

        SimulatedStackHelper.addParameterToStackFrame(outputFrame,
                externalCall.getReturnVariableUsage__CallReturnAction(), this.context.getStack().currentStackFrame());

        return SUCCESS;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseBranchAction(org.palladiosimulator.pcm.seff.BranchAction)
     */
    @Override
    public Object caseBranchAction(final BranchAction object) {
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
            this.getParentSwitch().doSwitch(branchTransition.getBranchBehaviour_BranchTransition());
        }

        return SUCCESS;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseCollectionIteratorAction(org.palladiosimulator.pcm.seff.CollectionIteratorAction)
     */
    @Override
    public Object caseCollectionIteratorAction(final CollectionIteratorAction object) {
        this.iterateOverCollection(object, object.getParameter_CollectionIteratorAction());

        return SUCCESS;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseForkAction(org.palladiosimulator.pcm.seff.ForkAction)
     */
    @Override
    public Object caseForkAction(final ForkAction object) {
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
                .getProcesses(object.getAsynchronousForkedBehaviours_ForkAction(), true);

        // get synced processes
        final List<ForkedBehaviourProcess> syncProcesses = this.determineSyncedProcesses(object);

        // combine both
        final List<ForkedBehaviourProcess> combinedProcesses = this.combineProcesses(asyncProcesses, syncProcesses);

        // create and start fork executor
        final ForkExecutor forkExecutor = new ForkExecutor(this.context.getThread(),
                combinedProcesses.toArray(new ForkedBehaviourProcess[0]));

        forkExecutor.run();

        return SUCCESS;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseLoopAction(org.palladiosimulator.pcm.seff.LoopAction)
     */
    @Override
    public Object caseLoopAction(final LoopAction object) {
        final PCMRandomVariable iterationCount = object.getIterationCount_LoopAction();
        final String stoex = iterationCount.getSpecification();

        // we expect an int here
        final int numberOfLoops = StackContext.evaluateStatic(stoex, Integer.class,
                this.context.getStack().currentStackFrame());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Determined number of loops: " + numberOfLoops + " " + object);
        }

        // interpret behavior the given number of times
        this.interpretLoop(object, numberOfLoops);

        return SUCCESS;
    }

    /**
     * @see org.palladiosimulator.pcm.seff.util.SeffSwitch#caseSetVariableAction(org.palladiosimulator.pcm.seff.SetVariableAction)
     */
    @Override
    public Object caseSetVariableAction(final SetVariableAction object) {
        SimulatedStackHelper.addParameterToStackFrame(this.context.getStack().currentStackFrame(),
                object.getLocalVariableUsages_SetVariableAction(), this.resultStackFrame);
        /*
         * Special attention has to be paid if the random variable to set is an INNER
         * characterisation. In this case, a late evaluating random variable has to be stored with
         * the current stack frame as evaluation context (cf. section 4.4.2).
         *
         * Why?
         */
        return SUCCESS;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.pcm.seff.util.SeffSwitch#caseAcquireAction(org.palladiosimulator.pcm.
     * seff.AcquireAction )
     */
    @Override
    public Object caseAcquireAction(final AcquireAction acquireAction) {
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
        return SUCCESS;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.pcm.seff.util.SeffSwitch#caseReleaseAction(org.palladiosimulator.pcm.
     * seff.ReleaseAction )
     */
    @Override
    public Object caseReleaseAction(final ReleaseAction releaseAction) {
        this.basicComponentInstance.releasePassiveResource(releaseAction.getPassiveResource_ReleaseAction(),
                this.context);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Process " + this.context.getThread().getId() + " released "
                    + releaseAction.getPassiveResource_ReleaseAction().getEntityName());
        }
        return SUCCESS;
    }

    /**
     * @param abstractAction
     * @param eventType
     */
    private <T extends AbstractAction> void firePassedEvent(final T abstractAction, final EventType eventType) {
        this.context.getRuntimeState().getEventNotificationHelper().firePassedEvent(new RDSEFFElementPassedEvent<T>(
                abstractAction, eventType, this.context.getThread(), this.context.getAssemblyContextStack().peek()));
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
        final List<ForkedBehaviourProcess> processes = new LinkedList<ForkedBehaviourProcess>();

        // for each create process, and add to array of processes

        for (final ForkedBehaviour forkedBehaviour : forkedBehaviours) {
            @SuppressWarnings("unchecked")
            final Stack<AssemblyContext> parentAssemblyContextStack = (Stack<AssemblyContext>) this.context
            .getAssemblyContextStack().clone();
            processes.add(new ForkedBehaviourProcess(this.context,
                    this.context.getAssemblyContextStack().peek().getId(), isAsync) {

                @Override
                protected void executeBehaviour() {

                    /*
                     * The forked behavior process has its own copied stack in its context, for type
                     * reasons we need an InterpreterDefaultContext. Thus we have to copy the
                     * context including its stack.
                     */
                    final InterpreterDefaultContext seffContext = new InterpreterDefaultContext(this.myContext,
                            RDSeffSwitch.this.context.getRuntimeState(), true,
                            RDSeffSwitch.this.context.getLocalPCMModelAtContextCreation());
                    seffContext.getAssemblyContextStack().addAll(parentAssemblyContextStack);
                    final RDSeffSwitch seffInterpreter = new RDSeffSwitch(seffContext,
                            RDSeffSwitch.this.basicComponentInstance);

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
    private void interpretLoop(final LoopAction object, final int numberOfLoops) {
        for (int i = 0; i < numberOfLoops; i++) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Interpret loop number " + i + ": " + object);
            }
            this.getParentSwitch().doSwitch(object.getBodyBehaviour_Loop());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Finished loop number " + i + ": " + object);
            }
        }
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
    private void iterateOverCollection(final CollectionIteratorAction object, final Parameter parameter) {
        // TODO make better
        final String idNumberOfLoops = parameter.getParameterName() + ".NUMBER_OF_ELEMENTS";

        // get number of loops
        final int numberOfLoops = StackContext.evaluateStatic(idNumberOfLoops, Integer.class,
                this.context.getStack().currentStackFrame());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Determined number of loops: " + numberOfLoops + " " + object);
        }
        for (int i = 0; i < numberOfLoops; i++) {
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

            this.getParentSwitch().doSwitch(object.getBodyBehaviour_Loop());

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
    }

    /**
     * @param internalAction
     * 				The internal action containing the resource demand
     */
    private void interpretResourceDemands(final InternalAction internalAction) {
        final AllocationContext allocationContext = this.getAllocationContext(this.allocation);
        final ResourceContainer resourceContainer = allocationContext.getResourceContainer_AllocationContext();

        for (final ParametricResourceDemand parametricResourceDemand : internalAction.getResourceDemand_Action()) {

            final ResourceRegistry resourceRegistry = this.context.getModel().getResourceRegistry();
            final String idRequiredResourceType = parametricResourceDemand
                    .getRequiredResource_ParametricResourceDemand().getId();
            final String specification = parametricResourceDemand.getSpecification_ParametericResourceDemand()
                    .getSpecification();
            final SimulatedStackframe<Object> currentStackFrame = this.context.getStack().currentStackFrame();
            final Double value = StackContext.evaluateStatic(specification, Double.class, currentStackFrame);

            resourceRegistry.getResourceContainer(resourceContainer.getId())
            .loadActiveResource(this.context.getThread(), idRequiredResourceType, value);

        }
    }


    /**
     * @param internalAction
     */
    private void interpretResourceCall(final InternalAction internalAction) {
        final AllocationContext allocationContext = this.getAllocationContext(this.allocation);
        final ResourceContainer resourceContainer = allocationContext.getResourceContainer_AllocationContext();

        for (final ResourceCall resourceCall : internalAction.getResourceCall__Action()) {

            // find the corresponding resource type which was invoked by the resource call
            final ResourceInterface resourceInterface = resourceCall.getSignature__ResourceCall()
                    .getResourceInterface__ResourceSignature();
            final ResourceRepository resourceRepository = resourceInterface.getResourceRepository__ResourceInterface();
            ResourceType currentResourceType = null;

            for (final ResourceType resourceType : resourceRepository.getAvailableResourceTypes_ResourceRepository()) {
                for (final ResourceProvidedRole resourceProvidedRole : resourceType
                        .getResourceProvidedRoles__ResourceInterfaceProvidingEntity()) {
                    if (resourceProvidedRole.getProvidedResourceInterface__ResourceProvidedRole().getId()
                            .equals(resourceInterface.getId())) {
                        currentResourceType = resourceType;
                        break;
                    }
                }
            }

            final ResourceSignature resourceSignature = resourceCall.getSignature__ResourceCall();
            final int resourceServiceId = resourceSignature.getResourceServiceId();

            final SimulatedStackframe<Object> currentStackFrame = this.context.getStack().currentStackFrame();
            final Double evaluatedDemand = NumberConverter.toDouble(
                    StackContext.evaluateStatic(resourceCall.getNumberOfCalls__ResourceCall().getSpecification(),
                            Double.class, currentStackFrame));
            final String idRequiredResourceType = currentResourceType.getId();

            final ResourceRegistry resourceRegistry = this.context.getModel().getResourceRegistry();

            resourceRegistry.getResourceContainer(resourceContainer.getId())
            .loadActiveResource(this.context.getThread(), resourceServiceId, idRequiredResourceType,
                    evaluatedDemand);

        }
    }


    /**
     * Gets the allocation context for the current assembly context stack. The stack is investigated
     * in a FIFO-manner, i.e., first upper elements are checked. This is needed for the case of sub
     * systems.
     *
     * @param allocation
     *            The allocation to find a suitable allocation context in.
     * @return The allocation context.
     * @throws PCMModelAccessException
     *             if no allocation context could be found.
     */
    private AllocationContext getAllocationContext(final Allocation allocation) {
        // For iterating top-down through a stack see:
        // http://stackoverflow.com/questions/16992758/is-there-a-bug-in-java-util-stacks-iterator
        for (final AllocationContext allocationContext : allocation.getAllocationContexts_Allocation()) {
            for (final ListIterator<AssemblyContext> iterator = this.context.getAssemblyContextStack()
                    .listIterator(this.context.getAssemblyContextStack().size()); iterator.hasPrevious();) {
                if (allocationContext.getAssemblyContext_AllocationContext().getId()
                        .equals(iterator.previous().getId())) {
                    return allocationContext;
                }
            }
        }

        throw new PCMModelAccessException("No AllocationContext in Allocation " + allocation + " for AssemblyContext "
                + this.context.getAssemblyContextStack().peek() + " or its parents.");
    }


	@Override
	public Switch<Object> getParentSwitch() {
		if (this.parentSwitch != null) {
			return this.parentSwitch;
		}

		return this;
	}
}
