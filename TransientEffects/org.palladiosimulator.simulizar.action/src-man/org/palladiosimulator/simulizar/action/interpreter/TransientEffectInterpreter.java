package org.palladiosimulator.simulizar.action.interpreter;

import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.eclipse.m2m.qvt.oml.util.Log;
import org.eclipse.m2m.qvt.oml.util.WriterLog;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.action.core.Action;
import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.core.util.CoreSwitch;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.mapping.ControllerMapping;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOExecutor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.usagemodel.AbstractUserAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.ScenarioBehaviour;
import de.uka.ipd.sdq.pcm.usagemodel.Start;
import de.uka.ipd.sdq.pcm.usagemodel.Stop;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelFactory;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUser;

public class TransientEffectInterpreter extends CoreSwitch<Boolean> {
    private static final Logger LOGGER = Logger.getLogger(QVTOExecutor.class);
    private SimuLizarRuntimeState state;
    private RoleSet roleSet;
    private ActionRepository repository;

    public TransientEffectInterpreter(SimuLizarRuntimeState state, RoleSet set, ActionRepository repository) {
        this.state = state;
        this.roleSet = set;
        this.repository = repository;
    }

    @Override
    public Boolean caseAction(Action action) {
        boolean successful = true;
        for (AdaptationStep step : action.getAdaptationSteps()) {
            successful &= this.doSwitch(step);
        }
        return successful;
    }

    @Override
    public Boolean caseAdaptationStep(AdaptationStep step) {
        return null;
    }

    @Override
    public Boolean caseResourceDemandingStep(ResourceDemandingStep step) {
        // perform controller completion
        // TODO FIXME currently it is assumed that all components are in the same repository
        boolean conflictExists = checkForConflictsAndApplyTransientStates(step);
        if (conflictExists) {
            return false;
        }
        /*
         * If no conflicts exists the action can be executed. Consequently stereotypes have been
         * applied. These must result in model updates. TODO FIXME Christian 'Real' reconfigurations
         * should be handled differently than stereotype applications. TODO FIXME Christian do not
         * return NULL.
         */
        Repository repository = step.getControllerCalls().get(0).getComponent().getRepository__RepositoryComponent();
        Mapping mapping = controllerCompletion(step, repository);
        state.getReconfigurator().getEventDispatcher().reconfigurationExecuted(null);
        final List<OpenWorkloadUser> users = new LinkedList<OpenWorkloadUser>();
        SimuComModel model = state.getMainContext().getModel();

        // Barrier process
        SimuComSimProcess barrierProcess = new SimuComSimProcess(model, "Transient Effect Barrier") {
            @Override
            protected void internalLifeCycle() {
                // wait for the users to finish processing
                while (checkIfUsersRun(users)) {
                    this.passivate();
                }
                // execute adaptation
                executeAdaptation(step, mapping);
                state.getReconfigurator().getEventDispatcher().reconfigurationExecuted(null);
            }
        };

        // consume resources
        for (ControllerMapping controllerMapping : mapping.getControllerMappings()) {
            ControllerCall call = controllerMapping.getMappedCall();
            IScenarioRunner runner = new IScenarioRunner() {
                @Override
                public void scenarioRunner(SimuComSimProcess thread) {
                    LOGGER.log(Level.INFO, "Starting with the controller scenario!");
                    final InterpreterDefaultContext newContext = new InterpreterDefaultContext(state.getMainContext(),
                            thread);
                    final UsageScenario usageScenario = UsagemodelFactory.eINSTANCE.createUsageScenario();
                    final ScenarioBehaviour behaviour = UsagemodelFactory.eINSTANCE.createScenarioBehaviour();
                    usageScenario.setScenarioBehaviour_UsageScenario(behaviour);
                    List<AbstractUserAction> actions = behaviour.getActions_ScenarioBehaviour();
                    final Start start = UsagemodelFactory.eINSTANCE.createStart();
                    final EntryLevelSystemCall sysCall = UsagemodelFactory.eINSTANCE.createEntryLevelSystemCall();
                    final Stop stop = UsagemodelFactory.eINSTANCE.createStop();
                    actions.add(start);
                    actions.add(sysCall);
                    actions.add(stop);
                    // ControllerMapping matchingControllerMapping = controllerLookup(mapping,
                    // call);
                    sysCall.setOperationSignature__EntryLevelSystemCall(call.getCalledSignature());
                    sysCall.setProvidedRole_EntryLevelSystemCall(controllerMapping.getControllerRole());
                    start.setSuccessor(sysCall);
                    sysCall.setSuccessor(stop);
                    new UsageScenarioSwitch<Object>(newContext).doSwitch(usageScenario);
                    barrierProcess.scheduleAt(0);
                }
            };
            List<Probe> usageStartStopProbes = Collections.unmodifiableList(Arrays.asList(
                    (Probe) new TakeCurrentSimulationTimeProbe(model.getSimulationControl()),
                    (Probe) new TakeCurrentSimulationTimeProbe(model.getSimulationControl())));
            OpenWorkloadUser user = new OpenWorkloadUser(model, step.getEntityName() + " " + call.getEntityName(),
                    runner, usageStartStopProbes);
            users.add(user);
            user.startUserLife();
        }
        barrierProcess.scheduleAt(0);
        return true;
    }

