package org.palladiosimulator.simulizar.modelobserver.tests

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition
import java.util.function.Supplier

class ResourceEnvironmentTestModels extends TestModelBase {
    /**
     * Creates a fully equipped PCMResourceSetPartition with a resource environment of two resource 
     * containers and on linking resource.
     */
    static class WithTwoContainersAndOneLink implements Supplier<PCMResourceSetPartition> {
        override get() {
            val cpuType = rtf.createProcessingResourceType => [entityName = "CPU"]
            val sPolicy = rtf.createSchedulingPolicy
            val linkType = rtf.createCommunicationLinkResourceType => [entityName = "Ethernet"]

            (new TestModelBase.Empty).get => [
                resourceTypeRepository => [
                    availableResourceTypes_ResourceRepository += cpuType
                    schedulingPolicies__ResourceRepository += sPolicy
                ]
                resourceEnvironment => [
                    resourceContainer_ResourceEnvironment += ref.createResourceContainer => [
                        entityName = "Container A"
                        activeResourceSpecifications_ResourceContainer += ref.createProcessingResourceSpecification => [
                            id = "A1"
                            activeResourceType_ActiveResourceSpecification = cpuType
                            schedulingPolicy = sPolicy
                            processingRate_ProcessingResourceSpecification = stoex("1000")
                        ]
                    ]
                    resourceContainer_ResourceEnvironment += ref.createResourceContainer => [
                        entityName = "Container B"
                        activeResourceSpecifications_ResourceContainer += ref.createProcessingResourceSpecification => [
                            id = "B1"
                            activeResourceType_ActiveResourceSpecification = cpuType
                            schedulingPolicy = sPolicy
                            processingRate_ProcessingResourceSpecification = stoex("1000")
                        ]
                    ]
                    linkingResources__ResourceEnvironment += ref.createLinkingResource => [
                        entityName = "Link 1"
                        communicationLinkResourceSpecifications_LinkingResource = ref.
                            createCommunicationLinkResourceSpecification => [
                            id = "L1"
                            communicationLinkResourceType_CommunicationLinkResourceSpecification = linkType
                            throughput_CommunicationLinkResourceSpecification = stoex("1000")
                            latency_CommunicationLinkResourceSpecification = stoex("100")
                        ]
                    ]
                    linkingResources__ResourceEnvironment.get(0).connectedResourceContainers_LinkingResource +=
                        resourceContainer_ResourceEnvironment
                ]
            ]

        }
    }
}
