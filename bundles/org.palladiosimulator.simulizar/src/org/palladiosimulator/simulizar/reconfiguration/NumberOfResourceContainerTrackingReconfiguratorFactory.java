package org.palladiosimulator.simulizar.reconfiguration;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

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
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.probes.TakeNumberOfResourceContainersProbe;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * This factory creates a new Reconfigurator which keeps track of the number of resource containers in a resource
 * environment.
 * 
 * The code factored out of {@link SimuLizarRuntimeState}.
 * 
 * @author Sebastian Krach
 *
 */
public class NumberOfResourceContainerTrackingReconfiguratorFactory implements ReconfiguratorFactory {
    
    private static final Logger LOGGER = Logger.getLogger(NumberOfResourceContainerTrackingReconfiguratorFactory.class);
    
    private SimuLizarWorkflowConfiguration configuration;
    private ISimulationControl simulationControl;
    private IResourceTableManager resourceTableManager;
    private PCMPartitionManager pcmPartitionManager;
    private SimuComModel model;

    @Inject
    public NumberOfResourceContainerTrackingReconfiguratorFactory(final SimuLizarWorkflowConfiguration configuration,
            final ISimulationControl simulationControl, IResourceTableManager resourceTableManager,
            final PCMPartitionManager pcmPartitionManager, SimuComModel model) {
        this.configuration = configuration;
        this.simulationControl = simulationControl;
        this.resourceTableManager = resourceTableManager;
        this.pcmPartitionManager = pcmPartitionManager;
        this.model = model;
    }

    @Override
    public Reconfigurator get() {
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
                reconfigEngines, configuration, resourceTableManager);
        reconfigurator.addObserver(new IReconfigurationListener() {
            
            int previousNumberOfContainers = getNumberOfResourceContainers();

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
                            &&previousNumberOfContainers != getNumberOfResourceContainers()) {
                        previousNumberOfContainers = getNumberOfResourceContainers();
                        numberOfResourceCalculatorsProbes.takeMeasurement();
                    }
                }
            }
        });
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

}
