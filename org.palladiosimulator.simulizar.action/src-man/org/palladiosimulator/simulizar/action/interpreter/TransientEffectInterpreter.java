package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
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
import org.palladiosimulator.simulizar.action.core.AdaptationAction;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationAction;
import org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingAction;
import org.palladiosimulator.simulizar.action.core.StateTransformingAction;
import org.palladiosimulator.simulizar.action.core.util.CoreSwitch;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.interpreter.notifications.AdaptationActionExecutedNotification;
import org.palladiosimulator.simulizar.action.interpreter.notifications.AdaptationBehaviorExecutedNotification;
import org.palladiosimulator.simulizar.action.mapping.ControllerMapping;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;
import org.palladiosimulator.simulizar.reconfiguration.ReconfigurationProcess;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOExecutor;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUser;

public class TransientEffectInterpreter extends CoreSwitch<Boolean> {
    private static final Logger LOGGER = Logger.getLogger(QVTOExecutor.class);
    private static final String STATE_TRANSFORMING_EXT_POINT_ID = "org.palladiosimulator.simulizar.action.stratetransformation";
    private static final String STATE_TRANSFORMING_CLASS_NAME = "class";

    private final SimuLizarRuntimeState state;
    private final RoleSet roleSet;
    private final TransientEffectQVTOExecutor qvtoExecutor;
    private final QVToModelCache availableModels;

    public TransientEffectInterpreter(final SimuLizarRuntimeState state, final RoleSet set,
            final AdaptationBehaviorRepository repository, IModelAccess modelAccess) {
        this.state = state;
        this.roleSet = set;
        this.availableModels = new QVToModelCache(Objects.requireNonNull(modelAccess));
        this.availableModels.storeModel(this.roleSet);
        this.qvtoExecutor = new TransientEffectQVTOExecutor(this.availableModels.snapshot());
    }

    @Override
    public Boolean caseAdaptationBehavior(final AdaptationBehavior adaptationBehavior) {
        boolean successful = adaptationBehavior.getAdaptationSteps().stream().reduce(Boolean.TRUE,
                (result, step) -> doSwitch(step), Boolean::logicalAnd);
        if (successful) {
            this.state.getReconfigurator().getReconfigurationProcess()
                    .appendReconfigurationNotification(new AdaptationBehaviorExecutedNotification(adaptationBehavior));
        }
        return successful;
    }

    @Override
    public Boolean caseGuardedAdaptationBehavior(GuardedAdaptationBehavior guardedAdaptationBehavior) {
        this.qvtoExecutor.enableForTransformationExecution(guardedAdaptationBehavior);

        TransientEffectQVTOExecutorUtil.validateGuardedAdaptationBehavior(this.qvtoExecutor, guardedAdaptationBehavior);
        return this.qvtoExecutor.executeTransformation(guardedAdaptationBehavior.getPreconditionURI());
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
    public Boolean caseAdaptationAction(final AdaptationAction step) {
        throw new AssertionError("AdaptationAction is abstract class, this case should not be reached at all!");
    }

    @Override
    public Boolean caseResourceDemandingAction(final ResourceDemandingAction resourceDemandingAction) {
        this.qvtoExecutor.enableForTransformationExecution(resourceDemandingAction);

        // perform controller completion
        Mapping mapping = executeResourceDemandingAction(resourceDemandingAction)
                .orElseThrow(() -> new RuntimeException("Controller Completion transformation failed!"));

        List<OpenWorkloadUser> users = new LinkedList<OpenWorkloadUser>();
        SimuComModel model = this.state.getMainContext().getModel();

        // get the process in which the reconfiguration is executed
        final ReconfigurationProcess reconfigurationProcess = this.state.getReconfigurator()
                .getReconfigurationProcess();

        // consume resources
        for (ControllerMapping controllerMapping : mapping.getControllerMappings()) {

            ControllerCall call = controllerMapping.getMappedCall();
            List<Probe> usageStartStopProbes = Collections.unmodifiableList(
                    Arrays.asList((Probe) new TakeCurrentSimulationTimeProbe(model.getSimulationControl()),
                            (Probe) new TakeCurrentSimulationTimeProbe(model.getSimulationControl())));
            OpenWorkloadUser user = new OpenWorkloadUser(model,
                    resourceDemandingAction.getEntityName() + " " + call.getEntityName(),
                    createAndScheduleControllerScenarioRunner(controllerMapping, reconfigurationProcess),
                    usageStartStopProbes);
            users.add(user);
            user.startUserLife();
        }
        // wait until all users have finished executing
        while (checkIfUsersRun(users)) {
            reconfigurationProcess.passivate();
        }
        return true;
    }

    @Override
    public Boolean caseEnactAdaptationAction(EnactAdaptationAction enactAdaptationAction) {
        this.qvtoExecutor.enableForTransformationExecution(enactAdaptationAction);

        // execute adaptation
        TransientEffectQVTOExecutorUtil.validateEnactAdaptationStep(this.qvtoExecutor, enactAdaptationAction);
        final boolean result = this.qvtoExecutor.executeTransformation(enactAdaptationAction.getAdaptationStepURI());
        if (result) {
            // get the process in which the reconfiguration is executed
            final ReconfigurationProcess reconfigurationProcess = this.state.getReconfigurator()
                    .getReconfigurationProcess();
            reconfigurationProcess
                    .appendReconfigurationNotification(new AdaptationActionExecutedNotification(enactAdaptationAction));
        }
        return result;
    }

    private IScenarioRunner createAndScheduleControllerScenarioRunner(final ControllerMapping controllerMapping,
            final ReconfigurationProcess reconfigurationProcess) {
        return process -> {
            LOGGER.log(Level.INFO, "Starting with the controller scenario!");
            final InterpreterDefaultContext newContext = new InterpreterDefaultContext(state.getMainContext(), process);
            final UsageScenario usageScenario = UsagemodelFactory.eINSTANCE.createUsageScenario();
            final ScenarioBehaviour behaviour = UsagemodelFactory.eINSTANCE.createScenarioBehaviour();
            usageScenario.setScenarioBehaviour_UsageScenario(behaviour);
            final List<AbstractUserAction> actions = behaviour.getActions_ScenarioBehaviour();
            final Start start = UsagemodelFactory.eINSTANCE.createStart();
            final EntryLevelSystemCall sysCall = UsagemodelFactory.eINSTANCE.createEntryLevelSystemCall();
            final Stop stop = UsagemodelFactory.eINSTANCE.createStop();
            actions.add(start);
            actions.add(sysCall);
            actions.add(stop);
            sysCall.setOperationSignature__EntryLevelSystemCall(controllerMapping.getMappedCall().getCalledSignature());
            sysCall.setProvidedRole_EntryLevelSystemCall(controllerMapping.getControllerRole());
            start.setSuccessor(sysCall);
            sysCall.setSuccessor(stop);
            new UsageScenarioSwitch<Object>(newContext).doSwitch(usageScenario);
            reconfigurationProcess.scheduleAt(0);
        };
    }

    private boolean checkIfUsersRun(final Collection<OpenWorkloadUser> users) {
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
}
