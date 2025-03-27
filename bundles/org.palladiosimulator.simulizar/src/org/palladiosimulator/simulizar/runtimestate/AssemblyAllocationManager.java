package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.di.base.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.entity.EntityReference;

import de.uka.ipd.sdq.simucomframework.core.resources.IAssemblyAllocationLookup;

@SimulationRuntimeScope
public class AssemblyAllocationManager
        implements IAssemblyAllocationLookup<EntityReference<ResourceContainer>>, RuntimeStateEntityManager {
    
    private final Map<String, EntityReference<ResourceContainer>> containerIdStorage = new HashMap<>();
    
    @Inject
    public AssemblyAllocationManager() {}


    /**
     * Returns the simulated resource container to which the provided assembly is
     * allocated to. If the assembly is not allocated directly, but through a
     * hierarchy of <code>CompositeComponent</code>s, the lookup needs to be done
     * using the string representation of the <code>FQComponentID</code>.
     * 
     * @return the simulated resource container
     */
    @Override
    public EntityReference<ResourceContainer> getAllocatedEntity(String assemblyContextId) {
        return containerIdStorage.get(assemblyContextId);
    }
    
    public void allocateAssembly(String fqid, EntityReference<ResourceContainer> rcRef) {
        containerIdStorage.put(fqid, rcRef);
    }
    
    public void deallocateAssembly(String fqid) {
        containerIdStorage.remove(fqid);
    }
    
    @Override
    public void cleanup() {
        containerIdStorage.clear();
    }
}
