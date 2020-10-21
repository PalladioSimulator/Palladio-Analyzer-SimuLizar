package org.palladiosimulator.simulizar.entity.access;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;

public class SimulatedResourceContainerAccess
        implements ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> {
    private ResourceRegistry resourceRegistry;

    @Inject
    public SimulatedResourceContainerAccess(ResourceRegistry resourceRegistry) {
        this.resourceRegistry = resourceRegistry;
    }

    @Override
    public AbstractSimulatedResourceContainer getSimulatedEntity(String modelEntityIdentifier) {
        return resourceRegistry.getResourceContainer(modelEntityIdentifier);
    }

}
