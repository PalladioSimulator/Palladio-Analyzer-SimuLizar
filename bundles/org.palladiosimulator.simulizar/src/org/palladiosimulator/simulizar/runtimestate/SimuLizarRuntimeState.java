package org.palladiosimulator.simulizar.runtimestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.scopes.SimulationScope;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import dagger.Lazy;
import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.SimulationResult;

/**
 * This class provides access to all simulation and SimuLizar related objects. This includes access
 * to the original SimuComModel (containing the simulated resources, simulated processes, etc.), to
 * SimuLizars central simulator event distribution object, and to simulated component instances
 * (e.g. to access their current state of passive resources, etc.).
 *
 * Per simulation run, there should be exactly one instance of this class and all of its managed
 * information objects.
 * 
 * NOTE: In an effort to improve testability, the number of class inter-dependencies need to be
 * reduced. Therefore, please refrain from referencing the RuntimeState to access dependencies. Use
 * dependency injection to retrieve the desired service / simulation entity directly.
 *
 * @author Steffen Becker, Sebastian Lehrig, Florian Rosenthal, Sebastian Krach
 *
 */
@SimulationScope
public class SimuLizarRuntimeState {

    private static final Logger LOGGER = Logger.getLogger(SimuLizarRuntimeState.class);

    protected final SimuComModel model;
    protected final EventNotificationHelper eventHelper;
    private final ComponentInstanceRegistry componentInstanceRegistry;
    private final SimulatedUsageModels usageModels;
    private final PCMPartitionManager pcmPartitionManager;
    private final Reconfigurator reconfigurator;
    private List<IModelObserver> modelObservers;
    private final SimulationCancelationDelegate cancelationDelegate;
    protected final UsageEvolverFacade usageEvolverFacade;
    private final Set<IInterpreterListener> interpreterListeners;
    private final Lazy<InterpreterDefaultContext> mainContext;
    private final UsageScenarioSwitchFactory usageScenarioSwitchFactory;

    /**
     * @param configuration
     * @param modelAccess
     */
    @Inject
    public SimuLizarRuntimeState(final PCMPartitionManager pcmPartitionManager, 
            final SimuComModel simuComModel,
            final EventNotificationHelper eventHelper,
            final ComponentInstanceRegistry componentInstanceRegistry, 
            final SimulatedUsageModels simulatedUsageModels, 
            final Reconfigurator reconfigurator,
            final UsageEvolverFacade usageEvolverFacade, 
            final Set<IInterpreterListener> interpreterListeners, 
            final SimulationCancelationDelegate cancelationDelegate,
            final @MainContext Lazy<InterpreterDefaultContext> mainContext,
            final UsageScenarioSwitchFactory usageScenarioSwitchFactory) {
        this.pcmPartitionManager = pcmPartitionManager;
        this.interpreterListeners = interpreterListeners;
        this.cancelationDelegate = cancelationDelegate;
        this.model = simuComModel;
        this.eventHelper = eventHelper;
        this.componentInstanceRegistry = componentInstanceRegistry;
		this.usageModels = simulatedUsageModels;
        this.reconfigurator = reconfigurator;
        this.usageEvolverFacade = usageEvolverFacade;
        this.mainContext = mainContext;
        this.usageScenarioSwitchFactory = usageScenarioSwitchFactory;
    }
    
    public void initialize() {
        this.modelObservers = this.initializeModelObservers(Collections.emptyList());
        this.initializeWorkloadDrivers();
        this.initializeCancelation();
        /*
         * ensure to initialize model syncers (in particular ResourceEnvironmentSyncer)
         * prior to interpreter listeners (in particular ProbeFrameworkListener) as
         * ProbeFrameworkListener uses calculators of resources created in
         * ResourceEnvironmentSyncer!
         */
        interpreterListeners.forEach(this.eventHelper::addObserver);
        this.initializeUsageEvolver();
        this.pcmPartitionManager.startObservingPcmChanges();
    }

    /**
     * @return the SimuCom model
     * @deprecated Use dependency injection to retrieve the SimuComModel instance.
     */
    public SimuComModel getModel() {
        return this.model;
    }

    /**
     * @return the event notification helper
     * @deprecated Use dependency injection to retrieve the EventNotificationHelper instance.
     */
    public EventNotificationHelper getEventNotificationHelper() {
        return this.eventHelper;
    }

