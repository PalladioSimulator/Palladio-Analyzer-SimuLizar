package org.palladiosimulator.simulizar.interpreter;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;
import org.palladiosimulator.simulizar.utils.TransitionDeterminer;

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
import de.uka.ipd.sdq.simucomframework.variables.StackContext;

/**
 * Switch for Usage Scenario in Usage Model
 * 
 * @author Joachim Meyer
 * 
 * @param <T>
 *            return type of switch methods.
 */

public class UsageScenarioSwitch<T> extends UsagemodelSwitch<T> {
    protected static final Logger LOGGER = Logger.getLogger(UsageScenarioSwitch.class.getName());

    private final InterpreterDefaultContext context;
    private final TransitionDeterminer transitionDeterminer;
    private final IModelAccessFactory modelAccessFactory;

    /**
     * Constructor
     * 
     * @param modelInterpreter
     *            the corresponding pcm model interpreter holding this switch..
     */
    public UsageScenarioSwitch(final InterpreterDefaultContext context, final IModelAccessFactory modelAccessFactory) {
        this.context = context;
        this.transitionDeterminer = new TransitionDeterminer(context);
        this.modelAccessFactory = modelAccessFactory;
    }

    /**
     * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseBranch(de.uka.ipd.sdq.pcm.usagemodel.Branch)
     */
    @Override
    public T caseBranch(final Branch object) {
        // determine branch transition
        final BranchTransition branchTransition = this.transitionDeterminer.determineBranchTransition(object
                .getBranchTransitions_Branch());

        // interpret scenario behaviour of branch transition
        this.doSwitch(branchTransition.getBranchedBehaviour_BranchTransition());

        return super.caseBranch(object);
    }

    /**
     * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseDelay(de.uka.ipd.sdq.pcm.usagemodel.Delay)
     */
    @Override
    public T caseDelay(final Delay object) {
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
        return super.caseDelay(object);
    }

    /**
     * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseEntryLevelSystemCall(de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall)
     */
    @Override
    public T caseEntryLevelSystemCall(final EntryLevelSystemCall entryLevelSystemCall) {
        final RepositoryComponentSwitch providedDelegationSwitch = new RepositoryComponentSwitch(this.context,
                this.modelAccessFactory, RepositoryComponentSwitch.SYSTEM_ASSEMBLY_CONTEXT,
                entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall(),
                entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall());

        this.context.getEventNotificationHelper().firePassedEvent(
                new ModelElementPassedEvent<EntryLevelSystemCall>(entryLevelSystemCall, EventType.BEGIN, this.context
                        .getThread()));

        // create new stack frame for input parameter
        SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                entryLevelSystemCall.getInputParameterUsages_EntryLevelSystemCall());
        providedDelegationSwitch.doSwitch(entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall());
        this.context.getStack().removeStackFrame();

        this.context.getEventNotificationHelper().firePassedEvent(
                new ModelElementPassedEvent<EntryLevelSystemCall>(entryLevelSystemCall, EventType.END, this.context
                        .getThread()));

        return super.caseEntryLevelSystemCall(entryLevelSystemCall);
    }

    /**
     * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseLoop(de.uka.ipd.sdq.pcm.usagemodel.Loop)
     */
    @Override
    public T caseLoop(final Loop object) {
        // determine number of loops
        final int numberOfLoops = StackContext.evaluateStatic(object.getLoopIteration_Loop().getSpecification(),
                Integer.class);
        for (int i = 0; i < numberOfLoops; i++) {
            LOGGER.debug("Interpret loop number " + i);
            this.doSwitch(object.getBodyBehaviour_Loop());
            LOGGER.debug("Finished loop number " + i);

        }
        return super.caseLoop(object);
    }

    /**
     * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseScenarioBehaviour(de.uka.ipd.sdq.pcm.usagemodel.ScenarioBehaviour)
     */
    @Override
    public T caseScenarioBehaviour(final ScenarioBehaviour object) {
        // interpret start user action
        for (final AbstractUserAction abstractUserAction : object.getActions_ScenarioBehaviour()) {
            if (abstractUserAction instanceof Start) {
                this.doSwitch(abstractUserAction);
                break;
            }
        }

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
    public T caseAbstractUserAction(final AbstractUserAction object) {
        if (object.getSuccessor() != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Interpret "+object.getSuccessor().eClass().getName()+": "+object);
            }
            this.doSwitch(object.getSuccessor());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Finished Interpretation of "+object.getSuccessor().eClass().getName()+": " + object);
            }        
        }
        return super.caseAbstractUserAction(object);
    }

    /**
     * @see de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch#caseUsageScenario(de.uka.ipd.sdq.pcm.usagemodel.UsageScenario)
     */
    @Override
    public T caseUsageScenario(final UsageScenario usageScenario) {
        this.context.getEventNotificationHelper().firePassedEvent(
                new ModelElementPassedEvent<UsageScenario>(usageScenario, EventType.BEGIN, this.context.getThread()));
        final int stacksize = this.context.getStack().size();
        this.doSwitch(usageScenario.getScenarioBehaviour_UsageScenario());
        if (this.context.getStack().size() != stacksize) {
            throw new PCMModelInterpreterException("Interpreter did not pop all pushed stackframes");
        }
        this.context.getEventNotificationHelper().firePassedEvent(
                new ModelElementPassedEvent<UsageScenario>(usageScenario, EventType.END, this.context.getThread()));
        return super.caseUsageScenario(usageScenario);
    }
}
