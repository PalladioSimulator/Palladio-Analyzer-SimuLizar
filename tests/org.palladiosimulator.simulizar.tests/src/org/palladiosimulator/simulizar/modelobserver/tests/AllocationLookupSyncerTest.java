package org.palladiosimulator.simulizar.modelobserver.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationFactory;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.simulizar.core.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.core.entity.SimuLizarEntityReferenceFactories;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;
import org.palladiosimulator.simulizar.test.commons.annotation.PCMInstanceFromSupplier;
import org.palladiosimulator.simulizar.test.commons.models.AllocationLookupSyncerTestModels;

@ExtendWith(MockitoExtension.class)
class AllocationLookupSyncerTest {
    EntityReferenceFactory<ResourceContainer> rcf = new SimuLizarEntityReferenceFactories.ResourceContainer();
    
    @PCMInstanceFromSupplier(AllocationLookupSyncerTestModels.TrivialAllocation.class)
    @Test
    void test(PCMResourceSetPartition partition, BasicComponent comp, 
            System system, Allocation allocation, ResourceContainer rc, @Mock AssemblyAllocationManager manager) {
        var syncerUnderTest = new AllocationLookupSyncer(rcf, partition, manager);
        
        syncerUnderTest.initialize();
        verify(manager).allocateAssembly(AllocationLookupSyncerTestModels.TrivialAllocation.AssemblyCtxId, 
                rcf.create(AllocationLookupSyncerTestModels.TrivialAllocation.RCId));
        
        var ctx2 = CompositionFactory.eINSTANCE.createAssemblyContext();
        ctx2.setEncapsulatedComponent__AssemblyContext(comp);
        system.getAssemblyContexts__ComposedStructure().add(ctx2);
        
        var alloc2 = AllocationFactory.eINSTANCE.createAllocationContext();
        alloc2.setAssemblyContext_AllocationContext(ctx2);
        alloc2.setResourceContainer_AllocationContext(rc);
        
        allocation.getAllocationContexts_Allocation().add(alloc2);
        
        verify(manager).allocateAssembly(ctx2.getId(), rcf.create(AllocationLookupSyncerTestModels.TrivialAllocation.RCId));
        
    }

    @PCMInstanceFromSupplier(AllocationLookupSyncerTestModels.CompositeComponentAllocation.class)
    @Test
    void testCompositeAllocation(PCMResourceSetPartition partition, @Mock AssemblyAllocationManager manager) {
        var syncerUnderTest = new AllocationLookupSyncer(rcf, partition, manager);
        var rcref = rcf.create(AllocationLookupSyncerTestModels.CompositeComponentAllocation.RCId);
        
        syncerUnderTest.initialize();
        verify(manager).allocateAssembly(AllocationLookupSyncerTestModels.CompositeComponentAllocation.AssemblyCtx1Id,
                rcref);
        verify(manager).allocateAssembly(Arrays
            .asList(AllocationLookupSyncerTestModels.CompositeComponentAllocation.AssemblyCtx1Id,
                    AllocationLookupSyncerTestModels.CompositeComponentAllocation.NestedAssemblyCtxId)
            .stream()
            .collect(Collectors.joining(FQComponentID.SEPARATOR)), rcref);
        verify(manager).allocateAssembly(AllocationLookupSyncerTestModels.CompositeComponentAllocation.AssemblyCtx2Id,
                rcref);
        verify(manager).allocateAssembly(Arrays
                .asList(AllocationLookupSyncerTestModels.CompositeComponentAllocation.AssemblyCtx2Id,
                        AllocationLookupSyncerTestModels.CompositeComponentAllocation.NestedAssemblyCtxId)
                .stream()
                .collect(Collectors.joining(FQComponentID.SEPARATOR)), rcref);
        verifyNoMoreInteractions(manager);
    }
    
    @PCMInstanceFromSupplier(AllocationLookupSyncerTestModels.SubsystemWhiteboxAllocation.class)
    @Test
    void testSubsystemWhiteboxAllocation(PCMResourceSetPartition partition, @Mock AssemblyAllocationManager manager) {
        var syncerUnderTest = new AllocationLookupSyncer(rcf, partition, manager);
        var rcref = rcf.create(AllocationLookupSyncerTestModels.CompositeComponentAllocation.RCId);
        
        syncerUnderTest.initialize();
        verify(manager).allocateAssembly(Arrays
                .asList(AllocationLookupSyncerTestModels.SubsystemWhiteboxAllocation.AssemblyCtx1Id,
                        AllocationLookupSyncerTestModels.SubsystemWhiteboxAllocation.NestedAssemblyCtxId)
                .stream()
                .collect(Collectors.joining(FQComponentID.SEPARATOR)), rcref);
        verifyNoMoreInteractions(manager);
    }
    
    @PCMInstanceFromSupplier(AllocationLookupSyncerTestModels.SubsystemWhiteboxAllocation.class)
    @Test
    void testExceptionOnAmbiguousSubsystemWhiteboxAllocation(PCMResourceSetPartition partition, @Mock AssemblyAllocationManager manager,
            System system, ResourceContainer rc) {
        var syncerUnderTest = new AllocationLookupSyncer(rcf, partition, manager);
        
        var nesting1 = system.getAssemblyContexts__ComposedStructure().get(0);
        var ctx2 = CompositionFactory.eINSTANCE.createAssemblyContext();
        ctx2.setEncapsulatedComponent__AssemblyContext(nesting1.getEncapsulatedComponent__AssemblyContext());
        system.getAssemblyContexts__ComposedStructure().add(ctx2);
        
        assertThrows(IllegalStateException.class, () -> syncerUnderTest.initialize());
    }

}
