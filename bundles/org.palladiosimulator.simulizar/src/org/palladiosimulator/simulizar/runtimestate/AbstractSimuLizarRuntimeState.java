package org.palladiosimulator.simulizar.runtimestate;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.probeframework.probes.TriggeredProbeList;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContextFactory;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationEngine;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.probes.TakeNumberOfResourceContainersProbe;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * This class provides access to all simulation and SimuLizar related objects.
 * This includes access to the original SimuComModel (containing the simulated
 * resources, simulated processes, etc.), to SimuLizars central simulator event
 * distribution object, and to simulated component instances (e.g. to access
 * their current state of passive resources, etc.).
 *
 * Per simulation run, there should be exactly one instance of this class and
 * all of its managed information objects.
 *
 * @author Steffen Becker, Sebastian Lehrig, slightly adapted by Florian
 *         Rosenthal
 *
 */
public abstract class AbstractSimuLizarRuntimeState {

    private static final Logger LOGGER = Logger.getLogger(SimuLizarRuntimeState.class);

    protected final SimuComModel model;
    protected final EventNotificationHelper eventHelper;
    private final ComponentInstanceRegistry componentInstanceRegistry;
    private final InterpreterDefaultContext mainContext;
    private final SimulatedUsageModels usageModels;
    private final PCMPartitionManager pcmPartitionManager;
    private final Reconfigurator reconfigurator;
    private final List<IModelObserver> modelObservers;
    protected final SimulationCancelationDelegate cancelationDelegate;
    protected final UsageEvolverFacade usageEvolverFacade;

    private long numberOfContainers = 0;

    /**
     * @param configuration
     * @param modelAccess
     */
    public AbstractSimuLizarRuntimeState(final SimuLizarWorkflowConfiguration configuration,
            final MDSDBlackboard blackboard, final SimulationCancelationDelegate cancelationDelegate) {
        super();
        this.pcmPartitionManager = new PCMPartitionManager(blackboard, configuration);
        this.cancelationDelegate = cancelationDelegate;
        this.model = SimuComModelFactory.createSimuComModel(configuration);

        this.eventHelper = new EventNotificationHelper();
        this.componentInstanceRegistry = new ComponentInstanceRegistry();
        
        ISimulatedModelEntityAccess<Identifier, AbstractSimulatedResourceContainer> resourceContainerAccess = 
                this.model.getResourceRegistry()::getResourceContainer;

        var allocationLookup = new AllocationLookupSyncer(resourceContainerAccess);
        this.mainContext = InterpreterDefaultContextFactory.Factory.create(this.getPCMPartitionManager(), this.getModel(), allocationLookup);
        
        this.usageModels = new SimulatedUsageModels(this.mainContext, this.getComponentInstanceRegistry(),
                this.getEventNotificationHelper());
        this.initializeWorkloadDrivers();

        this.reconfigurator = this.initializeReconfiguratorEngines(configuration, this.model.getSimulationControl());
        this.modelObservers = this.initializeModelObservers(Arrays.asList(allocationLookup));
        /*
         * ensure to initialize model syncers (in particular ResourceEnvironmentSyncer)
         * prior to interpreter listeners (in particular ProbeFrameworkListener) as
         * ProbeFrameworkListener uses calculators of resources created in
         * ResourceEnvironmentSyncer!
         */
        this.initializeCancelation();
        this.initializeInterpreterListeners(this.reconfigurator);
        this.usageEvolverFacade = new UsageEvolverFacade(this.pcmPartitionManager, this.model);
        this.initializeUsageEvolver();
        this.pcmPartitionManager.startObservingPcmChanges();
    }

    /**
     * @return the model
     */
    public SimuComModel getModel() {
        return this.model;
    }

    public EventNotificationHelper getEventNotificationHelper() {
        return this.eventHelper;
    }

    /**
     * @return the componentInstanceRegistry
     */
    public final ComponentInstanceRegistry getComponentInstanceRegistry() {
        return this.componentInstanceRegistry;
    }

    public InterpreterDefaultContext getMainContext() {
        return this.mainContext;
    }

    public SimulatedUsageModels getUsageModels() {
        return this.usageModels;
    }

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
     */
    public Reconfigurator getReconfigurator() {
        return this.reconfigurator;
    }

