package org.palladiosimulator.simulizar.runtimestate;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.access.ModelAccess;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
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
        // ensure to initialize model syncers (in particular ResourceEnvironmentSyncer) prior to
        // interpreter listeners
        // (in particular ProbeFrameworkListener) as ProbeFrameworkListener uses calculators of
        // resources created in ResourceEnvironmentSyncer!
        initializeInterpreterListeners(reconfigurator);
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
        for (final IModelSyncer modelSyncer : modelSyncers) {
            modelSyncer.stopSyncer();
        }
    }

    private void initializeInterpreterListeners(Reconfigurator reconfigurator) {
        LOGGER.debug("Adding Debug and monitoring interpreter listeners");
        eventHelper.addObserver(new LogDebugListener());
        eventHelper.addObserver(new ProbeFrameworkListener(modelAccess, model, reconfigurator));
    }

    private Reconfigurator initializeReconfiguratorEngines(final SimuLizarWorkflowConfiguration configuration,
            final ISimulationControl simulationControl) {
        LOGGER.debug("Initializing reconfigurator engines and their rule sets");

        IExtension[] extensions = Platform.getExtensionRegistry()
                .getExtensionPoint("org.palladiosimulator.simulizar.reconfigurationengine").getExtensions();

        List<IReconfigurator> engines = new LinkedList<IReconfigurator>();
        for (IExtension extension : extensions) {
            for (IConfigurationElement element : extension.getConfigurationElements()) {
                try {
                    IReconfigurator reconfigurator = (IReconfigurator) element
                            .createExecutableExtension("reconfigurationEngine");
                    reconfigurator.setConfiguration(configuration);
                    reconfigurator.setModelAccess(modelAccess);
                    engines.add(reconfigurator);
                } catch (CoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        reconfigurator = new Reconfigurator(model, modelAccess, simulationControl, engines);
        reconfigurator.addObserver(new IReconfigurationListener() {
            @Override
            public void reconfigurationExecuted(Collection<Notification> modelChanges) {
                // nothing to do
            }

            @Override
            public void beginReconfigurationEvent(BeginReconfigurationEvent event) {
                LOGGER.info("------- System reconfiguration started at simulation time "
                        + model.getSimulationControl().getCurrentSimulationTime() + "-------");
            }

            @Override
            public void endReconfigurationEvent(EndReconfigurationEvent event) {
                LOGGER.info("------- System reconfiguration finished at simulation time "
                        + model.getSimulationControl().getCurrentSimulationTime() + " and did "
                        + (event.getReconfigurationEventResult() == EventResult.FAILURE ? "NOT " : "") + "succeed-------");
            }
        });

        reconfigurator.addObserver(modelAccess);
        reconfigurator.startListening();

        return reconfigurator;
    }

    private void initializeModelSyncers() {
        LOGGER.debug("Initialize model syncers to keep simucom framework objects in sync with global PCM model");
        this.modelSyncers = new IModelSyncer[] { new ResourceEnvironmentSyncer(this), new UsageModelSyncer(this) };
        for (IModelSyncer modelSyncer : modelSyncers) {
            modelSyncer.initializeSyncer();
        }
    }

    private void initializeUsageEvolver() {
        LOGGER.debug("Start the code to evolve the usage model over time");
        new UsageEvolver(this).start();
    }
}
