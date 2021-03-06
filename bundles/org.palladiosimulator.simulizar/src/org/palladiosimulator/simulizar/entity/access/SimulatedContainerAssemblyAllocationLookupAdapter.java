package org.palladiosimulator.simulizar.entity.access;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;

import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;

/**
 * This adapter provides a lookup of the simulated resource container to which a given assembly is allocated to.
 * 
 * @see IAssemblyAllocationLookup
 * 
 */
public class SimulatedContainerAssemblyAllocationLookupAdapter
        implements IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> {

    private final IAssemblyAllocationLookup<EntityReference<ResourceContainer>> allocationLookup;
    private final ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simulatedEntityAccess;

    @Inject
    public SimulatedContainerAssemblyAllocationLookupAdapter(
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> allocationLookup,
            ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simulatedEntityAccess) {
        this.allocationLookup = allocationLookup;
        this.simulatedEntityAccess = simulatedEntityAccess;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractSimulatedResourceContainer getAllocatedEntity(String assemblyContextId) {
        var rcRef = allocationLookup.getAllocatedEntity(assemblyContextId);
        return simulatedEntityAccess.getSimulatedEntity(rcRef.getId());
    }

}
