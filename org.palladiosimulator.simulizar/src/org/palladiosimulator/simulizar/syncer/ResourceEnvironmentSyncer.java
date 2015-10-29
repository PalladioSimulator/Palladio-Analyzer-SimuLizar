package org.palladiosimulator.simulizar.syncer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.mdsdprofiles.notifier.MDSDProfilesNotifier;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.pcm.core.CorePackage;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.probeframework.probes.EventProbeList;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.ResourceStateListener;
import org.palladiosimulator.simulizar.runtimestate.CostModel;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.ContainerCostProbe;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredContainerEntity;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredCostModelEntity;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SchedulingStrategy;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.StoexPackage;

/**
 * Class to sync resource environment model with SimuCom.
 *
 * @author Joachim Meyer, Sebastian Lehrig, Matthias Becker
 */
public class ResourceEnvironmentSyncer extends AbstractSyncer<ResourceEnvironment> implements IModelSyncer {

    private static final Logger LOGGER = Logger.getLogger(ResourceEnvironmentSyncer.class.getName());
    private final MonitorRepository monitorRepository;
    private final RuntimeMeasurementModel runtimeMeasurementModel;
    private final CostModel costModel;
    private final HashMap<String, PeriodicallyTriggeredContainerEntity> periodicallyTriggeredContainerEntities;

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

