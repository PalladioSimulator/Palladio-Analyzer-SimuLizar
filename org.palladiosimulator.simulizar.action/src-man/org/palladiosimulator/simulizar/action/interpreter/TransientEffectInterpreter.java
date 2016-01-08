package org.palladiosimulator.simulizar.action.interpreter;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.Start;
import org.palladiosimulator.pcm.usagemodel.Stop;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationAction;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationAction;
import org.palladiosimulator.simulizar.action.core.GuardedAction;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingAction;
import org.palladiosimulator.simulizar.action.core.StateTransformingAction;
import org.palladiosimulator.simulizar.action.core.util.CoreSwitch;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.interpreter.notifications.AdaptationActionExecutedNotification;
import org.palladiosimulator.simulizar.action.interpreter.notifications.AdaptationBehaviorExecutedNotification;
import org.palladiosimulator.simulizar.action.mapping.ControllerMapping;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;
import org.palladiosimulator.simulizar.reconfiguration.ReconfigurationProcess;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOExecutor;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUser;

/**
 * Visitor implementation specialized to interpret {@link AdaptationBehavior}s that are triggered
 * during Simulizar runs. <br>
 * This class is comparable to the interpreter classes within Simulizar.
 * 
 * @author Florian Rosenthal
 *
 */
public class TransientEffectInterpreter extends CoreSwitch<Boolean> {
    private static final Logger LOGGER = Logger.getLogger(QVTOExecutor.class);
    private static final String STATE_TRANSFORMING_EXT_POINT_ID = "org.palladiosimulator.simulizar.action.stratetransformation";
    private static final String STATE_TRANSFORMING_CLASS_NAME = "class";

    private final SimuLizarRuntimeState state;
    private final ReconfigurationProcess associatedReconfigurationProcess;
    private final RoleSet roleSet;
    private final Map<ControllerCall, List<VariableUsage>> inputVariableUsagesPerControllerCall;
    private final TransientEffectQVTOExecutor qvtoExecutor;
    private final QVToModelCache availableModels;
    private final boolean isAsync;

    TransientEffectInterpreter(SimuLizarRuntimeState state, RoleSet set,
            ControllerCallInputVariableUsageCollection controllerCallsInputVariableUsages,
            AdaptationBehaviorRepository repository, IModelAccess modelAccess, boolean executeAsync) {
        this.state = state;
        this.associatedReconfigurationProcess = this.state.getReconfigurator().getReconfigurationProcess();
        this.roleSet = set;
        this.isAsync = executeAsync;
        this.inputVariableUsagesPerControllerCall = controllerCallsInputVariableUsages
                .getControllerCallInputVariableUsages().stream()
                .collect(groupingBy(ControllerCallInputVariableUsage::getCorrespondingControllerCall,
                        mapping(ControllerCallInputVariableUsage::getVariableUsage, toList())));
        this.availableModels = new QVToModelCache(Objects.requireNonNull(modelAccess));
        this.availableModels.storeModel(this.roleSet);
        this.qvtoExecutor = new TransientEffectQVTOExecutor(this.availableModels.snapshot());
    }

    private void spawnAsyncInterpreterProcess(AdaptationBehavior behaviorToInterpret) {
        SimuComSimProcess asyncInterpreter = new SimuComSimProcess(this.state.getModel(),
                "Async Transient Effect Interpreter", this.associatedReconfigurationProcess.getRequestContext()) {

            @Override
            protected void internalLifeCycle() {
                // within the async process, just proceed regularly, that is, do the interpretion as
                // usual
                TransientEffectInterpreter.this.executeAdaptationActions(behaviorToInterpret.getAdaptationActions());
            }
        };
        asyncInterpreter.scheduleAt(0);
    }

    @Override
    public Boolean caseAdaptationBehavior(AdaptationBehavior adaptationBehavior) {
        boolean successful = true;
        if (this.isAsync) {
            spawnAsyncInterpreterProcess(adaptationBehavior);
        } else {
            successful = executeAdaptationActions(adaptationBehavior.getAdaptationActions());
            if (successful) {
                this.forwardReconfigurationNotification(new AdaptationBehaviorExecutedNotification(adaptationBehavior));
            }
        }
        return successful;
    }

