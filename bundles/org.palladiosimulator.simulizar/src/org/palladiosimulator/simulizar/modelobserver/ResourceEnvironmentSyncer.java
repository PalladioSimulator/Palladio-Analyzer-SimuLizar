package org.palladiosimulator.simulizar.modelobserver;

import static org.palladiosimulator.edp2.util.MetricDescriptionUtility.metricDescriptionIdsEqual;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.pcm.core.CorePackage;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointSwitch;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SchedulingStrategy;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;
import de.uka.ipd.sdq.stoex.StoexPackage;

/**
 * Class to sync resource environment model with SimuCom.
 *
 * @author Joachim Meyer, Sebastian Lehrig, Matthias Becker, Florian Rosenthal
 */
public class ResourceEnvironmentSyncer extends AbstractResourceEnvironmentObserver {

    private static final Logger LOGGER = Logger.getLogger(ResourceEnvironmentSyncer.class.getName());
    private MonitorRepository monitorRepository;

    /** The stoex-based features of processing resource specification which support updates */
    private static final Set<EStructuralFeature> SUPPORTED_PROCESSING_RESOURCE_STOEX_PROPERTIES = Collections.singleton(
            ResourceenvironmentPackage.Literals.PROCESSING_RESOURCE_SPECIFICATION__PROCESSING_RATE_PROCESSING_RESOURCE_SPECIFICATION);

