package org.palladiosimulator.simulizar.modelobserver.tests

import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory
import org.palladiosimulator.pcm.resourcetype.ResourcetypeFactory

class ResourceEnvironmentTestModels {
    static val ref = ResourceenvironmentFactory.eINSTANCE
    static val rtf = ResourcetypeFactory.eINSTANCE

    static def empty() {
        ref.createResourceEnvironment
    }

    static def withTwoContainersAndOneLink() {
        ref.createResourceEnvironment => [
            resourceContainer_ResourceEnvironment += ref.createResourceContainer => [
                entityName = "Container A"
                activeResourceSpecifications_ResourceContainer += ref.createProcessingResourceSpecification => [
                    activeResourceType_ActiveResourceSpecification = rtf.createProcessingResourceType => [
                        entityName = "CPU"
                    ]
                    schedulingPolicy = rtf.createSchedulingPolicy
                ]
            ]
            resourceContainer_ResourceEnvironment += ref.createResourceContainer => [
                entityName = "Container B"
                activeResourceSpecifications_ResourceContainer += ref.createProcessingResourceSpecification => [
                    activeResourceType_ActiveResourceSpecification = rtf.createProcessingResourceType => [
                        entityName = "CPU"
                    ]
                    schedulingPolicy = rtf.createSchedulingPolicy
                ]
            ]
            linkingResources__ResourceEnvironment += ref.createLinkingResource => [
                entityName = "Link 1"
                communicationLinkResourceSpecifications_LinkingResource = ref.
                    createCommunicationLinkResourceSpecification => [
                    communicationLinkResourceType_CommunicationLinkResourceSpecification = rtf.
                        createCommunicationLinkResourceType => [
                        entityName = "Ethernet"
                    ]
                ]
            ]
            linkingResources__ResourceEnvironment.get(0).connectedResourceContainers_LinkingResource +=
                resourceContainer_ResourceEnvironment
        ]
    }
}
