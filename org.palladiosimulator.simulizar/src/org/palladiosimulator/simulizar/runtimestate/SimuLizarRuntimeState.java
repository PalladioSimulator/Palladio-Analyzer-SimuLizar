package org.palladiosimulator.simulizar.runtimestate;

import java.util.List;

import org.apache.commons.collections15.Closure;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.access.ModelAccess;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.syncer.IModelSyncer;
import org.palladiosimulator.simulizar.syncer.ResourceEnvironmentSyncer;
import org.palladiosimulator.simulizar.syncer.UsageModelSyncer;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolver;

import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * This class provides access to all simulation and simulizar related objects. This includes access
 * to the original SimuComModel (containing the simulated resources, simulated processes, etc.), to
 * simulizars central simulator event distribution object, and to simulated component instances
 * (e.g. to access their current state of passive ressources, etc.).
 * 
 * Per simulation run, there should be exactly one instance of this class and all of its managed
 * information objects.
 * 
 * @author Steffen Becker, slightly adapted by Florian Rosenthal
 *
 */
public class SimuLizarRuntimeState {

    private static final Logger LOGGER = Logger.getLogger(SimuLizarRuntimeState.class);

    private final SimuComModel model;
    private final EventNotificationHelper eventHelper;
    private final ComponentInstanceRegistry componentInstanceRegistry;
    private final InterpreterDefaultContext mainContext;
    private final SimulatedUsageModels usageModels;
    private final ModelAccess modelAccess;

    private Reconfigurator reconfigurator;
    private IModelSyncer[] modelSyncers;

    /**
     * @param configuration
     * @param modelAccess
     */
    public SimuLizarRuntimeState(final SimuLizarWorkflowConfiguration configuration, final ModelAccess modelAccess) {
        super();
        this.modelAccess = modelAccess;
        this.model = SimuComModelFactory.createSimuComModel(configuration);
        this.eventHelper = new EventNotificationHelper();
        this.componentInstanceRegistry = new ComponentInstanceRegistry();
        this.mainContext = new InterpreterDefaultContext(this);
        this.usageModels = new SimulatedUsageModels(mainContext);

        LOGGER.debug("Initialise simucom framework's workload drivers");
        this.model.setUsageScenarios(this.usageModels.getWorkloadDrivers());

        Reconfigurator reconfigurator = initializeReconfiguratorEngines(configuration,
                this.model.getSimulationControl());
        initializeModelSyncers();
        // ensure to initialize model syncers (in particular
        // ResourceEnvironmentSyncer) prior to
        // interpreter listeners
        // (in particular ProbeFrameworkListener) as ProbeFrameworkListener uses
        // calculators of
        // resources created in ResourceEnvironmentSyncer!
        initializeInterpreterListeners(reconfigurator);
        if (this.modelAccess.getUsageEvolutionModel() != null) {
            initializeUsageEvolver();
        }
        this.modelAccess.startObservingPcmChanges();
    }

    /**
     * @return the model
     */
    public final SimuComModel getModel() {
        return model;
    }

    public EventNotificationHelper getEventNotificationHelper() {
        return this.eventHelper;
    }

    /**
     * @return the componentInstanceRegistry
     */
    public final ComponentInstanceRegistry getComponentInstanceRegistry() {
        return componentInstanceRegistry;
    }

    public InterpreterDefaultContext getMainContext() {
        return this.mainContext;
    }

    public SimulatedUsageModels getUsageModels() {
        return this.usageModels;
    }

    public IModelAccess getModelAccess() {
        return this.modelAccess;
    }

    /**
     * Returns the reconfigurator responsible for executing reconfigurations and notifying listeners
     * of changes.
     * 
     * @return The reconfigurator.
     */
    public Reconfigurator getReconfigurator() {
        return reconfigurator;
    }

    public void runSimulation() {
        LOGGER.debug("Starting Simulizar simulation...");
        final double simRealTimeNano = ExperimentRunner.run(model);
        LOGGER.debug(
                "Finished Simulation. Simulator took " + (simRealTimeNano / Math.pow(10, 9)) + " real time seconds");
    }

    public void cleanUp() {
        LOGGER.debug("Deregister all listeners and execute cleanup code");
        this.eventHelper.removeAllListener();
        this.reconfigurator.removeAllObserver();
        this.reconfigurator.stopListening();
        this.modelAccess.stopObservingPcmChanges();
        this.model.getProbeFrameworkContext().finish();
        this.model.getConfiguration().getRecorderConfigurationFactory().finalizeRecorderConfigurationFactory();
        for (final IModelSyncer modelSyncer : modelSyncers) {
            modelSyncer.stopSyncer();
        }
    }

    private void initializeInterpreterListeners(final Reconfigurator reconfigurator) {
        LOGGER.debug("Adding Debug and monitoring interpreter listeners");
        eventHelper.addObserver(new LogDebugListener());
        eventHelper.addObserver(new ProbeFrameworkListener(modelAccess, model, reconfigurator));
    }

    private Reconfigurator initializeReconfiguratorEngines(final SimuLizarWorkflowConfiguration configuration,
            final ISimulationControl simulationControl) {
        LOGGER.debug("Initializing reconfigurator engines and their rule sets");

        List<IReconfigurator> reconfigEngines = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ID,
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ENGINE_ATTRIBUTE);

        for (IReconfigurator reconfigEngine : reconfigEngines) {
            reconfigEngine.setConfiguration(configuration);
            reconfigEngine.setModelAccess(this.modelAccess);

        }

        reconfigurator = new Reconfigurator(model, modelAccess, simulationControl, reconfigEngines);
        reconfigurator.addObserver(new IReconfigurationListener() {

            @Override
            public void beginReconfigurationEvent(final BeginReconfigurationEvent event) {
            }

            @Override
            public void endReconfigurationEvent(final EndReconfigurationEvent event) {
            }

            @Override
            public void reconfigurationExecuted(final ReconfigurationExecutedEvent reconfExecutedEvent) {
                if (reconfExecutedEvent.getReconfigurationResult() == EventResult.SUCCESS
                        && reconfExecutedEvent.getDuration() > 0) {
                    LOGGER.info("------- Successful system reconfiguration lasted " + reconfExecutedEvent.getDuration()
                            + " time units-------");
                    LOGGER.info("-------------- Collected notifications:");
                    CollectionUtils.forAllDo(reconfExecutedEvent.getModelChanges(), new Closure<Notification>() {

                        @Override
                        public void execute(Notification notification) {
                            LOGGER.info("--------------------- " + notification.getNotifier());
                        }
                    });
                    LOGGER.info("--------------");
                    LOGGER.info("-------");
                }
            }
        });

        reconfigurator.startListening();

        return reconfigurator;
    }

    private void initializeModelSyncers() {
        LOGGER.debug("Initialize model syncers to keep simucom framework objects in sync with global PCM model");
        this.modelSyncers = new IModelSyncer[] { new ResourceEnvironmentSyncer(this), new UsageModelSyncer(this) };
        for (final IModelSyncer modelSyncer : modelSyncers) {
            modelSyncer.initializeSyncer();
        }
    }

    private void initializeUsageEvolver() {
        LOGGER.debug("Start the code to evolve the usage model over time");
        new UsageEvolver(this).start();
    }
}
