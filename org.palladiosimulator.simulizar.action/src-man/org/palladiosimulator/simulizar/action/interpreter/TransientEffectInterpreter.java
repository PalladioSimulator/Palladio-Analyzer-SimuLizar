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
import org.palladiosimulator.simulizar.action.context.ContextFactory;
import org.palladiosimulator.simulizar.action.context.ContextPackage;
import org.palladiosimulator.simulizar.action.context.ExecutionContext;
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
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.reconfiguration.ReconfigurationProcess;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUser;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimProcess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimProcessListener;

/**
 * Visitor implementation specialized to interpret {@link AdaptationBehavior}s
 * that are triggered during Simulizar runs. <br>
 * This class is comparable to the interpreter classes within Simulizar.
 * 
 * @author Florian Rosenthal
 *
 */
public class TransientEffectInterpreter extends CoreSwitch<TransientEffectExecutionResult> {
	private static final Logger LOGGER = Logger.getLogger(TransientEffectInterpreter.class);
	private static final String STATE_TRANSFORMING_EXT_POINT_ID = "org.palladiosimulator.simulizar.action.stratetransformation";
	private static final String STATE_TRANSFORMING_CLASS_NAME = "class";

	private static final ExecutionContext DEFAULT_EXECUTION_CONTEXT = ContextFactory.eINSTANCE.createExecutionContext();

	private final SimuLizarRuntimeState state;
	private final ReconfigurationProcess associatedReconfigurationProcess;
	private final RoleSet roleSet;
	private final ControllerCallInputVariableUsageCollection controllerCallsInputVariableUsages;
	private final boolean isAsync;

	private Optional<ExecutionContext> executionContext;

	/**
	 * Initializes a new instance of the {@link TransientEffectInterpreter}
	 * class with the given arguments.
	 * 
	 * @param state
	 *            The {@link SimuLizarRuntimeState} of the current Simulizar
	 *            run.
	 * @param set
	 *            The {@link RoleSet} instance which shall be used for
	 *            interpretation.
	 * @param controllerCallsInputVariableUsages
	 *            The {@link ControllerCallInputVariableUsageCollection} model
	 *            element which contains the
	 *            {@link ControllerCallInputVariableUsage}s.
	 * @param repository
	 *            The current {@link AdaptationBehaviorRepository}.
	 * @param executeAsync
	 *            A flag to indicate whether the adaptation behaviors shall be
	 *            interpreted asynchronously in a dedicated
	 *            {@link SimuComSimProcess}.
	 */
	TransientEffectInterpreter(SimuLizarRuntimeState state, RoleSet set,
			ControllerCallInputVariableUsageCollection controllerCallsInputVariableUsages,
			AdaptationBehaviorRepository repository, boolean executeAsync,
			Optional<ExecutionContext> executionContext) {
		this.state = state;
		this.associatedReconfigurationProcess = this.state.getReconfigurator().getReconfigurationProcess();
		this.roleSet = set;
		this.isAsync = executeAsync;
		this.controllerCallsInputVariableUsages = Objects.requireNonNull(controllerCallsInputVariableUsages);
		this.executionContext = executionContext;
	}

	private AsyncInterpretationProcess createAsyncProcess(AdaptationBehavior behaviorToInterpret) {
		AsyncInterpretationProcess asyncInterpretationProcess = new AsyncInterpretationProcess(this.executionContext,
				behaviorToInterpret);
		asyncInterpretationProcess.addProcessListener(new ISimProcessListener() {

			@Override
			public void notifyTerminated(ISimProcess process) {
				ExecutionContextKeeper.getInstance().removeContextProcessMapping(
						asyncInterpretationProcess.getCorrespondingContext(), asyncInterpretationProcess);
			}

			@Override
			public void notifySuspending(ISimProcess process) {
			}

			@Override
			public void notifyResuming(ISimProcess process) {

			}
		});
		return asyncInterpretationProcess;
	}

	private SimuComSimProcess obtainExecutingProcessForContext() {
		assert !this.isAsync;

		SimuComSimProcess interpreterProcess = null;
		ExecutionContext context = this.executionContext.orElse(DEFAULT_EXECUTION_CONTEXT);
		if (context.getId().equals(DEFAULT_EXECUTION_CONTEXT.getId())) {
			interpreterProcess = this.associatedReconfigurationProcess;
		} else {
			interpreterProcess = ExecutionContextKeeper.getInstance().getProcessForContext(context)
					.orElseThrow(() -> new RuntimeException(
							"Invalid context for synchronous execution of adaptation behavior:\n"
									+ "Corresponding process does not exist or has already terminated!"));
		}
		return interpreterProcess;
	}

