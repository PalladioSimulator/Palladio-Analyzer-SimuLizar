package org.palladiosimulator.simulizar.syncer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
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
public class ResourceEnvironmentSyncer extends AbstractSyncer<ResourceEnvironment>implements IModelSyncer {

    private static final Logger LOGGER = Logger.getLogger(ResourceEnvironmentSyncer.class.getName());
    private final MonitorRepository monitorRepository;
    private final RuntimeMeasurementModel runtimeMeasurementModel;
    private int numberOfContainers;

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
        this.runtimeMeasurementModel = runtimeState.getModelAccess().getRuntimeMeasurementModel();
        this.numberOfContainers = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.simulizar.syncer.IModelSyncer#initializeSyncer()
     */
    @Override
    public void initializeSyncer() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initializing Simulated ResourcesContainer");
        }

        for (final ResourceContainer resourceContainer : this.model.getResourceContainer_ResourceEnvironment()) {
            createSimulatedResourceContainer(resourceContainer);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initialization done");
        }
    }

    @Override
    protected void synchronizeSimulationEntities(final Notification notification) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Synching ResourceContainer and Simulated ResourcesContainer");
        }

        switch (notification.getEventType()) {
        case Notification.ADD:
            if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getResourceEnvironment_ResourceContainer_ResourceEnvironment()) {
                addSimulatedResource((ResourceContainer) notification.getNewValue());
            } else if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getResourceContainer_ActiveResourceSpecifications_ResourceContainer()) {
                createSimulatedActiveResource((ProcessingResourceSpecification) notification.getNewValue());
            } else if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getResourceEnvironment_LinkingResources__ResourceEnvironment()
                    || notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                            .getLinkingResource_CommunicationLinkResourceSpecifications_LinkingResource()
                    || notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                            .getLinkingResource_ConnectedResourceContainers_LinkingResource()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Ignoring sync (add) of linking resources");
                }
            } else {
                throw new RuntimeException(
                        "Unsupported Notification.ADD for feature \"" + notification.getFeature() + "\"");
            }
            break;

        case Notification.REMOVE:
            if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getResourceEnvironment_ResourceContainer_ResourceEnvironment()) {
                removeSimulatedResource((ResourceContainer) notification.getOldValue());
            } else if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getResourceEnvironment_LinkingResources__ResourceEnvironment()
                    || notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                            .getLinkingResource_CommunicationLinkResourceSpecifications_LinkingResource()
                    || notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                            .getLinkingResource_ConnectedResourceContainers_LinkingResource()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Ignoring sync (remove) of linking resources");
                }
            } else {
                throw new RuntimeException(
                        "Unsupported Notification.ADD for feature \"" + notification.getFeature() + "\"");
            }
            break;

        case Notification.SET:
            if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getProcessingResourceSpecification_ProcessingRate_ProcessingResourceSpecification()) {
                syncProcessingRate((ProcessingResourceSpecification) notification.getNotifier(),
                        notification.getNewStringValue());
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Unsupported Notification.SET for feature \"" + notification.getFeature() + "\"");
                }
            }

            break;

        default:
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ignoring notification with event type \"" + notification.getEventType() + "\"");
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Synching done");
        }
    }

    private void createSimulatedResourceContainer(ResourceContainer resourceContainer) {
        final AbstractSimulatedResourceContainer simulatedResourceContainer = addSimulatedResource(resourceContainer);
        addActiveResources(resourceContainer, simulatedResourceContainer);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added SimulatedResourceContainer: ID: " + resourceContainer.getId() + " "
                    + simulatedResourceContainer);
        }
    }

    /**
     * @param resourceContainer
     */
    private AbstractSimulatedResourceContainer addSimulatedResource(ResourceContainer resourceContainer) {
        this.numberOfContainers++;
        return this.runtimeModel.getModel().getResourceRegistry().createResourceContainer(resourceContainer.getId());
    }

    private void removeSimulatedResource(final ResourceContainer resourceContainer) {
        this.numberOfContainers--;

        // FIXME shutdown the simulated resource container now (...somehow ;) )
        // AbstractSimulatedResourceContainer simulatedResourceContainer =
        // findSimuComFrameworkResourceContainer();
        // simulatedResourceContainer.shutdown() ???

        // FIXME when the next line is active, exceptions occur (trying to find non existing
        // container)
        // this.runtimeModel.getModel().getResourceRegistry()
        // .removeResourceContainerFromRegistry(resourceContainer.getId());
    }

    private void addActiveResources(ResourceContainer resourceContainer,
            AbstractSimulatedResourceContainer simulatedResourceContainer) {
        for (final ProcessingResourceSpecification processingResource : resourceContainer
                .getActiveResourceSpecifications_ResourceContainer()) {
            createSimulatedActiveResource(processingResource);
        }
    }

    /**
     * 
     * @param processingResource
     * @param schedulingStrategy
     */
    private void createSimulatedActiveResource(final ProcessingResourceSpecification processingResource) {
        final ResourceContainer resourceContainer = processingResource
                .getResourceContainer_ProcessingResourceSpecification();
        final SimulatedResourceContainer simulatedResourceContainer = (SimulatedResourceContainer) getSimulatedResourceContainer(
                processingResource);
        final String schedulingStrategy = getSchedulingStrategy(processingResource);
        final ScheduledResource scheduledResource = simulatedResourceContainer.addActiveResourceWithoutCalculators(
                processingResource, new String[] {}, resourceContainer.getId(), schedulingStrategy);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added ActiveResource. TypeID: " + getActiveResourceTypeID(processingResource)
                    + ", Description: " + ", SchedulingStrategy: " + schedulingStrategy);
        }

        for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                .getMeasurementSpecificationsForElement(this.monitorRepository, processingResource)) {
            final String metricID = measurementSpecification.getMetricDescription().getId();

            if (metricID.equals(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE.getId())
                    || metricID.equals(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC.getId())
                    || metricID.equals(MetricDescriptionConstants.WAITING_TIME_METRIC.getId())
                    || metricID.equals(MetricDescriptionConstants.HOLDING_TIME_METRIC.getId())
                    || metricID.equals(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC.getId())) {
                new ResourceStateListener(scheduledResource, runtimeModel.getModel().getSimulationControl(),
                        measurementSpecification, resourceContainer, runtimeMeasurementModel);
                initCalculator(schedulingStrategy, scheduledResource, measurementSpecification);
            } else if (metricID.equals(MetricDescriptionConstants.COST_OVER_TIME.getId())) {
                initPeriodicCostCalculator(simulatedResourceContainer,
                        measurementSpecification.getMonitor().getMeasuringPoint());
            }
        }
    }

    private void syncProcessingRate(final ProcessingResourceSpecification processingResourceSpecification,
            final String processingRate) {
        // processingRate does not need to be evaluated, will be done in
        // simulatedResourceContainers
        this.getScheduledResource(processingResourceSpecification).setProcessingRate(processingRate);
    }

    private String getActiveResourceTypeID(final ProcessingResourceSpecification processingResource) {
        return processingResource.getActiveResourceType_ActiveResourceSpecification().getId();
    }

    private AbstractSimulatedResourceContainer getSimulatedResourceContainer(
            final ProcessingResourceSpecification processingResource) {
        return this.runtimeModel.getModel().getResourceRegistry().getResourceContainer(
                processingResource.getResourceContainer_ProcessingResourceSpecification().getId());
    }

    /**
     * Gets the simulated resource by type id in given simulated resource container.
     * 
     * @return the ScheduledResource.
     */
    private ScheduledResource getScheduledResource(final ProcessingResourceSpecification processingResource) {
        final String typeId = getActiveResourceTypeID(processingResource);

        for (final AbstractScheduledResource abstractScheduledResource : getSimulatedResourceContainer(
                processingResource).getActiveResources()) {
            if (abstractScheduledResource.getResourceTypeId().equals(typeId)) {
                return (ScheduledResource) abstractScheduledResource;
            }
        }
        throw new RuntimeException("Did not find scheduled resource for type ID " + typeId);
    }

    private String getSchedulingStrategy(final ProcessingResourceSpecification processingResource) {
        final String schedulingStrategy = processingResource.getSchedulingPolicy().getId();
        if (schedulingStrategy.equals("ProcessorSharing")) {
            return SchedulingStrategy.PROCESSOR_SHARING;
        } else if (schedulingStrategy.equals("FCFS")) {
            return SchedulingStrategy.FCFS;
        } else if (schedulingStrategy.equals("Delay")) {
            return SchedulingStrategy.DELAY;
        } else {
            throw new RuntimeException("Unknown scheduling strategy");
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
                    CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource, this.runtimeModel.getModel(),
                            measuringPoint, measuringPoint.getReplicaID());
                } else {
                    CalculatorHelper.setupOverallUtilizationCalculator(scheduledResource, this.runtimeModel.getModel(),
                            measuringPoint);
                }
            } else if (schedulingStrategy.equals(SchedulingStrategy.DELAY)
                    || schedulingStrategy.equals(SchedulingStrategy.FCFS)) {
                assert(scheduledResource.getNumberOfInstances() == 1) : "DELAY and FCFS resources are expected to "
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