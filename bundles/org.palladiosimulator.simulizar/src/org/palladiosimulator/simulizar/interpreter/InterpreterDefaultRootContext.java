package org.palladiosimulator.simulizar.interpreter;

import javax.inject.Inject;

import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.core.entity.EntityReference;
import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.core.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.core.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.core.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.core.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;

public class InterpreterDefaultRootContext extends InterpreterDefaultContext {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2513109375366915646L;
    
    private final PCMPartitionManager partitionManager;

    @Inject
    public InterpreterDefaultRootContext(SimuComModel simuComModel, PCMPartitionManager partitionManager,
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> assemblyAllocationLookup,
            ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simRCAccess,
            IResourceTableManager resourceTableManager) {
        super(simuComModel, partitionManager.getGlobalPCMModel(),
                id -> simRCAccess.getSimulatedEntity(assemblyAllocationLookup.getAllocatedEntity(id)
                    .getId()),
                resourceTableManager, new SimulatedStack<>());
        this.partitionManager = partitionManager;
    }
    
    @Override
    public PCMResourceSetPartition getLocalPCMModel() {
        return partitionManager.getLocalPCMModel();
    }

}