        this.costModel = new CostModel();
        this.initPeriodicCostModelCalculator();
        this.periodicallyTriggeredContainerEntities = new HashMap<String, PeriodicallyTriggeredContainerEntity>();
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
            this.createSimulatedResourceContainer(resourceContainer);
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
                this.addSimulatedResource((ResourceContainer) notification.getNewValue());
            } else if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getResourceContainer_ActiveResourceSpecifications_ResourceContainer()) {
                this.createSimulatedActiveResource((ProcessingResourceSpecification) notification.getNewValue());
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
                this.removeSimulatedResource((ResourceContainer) notification.getOldValue());
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
                this.syncProcessingRate((ProcessingResourceSpecification) notification.getNotifier(),
                        notification.getNewStringValue());
            } else if (notification.getFeature() == CorePackage.eINSTANCE
                    .getPCMRandomVariable_ProcessingResourceSpecification_processingRate_PCMRandomVariable()) {
                final PCMRandomVariable pcmRandomVariable = (PCMRandomVariable) notification.getNotifier();
                final EObject parent = pcmRandomVariable.eContainer();

                if (parent instanceof ProcessingResourceSpecification) {
                    this.syncProcessingRate((ProcessingResourceSpecification) parent, notification.getNewStringValue());
                } else {
                    throw new RuntimeException(
                            "Unsupported Notification.SET for a PCMRandomVariable with parent " + parent);
                }
            } else if (notification.getFeature() == StoexPackage.eINSTANCE.getRandomVariable_Specification()) {
                final RandomVariable randomVariable = (RandomVariable) notification.getNotifier();
                final EObject parent = randomVariable.eContainer();

                if (parent instanceof ProcessingResourceSpecification) {
                    this.syncProcessingRate((ProcessingResourceSpecification) parent, notification.getNewStringValue());
                } else {
                    throw new RuntimeException(
                            "Unsupported Notification.SET for a RandomVariable with parent " + parent);
                }
            } else if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                    .getResourceContainer_ResourceEnvironment_ResourceContainer()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Ignoring syncing that links resource containers to their environment");
                }
            } else {
                throw new RuntimeException(
                        "Unsupported Notification.SET for feature \"" + notification.getFeature() + "\"");
            }
            break;

        case Notification.MOVE:
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ignoring sync of move events; e.g., rewiring of linking resources");
            }
            break;

        case MDSDProfilesNotifier.APPLY_PROFILE:
        case MDSDProfilesNotifier.APPLY_STEREOTYPE:
        case MDSDProfilesNotifier.UNAPPLY_PROFILE:
        case MDSDProfilesNotifier.UNAPPLY_STEREOTYPE:
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ignoring sync of profiles and stereotypes");
            }
            break;

        default:
            throw new RuntimeException("Ignoring notification with event type \"" + notification.getEventType() + "\"");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Synching done");
        }
    }

    private void createSimulatedResourceContainer(final ResourceContainer resourceContainer) {
        final AbstractSimulatedResourceContainer simulatedResourceContainer = this
                .addSimulatedResource(resourceContainer);
        this.addActiveResources(resourceContainer, simulatedResourceContainer);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added SimulatedResourceContainer: ID: " + resourceContainer.getId() + " "
                    + simulatedResourceContainer);
        }
    }

    /**
     * @param resourceContainer
     */
    private AbstractSimulatedResourceContainer addSimulatedResource(final ResourceContainer resourceContainer) {
        final AbstractSimulatedResourceContainer simulatedResourceContainer = this.runtimeModel.getModel()
                .getResourceRegistry().createResourceContainer(resourceContainer.getId());
        initPeriodicCostCalculator(resourceContainer);

        return simulatedResourceContainer;
    }

    private void removeSimulatedResource(final ResourceContainer resourceContainer) {
        // FIXME shutdown the simulated resource container now (...somehow ;) )
        // AbstractSimulatedResourceContainer simulatedResourceContainer =
        // findSimuComFrameworkResourceContainer();
        // simulatedResourceContainer.shutdown() ???

        // FIXME when the next line is active, exceptions occur (trying to find non existing
        // container)
        // this.runtimeModel.getModel().getResourceRegistry()
        // .removeResourceContainerFromRegistry(resourceContainer.getId());
        if (!StereotypeAPI.isStereotypeApplied(resourceContainer, "Price")) {
            return;
        }

        this.periodicallyTriggeredContainerEntities.get(resourceContainer.getId()).removeEvent();
        this.periodicallyTriggeredContainerEntities.remove(resourceContainer.getId());
    }

    private void addActiveResources(final ResourceContainer resourceContainer,
            final AbstractSimulatedResourceContainer simulatedResourceContainer) {
        for (final ProcessingResourceSpecification processingResource : resourceContainer
                .getActiveResourceSpecifications_ResourceContainer()) {
            this.createSimulatedActiveResource(processingResource);
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
        final SimulatedResourceContainer simulatedResourceContainer = (SimulatedResourceContainer) this
                .getSimulatedResourceContainer(processingResource);
        final String schedulingStrategy = this.getSchedulingStrategy(processingResource);
        final ScheduledResource scheduledResource = simulatedResourceContainer.addActiveResourceWithoutCalculators(
                processingResource, new String[] {}, resourceContainer.getId(), schedulingStrategy);

        this.attachMonitors(processingResource, resourceContainer, schedulingStrategy, scheduledResource);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added ActiveResource. TypeID: " + this.getActiveResourceTypeID(processingResource)
                    + ", Description: " + ", SchedulingStrategy: " + schedulingStrategy);
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
        final String typeId = this.getActiveResourceTypeID(processingResource);

        for (final AbstractScheduledResource abstractScheduledResource : this
                .getSimulatedResourceContainer(processingResource).getActiveResources()) {
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
     * TODO Attaching listeners should not be a concern of the syncer. Instead, listen directly for
     * events that require monitor attachment [Lehrig]
     */
    private void attachMonitors(final ProcessingResourceSpecification processingResource,
            final ResourceContainer resourceContainer, final String schedulingStrategy,
            final ScheduledResource scheduledResource) {
        // Processing Resource monitors
        for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                .getMeasurementSpecificationsForElement(this.monitorRepository, processingResource)) {
            final String metricID = measurementSpecification.getMetricDescription().getId();

            if (metricID.equals(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE.getId())
                    || metricID.equals(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC.getId())
                    || metricID.equals(MetricDescriptionConstants.WAITING_TIME_METRIC.getId())
                    || metricID.equals(MetricDescriptionConstants.HOLDING_TIME_METRIC.getId())
                    || metricID.equals(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC.getId())) {
                this.attachResourceStateListener(resourceContainer, scheduledResource, measurementSpecification);
                final ActiveResourceMeasuringPoint measuringPoint = (ActiveResourceMeasuringPoint) measurementSpecification
                        .getMonitor().getMeasuringPoint();

                if (metricID.equals(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE.getId())
                        || metricID.equals(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC.getId())) {
                    // setup utilization calculators depending on their scheduling strategy
                    // and number of cores (e.g., more than 1 cores requires overall utilization)
                    if (schedulingStrategy.equals(SchedulingStrategy.PROCESSOR_SHARING)) {
                        if (scheduledResource.getNumberOfInstances() == 1) {
                            CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource,
                                    this.runtimeModel.getModel(), measuringPoint, measuringPoint.getReplicaID());
                        } else {
                            CalculatorHelper.setupOverallUtilizationCalculator(scheduledResource,
                                    this.runtimeModel.getModel(), measuringPoint);
                        }
                    } else if (schedulingStrategy.equals(SchedulingStrategy.DELAY)
                            || schedulingStrategy.equals(SchedulingStrategy.FCFS)) {
                        assert (scheduledResource
                                .getNumberOfInstances() == 1) : "DELAY and FCFS resources are expected to "
                                        + "have exactly one core";
                        CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource,
                                this.runtimeModel.getModel(), measuringPoint, 0);
                    } else {
                        throw new IllegalArgumentException(
                                "Unknown active resource type instrumented with state metric");
                    }
                } else if (metricID.equals(MetricDescriptionConstants.WAITING_TIME_METRIC.getId())) {
                    // CalculatorHelper.setupWaitingTimeCalculator(r, this.myModel); FIXME
                } else if (metricID.equals(MetricDescriptionConstants.HOLDING_TIME_METRIC.getId())) {
                    // CalculatorHelper.setupHoldingTimeCalculator(r, this.myModel); FIXME
                } else if (metricID.equals(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC.getId())) {
                    CalculatorHelper.setupDemandCalculator(scheduledResource, this.runtimeModel.getModel(),
                            measuringPoint);
                }
            }
        }
    }

    private void attachResourceStateListener(final ResourceContainer resourceContainer,
            final ScheduledResource scheduledResource, final MeasurementSpecification measurementSpecification) {
        new ResourceStateListener(scheduledResource, this.runtimeModel.getModel().getSimulationControl(),
                measurementSpecification, resourceContainer, this.runtimeMeasurementModel);
    }

    private void initPeriodicCostCalculator(final ResourceContainer resourceContainer) {
        if (!StereotypeAPI.isStereotypeApplied(resourceContainer, "Price")) {
            return;
        }

        this.periodicallyTriggeredContainerEntities.put(resourceContainer.getId(),
                new PeriodicallyTriggeredContainerEntity(this.runtimeModel.getModel(), this.costModel,
                        resourceContainer));
    }

    private void initPeriodicCostModelCalculator() {
        if (!StereotypeAPI.isStereotypeApplied(this.model, "CostReport")) {
            return;
        }
        final double interval = StereotypeAPI.getTaggedValue(this.model, "interval", "CostReport");

        for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                .getMeasurementSpecificationsForElement(this.monitorRepository, this.model)) {
            final String metricID = measurementSpecification.getMetricDescription().getId();

            if (metricID.equals(MetricDescriptionConstants.COST_OVER_TIME.getId())) {

                final SimuComModel simuComModel = this.runtimeModel.getModel();

                final Probe probe = new EventProbeList(MetricDescriptionConstants.COST_OVER_TIME,
                        new ContainerCostProbe(new PeriodicallyTriggeredCostModelEntity(simuComModel, this.costModel,
                                interval, interval)),
                        Arrays.asList((TriggeredProbe) new TakeCurrentSimulationTimeProbe(
                                simuComModel.getSimulationControl())));

                simuComModel.getProbeFrameworkContext().getCalculatorFactory()
                        .buildCostOverTimeCalculator(measurementSpecification.getMonitor().getMeasuringPoint(), probe);
            }
        }

    }
}