    /** The stoex-based features of linking resource specification which support updates */
    private static final Set<EStructuralFeature> SUPPORTED_LINKING_RESOURCE_STOEX_PROPERTIES = Collections
        .unmodifiableSet(new HashSet<>(Arrays.asList(
                ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__LATENCY_COMMUNICATION_LINK_RESOURCE_SPECIFICATION,
                ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__THROUGHPUT_COMMUNICATION_LINK_RESOURCE_SPECIFICATION)));

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final AbstractSimuLizarRuntimeState runtimeState) {
        super.initialize(runtimeState);

        PCMPartitionManager manager = runtimeState.getPCMPartitionManager();
        this.monitorRepository = manager.findModel(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initializing Simulated ResourcesContainer");
        }

        this.model.getResourceContainer_ResourceEnvironment()
            .forEach(this::createSimulatedResourceContainer);
        this.model.getLinkingResources__ResourceEnvironment()
            .forEach(this::createSimulatedLinkingResource);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initialization done");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void add(final Notification notification) {
        ResourceenvironmentPackage resourceenvironmentPackage = ResourceenvironmentPackage.eINSTANCE;
        Object changedFeature = notification.getFeature();

        if (changedFeature == resourceenvironmentPackage
            .getResourceEnvironment_ResourceContainer_ResourceEnvironment()) {
            this.createSimulatedResourceContainer((ResourceContainer) notification.getNewValue());
        } else if (changedFeature == resourceenvironmentPackage
            .getResourceContainer_ActiveResourceSpecifications_ResourceContainer()) {
            this.createSimulatedActiveResource((ProcessingResourceSpecification) notification.getNewValue());
        } else if (changedFeature == resourceenvironmentPackage
            .getResourceEnvironment_LinkingResources__ResourceEnvironment()) {
            this.createSimulatedLinkingResource((LinkingResource) notification.getNewValue());
        } else {
            this.logDebugInfo(notification);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void set(final Notification notification) {
        var handled = syncIfFeatureOrCharacterizingStoExChanged(notification, ProcessingResourceSpecification.class,
                SUPPORTED_PROCESSING_RESOURCE_STOEX_PROPERTIES, this::syncProcessingResource);

        handled |= syncIfFeatureOrCharacterizingStoExChanged(notification, CommunicationLinkResourceSpecification.class,
                SUPPORTED_LINKING_RESOURCE_STOEX_PROPERTIES, this::syncLinkingResource);

        if (!handled) {
            this.logDebugInfo(notification);
        }
    }

    /**
     * This method supports checking different notifications which can occur when a stoex
     * specification based property is changed.
     * 
     * @param notification
     *            the notification, as provided by the EMF Adapter.
     * @param clazz
     *            the class object of characterized model entity (e. g.
     *            <code>ProcessingResourceSpecification</code>).
     * @param features
     *            the features, that if changed, trigger an execution of the sync function.
     * @param syncFunction
     *            the function to be called if the respective features changed.
     * @return True, if the sync function was called. False, otherwise.
     */
    private static <T> boolean syncIfFeatureOrCharacterizingStoExChanged(Notification notification, Class<T> clazz,
            Set<EStructuralFeature> features, Consumer<T> syncFunction) {
        Optional<Object> candidate = Optional.empty();

        if (features.contains(notification.getFeature())) {
            candidate = Optional.of(notification.getNotifier());
        } else if (CorePackage.Literals.PCM_RANDOM_VARIABLE.isInstance(notification.getNotifier())) {
            if (StoexPackage.Literals.RANDOM_VARIABLE__SPECIFICATION == notification.getFeature()
                    && (features.contains(((EObject) notification.getNotifier()).eContainmentFeature())
                            || featureIsOppositeReference(notification.getFeature(), features))) {
                candidate = Optional.of(((EObject) notification.getNotifier()).eContainer());
            }
        }
        return candidate.filter(clazz::isInstance)
            .map(clazz::cast)
            .map(obj -> {
                syncFunction.accept(obj);
                return true;
            })
            .orElse(false);
    }

    /**
     * A helper function to determine whether a feature is an opposite reference to one of a
     * selection of features.
     * 
     * @param feature
     *            the feature to be checked.
     * @param features
     *            the set of potential eopposite references.
     * @return True, if feature is EOpposite to on of the provided features.
     */
    private static boolean featureIsOppositeReference(Object feature, Set<EStructuralFeature> features) {
        return EcorePackage.Literals.EREFERENCE.isInstance(feature)
                && features.contains(((EObject) feature).eGet(EcorePackage.Literals.EREFERENCE__EOPPOSITE));
    }

    /**
     * Processes the addition of a new resource container. It creates the simulation entities for
     * the container and its contained resources.
     * 
     * @param resourceContainer
     *            the new resource container.
     */
    private void createSimulatedResourceContainer(final ResourceContainer resourceContainer) {
        if (!this.runtimeModel.getModel()
            .getResourceRegistry()
            .containsResourceContainer(resourceContainer.getId())) {
            final var simulatedResourceContainer = this.runtimeModel.getModel()
                .getResourceRegistry()
                .createResourceContainer(resourceContainer.getId());

            resourceContainer.getActiveResourceSpecifications_ResourceContainer()
                .forEach(this::createSimulatedActiveResource);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Added SimulatedResourceContainer: ID: " + resourceContainer.getId() + " "
                        + simulatedResourceContainer);
            }
        } else {
            LOGGER.warn("SimulatedResourceContainer was already present for ID: " + resourceContainer.getId());
        }
    }

    /**
     * Processes the addition of a new linking resource. It creates the simulation entities for the
     * linking resource.
     * 
     * @param linkingResource
     *            the new linking resource.
     */
    private void createSimulatedLinkingResource(final LinkingResource linkingResource) {
        if (!this.runtimeModel.getModel()
            .getResourceRegistry()
            .containsResourceContainer(linkingResource.getId())) {
            final var simulatedResourceContainer = this.runtimeModel.getModel()
                .getResourceRegistry()
                .createLinkingResourceContainer(linkingResource.getId());
            if (linkingResource.getCommunicationLinkResourceSpecifications_LinkingResource() != null) {
                syncLinkingResource(linkingResource.getCommunicationLinkResourceSpecifications_LinkingResource());
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Added SimulatedLinkingResource: ID: " + linkingResource.getId() + " "
                        + simulatedResourceContainer);
            }
        } else {
            LOGGER.warn("SimulatedLinkingResource was already present for ID: " + linkingResource.getId());
        }

    }

    /**
     * Processes the addition of a new Processing Resource. If the resource already exists, it will
     * update it according to the provided specification.
     * 
     * @param processingResource
     *            the new processing resource.
     */
    private void createSimulatedActiveResource(final ProcessingResourceSpecification processingResource) {
        final ResourceContainer resourceContainer = processingResource
            .getResourceContainer_ProcessingResourceSpecification();
        final SimulatedResourceContainer simulatedResourceContainer = (SimulatedResourceContainer) this
            .getSimulatedResourceContainer(resourceContainer);

        if (simulatedResourceContainer.getAllActiveResources()
            .containsKey(processingResource.getActiveResourceType_ActiveResourceSpecification()
                .getId())) {
            syncProcessingResource(processingResource);
        } else {
            final ScheduledResource scheduledResource = simulatedResourceContainer.addActiveResourceWithoutCalculators(
                    processingResource, new String[] {}, resourceContainer.getId(),
                    processingResource.getSchedulingPolicy()
                        .getId());
            scheduledResource.activateResource();

            this.attachMonitors(processingResource, resourceContainer, scheduledResource.getSchedulingStrategyID(),
                    scheduledResource);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Added ActiveResource. TypeID: " + this.getActiveResourceTypeID(processingResource)
                        + ", Description: " + ", SchedulingStrategy: " + scheduledResource.getSchedulingStrategyID());
            }
        }
    }

    /**
     * Updates the simulation entities of a linking resource according to a changed specification.
     * This method takes into account the features as specified in
     * <code>SUPPORTED_LINKING_RESOURCE_STOEX_PROPERTIES</code>.
     * 
     * @param resourceSpec
     *            the changed linking resource specification
     */
    private void syncLinkingResource(final CommunicationLinkResourceSpecification resourceSpec) {
        var linkContainer = (SimulatedLinkingResourceContainer) getSimulatedResourceContainer(
                resourceSpec.getLinkingResource_CommunicationLinkResourceSpecification());
        var activeResources = linkContainer.getAllActiveResources();
        if (activeResources.isEmpty()) {
            linkContainer.addActiveResourceWithoutCalculators(
                    resourceSpec.getLinkingResource_CommunicationLinkResourceSpecification(),
                    linkContainer.getResourceContainerID());
        } else {
            var resource = Optional
                .ofNullable(activeResources
                    .get(resourceSpec.getCommunicationLinkResourceType_CommunicationLinkResourceSpecification()
                        .getId()))
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "The %s currently does not support changing the resource type of a linking resource",
                        getClass())));
            if (resource instanceof SimulatedLinkingResource) {
                var linkResource = (SimulatedLinkingResource) resource;
                linkResource.setLatency(resourceSpec.getLatency_CommunicationLinkResourceSpecification()
                    .getSpecification());
                linkResource.setThroughput(resourceSpec.getThroughput_CommunicationLinkResourceSpecification()
                    .getSpecification());
            } else {
                LOGGER.warn(String.format(
                        "Update of linking resource parameters failed due to unsupported resource implementation %s",
                        resource.getClass()));
            }
        }
    }

    /**
     * Updates the simulation entities of a linking resource according to a changed specification.
     * This method takes into account the features as specified in
     * <code>SUPPORTED_PROCESSING_RESOURCE_STOEX_PROPERTIES</code>.
     * 
     * @param processingResourceSpecification
     *            the changed processing resource specification
     */
    private void syncProcessingResource(final ProcessingResourceSpecification processingResourceSpecification) {
        // processingRate does not need to be evaluated, will be done in
        // simulatedResourceContainers
        this.getScheduledResource(processingResourceSpecification)
            .setProcessingRate(processingResourceSpecification.getProcessingRate_ProcessingResourceSpecification()
                .getSpecification());
    }

    private String getActiveResourceTypeID(final ProcessingResourceSpecification processingResource) {
        return processingResource.getActiveResourceType_ActiveResourceSpecification()
            .getId();
    }

    private AbstractSimulatedResourceContainer getSimulatedResourceContainer(final Identifier container) {
        return this.runtimeModel.getModel()
            .getResourceRegistry()
            .getResourceContainer(container.getId());
    }

    /**
     * Gets the simulated resource by type id in given simulated resource container.
     *
     * @return the ScheduledResource.
     */
    private ScheduledResource getScheduledResource(final ProcessingResourceSpecification processingResource) {
        final String typeId = this.getActiveResourceTypeID(processingResource);

        return Optional
            .ofNullable(getSimulatedResourceContainer(
                    processingResource.getResourceContainer_ProcessingResourceSpecification()).getAllActiveResources()
                        .get(typeId))
            .filter(ScheduledResource.class::isInstance)
            .map(ScheduledResource.class::cast)
            .orElseThrow(() -> new NoSuchElementException("Did not find scheduled resource for type ID " + typeId));
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

            }.doSwitch(measurementSpecification.getMonitor()
                .getMeasuringPoint());
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
                throw new IllegalArgumentException("MetricDescription ("
                        + MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC.getName() + ") '"
                        + measurementSpecification.getName() + "' of Monitor '" + measurementSpecification.getMonitor()
                            .getEntityName()
                        + "' must provide a " + MonitorRepositoryPackage.Literals.PROCESSING_TYPE.getName()
                        + " of Type '" + MonitorRepositoryPackage.Literals.FEED_THROUGH.getName() + "'!");
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