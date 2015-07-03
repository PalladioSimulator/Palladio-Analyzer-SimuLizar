package org.palladiosimulator.simulizar.action.interpreter;

import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
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
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUser;

public class TransientEffectInterpreter extends CoreSwitch<Boolean> {
	private static final Logger LOGGER = Logger.getLogger(QVTOExecutor.class);
	private final SimuLizarRuntimeState state;
	private final RoleSet roleSet;

	public TransientEffectInterpreter(final SimuLizarRuntimeState state,
			final RoleSet set, final ActionRepository repository) {
		this.state = state;
		this.roleSet = set;
	}

	@Override
	public Boolean caseAction(final Action action) {
		boolean successful = true;
		for (final AdaptationStep step : action.getAdaptationSteps()) {
			successful &= this.doSwitch(step);
		}
		if (successful) {
			this.state.getReconfigurator()
					.getReconfigurationProcess()
					.appendReconfigurationNotification(
							new ActionExecutedNotification(action));
		}
		return successful;
	}

	@Override
	public Boolean caseAdaptationStep(final AdaptationStep step) {
		throw new AssertionError(
				"AdaptationStep is abstract class, this case should not be reached at all!");
	}

	@Override
	public Boolean caseResourceDemandingStep(final ResourceDemandingStep step) {
		// perform controller completion
		// TODO FIXME currently it is assumed that all components are in the
		// same repository
		final boolean conflictExists = checkForConflictsAndApplyTransientStates(step);
		if (conflictExists) {
			return false;
		}
		/*
		 * If no conflicts exists the action can be executed. Consequently
		 * stereotypes have been applied. These must result in model updates.
		 * TODO FIXME Christian 'Real' reconfigurations should be handled
		 * differently than stereotype applications.
		 */
		final Repository repository = step.getControllerCalls().get(0)
				.getComponent().getRepository__RepositoryComponent();
		final Mapping mapping = controllerCompletion(step, repository);

		List<OpenWorkloadUser> users = new LinkedList<OpenWorkloadUser>();
		SimuComModel model = this.state.getMainContext().getModel();

		// get the process in which the reconfiguration is executed
		final ReconfigurationProcess reconfigurationProcess = this.state
				.getReconfigurator().getReconfigurationProcess();

		// consume resources
		for (ControllerMapping controllerMapping : mapping
				.getControllerMappings()) {

			ControllerCall call = controllerMapping.getMappedCall();
			List<Probe> usageStartStopProbes = Collections
					.unmodifiableList(Arrays.asList(
							(Probe) new TakeCurrentSimulationTimeProbe(model
									.getSimulationControl()),
							(Probe) new TakeCurrentSimulationTimeProbe(model
									.getSimulationControl())));
			OpenWorkloadUser user = new OpenWorkloadUser(model,
					step.getEntityName() + " " + call.getEntityName(),
					createAndScheduleControllerScenarioRunner(
							controllerMapping, reconfigurationProcess),
					usageStartStopProbes);
			users.add(user);
			user.startUserLife();
		}
		// wait until all users have finished executing
		while (checkIfUsersRun(users)) {
			reconfigurationProcess.passivate();
		}
		// execute adaptation
		final boolean result = executeAdaptation(step, mapping);
		if (result) {
			reconfigurationProcess.appendReconfigurationNotification(new AdaptationStepExecutedNotification(step));
		}
		return result;
	}

	private IScenarioRunner createAndScheduleControllerScenarioRunner(
			final ControllerMapping controllerMapping,
			final ReconfigurationProcess reconfigurationProcess) {
		return new IScenarioRunner() {
			@Override
			public void scenarioRunner(final SimuComSimProcess process) {
				LOGGER.log(Level.INFO, "Starting with the controller scenario!");
				final InterpreterDefaultContext newContext = new InterpreterDefaultContext(
						state.getMainContext(), process);
				final UsageScenario usageScenario = UsagemodelFactory.eINSTANCE
						.createUsageScenario();
				final ScenarioBehaviour behaviour = UsagemodelFactory.eINSTANCE
						.createScenarioBehaviour();
				usageScenario.setScenarioBehaviour_UsageScenario(behaviour);
				final List<AbstractUserAction> actions = behaviour
						.getActions_ScenarioBehaviour();
				final Start start = UsagemodelFactory.eINSTANCE.createStart();
				final EntryLevelSystemCall sysCall = UsagemodelFactory.eINSTANCE
						.createEntryLevelSystemCall();
				final Stop stop = UsagemodelFactory.eINSTANCE.createStop();
				actions.add(start);
				actions.add(sysCall);
				actions.add(stop);
				sysCall.setOperationSignature__EntryLevelSystemCall(controllerMapping
						.getMappedCall().getCalledSignature());
				sysCall.setProvidedRole_EntryLevelSystemCall(controllerMapping
						.getControllerRole());
				start.setSuccessor(sysCall);
				sysCall.setSuccessor(stop);
				new UsageScenarioSwitch<Object>(newContext)
						.doSwitch(usageScenario);
				reconfigurationProcess.scheduleAt(0);
			}
		};
	}

