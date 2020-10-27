package org.palladiosimulator.simulizar.modelobserver.tests

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition
import java.util.function.Supplier
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType
import org.palladiosimulator.pcm.resourcetype.CommunicationLinkResourceType

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
                    availableResourceTypes_ResourceRepository += linkType
                    schedulingPolicies__ResourceRepository += sPolicy
                ]
                resourceEnvironment => [
                    resourceContainer_ResourceEnvironment += ref.createResourceContainer => [
                    	id = "CA"
                        entityName = "Container A"
                        activeResourceSpecifications_ResourceContainer += ref.createProcessingResourceSpecification => [
                            id = "A1"
                            activeResourceType_ActiveResourceSpecification = cpuType
                            schedulingPolicy = sPolicy
                            processingRate_ProcessingResourceSpecification = stoex("1000")
                        ]
                    ]
                    resourceContainer_ResourceEnvironment += ref.createResourceContainer => [
                    	id = "CB"
                        entityName = "Container B"
                        activeResourceSpecifications_ResourceContainer += ref.createProcessingResourceSpecification => [
                            id = "B1"
                            activeResourceType_ActiveResourceSpecification = cpuType
                            schedulingPolicy = sPolicy
                            processingRate_ProcessingResourceSpecification = stoex("1000")
                        ]
                    ]
                    linkingResources__ResourceEnvironment += ref.createLinkingResource => [
                    	id = "LC1"
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
    
    static class WithThreeContainersAndTwoLinks implements Supplier<PCMResourceSetPartition> {
        override get() {
        	(new WithTwoContainersAndOneLink).get => [
        		val cpuType = resourceTypeRepository.availableResourceTypes_ResourceRepository.filter(ProcessingResourceType).head
        		val sPolicy = resourceTypeRepository.schedulingPolicies__ResourceRepository.head
        		val linkType = resourceTypeRepository.availableResourceTypes_ResourceRepository.filter(CommunicationLinkResourceType).head
        		resourceEnvironment => [
        			resourceContainer_ResourceEnvironment += ref.createResourceContainer => [
                    	id = "CC"
                        entityName = "Container C"
                        activeResourceSpecifications_ResourceContainer += ref.createProcessingResourceSpecification => [
                            id = "C1"
                            activeResourceType_ActiveResourceSpecification = cpuType
                            schedulingPolicy = sPolicy
                            processingRate_ProcessingResourceSpecification = stoex("1000")
                        ]
                    ]
                    
                    val link2 = ref.createLinkingResource => [
                    	id = "LC2"
                        entityName = "Link 2"
                        communicationLinkResourceSpecifications_LinkingResource = ref.
                            createCommunicationLinkResourceSpecification => [
                            id = "L2"
                            communicationLinkResourceType_CommunicationLinkResourceSpecification = linkType
                            throughput_CommunicationLinkResourceSpecification = stoex("1000")
                            latency_CommunicationLinkResourceSpecification = stoex("100")
                        ]
                    ]
                    linkingResources__ResourceEnvironment += link2
                    link2.connectedResourceContainers_LinkingResource += resourceContainer_ResourceEnvironment.filter[#{"CB", "CC"}.contains(it.id)]
        		]
        	]
        }    
    }
}
