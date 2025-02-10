package org.palladiosimulator.simulizar.reconfiguration;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME;

import java.util.Arrays;
import java.util.Optional;

import jakarta.inject.Inject;

import org.apache.log4j.Logger;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.probeframework.calculator.DefaultCalculatorProbeSets;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.probeframework.probes.TriggeredProbeList;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.probes.TakeNumberOfResourceContainersProbe;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * This factory creates a new Reconfigurator which keeps track of the number of resource containers in a resource
 * environment.
 * 
 * @author Sebastian Krach
 *
 */
public class NumberOfResourceContainerTrackingListener implements IReconfigurationListener {
    
    private static final Logger LOGGER = Logger.getLogger(NumberOfResourceContainerTrackingListener.class);
    
    private final ISimulationControl simulationControl;
    private final PCMResourceSetPartition pcmPartition;
    private final IGenericCalculatorFactory calculatorFactory;

    @Inject
    public NumberOfResourceContainerTrackingListener(
            final ISimulationControl simulationControl,
            @Global final PCMResourceSetPartition pcmPartition, 
            IGenericCalculatorFactory calculatorFactory) {
        this.simulationControl = simulationControl;
        this.pcmPartition = pcmPartition;
        this.calculatorFactory = calculatorFactory;
    }

    
    int previousNumberOfContainers = 0;
    Optional<TriggeredProbe> numberOfResourceContainerProbe = Optional.empty();
    
    @Override
    public void initialize() {
        previousNumberOfContainers = getNumberOfResourceContainers();
        numberOfResourceContainerProbe = initNumberOfResourceContainersCalculator();
    }

    @Override
    public void reconfigurationExecuted(final ReconfigurationExecutedEvent reconfExecutedEvent) {
        if (numberOfResourceContainerProbe.isEmpty()) return;
        if (reconfExecutedEvent.getReconfigurationResult() == EventResult.SUCCESS) {
            LOGGER.debug("Successful system reconfiguration lasted " + reconfExecutedEvent.getDuration()
                    + " time units");
            LOGGER.debug("Collected notifications:");
            reconfExecutedEvent.getModelChanges()
                    .forEach(notification -> LOGGER.debug(" " + notification.getNotifier()));

            var noRC = getNumberOfResourceContainers();
            if (previousNumberOfContainers != noRC) {
                previousNumberOfContainers = noRC;
                numberOfResourceContainerProbe.get().takeMeasurement();
            }
        }
    }

    
    
    /**
     * Initializes the <i>number of resource containers</i> measurements. First gets
     * the monitored elements from the monitor repository, then creates
     * corresponding calculators.
     */
    private Optional<TriggeredProbe> initNumberOfResourceContainersCalculator() {
        var repo = pcmPartition.getElement(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository()).stream().findAny();
        if (repo.isPresent()) {
            var monitorRepository = (MonitorRepository) repo.get();
            var resourceEnvironment = pcmPartition.getAllocation().getTargetResourceEnvironment_Allocation();
            for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                    .getMeasurementSpecificationsForElement(monitorRepository, resourceEnvironment)) {

                if (MetricDescriptionUtility.metricDescriptionIdsEqual(measurementSpecification.getMetricDescription(),
                        NUMBER_OF_RESOURCE_CONTAINERS)) {

                    final MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();

                    final TriggeredProbeList numberOfResourceCalculatorsProbes = new TriggeredProbeList(
                            NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME,
                            Arrays.asList(new TakeNumberOfResourceContainersProbe(resourceEnvironment),
                                    (TriggeredProbe) new TakeCurrentSimulationTimeProbe(simulationControl)));

                    calculatorFactory.buildCalculator(NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME, measuringPoint,
                            DefaultCalculatorProbeSets.createSingularProbeConfiguration(numberOfResourceCalculatorsProbes));
                    
                    numberOfResourceCalculatorsProbes.takeMeasurement();

                    return Optional.of(numberOfResourceCalculatorsProbes);
                }
            }
        }
        return Optional.empty();
        
    }
    
    private int getNumberOfResourceContainers() {
        return pcmPartition.getAllocation().getTargetResourceEnvironment_Allocation()
                .getResourceContainer_ResourceEnvironment().size();
    }


}
