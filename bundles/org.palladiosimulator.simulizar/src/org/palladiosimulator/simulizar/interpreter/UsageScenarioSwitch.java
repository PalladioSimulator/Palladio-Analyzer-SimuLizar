package org.palladiosimulator.simulizar.interpreter;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.Branch;
import org.palladiosimulator.pcm.usagemodel.BranchTransition;
import org.palladiosimulator.pcm.usagemodel.Delay;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.Loop;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.entity.SimuLizarEntityReferenceFactories;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.InterpreterResultEventEmitter;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.SystemOperationPassedEvent;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultHandler;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResumptionPolicy;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;
import org.palladiosimulator.simulizar.utils.TransitionDeterminer;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * Switch for Usage Scenario in Usage Model
 *
 * @author Joachim Meyer
 *
 * @param <T>
 *            return type of switch methods.
 */
public class UsageScenarioSwitch extends UsagemodelSwitch<InterpreterResult> {
    
    @AssistedFactory
    public static interface Factory {
        UsageScenarioSwitch create(final InterpreterDefaultContext context);
    }

    protected static final Logger LOGGER = Logger.getLogger(UsageScenarioSwitch.class.getName());

    private final InterpreterDefaultContext context;
    private final TransitionDeterminer transitionDeterminer;
    private final RepositoryComponentSwitch.Factory repositoryComponentSwitchFactory;

    private final EventDispatcher eventHelper;

    private final InterpreterResultMerger resultMerger;
    private final InterpreterResultHandler issueHandler;

    private final InterpreterResultEventEmitter resultEventEmitter;
    private final EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory;
    
    /**
     * @see UsageScenarioSwitchFactory#create(InterpreterDefaultContext)
     */
    @AssistedInject
    UsageScenarioSwitch(@Assisted final InterpreterDefaultContext context, RepositoryComponentSwitch.Factory repositoryComponentSwitchFactory,
            EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory,
            EventDispatcher eventHelper,
            InterpreterResultHandler issueHandler,
            InterpreterResultMerger resultMerger,
            InterpreterResultEventEmitter resultEventEmitter) {
        this.context = context;
        this.repositoryComponentSwitchFactory = repositoryComponentSwitchFactory;
        this.usageScenarioReferenceFactory = usageScenarioReferenceFactory;
        this.eventHelper = eventHelper;
        this.issueHandler = issueHandler;
        this.resultMerger = resultMerger;
        this.resultEventEmitter = resultEventEmitter;
        this.transitionDeterminer = new TransitionDeterminer(context);
    }
    
    /**
     * @see org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch#caseScenarioBehaviour(org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour)
     */
    @Override
    public InterpreterResult caseScenarioBehaviour(final ScenarioBehaviour object) {
        final int stacksize = this.context.getStack().size();

        AbstractUserAction currentAction = null;
        // interpret start action
        for (final AbstractUserAction abstractAction : object.getActions_ScenarioBehaviour()) {
            if (abstractAction.eClass() == UsagemodelPackage.Literals.START) {
                firePassedEvent(abstractAction, EventType.BEGIN);
                currentAction = abstractAction.getSuccessor();
                firePassedEvent(abstractAction, EventType.END);
                break;
            }
        }
        if (currentAction == null) {
            throw new PCMModelInterpreterException("Usage Scenario is invalid, it misses a start action");
        }

        InterpreterResult result = InterpreterResult.OK;
        while (issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE
                && currentAction.eClass() != UsagemodelPackage.Literals.STOP) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Interpret " + currentAction.eClass().getName() + ": " + currentAction);
            }
            
