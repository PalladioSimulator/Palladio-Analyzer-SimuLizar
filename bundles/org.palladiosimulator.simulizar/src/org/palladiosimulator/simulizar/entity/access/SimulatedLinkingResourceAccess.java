package org.palladiosimulator.simulizar.entity.access;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;

import de.uka.ipd.sdq.simucomframework.core.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.core.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.core.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.core.resources.SimulatedLinkingResourceContainer;

/**
 * This class supplies access to SimulatedLinkingResources based on the respective entity reference using a given ResourceRegistry.
 *
 * @see ISimulatedModelEntityAccess
 * @see ResourceRegistry
 * 
 */
public class SimulatedLinkingResourceAccess
        implements ISimulatedModelEntityAccess<LinkingResource, SimulatedLinkingResource> {
    
    private ResourceRegistry resourceRegistry;

    @Inject
    public SimulatedLinkingResourceAccess(ResourceRegistry resourceRegistry) {
        this.resourceRegistry = resourceRegistry;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SimulatedLinkingResource getSimulatedEntity(String modelEntityIdentifier) {
        var container = resourceRegistry.getResourceContainer(modelEntityIdentifier);
        if (container instanceof SimulatedLinkingResourceContainer) {
            var linkId = ((SimulatedLinkingResourceContainer) container).getLinkingResourceTypeId();
            var resource = container.getAllActiveResources().get(linkId);
            if (resource instanceof SimulatedLinkingResource) {
                return (SimulatedLinkingResource) resource;
            }
        }
        return null;
    }

}