    private boolean checkForConflictsAndApplyTransientStates(ResourceDemandingStep step) {
        // loadProfile(step.getAction());
        IModelAccess access = state.getModelAccess();
        List<EObject> pcmAllocation = Arrays.asList((EObject) access.getGlobalPCMModel().getAllocation());
        ModelExtent inAllocation = new BasicModelExtent(pcmAllocation);
        List<EObject> roleSetList = Arrays.asList(roleSet);
        ModelExtent inRoleSetExtent = new BasicModelExtent(roleSetList);
        List<EObject> stepList = Arrays.asList(step);
        ModelExtent stepExtent = new BasicModelExtent(stepList);
        ExecutionContextImpl executionContext = new ExecutionContextImpl();
        OutputStreamWriter outStream = new OutputStreamWriter(System.out);
        Log log = new WriterLog(outStream);
        executionContext.setLog(log);
        // Make it so that the executor is only instantiated once
        TransformationExecutor conflictCheckExecutor = new TransformationExecutor(URI.createURI(step
                .getPreconditionURI()));
        // execute controller completion
        ExecutionDiagnostic result = conflictCheckExecutor.execute(executionContext, inAllocation, inRoleSetExtent,
                stepExtent);
        return result.getSeverity() != Diagnostic.OK;
    }

    // private void loadProfile(Action action) {
    // IProfileRegistry registry = IProfileRegistry.INSTANCE;
    // // TODO is if and the code within this needed?
    // if(registry.getProfileProvider(action.getTransientStateProfile()) == null) {
    // registry.registerProfile(new ActionProfileProvider(action));
    // }
    // for(Stereotype stereotype : action.getTransientStateProfile().getStereotypes()) {
    // for(Extension extension : stereotype.getExtensions()) {
    // }
    // }
    // }

    private boolean checkIfUsersRun(List<OpenWorkloadUser> users) {
        for (OpenWorkloadUser user : users) {
            if (!user.isTerminated()) {
                return true;
            }
        }
        return false;
    }

    private Mapping controllerCompletion(AdaptationStep step, Repository controllerRepository) {
        IModelAccess access = state.getModelAccess();
        List<EObject> pcmAllocation = Arrays.asList((EObject) access.getGlobalPCMModel().getAllocation());
        // get PCM model that is transformed
        ModelExtent inoutAllocation = new BasicModelExtent(pcmAllocation);
        List<EObject> roleSetList = Arrays.asList(roleSet);
        ModelExtent inRoleSetExtent = new BasicModelExtent(roleSetList);
        ModelExtent outMapping = new BasicModelExtent();
        List<EObject> repositoryList = Arrays.asList(controllerRepository);
        ModelExtent controllerRepositoryExtent = new BasicModelExtent(repositoryList);
        List<EObject> stepList = Arrays.asList(step);
        ModelExtent stepExtent = new BasicModelExtent(stepList);
        ExecutionContextImpl executionContext = new ExecutionContextImpl();
        OutputStreamWriter outStream = new OutputStreamWriter(System.out);
        Log log = new WriterLog(outStream);
        executionContext.setLog(log);
        // Make it so that the executor is only instantiated once
        TransformationExecutor controllerCompletionExecutor = new TransformationExecutor(URI.createURI(step
                .getControllerCompletionURI()));
        // execute controller completion
        ExecutionDiagnostic result = controllerCompletionExecutor.execute(executionContext, inoutAllocation,
                controllerRepositoryExtent, inRoleSetExtent, stepExtent, outMapping);
        return (Mapping) outMapping.getContents().get(0);
    }

    private void executeAdaptation(AdaptationStep step, Mapping mapping) {
        IModelAccess access = state.getModelAccess();
        List<EObject> pcmAllocation = Arrays.asList((EObject) access.getGlobalPCMModel().getAllocation());
        // get PCM model that is transformed
        ModelExtent inoutAllocation = new BasicModelExtent(pcmAllocation);
        List<EObject> rolesList = Arrays.asList(this.roleSet);
        ModelExtent rolesExtent = new BasicModelExtent(rolesList);
        List<EObject> mappingList = Arrays.asList(mapping);
        ModelExtent mappingExtent = new BasicModelExtent(mappingList);
        ExecutionContextImpl executionContext = new ExecutionContextImpl();
        // Make it so that the executor is only instantiated once
        TransformationExecutor adaptationExecutor = new TransformationExecutor(URI.createURI(step
                .getAdaptationStepURI()));
        // execute controller completion
        ExecutionDiagnostic result = adaptationExecutor.execute(executionContext, inoutAllocation, rolesExtent,
                mappingExtent);
    }
}