    public void runSimulation() {
        LOGGER.debug("Starting Simulizar simulation...");
        final double simRealTimeNano = ExperimentRunner.run(this.model);
        LOGGER.debug(
                "Finished Simulation. Simulator took " + (simRealTimeNano / Math.pow(10, 9)) + " real time seconds");
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

    protected abstract void initializeInterpreterListeners(final Reconfigurator reconfigurator);

    private Reconfigurator initializeReconfiguratorEngines(final SimuLizarWorkflowConfiguration configuration,
            final ISimulationControl simulationControl) {
        LOGGER.debug("Initializing reconfigurator engines and their rule sets");

        final TriggeredProbe numberOfResourceCalculatorsProbes = initNumberOfResourceContainersCalculator();

        final List<IReconfigurationEngine> reconfigEngines = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ID,
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ENGINE_ATTRIBUTE);
        reconfigEngines.forEach(engine -> {
            engine.setConfiguration(configuration);
            engine.setPCMPartitionManager(pcmPartitionManager);
        });

        RuntimeMeasurementModel rmModel = this.pcmPartitionManager
                .findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
        final Reconfigurator reconfigurator = new Reconfigurator(this.model, rmModel, simulationControl,
                reconfigEngines, configuration);
        reconfigurator.addObserver(new IReconfigurationListener() {

            @Override
            public void beginReconfigurationEvent(final BeginReconfigurationEvent event) {
            }

            @Override
            public void endReconfigurationEvent(final EndReconfigurationEvent event) {
            }

            @Override
            public void reconfigurationExecuted(final ReconfigurationExecutedEvent reconfExecutedEvent) {
                if (reconfExecutedEvent.getReconfigurationResult() == EventResult.SUCCESS) {
                    LOGGER.debug("Successful system reconfiguration lasted " + reconfExecutedEvent.getDuration()
                            + " time units");
                    LOGGER.debug("Collected notifications:");
                    reconfExecutedEvent.getModelChanges()
                            .forEach(notification -> LOGGER.debug(" " + notification.getNotifier()));

                    if (numberOfResourceCalculatorsProbes != null
                            && AbstractSimuLizarRuntimeState.this.numberOfContainers != getNumberOfResourceContainers()) {
                        AbstractSimuLizarRuntimeState.this.numberOfContainers = getNumberOfResourceContainers();
                        numberOfResourceCalculatorsProbes.takeMeasurement();
                    }
                }
            }
        });

        reconfigurator.startListening();

        return reconfigurator;
    }

    /**
     * Initializes the <i>number of resource containers</i> measurements. First gets
     * the monitored elements from the monitor repository, then creates
     * corresponding calculators.
     */
    private TriggeredProbe initNumberOfResourceContainersCalculator() {
        final MonitorRepository monitorRepository = this.pcmPartitionManager
                .findModel(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository());
        final ResourceEnvironment resourceEnvironment = this.pcmPartitionManager.getGlobalPCMModel().getAllocation()
                .getTargetResourceEnvironment_Allocation();

        for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                .getMeasurementSpecificationsForElement(monitorRepository, resourceEnvironment)) {

            if (MetricDescriptionUtility.metricDescriptionIdsEqual(measurementSpecification.getMetricDescription(),
                    NUMBER_OF_RESOURCE_CONTAINERS)) {

                final MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();

                final TriggeredProbeList numberOfResourceCalculatorsProbes = new TriggeredProbeList(
                        NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME,
                        Arrays.asList(new TakeNumberOfResourceContainersProbe(resourceEnvironment),
                                (TriggeredProbe) new TakeCurrentSimulationTimeProbe(
                                        this.model.getSimulationControl())));

                this.model.getProbeFrameworkContext().getCalculatorFactory()
                        .buildNumberOfResourceContainersCalculator(measuringPoint, numberOfResourceCalculatorsProbes);

                this.numberOfContainers = getNumberOfResourceContainers();

                numberOfResourceCalculatorsProbes.takeMeasurement();

                return numberOfResourceCalculatorsProbes;
            }
        }

        return null;
    }

    private int getNumberOfResourceContainers() {
        return this.pcmPartitionManager.getGlobalPCMModel().getAllocation().getTargetResourceEnvironment_Allocation()
                .getResourceContainer_ResourceEnvironment().size();
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

            this.usageEvolverFacade.start();
        }
    }

    private void initializeCancelation() {
        this.model.getSimulationControl().addStopCondition(this::isCanceled);
    }

    public UsageEvolverFacade getUsageEvolverFacade() {
        return this.usageEvolverFacade;
    }
}