            this.firePassedEvent(currentAction, EventType.BEGIN);
            result = resultMerger.merge(result, this.doSwitch(currentAction));
            this.firePassedEvent(currentAction, EventType.END);
            currentAction = currentAction.getSuccessor();
        }

        if (this.context.getStack().size() != stacksize) {
            throw new PCMModelInterpreterException("Interpreter did not pop all pushed stackframes");
        }

        return result;
    }

    /**
     * @see org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch#caseBranch(org.palladiosimulator.pcm.usagemodel.Branch)
     */
    @Override
    public InterpreterResult caseBranch(final Branch object) {
        // determine branch transition
        final BranchTransition branchTransition = this.transitionDeterminer
                .determineBranchTransition(object.getBranchTransitions_Branch());

        // interpret scenario behaviour of branch transition
        return this.doSwitch(branchTransition.getBranchedBehaviour_BranchTransition());
    }

    /**
     * @see org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch#caseDelay(org.palladiosimulator.pcm.usagemodel.Delay)
     */
    @Override
    public InterpreterResult caseDelay(final Delay object) {
        // determine delay
        final double delay = StackContext.evaluateStatic(object.getTimeSpecification_Delay().getSpecification(),
                Double.class);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start delay " + delay + " @ simulation time "
                    + this.context.getModel().getSimulationControl().getCurrentSimulationTime());
        }
        // hold simulation process
        this.context.getThread().hold(delay);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Continue user @ simulation time "
                    + this.context.getModel().getSimulationControl().getCurrentSimulationTime());
        }
        return InterpreterResult.OK;
    }

    /**
     * @see org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch#caseEntryLevelSystemCall(org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall)
     */
    @Override
    public InterpreterResult caseEntryLevelSystemCall(final EntryLevelSystemCall entryLevelSystemCall) {
        final RepositoryComponentSwitch providedDelegationSwitch = repositoryComponentSwitchFactory.create(this.context,
                RepositoryComponentSwitch.SYSTEM_ASSEMBLY_CONTEXT,
                entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall(),
                entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall());

        eventHelper.firePassedEvent(
                new SystemOperationPassedEvent<org.palladiosimulator.pcm.system.System, ProvidedRole, Signature>(
                        entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall(), EventType.BEGIN, this.context,
                        entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall(),
                        (org.palladiosimulator.pcm.system.System) entryLevelSystemCall
                            .getProvidedRole_EntryLevelSystemCall()
                            .getProvidingEntity_ProvidedRole()));

        // create new stack frame for input parameter
        SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                entryLevelSystemCall.getInputParameterUsages_EntryLevelSystemCall());
        
        this.context.getResultFrameStack().push(new SimulatedStackframe<>());
        var result = Objects.requireNonNull(providedDelegationSwitch.doSwitch(entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall()));
        this.context.getStack().removeStackFrame();
        
        SimulatedStackHelper.addParameterToStackFrame(context.getResultFrameStack().pop(),
                entryLevelSystemCall.getOutputParameterUsages_EntryLevelSystemCall(), this.context.getStack().currentStackFrame());

        eventHelper.firePassedEvent(
                new SystemOperationPassedEvent<org.palladiosimulator.pcm.system.System, ProvidedRole, Signature>(
                        entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall(), EventType.END, this.context,
                        entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall(),
                        (org.palladiosimulator.pcm.system.System) entryLevelSystemCall
                            .getProvidedRole_EntryLevelSystemCall()
                            .getProvidingEntity_ProvidedRole()));

        return result;
    }

    /**
     * @see org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch#caseLoop(org.palladiosimulator.pcm.usagemodel.Loop)
     */
    @Override
    public InterpreterResult caseLoop(final Loop object) {
        // determine number of loops
        final int numberOfLoops = StackContext.evaluateStatic(object.getLoopIteration_Loop().getSpecification(),
                Integer.class);
        var result = InterpreterResult.OK;
        for (int i = 0; issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE
                && i < numberOfLoops; i++) {
            LOGGER.debug("Interpret loop number " + i);
            result = resultMerger.merge(result, this.doSwitch(object.getBodyBehaviour_Loop()));
            LOGGER.debug("Finished loop number " + i);
        }
        return result;
    }


    /**
     * @see org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch#caseAbstractUserAction(org.palladiosimulator.pcm.usagemodel.AbstractUserAction)
     */
    @Override
    public InterpreterResult caseAbstractUserAction(final AbstractUserAction object) {
        throw new UnsupportedOperationException("An unsupported usage model element was encountered: " + object.eClass().getName());
    }

    /**
     * @see org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch#caseUsageScenario(org.palladiosimulator.pcm.usagemodel.UsageScenario)
     */
    @Override
    public InterpreterResult caseUsageScenario(final UsageScenario usageScenario) {
        eventHelper.firePassedEvent(
                new ModelElementPassedEvent<UsageScenario>(usageScenario, EventType.BEGIN, this.context));
        final int stacksize = this.context.getStack().size();
        var result = this.doSwitch(usageScenario.getScenarioBehaviour_UsageScenario());
        if (this.context.getStack().size() != stacksize) {
            throw new PCMModelInterpreterException("Interpreter did not pop all pushed stackframes");
        }
        if (!this.context.getResultFrameStack().isEmpty()) {
            throw new PCMModelInterpreterException("Interpreter missbehaving, not all result stack frames were properly removed.");
        }
        eventHelper.firePassedEvent(
                new ModelElementPassedEvent<UsageScenario>(usageScenario, EventType.END, this.context));
        resultEventEmitter.emitInterpretationFinished(usageScenarioReferenceFactory.createCached(usageScenario), result, context);
        return result;
    }
    
    private <T extends AbstractUserAction> void firePassedEvent(final T abstractAction, final EventType eventType) {
        eventHelper.firePassedEvent(new ModelElementPassedEvent<T>(abstractAction, eventType, this.context));
    }
}