	@Override
	public TransientEffectExecutionResult caseAdaptationBehavior(AdaptationBehavior adaptationBehavior) {
		TransientEffectExecutionResult result;

		if (this.isAsync) {
			// spawn an async process for interpretation and return immediately
			AsyncInterpretationProcess asyncProcess = createAsyncProcess(adaptationBehavior);
			ExecutionContextKeeper.getInstance().addContextProcessMapping(asyncProcess.getCorrespondingContext(),
					asyncProcess);
			TransientEffectInterpreter.this.executionContext = 
				Optional.of(asyncProcess.getCorrespondingContext());
			asyncProcess.activate();
			LOGGER.info("Scheduled process for async interpretation of adaptation behavior.");
			result = new TransientEffectExecutionResult(EventResult.SUCCESS, asyncProcess.getCorrespondingContext());
		} else {
			boolean successful = executeAdaptationActions(adaptationBehavior.getAdaptationActions(),
					obtainExecutingProcessForContext());
			if (successful) {
				this.forwardReconfigurationNotification(new AdaptationBehaviorExecutedNotification(adaptationBehavior));
			}
			// synchronous execution: no context shall be part of the result
			result = new TransientEffectExecutionResult(EventResult.fromBoolean(successful),
					this.executionContext.orElse(DEFAULT_EXECUTION_CONTEXT));
		}
		return result;
	}

	private Boolean executeAdaptationActions(Collection<AdaptationAction> adaptationActions,
			SimuComSimProcess executingProcess) {
		assert adaptationActions != null && executingProcess != null;
		InternalSwitch executingSwitch = new InternalSwitch(executingProcess);
		// no short-circuit evaluation: ensure that all actions be executed
		return adaptationActions.stream().reduce(true, (result, action) -> executingSwitch.doSwitch(action),
				Boolean::logicalAnd);
	}

	private void forwardReconfigurationNotification(Notification notification) {
		this.associatedReconfigurationProcess.appendReconfigurationNotification(notification);
	}

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

	private final class InternalSwitch extends CoreSwitch<Boolean> {

		/**
		 * Stores the {@link SimuComSimProcess} that creates and starts the user
		 * processes for the controller scenarios (
		 * {@link ResourceDemandingAction}s if necessary.<br>
		 * In case of a synchronous execution of the adaptation behavior, this
		 * is just the associated {@link ReconfigurationProcess}. Otherwise,
		 * this is the new process that has been created for the asynchronous
		 * execution of the adaptation behavior.
		 * 
		 * @see #spawnAsyncInterpreterProcess(AdaptationBehavior)
		 * @see #caseResourceDemandingAction(ResourceDemandingAction)
		 * @see #createAndScheduleControllerScenarioRunner(ControllerMapping)
		 */
		private final SimuComSimProcess executingProcess;
		private final TransientEffectQVTOExecutor qvtoExecutor;
		private final Map<ControllerCall, List<VariableUsage>> inputVariableUsagesPerControllerCall;
		// private final QVToModelCache availableModels;

		private InternalSwitch(SimuComSimProcess executingProcess) {
			this.executingProcess = executingProcess;
			QVToModelCache availableModels = new QVToModelCache(
					Objects.requireNonNull(TransientEffectInterpreter.this.state.getModelAccess()));

			this.qvtoExecutor = new TransientEffectQVTOExecutor(availableModels.snapshot());
			this.inputVariableUsagesPerControllerCall = TransientEffectInterpreter.this.controllerCallsInputVariableUsages
					.getControllerCallInputVariableUsages().stream()
					.collect(groupingBy(ControllerCallInputVariableUsage::getCorrespondingControllerCall,
							mapping(ControllerCallInputVariableUsage::getVariableUsage, toList())));

			this.qvtoExecutor.addTransformationParameters(TransientEffectInterpreter.this.roleSet,
					TransientEffectInterpreter.this.executionContext.orElse(DEFAULT_EXECUTION_CONTEXT));
		}

		@Override
		public Boolean caseNestedAdaptationBehavior(NestedAdaptationBehavior nestedAdaptationBehavior) {
			return executeAdaptationActions(nestedAdaptationBehavior.getAdaptationActions(), this.executingProcess);
		}

		@Override
		public Boolean caseGuardedTransition(GuardedTransition guardedTransition) {
			this.qvtoExecutor.enableForTransformationExecution(guardedTransition);
			TransientEffectQVTOExecutorUtil.validateGuardedTransition(this.qvtoExecutor, guardedTransition);

			return this.qvtoExecutor.executeGuardedTransition(guardedTransition);
		}

