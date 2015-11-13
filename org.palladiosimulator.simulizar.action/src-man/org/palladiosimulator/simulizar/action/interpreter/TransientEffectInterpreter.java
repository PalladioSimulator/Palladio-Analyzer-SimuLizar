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
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.Start;
import org.palladiosimulator.pcm.usagemodel.Stop;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.simulizar.action.core.Action;
import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.core.util.CoreSwitch;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.interpreter.notifications.ActionExecutedNotification;
import org.palladiosimulator.simulizar.action.interpreter.notifications.AdaptationStepExecutedNotification;
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
    private final SimuLizarRuntimeState state;
    private final RoleSet roleSet;
    private TransientEffectQVTOExecutor qvtoExecutor;
    private final QVToModelCache availableModels;

    public TransientEffectInterpreter(final SimuLizarRuntimeState state, final RoleSet set,
            final ActionRepository repository, QVToModelCache availableModels) {
        this.state = state;
        this.roleSet = set;
        this.availableModels = Objects.requireNonNull(availableModels).snapshot();
        this.availableModels.storeModel(this.roleSet);
    }

    @Override
    public Boolean caseAction(final Action action) {
        boolean successful = true;
        action.getAdaptationSteps().stream().reduce(Boolean.TRUE, (result, step) -> doSwitch(step),
                Boolean::logicalAnd);
        if (successful) {
            this.state.getReconfigurator().getReconfigurationProcess()
                    .appendReconfigurationNotification(new ActionExecutedNotification(action));
        }
        return successful;
    }

    @Override
    public Boolean caseAdaptationStep(final AdaptationStep step) {
        throw new AssertionError("AdaptationStep is abstract class, this case should not be reached at all!");
    }

    @Override
    public Boolean caseResourceDemandingStep(final ResourceDemandingStep step) {
        this.qvtoExecutor = new TransientEffectQVTOExecutor(this.availableModels.snapshot(), step);
        // perform controller completion
        // TODO FIXME currently it is assumed that all components are in the
        // same repository
        if (!applyTransientStates()) {
            return false;
        }
        /*
         * If no conflicts exists the action can be executed. Consequently stereotypes have been
         * applied. These must result in model updates. TODO FIXME Christian 'Real' reconfigurations
         * should be handled differently than stereotype applications.
         */
        Repository repository = step.getControllerCalls().get(0).getComponent().getRepository__RepositoryComponent();
        Mapping mapping = controllerCompletion(repository)
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
            OpenWorkloadUser user = new OpenWorkloadUser(model, step.getEntityName() + " " + call.getEntityName(),
                    createAndScheduleControllerScenarioRunner(controllerMapping, reconfigurationProcess),
                    usageStartStopProbes);
            users.add(user);
            user.startUserLife();
        }
        // wait until all users have finished executing
        while (checkIfUsersRun(users)) {
            reconfigurationProcess.passivate();
        }
        // execute adaptation
        final boolean result = executeAdaptation();
        if (result) {
            reconfigurationProcess.appendReconfigurationNotification(new AdaptationStepExecutedNotification(step));
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

    private boolean applyTransientStates() {
        return this.qvtoExecutor.executePrecondition();
    }

    private boolean checkIfUsersRun(final Collection<OpenWorkloadUser> users) {
        return users.stream().anyMatch(u -> !u.isTerminated());
    }

    private Optional<Mapping> controllerCompletion(Repository controllerRepository) {
        return this.qvtoExecutor.executeControllerCompletion(controllerRepository);
    }

    private boolean executeAdaptation() {
        return this.qvtoExecutor.executeAdaptationStep();
    }
}