	private boolean checkForConflictsAndApplyTransientStates(
			final ResourceDemandingStep step) {
		final IModelAccess access = state.getModelAccess();
		final List<EObject> pcmAllocation = Arrays.asList((EObject) access
				.getGlobalPCMModel().getAllocation());
		final ModelExtent inAllocation = new BasicModelExtent(pcmAllocation);
		final List<EObject> roleSetList = Arrays.asList((EObject) roleSet);
		final ModelExtent inRoleSetExtent = new BasicModelExtent(roleSetList);
		final List<EObject> stepList = Arrays.asList((EObject) step);
		final ModelExtent stepExtent = new BasicModelExtent(stepList);
		final ExecutionContextImpl executionContext = new ExecutionContextImpl();
		final OutputStreamWriter outStream = new OutputStreamWriter(System.out);
		final Log log = new WriterLog(outStream);
		executionContext.setLog(log);
		// Make it so that the executor is only instantiated once
		final TransformationExecutor conflictCheckExecutor = new TransformationExecutor(
				URI.createURI(step.getPreconditionURI()));
		// execute controller completion
		final ExecutionDiagnostic result = conflictCheckExecutor.execute(
				executionContext, inAllocation, inRoleSetExtent, stepExtent);
		return result.getSeverity() != Diagnostic.OK;
	}

	private boolean checkIfUsersRun(final Collection<OpenWorkloadUser> users) {
		for (final OpenWorkloadUser user : users) {
			if (!user.isTerminated()) {
				return true;
			}
		}

		return false;
	}

	private Mapping controllerCompletion(final AdaptationStep step,
			final Repository controllerRepository) {
		final IModelAccess access = state.getModelAccess();
		final List<EObject> pcmAllocation = Arrays.asList((EObject) access
				.getGlobalPCMModel().getAllocation());
		// get PCM model that is transformed
		final ModelExtent inoutAllocation = new BasicModelExtent(pcmAllocation);
		final List<EObject> roleSetList = Arrays.asList((EObject) roleSet);
		final ModelExtent inRoleSetExtent = new BasicModelExtent(roleSetList);
		final ModelExtent outMapping = new BasicModelExtent();
		final List<EObject> repositoryList = Arrays
				.asList((EObject) controllerRepository);
		final ModelExtent controllerRepositoryExtent = new BasicModelExtent(
				repositoryList);
		final List<EObject> stepList = Arrays.asList((EObject) step);
		final ModelExtent stepExtent = new BasicModelExtent(stepList);
		final ExecutionContextImpl executionContext = new ExecutionContextImpl();
		final OutputStreamWriter outStream = new OutputStreamWriter(System.out);
		final Log log = new WriterLog(outStream);
		executionContext.setLog(log);
		// Make it so that the executor is only instantiated once
		final TransformationExecutor controllerCompletionExecutor = new TransformationExecutor(
				URI.createURI(step.getControllerCompletionURI()));
		// execute controller completion
		final ExecutionDiagnostic result = controllerCompletionExecutor
				.execute(executionContext, inoutAllocation,
						controllerRepositoryExtent, inRoleSetExtent,
						stepExtent, outMapping);
		if (result.getSeverity() == Diagnostic.OK) {
			return (Mapping) outMapping.getContents().get(0);
		}
		throw new RuntimeException(
				"Controller Completion transformation did fail!");
	}

	private boolean executeAdaptation(final AdaptationStep step,
			final Mapping mapping) {
		final IModelAccess access = state.getModelAccess();
		final List<EObject> pcmAllocation = Arrays.asList((EObject) access
				.getGlobalPCMModel().getAllocation());
		// get PCM model that is transformed
		final ModelExtent inoutAllocation = new BasicModelExtent(pcmAllocation);
		final List<EObject> rolesList = Arrays.asList((EObject) this.roleSet);
		final ModelExtent rolesExtent = new BasicModelExtent(rolesList);
		final List<EObject> mappingList = Arrays.asList((EObject) mapping);
		final ModelExtent mappingExtent = new BasicModelExtent(mappingList);
		final ExecutionContextImpl executionContext = new ExecutionContextImpl();
		// Make it so that the executor is only instantiated once
		final TransformationExecutor adaptationExecutor = new TransformationExecutor(
				URI.createURI(step.getAdaptationStepURI()));
		// execute adaptation
		final ExecutionDiagnostic result = adaptationExecutor.execute(
				executionContext, inoutAllocation, rolesExtent, mappingExtent);
		return result.getSeverity() == Diagnostic.OK;
	}
}