		@Override
		public Boolean caseGuardedAction(GuardedAction guardedAction) {
			// find the first GuardedTransition whose condition is evaluated to
			// true
			Optional<NestedAdaptationBehavior> branchToExecute = guardedAction.getGuardedTransitions().stream()
					.filter(this::caseGuardedTransition).findFirst()
					.map(GuardedTransition::getNestedAdaptationBehavior);
			// incorporate case that no branch is to be executed (all conditions
			// failed);
			// then we return false
			return branchToExecute.map(this::doSwitch).orElse(false);
		}

		@Override
		public Boolean caseStateTransformingAction(StateTransformingAction stateTransformingAction) {
			this.qvtoExecutor.enableForTransformationExecution(stateTransformingAction);

			String extensionId = stateTransformingAction.getId();
			AbstractStateTransformation transformation = TransientEffectInterpreter.getStateTransformation(extensionId);
			transformation.setSimulationState(TransientEffectInterpreter.this.state);
			return transformation.execute(TransientEffectInterpreter.this.roleSet);
		};

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
			SimuComModel model = TransientEffectInterpreter.this.state.getMainContext().getModel();

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
			// wait until all users have finished executing by passivating the
			// executing process
			// if this is the underlying reconfiguration process, this ensures
			// that no other
			// reconfigurations can take place concurrently
			while (checkIfUsersRun(users)) {
				this.executingProcess.passivate();
			}
			return true;
		}

		@Override
		public Boolean caseEnactAdaptationAction(EnactAdaptationAction enactAdaptationAction) {
			this.qvtoExecutor.enableForTransformationExecution(enactAdaptationAction);

			// execute adaptation
			TransientEffectQVTOExecutorUtil.validateEnactAdaptationStep(this.qvtoExecutor, enactAdaptationAction);
			final boolean result = this.qvtoExecutor
					.executeTransformation(enactAdaptationAction.getAdaptationStepURI());
			if (result && !TransientEffectInterpreter.this.isAsync) {
				TransientEffectInterpreter.this.forwardReconfigurationNotification(
						new AdaptationActionExecutedNotification(enactAdaptationAction));
			}
			return result;
		}

		private IScenarioRunner createAndScheduleControllerScenarioRunner(ControllerMapping controllerMapping) {

			ControllerCall mappedCall = controllerMapping.getMappedCall();
			Collection<VariableUsage> variableUsages = this.inputVariableUsagesPerControllerCall
					.getOrDefault(mappedCall, Collections.emptyList());

			return process -> {
				LOGGER.info("Start executing the controller scenario ('" + mappedCall.getEntityName() + "')!");

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
				// finally, reschedule the executing process (this is crucial!)
				// as it is passivated in caseResourceDemandingAction if mapped
				// calls are running
				this.executingProcess.scheduleAt(0);

				LOGGER.info("Execution of the controller scenario ('" + mappedCall.getEntityName() + "') finished!");
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
			 * TODO FIXME Christian 'Real' reconfigurations should be handled
			 * differently than stereotype applications.
			 */
			Repository repository = resourceDemandingAction.getControllerCalls().get(0).getComponent()
					.getRepository__RepositoryComponent();
			TransientEffectQVTOExecutorUtil.validateResourceDemandingAction(this.qvtoExecutor, resourceDemandingAction);
			return this.qvtoExecutor.executeControllerCompletion(repository,
					resourceDemandingAction.getControllerCompletionURI());
		}
	}

	private final class AsyncInterpretationProcess extends SimuComSimProcess {

		private final AdaptationBehavior behaviorToInterpret;
		private final ExecutionContext correspondingContext;

		private AsyncInterpretationProcess(Optional<ExecutionContext> context, AdaptationBehavior behaviorToInterpret) {
			super(TransientEffectInterpreter.this.state.getModel(),
					"SimuComSimProcess For Async Action Interpretation");
			this.correspondingContext = context.orElseGet(ContextFactory.eINSTANCE::createExecutionContext);
			this.behaviorToInterpret = behaviorToInterpret;
		}

		@Override
		protected void internalLifeCycle() {
			LOGGER.info("Async execution of adaptation behavior is taking place.");
			// within the async process, just proceed regularly, that is, do the
			// interpretation
			// as usual, that is: with the async process, everything is
			// processed
			// synchronously
			TransientEffectInterpreter.this.executeAdaptationActions(this.behaviorToInterpret.getAdaptationActions(),
					this);
			LOGGER.info("Async execution of adaptation behavior done.");
		}

		private ExecutionContext getCorrespondingContext() {
			return this.correspondingContext;
		}
	}
}
