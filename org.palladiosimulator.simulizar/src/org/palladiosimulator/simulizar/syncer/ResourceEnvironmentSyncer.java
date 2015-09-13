package org.palladiosimulator.simulizar.syncer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourcetype.SchedulingPolicy;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.probes.EventProbeList;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.ResourceStateListener;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.ContainerCostProbe;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredContainerEntity;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SchedulingStrategy;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

/**
 * Class to sync resource environment model with SimuCom.
 * 
 * @author Joachim Meyer, Sebastian Lehrig, Matthias Becker
 */
public class ResourceEnvironmentSyncer extends AbstractSyncer<ResourceEnvironment> implements IModelSyncer {

    private static final Logger LOGGER = Logger.getLogger(ResourceEnvironmentSyncer.class.getName());
    private final MonitorRepository monitorRepository;
    private final RuntimeMeasurementModel prm;

    /**
     * Constructor
     * 
     * @param runtimeState
     *            the SimuCom model.
     */
    public ResourceEnvironmentSyncer(final SimuLizarRuntimeState runtimeState) {
        super(Objects.requireNonNull(runtimeState), runtimeState.getModelAccess().getGlobalPCMModel().getAllocation()
                .getTargetResourceEnvironment_Allocation());
        this.monitorRepository = runtimeState.getModelAccess().getMonitorRepositoryModel();
        this.prm = runtimeState.getModelAccess().getRuntimeMeasurementModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.simulizar.syncer.IModelSyncer#initializeSyncer()
     */
    @Override
    public void initializeSyncer() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Synchronise ResourceContainer and Simulated ResourcesContainer");
        }
        // add resource container, if not done already
        for (ResourceContainer resourceContainer : this.model.getResourceContainer_ResourceEnvironment()) {
            String resourceContainerId = resourceContainer.getId();
            AbstractSimulatedResourceContainer simulatedResourceContainer = null;
            if (this.runtimeModel.getModel().getResourceRegistry().containsResourceContainer(resourceContainerId)) {
                simulatedResourceContainer = this.runtimeModel.getModel().getResourceRegistry()
                        .getResourceContainer(resourceContainerId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("SimulatedResourceContainer already exists: " + simulatedResourceContainer);
                }

            } else {
                // create a new SimulatedResourceContainer
                simulatedResourceContainer = createSimulatedResource(resourceContainer, resourceContainerId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Added SimulatedResourceContainer: ID: " + resourceContainerId + " "
                            + simulatedResourceContainer);
                }
            }
            // now sync active resources
            syncActiveResources(resourceContainer, simulatedResourceContainer);
        }
        LOGGER.debug("Synchronisation done");
    }

    /**
     * @param resourceContainer
     * @param resourceContainerId
     */
    private AbstractSimulatedResourceContainer createSimulatedResource(ResourceContainer resourceContainer,
            final String resourceContainerId) {
        AbstractSimulatedResourceContainer simulatedResourceContainer = this.runtimeModel.getModel()
                .getResourceRegistry().createResourceContainer(resourceContainerId);
        return simulatedResourceContainer;
    }

    @Override
    protected void synchronizeSimulationEntities(final Notification notification) {
        // TODO: Inspect notification and act accordingly
        initializeSyncer();
    }

    /**
     * Gets the simulated resource by type id in given simulated resource container.
     * 
     * @param simulatedResourceContainer
     *            the simulated resource container.
     * @param typeId
     *            id of the resource.
     * @return the ScheduledResource.
     */
    private ScheduledResource getScheduledResourceByTypeId(
            final AbstractSimulatedResourceContainer simulatedResourceContainer, final String typeId) {
        // Resource already exists?
        for (final AbstractScheduledResource abstractScheduledResource : simulatedResourceContainer
                .getActiveResources()) {
            if (abstractScheduledResource.getResourceTypeId().equals(typeId)) {
                return (ScheduledResource) abstractScheduledResource;
            }
        }
        return null;
    }

    /**
     * Sync resources in resource container. If simulated resource already exists in SimuCom,
     * setProcessingRate will be updated.
     * 
     * @param resourceContainer
     *            the resource container.
     * @param simulatedResourceContainer
     *            the corresponding simulated resource container in SimuCom.
     */
    private void syncActiveResources(final ResourceContainer resourceContainer,
            final AbstractSimulatedResourceContainer simulatedResourceContainer) {

        // add resources
        for (final ProcessingResourceSpecification processingResource : resourceContainer
                .getActiveResourceSpecifications_ResourceContainer()) {
            final String typeId = processingResource.getActiveResourceType_ActiveResourceSpecification().getId();
            final String processingRate = processingResource.getProcessingRate_ProcessingResourceSpecification()
                    .getSpecification();
            // processingRate does not need to be evaluated, will be done in
            // simulatedResourceContainers

            // SchedulingStrategy
            final SchedulingPolicy schedulingPolicy = processingResource.getSchedulingPolicy();

            String schedulingStrategy = schedulingPolicy.getId();
            if (schedulingStrategy.equals("ProcessorSharing")) {
                schedulingStrategy = SchedulingStrategy.PROCESSOR_SHARING;
            } else if (schedulingStrategy.equals("FCFS")) {
                schedulingStrategy = SchedulingStrategy.FCFS;
            } else if (schedulingStrategy.equals("Delay")) {
                schedulingStrategy = SchedulingStrategy.DELAY;
            }

            final ScheduledResource scheduledResource = this.getScheduledResourceByTypeId(simulatedResourceContainer,
                    typeId);
            if (scheduledResource != null) {
                // scheduled resource already exists, so just adapt the processing rate
                scheduledResource.setProcessingRate(processingRate);
            } else {
                // we have to create a new ScheduledResource of the given type
                createSimulatedActiveResource(resourceContainer, simulatedResourceContainer, processingResource,
                        schedulingStrategy);
            }
        }
    }

    /**
     * 
     * @param resourceContainer
     * @param simulatedResourceContainer
     * @param processingResource
     * @param schedulingStrategy
     */
    private void createSimulatedActiveResource(final ResourceContainer resourceContainer,
            final AbstractSimulatedResourceContainer simulatedResourceContainer,
            final ProcessingResourceSpecification processingResource, String schedulingStrategy) {
        final ScheduledResource scheduledResource = ((SimulatedResourceContainer) simulatedResourceContainer)
                .addActiveResourceWithoutCalculators(processingResource, new String[] {}, resourceContainer.getId(),
                        schedulingStrategy);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added ActiveResource. TypeID: "
                    + processingResource.getActiveResourceType_ActiveResourceSpecification().getId()
                    + ", Description: " + ", SchedulingStrategy: " + schedulingStrategy);
        }

        if (this.monitorRepository != null) {
            for (final Monitor monitor : this.monitorRepository.getMonitors()) {
                if (MonitorRepositoryUtil.elementConformingToMeasuringPoint(processingResource,
                        monitor.getMeasuringPoint())) {
                    for (final MeasurementSpecification measurementSpecification : monitor
                            .getMeasurementSpecifications()) {

                        final String metricID = measurementSpecification.getMetricDescription().getId();
                        if (metricID.equals(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE.getId())
                                || metricID.equals(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC.getId())
                                || metricID.equals(MetricDescriptionConstants.WAITING_TIME_METRIC.getId())
                                || metricID.equals(MetricDescriptionConstants.HOLDING_TIME_METRIC.getId())
                                || metricID.equals(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC.getId())) {
                            new ResourceStateListener(scheduledResource,
                                    runtimeModel.getModel().getSimulationControl(), measurementSpecification,
                                    resourceContainer, prm);
                            initCalculator(schedulingStrategy, scheduledResource, measurementSpecification);
                        } else if (metricID.equals(MetricDescriptionConstants.COST_OVER_TIME.getId())) {
                            initPeriodicCostCalculator((SimulatedResourceContainer) simulatedResourceContainer,
                                    monitor.getMeasuringPoint());
                        }
                    }
                }
            }
        }
    }
    /**
     * TODO review
     */
    private void initPeriodicCostCalculator(SimulatedResourceContainer simulatedContainer,
            MeasuringPoint measuringPoint) {

        List<TriggeredProbe> probeList = new ArrayList<TriggeredProbe>(1);
        final SimuComModel model = this.runtimeModel.getModel();
        final ProbeFrameworkContext ctx = model.getProbeFrameworkContext();

        PeriodicallyTriggeredContainerEntity periodicContainerTrigger = new PeriodicallyTriggeredContainerEntity(
                runtimeModel.getModel(), 0, 360, simulatedContainer); // if
                                                                      // Simulation
                                                                      // Time
                                                                      // is
                                                                      // seconds
                                                                      // 360=1hr,

        ContainerCostProbe containerCostProbe = new ContainerCostProbe(periodicContainerTrigger);

        // EventProbeList(NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME,
        // new TakeNumberOfResourceContainersProbe(model.getResourceRegistry()),


        probeList.add(new TakeCurrentSimulationTimeProbe(model.getSimulationControl()));

        final Probe triggeredProbeList = new EventProbeList(MetricDescriptionConstants.COST_OVER_TIME,
                containerCostProbe, probeList);


        ctx.getCalculatorFactory().buildCostOverTimeCalculator(measuringPoint, triggeredProbeList);
    }

    /**
     * Sets up calculators for active resources.
     * 
     * TODO setup waiting time calculator [Lehrig]
     * 
     * TODO setup holding time calculator (actually, should be renamed to service time for active
     * resources) [Lehrig]
     * 
     * @param schedulingStrategy
     * @param scheduledResource
     * @param metricID
     */
    private void initCalculator(String schedulingStrategy, final ScheduledResource scheduledResource,
            final MeasurementSpecification measurementSpecification) {
        final ActiveResourceMeasuringPoint measuringPoint = (ActiveResourceMeasuringPoint) measurementSpecification
                .getMonitor().getMeasuringPoint();
        final String metricID = measurementSpecification.getMetricDescription().getId();

        if (metricID.equals(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE.getId())
                || metricID.equals(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC.getId())) {
            // setup utilization calculators depending on their scheduling strategy
            // and number of cores (e.g., more than 1 cores requires overall utilization)
            if (schedulingStrategy.equals(SchedulingStrategy.PROCESSOR_SHARING)) {
                if (scheduledResource.getNumberOfInstances() == 1) {
                    CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource,
                            this.runtimeModel.getModel(), measuringPoint, measuringPoint.getReplicaID());
                } else {
                    CalculatorHelper.setupOverallUtilizationCalculator(scheduledResource, this.runtimeModel.getModel(),
                            measuringPoint);
                }
            } else if (schedulingStrategy.equals(SchedulingStrategy.DELAY)
                    || schedulingStrategy.equals(SchedulingStrategy.FCFS)) {
                assert (scheduledResource.getNumberOfInstances() == 1) : "DELAY and FCFS resources are expected to "
                        + "have exactly one core";
                CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource, this.runtimeModel.getModel(),
                        measuringPoint, 0);
            } else {
                throw new IllegalArgumentException("Unknown active resource type instrumented with state metric");
            }
        } else if (metricID.equals(MetricDescriptionConstants.WAITING_TIME_METRIC.getId())) {
            // CalculatorHelper.setupWaitingTimeCalculator(r, this.myModel); FIXME
        } else if (metricID.equals(MetricDescriptionConstants.HOLDING_TIME_METRIC.getId())) {
            // CalculatorHelper.setupHoldingTimeCalculator(r, this.myModel); FIXME
        } else if (metricID.equals(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC.getId())) {
            CalculatorHelper.setupDemandCalculator(scheduledResource, this.runtimeModel.getModel(), measuringPoint);
        }
    }
}