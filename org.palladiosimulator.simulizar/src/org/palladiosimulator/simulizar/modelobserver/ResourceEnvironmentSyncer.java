package org.palladiosimulator.simulizar.modelobserver;

import static org.palladiosimulator.edp2.util.MetricDescriptionUtility.metricDescriptionIdsEqual;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.pcm.core.CorePackage;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointSwitch;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

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
 * @author Joachim Meyer, Sebastian Lehrig, Matthias Becker, Florian Rosenthal
 */
public class ResourceEnvironmentSyncer extends AbstractResourceEnvironmentObserver {

    private static final Logger LOGGER = Logger.getLogger(ResourceEnvironmentSyncer.class.getName());
    private MonitorRepository monitorRepository;

    /*
     * (non-Javadoc)
     *
     * @see org.palladiosimulator.simulizar.syncer.IModelObserver#initializeSyncer()
     */
    @Override
    public void initialize(final AbstractSimuLizarRuntimeState runtimeState) {
        super.initialize(runtimeState);

        this.monitorRepository = runtimeState.getModelAccess().getMonitorRepositoryModel();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initializing Simulated ResourcesContainer");
        }

        this.model.getResourceContainer_ResourceEnvironment().forEach(this::createSimulatedResourceContainer);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initialization done");
        }
    }

    @Override
    protected void add(final Notification notification) {
        ResourceenvironmentPackage resourceenvironmentPackage = ResourceenvironmentPackage.eINSTANCE;
        Object changedFeature = notification.getFeature();

        if (changedFeature == resourceenvironmentPackage
                .getResourceEnvironment_ResourceContainer_ResourceEnvironment()) {
            this.addSimulatedResource((ResourceContainer) notification.getNewValue());
        } else if (changedFeature == resourceenvironmentPackage
                .getResourceContainer_ActiveResourceSpecifications_ResourceContainer()) {
            this.createSimulatedActiveResource((ProcessingResourceSpecification) notification.getNewValue());
        } else if (changedFeature == resourceenvironmentPackage
                .getResourceEnvironment_LinkingResources__ResourceEnvironment()
                || changedFeature == resourceenvironmentPackage
                        .getLinkingResource_CommunicationLinkResourceSpecifications_LinkingResource()
                || changedFeature == resourceenvironmentPackage
                        .getLinkingResource_ConnectedResourceContainers_LinkingResource()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ignoring sync (add) of linking resources");
            }
        } else {
            this.logDebugInfo(notification);
        }
    }

    @Override
    protected void remove(final Notification notification) {
        ResourceenvironmentPackage resourceenvironmentPackage = ResourceenvironmentPackage.eINSTANCE;
        Object changedFeature = notification.getFeature();

        if (changedFeature == resourceenvironmentPackage
                .getResourceEnvironment_ResourceContainer_ResourceEnvironment()) {
            this.removeSimulatedResource((ResourceContainer) notification.getOldValue());
        } else if (changedFeature == resourceenvironmentPackage
                .getResourceEnvironment_LinkingResources__ResourceEnvironment()
                || changedFeature == resourceenvironmentPackage
                        .getLinkingResource_CommunicationLinkResourceSpecifications_LinkingResource()
                || changedFeature == resourceenvironmentPackage
                        .getLinkingResource_ConnectedResourceContainers_LinkingResource()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ignoring sync (remove) of linking resources");
            }
        } else {
            this.logDebugInfo(notification);
        }
    }

    @Override
    protected void set(final Notification notification) {
        Object changedFeature = notification.getFeature();

        if (changedFeature == ResourceenvironmentPackage.eINSTANCE
                .getProcessingResourceSpecification_ProcessingRate_ProcessingResourceSpecification()) {
            this.syncProcessingRate((ProcessingResourceSpecification) notification.getNotifier(),
                    notification.getNewStringValue());
        } else if (changedFeature == CorePackage.eINSTANCE
                .getPCMRandomVariable_ProcessingResourceSpecification_processingRate_PCMRandomVariable()) {
            final PCMRandomVariable pcmRandomVariable = (PCMRandomVariable) notification.getNotifier();
            final EObject parent = pcmRandomVariable.eContainer();

            if (ResourceenvironmentPackage.Literals.PROCESSING_RESOURCE_SPECIFICATION.isInstance(parent)) {
                this.syncProcessingRate((ProcessingResourceSpecification) parent, notification.getNewStringValue());
            } else {
                throw new RuntimeException(
                        "Unsupported Notification.SET for a PCMRandomVariable with parent " + parent);
            }
        } else if (changedFeature == StoexPackage.eINSTANCE.getRandomVariable_Specification()) {
            final RandomVariable randomVariable = (RandomVariable) notification.getNotifier();
            final EObject parent = randomVariable.eContainer();

            if (ResourceenvironmentPackage.Literals.PROCESSING_RESOURCE_SPECIFICATION.isInstance(parent)) {
                this.syncProcessingRate((ProcessingResourceSpecification) parent, notification.getNewStringValue());
            } else {
                throw new RuntimeException("Unsupported Notification.SET for a RandomVariable with parent " + parent);
            }
        } else if (changedFeature == ResourceenvironmentPackage.eINSTANCE
                .getResourceContainer_ResourceEnvironment_ResourceContainer()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ignoring syncing that links resource containers to their environment");
            }
        } else {
            this.logDebugInfo(notification);
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
        return this.runtimeModel.getModel().getResourceRegistry().createResourceContainer(resourceContainer.getId());
    }

    private void removeSimulatedResource(final ResourceContainer resourceContainer) {
        // FIXME shutdown the simulated resource container now (...somehow ;) )
        // AbstractSimulatedResourceContainer simulatedResourceContainer =
        // findSimuComFrameworkResourceContainer();
        // simulatedResourceContainer.shutdown() ???
    }

    private void addActiveResources(final ResourceContainer resourceContainer,
            final AbstractSimulatedResourceContainer simulatedResourceContainer) {
        resourceContainer.getActiveResourceSpecifications_ResourceContainer()
                .forEach(this::createSimulatedActiveResource);
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
        // ScheduledResource takes care about loading (extendend) scheduled
        // resources
        final ScheduledResource scheduledResource = simulatedResourceContainer.addActiveResourceWithoutCalculators(
                processingResource, new String[] {}, resourceContainer.getId(),
                processingResource.getSchedulingPolicy().getId());
        scheduledResource.activateResource();

        this.attachMonitors(processingResource, resourceContainer, scheduledResource.getSchedulingStrategyID(),
                scheduledResource);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added ActiveResource. TypeID: " + this.getActiveResourceTypeID(processingResource)
                    + ", Description: " + ", SchedulingStrategy: " + scheduledResource.getSchedulingStrategyID());
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

        return getSimulatedResourceContainer(processingResource).getActiveResources().stream()
                .filter(abstractScheduledResource -> abstractScheduledResource.getResourceTypeId().equals(typeId))
                .findFirst().map(resource -> (ScheduledResource) resource)
                .orElseThrow(() -> new RuntimeException("Did not find scheduled resource for type ID " + typeId));
    }

    /**
     * TODO Attaching listeners should not be a concern of the syncer. Instead, listen directly for
     * events that require monitor attachment [Lehrig]
     */
    private void attachMonitors(final ProcessingResourceSpecification processingResource,
            final ResourceContainer resourceContainer, final String schedulingStrategy,
            final ScheduledResource scheduledResource) {
        for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                .getMeasurementSpecificationsForElement(this.monitorRepository, processingResource)) {

            new PcmmeasuringpointSwitch<Calculator>() {

                @Override
                public Calculator caseActiveResourceMeasuringPoint(
                        final ActiveResourceMeasuringPoint activeResourceMeasuringPoint) {
                    return attachMonitorForActiveResourceMeasuringPoint(activeResourceMeasuringPoint,
                            measurementSpecification, resourceContainer, scheduledResource, schedulingStrategy);
                };

            }.doSwitch(measurementSpecification.getMonitor().getMeasuringPoint());
        }
    }

    private Calculator attachMonitorForActiveResourceMeasuringPoint(
            final ActiveResourceMeasuringPoint activeResourceMeasuringPoint,
            final MeasurementSpecification measurementSpecification, final ResourceContainer resourceContainer,
            final ScheduledResource scheduledResource, final String schedulingStrategy) {

        Calculator result = null;
        MetricDescription metric = measurementSpecification.getMetricDescription();

        if (metricDescriptionIdsEqual(metric, MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC)) {
            if (!MonitorRepositoryPackage.Literals.FEED_THROUGH
                    .isInstance(measurementSpecification.getProcessingType())) {
                throw new IllegalArgumentException(
                        "MetricDescription (" + MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC.getName()
                                + ") '" + measurementSpecification.getName() + "' of Monitor '"
                                + measurementSpecification.getMonitor().getEntityName() + "' must provide a "
                                + MonitorRepositoryPackage.Literals.PROCESSING_TYPE.getName() + " of Type '"
                                + MonitorRepositoryPackage.Literals.FEED_THROUGH.getName() + "'!");
            }

            // setup utilization calculators depending on their scheduling strategy
            // and number of cores (e.g., more than 1 core requires overall utilization
            // in addition to state of active resource)
            if (activeResourceMeasuringPoint.getReplicaID() == 0 && scheduledResource.getNumberOfInstances() > 1) {
                includeOverallUtilizationCalculator(scheduledResource);
            }
            if (schedulingStrategy.equals(SchedulingStrategy.DELAY)
                    || schedulingStrategy.equals(SchedulingStrategy.FCFS)) {
                assert (scheduledResource.getNumberOfInstances() == 1) : "DELAY and FCFS resources are expected to "
                        + "have exactly one core";
                result = CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource,
                        this.runtimeModel.getModel(), activeResourceMeasuringPoint, 0);
            } else {
                // the general case includes the PROCESSOR_SHARING strategy which is the most common
                result = CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource,
                        this.runtimeModel.getModel(), activeResourceMeasuringPoint,
                        activeResourceMeasuringPoint.getReplicaID());
            }
        } else if (metricDescriptionIdsEqual(metric, MetricDescriptionConstants.WAITING_TIME_METRIC)) {
            // return CalculatorHelper.setupWaitingTimeCalculator(r, this.myModel); FIXME
        } else if (metricDescriptionIdsEqual(metric, MetricDescriptionConstants.HOLDING_TIME_METRIC)) {
            // return CalculatorHelper.setupHoldingTimeCalculator(r, this.myModel); FIXME
        } else if (metricDescriptionIdsEqual(metric, MetricDescriptionConstants.RESOURCE_DEMAND_METRIC)) {
            result = CalculatorHelper.setupDemandCalculator(scheduledResource, this.runtimeModel.getModel(),
                    activeResourceMeasuringPoint);
        }
        return result;
    }

    private void includeOverallUtilizationCalculator(final ScheduledResource scheduledResource) {

        MeasuringPoint utilization = CalculatorHelper.createMeasuringPoint(scheduledResource,
                scheduledResource.getNumberOfInstances());
        CalculatorHelper.setupOverallUtilizationCalculator(scheduledResource, this.runtimeModel.getModel(),
                utilization);
    }
}