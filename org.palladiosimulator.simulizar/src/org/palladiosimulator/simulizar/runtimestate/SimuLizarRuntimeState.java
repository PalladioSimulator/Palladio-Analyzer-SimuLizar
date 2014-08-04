package org.palladiosimulator.simulizar.runtimestate;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.access.ModelAccess;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.storydiagrams.SDReconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.syncer.IModelSyncer;
import org.palladiosimulator.simulizar.syncer.ResourceEnvironmentSyncer;
import org.palladiosimulator.simulizar.syncer.UsageModelSyncer;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolver;

import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * This class provides access to all simulation and simulizar related objects. This includes access
 * to the original SimuComModel (containing the simulated resources, simulated processes, etc.), to
 * simulizars central simulator event distribution object, and to simulated component instances
 * (e.g. to access their current state of passive ressources, etc.).
 * 
 * Per simulation run, there should be exactly one instance of this class and all of its managed
 * information objects.
 * 
 * @author Steffen Becker
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

        initializeInterpreterListeners();
        initializeReconfiguratorEngines(configuration);
        initializeModelSyncers();
        initializeUsageEvolver();
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

    public void runSimulation() {
        LOGGER.debug("Starting Simulizar simulation...");
        final double simRealTimeNano = ExperimentRunner.run(model);
        LOGGER.debug("Finished Simulation. Simulator took " + (simRealTimeNano / Math.pow(10, 9))
                + " real time seconds");
    }

    public void cleanUp() {
        LOGGER.debug("Deregister all listeners and execute cleanup code");
        this.eventHelper.removeAllListener();
        reconfigurator.removeAllObserver();
        reconfigurator.stopListening();
        this.model.getProbeFrameworkContext().finish();
        this.model.getConfiguration().getRecorderConfigurationFactory().finalizeRecorderConfigurationFactory();
        for (IModelSyncer modelSyncer : modelSyncers) {
            modelSyncer.stopSyncer();
        }
    }

    private void initializeInterpreterListeners() {
        LOGGER.debug("Adding Debug and monitoring interpreter listeners");
        eventHelper.addObserver(new LogDebugListener());
        eventHelper.addObserver(new ProbeFrameworkListener(modelAccess, model));
    }

    private void initializeReconfiguratorEngines(final SimuLizarWorkflowConfiguration configuration) {
        LOGGER.debug("Initializing reconfigurator engines and their rule sets");
        reconfigurator = new Reconfigurator(modelAccess, new IReconfigurator[] {
                new SDReconfigurator(modelAccess), new QVTOReconfigurator(modelAccess, configuration)
        });
        reconfigurator.addObserver(new IReconfigurationListener() {

            @Override
            public void reconfigurationExecuted(Collection<Notification> modelChanges) {
                LOGGER.info("------- System reconfigured at simulation time "
                        + model.getSimulationControl().getCurrentSimulationTime() + "-------");
            }
        });
        reconfigurator.addObserver(modelAccess);
        reconfigurator.startListening();
    }

    private void initializeModelSyncers() {
        LOGGER.debug("Initialize model syncers to keep simucom framework objects in sync with global PCM model");
        this.modelSyncers = new IModelSyncer[] {
                new ResourceEnvironmentSyncer(this), new UsageModelSyncer(this)
        };
        for (IModelSyncer modelSyncer : modelSyncers) {
            modelSyncer.initializeSyncer();
        }
    }

    private void initializeUsageEvolver() {
        LOGGER.debug("Start the code to evolve the usage model over time");
        new UsageEvolver(this).start();
    }
}
