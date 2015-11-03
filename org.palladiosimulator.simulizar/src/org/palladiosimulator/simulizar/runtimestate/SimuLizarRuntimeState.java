package org.palladiosimulator.simulizar.runtimestate;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections15.Closure;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.probeframework.probes.TriggeredProbeList;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.access.ModelAccess;
import org.palladiosimulator.simulizar.access.ModelAccessUseOriginalReferences;
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
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.probes.TakeNumberOfResourceContainersProbe;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * This class provides access to all simulation and SimuLizar related objects. This includes access
 * to the original SimuComModel (containing the simulated resources, simulated processes, etc.), to
 * SimuLizars central simulator event distribution object, and to simulated component instances
 * (e.g. to access their current state of passive resources, etc.).
 *
 * Per simulation run, there should be exactly one instance of this class and all of its managed
 * information objects.
 *
 * @author Steffen Becker, Sebastian Lehrig, slightly adapted by Florian Rosenthal
 *
 */
public class SimuLizarRuntimeState {

    private static final Logger LOGGER = Logger.getLogger(SimuLizarRuntimeState.class);

    private final SimuComModel model;
    private final EventNotificationHelper eventHelper;
    private final ComponentInstanceRegistry componentInstanceRegistry;
    private final InterpreterDefaultContext mainContext;
    private final SimulatedUsageModels usageModels;
//  FIXME @Igor: Use ModelAccess instead of ModelAccessUseOriginalReferences. 
//  After we find a way to copy models so that their links do not point to intermediary, but to the models directly.
    private final ModelAccessUseOriginalReferences modelAccess;
    private final Reconfigurator reconfigurator;
    private final IModelSyncer[] modelSyncers;

    private long numberOfContainers = 0;

    /**
     * @param configuration
     * @param modelAccess
     */
    public SimuLizarRuntimeState(final SimuLizarWorkflowConfiguration configuration, final ModelAccessUseOriginalReferences modelAccess) {
        super();
        this.modelAccess = modelAccess;
        this.model = SimuComModelFactory.createSimuComModel(configuration);
        this.eventHelper = new EventNotificationHelper();
        this.componentInstanceRegistry = new ComponentInstanceRegistry();
        this.mainContext = new InterpreterDefaultContext(this);
        this.usageModels = new SimulatedUsageModels(this.mainContext);
        this.initializeWorkloadDrivers();

        this.reconfigurator = this.initializeReconfiguratorEngines(configuration, this.model.getSimulationControl());
        this.modelSyncers = this.initializeModelSyncers();
        // ensure to initialize model syncers (in particular
        // ResourceEnvironmentSyncer) prior to
        // interpreter listeners
        // (in particular ProbeFrameworkListener) as ProbeFrameworkListener uses
        // calculators of
        // resources created in ResourceEnvironmentSyncer!
        this.initializeInterpreterListeners(this.reconfigurator);
        this.initializeUsageEvolver();
        this.modelAccess.startObservingPcmChanges();
    }

    /**
     * @return the model
     */
    public final SimuComModel getModel() {
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
        this.modelAccess.stopObservingPcmChanges();
        this.model.getProbeFrameworkContext().finish();
        this.model.getConfiguration().getRecorderConfigurationFactory().finalizeRecorderConfigurationFactory();
        for (final IModelSyncer modelSyncer : this.modelSyncers) {
            modelSyncer.stopSyncer();
        }
    }

    private void initializeWorkloadDrivers() {
        LOGGER.debug("Initialise simucom framework's workload drivers");
        this.model.setUsageScenarios(this.usageModels.getWorkloadDrivers());
    }

    private void initializeInterpreterListeners(final Reconfigurator reconfigurator) {
        LOGGER.debug("Adding Debug and monitoring interpreter listeners");
        this.eventHelper.addObserver(new LogDebugListener());
        this.eventHelper.addObserver(new ProbeFrameworkListener(this.modelAccess, this.model, reconfigurator));
    }

    private Reconfigurator initializeReconfiguratorEngines(final SimuLizarWorkflowConfiguration configuration,
            final ISimulationControl simulationControl) {
        LOGGER.debug("Initializing reconfigurator engines and their rule sets");

        final TriggeredProbe numberOfResourceCalculatorsProbes = initNumberOfResourceContainersCalculator();

        final List<IReconfigurator> reconfigEngines = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ID,
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ENGINE_ATTRIBUTE);

        for (final IReconfigurator reconfigEngine : reconfigEngines) {
            reconfigEngine.setConfiguration(configuration);
            reconfigEngine.setModelAccess(this.modelAccess);
        }

        final Reconfigurator reconfigurator = new Reconfigurator(this.model, this.modelAccess, simulationControl,
                reconfigEngines);
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
                        public void execute(final Notification notification) {
                            LOGGER.info("--------------------- " + notification.getNotifier());
                        }
                    });
                    LOGGER.info("--------------");
                    LOGGER.info("-------");

                    if (numberOfResourceCalculatorsProbes != null
                            && SimuLizarRuntimeState.this.numberOfContainers != getNumberOfResourceContainers()) {
                        SimuLizarRuntimeState.this.numberOfContainers = getNumberOfResourceContainers();
                        numberOfResourceCalculatorsProbes.takeMeasurement();
                    }
                }
            }
        });

        reconfigurator.startListening();

        return reconfigurator;
    }

    /**
     * Initializes the <i>number of resource containers</i> measurements. First gets the monitored
     * elements from the monitor repository, then creates corresponding calculators.
     */
    private TriggeredProbe initNumberOfResourceContainersCalculator() {
        final MonitorRepository monitorRepository = this.getModelAccess().getMonitorRepositoryModel();
        final ResourceEnvironment resourceEnvironment = this.getModelAccess().getGlobalPCMModel().getAllocation()
                .getTargetResourceEnvironment_Allocation();

        for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                .getMeasurementSpecificationsForElement(monitorRepository, resourceEnvironment)) {
            final String metricID = measurementSpecification.getMetricDescription().getId();

            if (metricID.equals(NUMBER_OF_RESOURCE_CONTAINERS.getId())) {

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
        return this.getModelAccess().getGlobalPCMModel().getAllocation().getTargetResourceEnvironment_Allocation()
                .getResourceContainer_ResourceEnvironment().size();
    }

    private IModelSyncer[] initializeModelSyncers() {
        LOGGER.debug("Initialize model syncers to keep simucom framework objects in sync with global PCM model");

        final IModelSyncer[] modelSyncers = new IModelSyncer[] { new ResourceEnvironmentSyncer(this),
                new UsageModelSyncer(this) };
        for (final IModelSyncer modelSyncer : modelSyncers) {
            modelSyncer.initializeSyncer();
        }

        return modelSyncers;
    }

    private void initializeUsageEvolver() {
        if (this.modelAccess.getUsageEvolutionModel() != null) {
            LOGGER.debug("Start the code to evolve the usage model over time");

            new UsageEvolver(this).start();
        }
    }
}