    @Override
    public Boolean caseNestedAdaptationBehavior(NestedAdaptationBehavior nestedAdaptationBehavior) {
        return executeAdaptationActions(nestedAdaptationBehavior.getAdaptationActions());
    }

    private Boolean executeAdaptationActions(Collection<AdaptationAction> adaptationActions) {
        assert adaptationActions != null;

        // no short-circuit evaluation: ensure that all actions be executed
        return adaptationActions.stream().reduce(true, (result, action) -> doSwitch(action), Boolean::logicalAnd);
    }

    @Override
    public Boolean caseGuardedTransition(GuardedTransition guardedTransition) {
        this.qvtoExecutor.enableForTransformationExecution(guardedTransition);
        TransientEffectQVTOExecutorUtil.validateGuardedTransition(this.qvtoExecutor, guardedTransition);

        return this.qvtoExecutor.executeGuardedTransition(guardedTransition);
    }

    @Override
    public Boolean caseGuardedAction(GuardedAction guardedAction) {
        // find the first GuardedTransition whose condition is evaluated to true
        Optional<NestedAdaptationBehavior> branchToExecute = guardedAction.getGuardedTransitions().stream()
                .filter(this::caseGuardedTransition).findFirst().map(GuardedTransition::getNestedAdaptationBehavior);
        // incorporate case that no branch is to be executed (all conditions failed);
        // then we return false
        return branchToExecute.map(this::doSwitch).orElse(false);
    }

    @Override
    public Boolean caseStateTransformingAction(StateTransformingAction stateTransformingAction) {
        this.qvtoExecutor.enableForTransformationExecution(stateTransformingAction);

        String extensionId = stateTransformingAction.getId();
        AbstractStateTransformation transformation = getStateTransformation(extensionId);
        transformation.setSimulationState(this.state);
        return transformation.execute(this.roleSet);
    };

    private static AbstractStateTransformation getStateTransformation(String extensionId) {
        Optional<IExtension> stateTransformingExtension = Arrays
                .stream(Platform.getExtensionRegistry().getExtensionPoint(STATE_TRANSFORMING_EXT_POINT_ID)
                        .getExtensions())
                .filter(extension -> extension.getUniqueIdentifier().equals(extensionId)).findAny();
        IExtension extension = stateTransformingExtension.orElseThrow(() -> new IllegalStateException(
                "No state transformation registered for State Transforming Step " + extensionId));
        for (IConfigurationElement element : extension.getConfigurationElements()) {
            try {
                return (AbstractStateTransformation) element.createExecutableExtension(STATE_TRANSFORMING_CLASS_NAME);
            } catch (CoreException e) {
                LOGGER.error(e.getStackTrace());
            }
        }
        throw new IllegalStateException(
                "No state transformation registered for State Transforming Step " + extensionId);
    }

    @Override
    public Boolean caseAbstractAdaptationBehavior(AbstractAdaptationBehavior abstractAdaptationBehavior) {
        throw new AssertionError("AbstractAdaptationBehavior is abstract, this case should not be reached at all!");
    }

    @Override
    public Boolean caseAdaptationAction(final AdaptationAction step) {
        throw new AssertionError("AdaptationAction is abstract, this case should not be reached at all!");
    }

    @Override
    public Boolean caseResourceDemandingAction(final ResourceDemandingAction resourceDemandingAction) {
        this.qvtoExecutor.enableForTransformationExecution(resourceDemandingAction);

        // perform controller completion
        Mapping mapping = executeResourceDemandingAction(resourceDemandingAction)
                .orElseThrow(() -> new RuntimeException("Controller Completion transformation failed!"));

        List<OpenWorkloadUser> users = new LinkedList<OpenWorkloadUser>();
        SimuComModel model = this.state.getMainContext().getModel();

        // consume resources
        for (ControllerMapping controllerMapping : mapping.getControllerMappings()) {
            ControllerCall call = controllerMapping.getMappedCall();
            List<Probe> usageStartStopProbes = Collections.unmodifiableList(
                    Arrays.asList((Probe) new TakeCurrentSimulationTimeProbe(model.getSimulationControl()),
                            (Probe) new TakeCurrentSimulationTimeProbe(model.getSimulationControl())));
            OpenWorkloadUser user = new OpenWorkloadUser(model,
                    resourceDemandingAction.getEntityName() + " " + call.getEntityName(),
                    createAndScheduleControllerScenarioRunner(controllerMapping), usageStartStopProbes);
            users.add(user);
            user.startUserLife();
        }
        if (!this.isAsync) {
            // wait until all users have finished executing by passivating the underlying
            // reconfiguration process
            // this ensures that no other reconfigurations can take place concurrently
            while (checkIfUsersRun(users)) {
                this.associatedReconfigurationProcess.passivate();
            }
        }
        return true;
    }