    /**
     * @return the component instance registry
     * @deprecated Use dependency injection to retrieve the ComponentInstanceRegistry instance.
     */
    public final ComponentInstanceRegistry getComponentInstanceRegistry() {
        return this.componentInstanceRegistry;
    }

    /**
     * @return the main Context
     * @deprecated Use dependency injection to retrieve the main context.
     */
    @Deprecated
    public InterpreterDefaultContext getMainContext() {
        return mainContext.get();
    }

    /**
     * @return the simulated usage model singleton
     * @deprecated Use dependency injection to retrieve the SimulatedUsageModels instance.
     */
    @Deprecated
    public SimulatedUsageModels getUsageModels() {
        return this.usageModels;
    }

    /**
     * @return the PCM partition manager
     * @deprecated Use dependency injection to retrieve the PCMPartitionManager instance.
     */
    @Deprecated
    public PCMPartitionManager getPCMPartitionManager() {
        return this.pcmPartitionManager;
    }

    public boolean isCanceled() {
        return this.cancelationDelegate.isCanceled();
    }

    /**
     * Returns the reconfigurator responsible for executing reconfigurations and
     * notifying listeners of changes.
     *
     * @return The reconfigurator.
     * 
     * @deprecated Use dependency injection to retrieve the Reconfigurator instance.
     */
    @Deprecated
    public Reconfigurator getReconfigurator() {
        return this.reconfigurator;
    }

    public void runSimulation() {
        if (this.modelObservers == null) {
            this.initialize();
        }
        LOGGER.debug("Starting Simulizar simulation...");
        final double simRealTimeNano = ExperimentRunner.run(this.model);
        LOGGER.debug(
                "Finished Simulation. Simulator took " + (simRealTimeNano / Math.pow(10, 9)) + " real time seconds");
        
        if (!SimulationResult.OK.equals(this.model.getErrorStatus())) {
            throw new RuntimeException("The simulation was aborted due to an exception during interpretation.", 
                    this.model.getErrorThrowable());
        }
    }

    public void cleanUp() {
        LOGGER.debug("Deregister all listeners and execute cleanup code");
        this.eventHelper.removeAllListener();
        this.reconfigurator.removeAllObserver();
        this.reconfigurator.cleanUp();
        this.pcmPartitionManager.stopObservingPcmChanges();
        this.model.getProbeFrameworkContext().finish();
        this.model.getConfiguration().getRecorderConfigurationFactory().finalizeRecorderConfigurationFactory();
        this.modelObservers.forEach(IModelObserver::unregister);
        this.pcmPartitionManager.cleanUp();
    }

    private void initializeWorkloadDrivers() {
        LOGGER.debug("Initialise simucom framework's workload drivers");
        this.model.setUsageScenarios(this.usageModels.createWorkloadDrivers());
    }

    private List<IModelObserver> initializeModelObservers(List<IModelObserver> internalObservers) {
        LOGGER.debug(
                "Initialize model observers, e.g., to keep simucom framework objects in sync with global PCM model");

        final List<IModelObserver> modelObservers = new ArrayList<>();
        modelObservers.addAll(internalObservers);
        modelObservers.addAll(ExtensionHelper.getExecutableExtensions("org.palladiosimulator.simulizar.modelobserver",
                "modelObserver"));
        modelObservers.forEach(m -> m.initialize(this));

        return Collections.unmodifiableList(modelObservers);
    }

    private void initializeUsageEvolver() {
        UsageEvolution ueModel = this.pcmPartitionManager
                .findModel(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
        if (ueModel != null) {
            LOGGER.debug("Start the code to evolve the usage model over time");

            this.usageEvolverFacade.start(this);
        }
    }

    private void initializeCancelation() {
        this.model.getSimulationControl().addStopCondition(this::isCanceled);
    }
    
    /**
     * @return the Usage Evolver Facade
     * @deprecated Use dependency injection to retrieve the UsageEvolverFacade instance.
     */
    @Deprecated
    public UsageEvolverFacade getUsageEvolverFacade() {
        return this.usageEvolverFacade;
    }

    /**
     * @return the Usage Scenario Switch Factory
     * @deprecated Use dependency injection to retrieve the UsageScenarioSwitchFactory instance.
     */
    @Deprecated
    public UsageScenarioSwitchFactory getUsageScenarioSwitchFactory() {
        return this.usageScenarioSwitchFactory;
    }
    
}
