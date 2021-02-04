package org.palladiosimulator.simulizar.test.commons.models

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.plugin.EcorePlugin
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition
import org.palladiosimulator.pcm.allocation.AllocationFactory
import org.palladiosimulator.pcm.core.CoreFactory
import org.palladiosimulator.pcm.repository.RepositoryFactory
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory
import org.palladiosimulator.pcm.resourcetype.ResourcetypeFactory
import org.palladiosimulator.pcm.system.SystemFactory
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory
import org.palladiosimulator.simulizar.test.commons.util.PartitionSupplier

class TestModelBase {
    protected static val af = AllocationFactory.eINSTANCE
    protected static val cf = CoreFactory.eINSTANCE
    protected static val rf = RepositoryFactory.eINSTANCE
    protected static val sf = SystemFactory.eINSTANCE
    protected static val ref = ResourceenvironmentFactory.eINSTANCE
    protected static val rtf = ResourcetypeFactory.eINSTANCE
    protected static val uf = UsagemodelFactory.eINSTANCE

    /**
     * Creates a fully instantiated PCMResourceSetPartion with empty models.
     */
    static class Empty implements PartitionSupplier {
        override get() {
            EcorePlugin.ExtensionProcessor.process(null);
            new PCMResourceSetPartition => [ rsp |
                rsp.resourceSet => [
                    createResource(URI.createFileURI("test.repository")) => [
                        contents += rf.createRepository
                    ]
                    createResource(URI.createFileURI("test.system")) => [
                        contents += sf.createSystem
                    ]
                    createResource(URI.createFileURI("test.resourceenvironment")) => [
                        contents += ref.createResourceEnvironment
                    ]
                    createResource(URI.createFileURI("test.allocation")) => [
                        contents += af.createAllocation => [
                            targetResourceEnvironment_Allocation = rsp.resourceEnvironment
                            system_Allocation = rsp.system
                        ]
                    ]
                    createResource(URI.createFileURI("test.resourcetype")) => [
                        contents += rtf.createResourceRepository
                    ]
                    createResource(URI.createFileURI("test.usagemodel")) => [
                        contents += uf.createUsageModel
                    ]
                ]
            ]
        }
    }

    /**
     * Creates a StoEx based on the provided specification string.
     */
    static def stoex(String spec) {
        cf.createPCMRandomVariable => [
            specification = spec
        ]
    }
}