    @Override
    public Boolean caseEnactAdaptationAction(EnactAdaptationAction enactAdaptationAction) {
        this.qvtoExecutor.enableForTransformationExecution(enactAdaptationAction);

        // execute adaptation
        TransientEffectQVTOExecutorUtil.validateEnactAdaptationStep(this.qvtoExecutor, enactAdaptationAction);
        final boolean result = this.qvtoExecutor.executeTransformation(enactAdaptationAction.getAdaptationStepURI());
        if (result && !this.isAsync) {
            this.forwardReconfigurationNotification(new AdaptationActionExecutedNotification(enactAdaptationAction));
        }
        return result;
    }

    private IScenarioRunner createAndScheduleControllerScenarioRunner(ControllerMapping controllerMapping) {

        ControllerCall mappedCall = controllerMapping.getMappedCall();
        Collection<VariableUsage> variableUsages = this.inputVariableUsagesPerControllerCall.getOrDefault(mappedCall,
                Collections.emptyList());

        return process -> {
            LOGGER.log(Level.INFO, "Starting with the controller scenario ('" + mappedCall.getEntityName() + "')!");
            InterpreterDefaultContext newContext = new InterpreterDefaultContext(state.getMainContext(), process);
            UsageScenario usageScenario = UsagemodelFactory.eINSTANCE.createUsageScenario();
            ScenarioBehaviour behaviour = UsagemodelFactory.eINSTANCE.createScenarioBehaviour();
            usageScenario.setScenarioBehaviour_UsageScenario(behaviour);
            List<AbstractUserAction> actions = behaviour.getActions_ScenarioBehaviour();
            Start start = UsagemodelFactory.eINSTANCE.createStart();
            EntryLevelSystemCall sysCall = UsagemodelFactory.eINSTANCE.createEntryLevelSystemCall();
            Stop stop = UsagemodelFactory.eINSTANCE.createStop();
            actions.add(start);
            actions.add(sysCall);
            actions.add(stop);
            sysCall.setOperationSignature__EntryLevelSystemCall(mappedCall.getCalledSignature());
            sysCall.setProvidedRole_EntryLevelSystemCall(controllerMapping.getControllerRole());
            // insert the input variable usages now
            sysCall.getInputParameterUsages_EntryLevelSystemCall().addAll(variableUsages);
            start.setSuccessor(sysCall);
            sysCall.setSuccessor(stop);
            new UsageScenarioSwitch<Object>(newContext).doSwitch(usageScenario);
            if (!this.isAsync) {
                // finally, reschedule the reconfiguration process (this is crucial!)
                // as it could have been passivated when a resource demanding action is executed
                // synchronously
                this.associatedReconfigurationProcess.scheduleAt(0);
            }
        };
    }

    private boolean checkIfUsersRun(Collection<OpenWorkloadUser> users) {
        return users.stream().anyMatch(u -> !u.isTerminated());
    }

    private Optional<Mapping> executeResourceDemandingAction(ResourceDemandingAction resourceDemandingAction) {
        assert resourceDemandingAction != null;

        // TODO FIXME currently it is assumed that all components are in the
        // same repository
        /*
         * TODO FIXME Christian 'Real' reconfigurations should be handled differently than
         * stereotype applications.
         */
        Repository repository = resourceDemandingAction.getControllerCalls().get(0).getComponent()
                .getRepository__RepositoryComponent();
        TransientEffectQVTOExecutorUtil.validateControllerCompletion(this.qvtoExecutor, resourceDemandingAction);
        return this.qvtoExecutor.executeControllerCompletion(repository,
                resourceDemandingAction.getControllerCompletionURI());
    }

    private void forwardReconfigurationNotification(Notification notification) {
        this.associatedReconfigurationProcess.appendReconfigurationNotification(notification);
    }
}
