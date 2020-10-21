package org.palladiosimulator.simulizar.interpreter.linking.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;

/**
 * This class implements the routing behavior of SimuCom for SimuLizar. It
 * checks for every registered linking resource if both resource containers are
 * connected to this link.
 * 
 * This router monitors the ResourceEnvironment model to pick up on changes and
 * update the internal data structures.
 * 
 * @author Sebastian Krach
 *
 */
public class ResourceEnvironmentObservingLegacyRouter
        implements ILinkingResourceRouter<AbstractSimulatedResourceContainer, SimulatedLinkingResource> {

    private final ISimulatedModelEntityAccess<Identifier, AbstractSimulatedResourceContainer> resourceContainerAccess;
    private final Collection<String> linkingResources = new LinkedList<>();
    private final Map<String, Set<String>> linkContainerAllocation = new HashMap<>();
    private final Map<String, String> linkContainerResourceType = new HashMap<>();

    /**
     * Creates a new ResourceEnvironmentObservingLegacyRouter.
     * 
     * @param modelManager            access to the PCM instances during simulation
     * @param resourceContainerAccess a way to look up
     *                                SimulatedLinkingResourceContainers during
     *                                simulation
     */
    @Inject
    ResourceEnvironmentObservingLegacyRouter(PCMPartitionManager modelManager,
            ISimulatedModelEntityAccess<Identifier, AbstractSimulatedResourceContainer> resourceContainerAccess) {
        this.resourceContainerAccess = resourceContainerAccess;

        var resEnv = modelManager.<ResourceEnvironment>findModel(ResourceenvironmentPackage.Literals.RESOURCE_ENVIRONMENT);
        resEnv.eAdapters()
                .add(new EContentAdapter() {
                    @Override
                    public void notifyChanged(Notification msg) {
                        handleNotification(msg);
                        super.notifyChanged(msg);
                    }
                });
        initialize(resEnv);
    }

    /**
     * {@inheritDoc}
     * 
     * LIMITATION: Routes are only found between containers which are connected
     * directly to the same linking resource.
     */
    @Override
    public Optional<Iterable<SimulatedLinkingResource>> findRoute(AbstractSimulatedResourceContainer transmissionSource,
            AbstractSimulatedResourceContainer transmissionTarget) {
        if (transmissionSource.getResourceContainerID()
            .equals(transmissionTarget.getResourceContainerID())) {
            return Optional.of(Collections.emptyList());
        }
        for (var link : linkingResources) {
            var containers = linkContainerAllocation.getOrDefault(link, Collections.emptySet());
            if (containers.contains(transmissionSource.getResourceContainerID())
                    && containers.contains(transmissionTarget.getResourceContainerID())) {
                var resource = resourceContainerAccess.getSimulatedEntity(link)
                    .getAllActiveResources()
                    .get(linkContainerResourceType.get(link));
                return Optional.of(Collections.singleton((SimulatedLinkingResource) resource));
            }
        }
        return Optional.empty();
    }

    protected void handleNotification(Notification msg) {
        if (msg.getFeature() == ResourceenvironmentPackage.Literals.RESOURCE_ENVIRONMENT__LINKING_RESOURCES_RESOURCE_ENVIRONMENT) {
            handleLinkingResourcesChange(msg);
        } else if (msg
                .getFeature() == ResourceenvironmentPackage.Literals.LINKING_RESOURCE__CONNECTED_RESOURCE_CONTAINERS_LINKING_RESOURCE) {
            handleConnectedResourceContainersChange(msg);
        } else if (msg
                .getFeature() == ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__COMMUNICATION_LINK_RESOURCE_TYPE_COMMUNICATION_LINK_RESOURCE_SPECIFICATION) {
            handleCommunicationResourceChange(msg);
        } else if (msg
                .getFeature() == ResourceenvironmentPackage.Literals.LINKING_RESOURCE__COMMUNICATION_LINK_RESOURCE_SPECIFICATIONS_LINKING_RESOURCE) {
            handleCommunicationLinkSpecificationChange(msg);
        }
    }

    protected void initialize(ResourceEnvironment env) {
        env.getLinkingResources__ResourceEnvironment().forEach(this::doAddLinkingResource);
    }

    /**
     * Handle notifications about new or deleted linking resources
     * 
     * @param msg the notification
     */
    @SuppressWarnings("unchecked")
    protected void handleLinkingResourcesChange(Notification msg) {
        switch (msg.getEventType()) {
        case Notification.ADD:
            doAddLinkingResource((LinkingResource) msg.getNewValue());
            break;
        case Notification.ADD_MANY:
            ((Collection<LinkingResource>) msg.getNewValue()).forEach(this::doAddLinkingResource);
            break;
        case Notification.REMOVE:
            doRemoveLinkingResource((LinkingResource) msg.getOldValue());
            break;
        case Notification.REMOVE_MANY:
            ((Collection<LinkingResource>) msg.getOldValue()).forEach(this::doRemoveLinkingResource);
            break;
        default:
            throw new UnsupportedOperationException(
                    String.format("The event type %d is not supported for changes of feature %s by %s",
                            msg.getEventType(), msg.getFeature().toString(), this.getClass().getName()));
        }
    }

    @SuppressWarnings("unchecked")
    protected void handleConnectedResourceContainersChange(Notification msg) {
        var link = (LinkingResource) msg.getNotifier();
        switch (msg.getEventType()) {
        case Notification.ADD:
            doAddConnectedResourceContainer(link, (ResourceContainer) msg.getNewValue());
            break;
        case Notification.ADD_MANY:
            ((Collection<ResourceContainer>) msg.getNewValue()).forEach(c -> doAddConnectedResourceContainer(link, c));
            break;
        case Notification.REMOVE:
            doRemoveConnectedResourceContainer(link, (ResourceContainer) msg.getOldValue());
            break;
        case Notification.REMOVE_MANY:
            ((Collection<ResourceContainer>) msg.getNewValue())
                    .forEach(c -> doRemoveConnectedResourceContainer(link, c));
            break;
        default:
            throw new UnsupportedOperationException(
                    String.format("The event type %d is not supported for changes of feature %s by %s",
                            msg.getEventType(), msg.getFeature().toString(), this.getClass().getName()));
        }
    }

    protected void handleCommunicationLinkSpecificationChange(Notification msg) {
        switch (msg.getEventType()) {
        case Notification.SET:
            doSetCommunicationLinkResourceType((CommunicationLinkResourceSpecification) msg.getNewValue());
            break;
        default:
            throw new UnsupportedOperationException(
                    String.format("The event type %d is not supported for changes of feature %s by %s",
                            msg.getEventType(), msg.getFeature().toString(), this.getClass().getName()));
        }
    }

    protected void handleCommunicationResourceChange(Notification msg) {
        switch (msg.getEventType()) {
        case Notification.SET:
            doSetCommunicationLinkResourceType((CommunicationLinkResourceSpecification) msg.getNotifier());
            break;
        default:
            throw new UnsupportedOperationException(
                    String.format("The event type %d is not supported for changes of feature %s by %s",
                            msg.getEventType(), msg.getFeature().toString(), this.getClass().getName()));
        }
    }

    protected void doAddLinkingResource(LinkingResource link) {
        linkingResources.add(link.getId());
        link.getConnectedResourceContainers_LinkingResource().forEach(c -> doAddConnectedResourceContainer(link, c));
        doSetCommunicationLinkResourceType(link.getCommunicationLinkResourceSpecifications_LinkingResource());
    }

    protected void doRemoveLinkingResource(LinkingResource link) {
        linkingResources.remove(link.getId());
        linkContainerAllocation.remove(link.getId());
        linkContainerResourceType.remove(link.getId());
    }

    protected void doAddConnectedResourceContainer(LinkingResource link, ResourceContainer container) {
        linkContainerAllocation.computeIfAbsent(link.getId(), (k) -> new HashSet<String>()).add(container.getId());
    }

    protected void doRemoveConnectedResourceContainer(LinkingResource link, ResourceContainer container) {
        linkContainerAllocation.getOrDefault(container.getId(), Collections.emptySet()).remove(container.getId());
    }

    protected void doSetCommunicationLinkResourceType(CommunicationLinkResourceSpecification spec) {
        linkContainerResourceType.put(spec.getLinkingResource_CommunicationLinkResourceSpecification().getId(),
                spec.getCommunicationLinkResourceType_CommunicationLinkResourceSpecification().getId());
    }

}
