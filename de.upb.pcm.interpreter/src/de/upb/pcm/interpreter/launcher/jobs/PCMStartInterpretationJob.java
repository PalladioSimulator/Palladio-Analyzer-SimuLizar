package de.upb.pcm.interpreter.launcher.jobs;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.probespec.framework.ISampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.SampleBlackboard;
import de.uka.ipd.sdq.simucomframework.DiscardInvalidMeasurementsBlackboardDecorator;
import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.SimuComGarbageCollector;
import de.uka.ipd.sdq.simucomframework.calculator.CalculatorFactory;
import de.uka.ipd.sdq.simucomframework.calculator.SetupPipesAndFiltersStrategy;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.SimuComProbeStrategyRegistry;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimuComStatus;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimucomstatusFactory;
import de.uka.ipd.sdq.simulation.AbstractSimulationConfig;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.preferences.SimulationPreferencesHelper;
import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.upb.pcm.interpreter.access.UsageModelAccess;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.prm.PrmFactory;

/**
 * Job starting the pcm interpretation.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMStartInterpretationJob implements
		IBlackboardInteractingJob<MDSDBlackboard> {
	private static final Logger logger = Logger
			.getLogger(PCMStartInterpretationJob.class.getName());

	private MDSDBlackboard blackboard;

	private final SimuComWorkflowConfiguration configuration;

	/**
	 * Constructor
	 * 
	 * @param configuration
	 *            the SimuCom workflow configuration.
	 */
	public PCMStartInterpretationJob(
			final SimuComWorkflowConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(final IProgressMonitor monitor)
			throws JobFailedException, UserCanceledException {
		InterpreterLogger.info(logger, "Start job: " + this);
		final SimuComModel simuComModel = initialiseSimuComModel();

		// factories and loaders
		final ModelHelper modelHelper = new ModelHelper(this.blackboard,
				simuComModel, PrmFactory.eINSTANCE.createPRMModel());

		// create usage model access
		final UsageModelAccess usageModelAccess = modelHelper
				.getModelAccessFactory().getUsageModelAccess(
						new InterpreterDefaultContext(simuComModel));

		simuComModel.getSimulationStatus().setCurrentSimulationTime(0);
		simuComModel.setUsageScenarios(
				usageModelAccess.getWorkloadDrivers());

		InterpreterLogger.debug(logger,
				"Start usage scenario for each simulated user");
		final double simRealTimeNano = ExperimentRunner.run(
				modelHelper.getSimuComModel(), modelHelper.getSimuComModel()
						.getConfiguration().getSimuTime());
		final double simRealTimeSeconds = simRealTimeNano / Math.pow(10, 9);
		InterpreterLogger.debug(logger,
				"Finished UsageModel. Interpretation took "
						+ simRealTimeSeconds + " real time seconds");

		simuComModel.getProbeSpecContext().finish();
		InterpreterLogger.info(logger, "finished job: " + this);
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IJob#getName()
	 */
	@Override
	public String getName() {
		return "Perform PCM interpreter";
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void rollback(final IProgressMonitor monitor)
			throws RollbackFailedException {
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IBlackboardInteractingJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
	 */
	@Override
	public void setBlackboard(final MDSDBlackboard blackboard) {
		this.blackboard = blackboard;
	}

	private SimuComModel initialiseSimuComModel() {
		// Configuration options for the simulation engine
		final AbstractSimulationConfig simulationConfiguration = getConfiguration()
				.getSimulationConfiguration();

		// Status model to store the runtime state of the simulator
		final SimuComStatus simuComStatus = createSimuComStatus();

		// Factory used to create the simulation engine used in the simulation,
		// e.g., SSJ engine or Desmo-J engine
		final ISimEngineFactory simEngineFactory = getSimEngineFactory();

		// Probe spec context used to take the measurements of the simulation
		final ProbeSpecContext probeSpecContext = new ProbeSpecContext();

		final SimuComModel simuComModel = new SimuComModel(
				(SimuComConfig) simulationConfiguration, simuComStatus,
				simEngineFactory, false, probeSpecContext);

		linkSimuComAndProbeSpec(simuComModel, probeSpecContext);
		
		return simuComModel;
	}

	private ISimEngineFactory getSimEngineFactory() {
		// load factory for the preferred simulation engine
		ISimEngineFactory factory = SimulationPreferencesHelper
				.getPreferredSimulationEngine();
		if (factory == null) {
			throw new RuntimeException(
					"There is no simulation engine available. Install at least one engine.");
		}
		return factory;
	}

	/**
	 * @return returns the configuration.
	 */
	private SimuComWorkflowConfiguration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Gets the SimuCom status, creates one if none exists.
	 * 
	 * @return the SimuCom status.
	 */
	private SimuComStatus createSimuComStatus() {
		final SimuComStatus simuComStatus = SimucomstatusFactory.eINSTANCE
				.createSimuComStatus();

		simuComStatus.setProcessStatus(SimucomstatusFactory.eINSTANCE
				.createSimulatedProcesses());
		simuComStatus.setResourceStatus(SimucomstatusFactory.eINSTANCE
				.createSimulatedResources());

		return simuComStatus;
	}

	/**
	 * Sets SampleBlackboard instead of concurrency sample blackboard.
	 * 
	 * @param simuComModel
	 *            the SimuCom model.
	 */
	private void linkSimuComAndProbeSpec(final SimuComModel simuComModel,
			final ProbeSpecContext probeSpecContext) {
		final ISampleBlackboard sampleBlackboard = new DiscardInvalidMeasurementsBlackboardDecorator(
				new SampleBlackboard(), simuComModel.getSimulationControl());

		probeSpecContext.initialise(sampleBlackboard,
				new SimuComProbeStrategyRegistry(), new CalculatorFactory(
						simuComModel, new SetupPipesAndFiltersStrategy(
								simuComModel)));

		// install a garbage collector which keeps track of the samples stored
		// on the blackboard and
		// removes samples when they become obsolete
		SimuComGarbageCollector garbageCollector = new SimuComGarbageCollector(
				sampleBlackboard);
		probeSpecContext.setBlackboardGarbageCollector(garbageCollector);
	}
}
