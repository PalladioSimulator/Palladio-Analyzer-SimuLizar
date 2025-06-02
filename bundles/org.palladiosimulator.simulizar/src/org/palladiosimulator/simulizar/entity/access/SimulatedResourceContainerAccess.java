package org.palladiosimulator.simulizar.entity.access;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

import de.uka.ipd.sdq.simucomframework.core.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.core.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.core.resources.ISimulatedModelEntityAccess;

/**
 * This class supplies access to AbstractSimulatedResourceContainers based on the respective entity
 * reference using a given ResourceRegistry.
 *
 * @see ISimulatedModelEntityAccess
 * @see ResourceRegistry
 * 
 */
public class SimulatedResourceContainerAccess
        implements ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> {
    private ResourceRegistry resourceRegistry;

    @Inject
    public SimulatedResourceContainerAccess(ResourceRegistry resourceRegistry) {
        this.resourceRegistry = resourceRegistry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractSimulatedResourceContainer getSimulatedEntity(String modelEntityIdentifier) {
        return resourceRegistry.getResourceContainer(modelEntityIdentifier);
    }

}
