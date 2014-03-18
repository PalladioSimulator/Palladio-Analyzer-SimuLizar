package org.palladiosimulator.simulizar.launcher.jobs;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.access.AccessFactory;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.access.UsageModelAccess;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeSpecListener;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.ReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOReconfigurator;
import org.palladiosimulator.simulizar.utils.ResourceSyncer;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.calculator.DefaultCalculatorFactory;
import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimuComStatus;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimucomstatusFactory;
import de.uka.ipd.sdq.simulation.AbstractSimulationConfig;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.preferences.SimulationPreferencesHelper;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Job starting the pcm interpretation.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMStartInterpretationJob implements IBlackboardInteractingJob<MDSDBlackboard> {

    private static final Logger logger = Logger.getLogger(PCMStartInterpretationJob.class.getName());

    private MDSDBlackboard blackboard;

    private final SimuComWorkflowConfiguration configuration;

    /**
     * Constructor
     * 
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    public PCMStartInterpretationJob(final SimuComWorkflowConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Start job: " + this);

        // 1. Initialise SimuComModel & Simulation Engine
        final SimuComModel simuComModel = this.initialiseSimuComModel();

        // 2. Initialise Model Access Factory
        final IModelAccessFactory modelAccessFactory = AccessFactory.createModelAccessFactory(this.blackboard);

        final InterpreterDefaultContext mainContext = new InterpreterDefaultContext(simuComModel);
        mainContext.getEventNotificationHelper().addListener(new LogDebugListener());
        mainContext.getEventNotificationHelper().addListener(new ProbeSpecListener(modelAccessFactory, simuComModel));

        // 3. Setup interpreters for each usage scenario
        final UsageModelAccess usageModelAccess = modelAccessFactory.getUsageModelAccess(mainContext);
        simuComModel.setUsageScenarios(usageModelAccess.getWorkloadDrivers(modelAccessFactory));

        /*
         * 4. Setup Actuators that keep simulated system and model@runtime consistent Sync Resources
         * from global pcm model with simucom model for the first time, models are already loaded
         * into the blackboard by the workflow engine
         */
        final ResourceSyncer resourceSyncer = new ResourceSyncer(simuComModel, modelAccessFactory);
        resourceSyncer.initialiseResourceEnvironment();

        // 5. Setup reconfiguration rules and engines
        final ReconfigurationListener reconfigurator = new ReconfigurationListener(modelAccessFactory,
                new IReconfigurator[] { new QVTOReconfigurator(modelAccessFactory, configuration, this.blackboard) });
        reconfigurator.startListening();

        // 6. Run Simulation
        logger.debug("Start simulation");
        final double simRealTimeNano = ExperimentRunner.run(simuComModel);
        logger.debug("Finished Simulation. Simulator took " + (simRealTimeNano / Math.pow(10, 9))
                + " real time seconds");

        // 7. Deregister all listeners and execute cleanup code
        mainContext.getEventNotificationHelper().removeAllListener();
        reconfigurator.stopListening();
        simuComModel.getProbeSpecContext().finish();
        logger.info("finished job: " + this);
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#getName()
     */
    @Override
    public String getName() {
        return "Run SimuLizar";
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {
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
        final AbstractSimulationConfig simulationConfiguration = this.getConfiguration().getSimulationConfiguration();

        // Status model to store the runtime state of the simulator
        final SimuComStatus simuComStatus = this.createSimuComStatus();

        // Factory used to create the simulation engine used in the simulation,
        // e.g., SSJ engine or Desmo-J engine
        final ISimEngineFactory simEngineFactory = this.getSimEngineFactory();

        // Probe spec context used to take the measurements of the simulation
        final ProbeSpecContext probeSpecContext = new ProbeSpecContext(new DefaultCalculatorFactory());

        final SimuComModel simuComModel = new SimuComModel((SimuComConfig) simulationConfiguration, simuComStatus,
                simEngineFactory, false, probeSpecContext);

        simuComModel.getSimulationStatus().setCurrentSimulationTime(0);

        this.linkSimuComAndProbeSpec(simuComModel, probeSpecContext);

        return simuComModel;
    }

    private ISimEngineFactory getSimEngineFactory() {
        // load factory for the preferred simulation engine
        final ISimEngineFactory factory = SimulationPreferencesHelper.getPreferredSimulationEngine();
        if (factory == null) {
            throw new RuntimeException("There is no simulation engine available. Install at least one engine.");
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
        final SimuComStatus simuComStatus = SimucomstatusFactory.eINSTANCE.createSimuComStatus();

        simuComStatus.setProcessStatus(SimucomstatusFactory.eINSTANCE.createSimulatedProcesses());
        simuComStatus.setResourceStatus(SimucomstatusFactory.eINSTANCE.createSimulatedResources());

        return simuComStatus;
    }

    /**
     * Sets SampleBlackboard instead of concurrency sample blackboard.
     * 
     * @param simuComModel
     *            the SimuCom model.
     */
    private void linkSimuComAndProbeSpec(final SimuComModel simuComModel, final ProbeSpecContext probeSpecContext) {
        //final ISampleBlackboard discardingBlackboard = new DiscardInvalidMeasurementsBlackboardDecorator(
         //       new SampleBlackboard(), simuComModel.getSimulationControl());

        //probeSpecContext.initialise(discardingBlackboard, new SimuComProbeStrategyRegistry(), new CalculatorFactory(
        //        simuComModel, new SetupPipesAndFiltersStrategy(simuComModel)));

        // install a garbage collector which keeps track of the samples stored
        // on the blackboard and
        // removes samples when they become obsolete
        //probeSpecContext.setBlackboardGarbageCollector(new SimuComGarbageCollector(discardingBlackboard));
    }
}
