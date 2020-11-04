package org.palladiosimulator.simulizar.entity;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

/**
 * This implementation provides a more specialized resource container lookup strategy. It is fully
 * compliant to the contract of {@link EntityReference}.
 * 
 * @see SimuLizarEntityReferenceFactories.ResourceContainer
 */
public class ResourceContainerReference extends EntityReference<ResourceContainer> {

    ResourceContainerReference(String id) {
        super(id, ResourceContainer.class);
    }

    @Override
    protected ResourceContainer retrieveModelElement(PCMResourceSetPartition partition) {
        return partition.getResourceEnvironment()
            .getResourceContainer_ResourceEnvironment()
            .stream()
            .filter(rc -> rc.getId()
                .equals(getId()))
            .findAny()
            .orElseGet(() -> super.retrieveModelElement(partition));
    }

}
