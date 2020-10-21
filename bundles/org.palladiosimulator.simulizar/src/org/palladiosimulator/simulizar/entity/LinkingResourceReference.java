package org.palladiosimulator.simulizar.entity;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;

public class LinkingResourceReference extends EntityReference<LinkingResource> {

    LinkingResourceReference(String id) {
        super(id, LinkingResource.class);
    }

    @Override
    protected LinkingResource retrieveModelElement(PCMResourceSetPartition partition) {
        return partition.getResourceEnvironment()
            .getLinkingResources__ResourceEnvironment()
            .stream()
            .filter(it -> it.getId()
                .equals(getId()))
            .findAny()
            .orElseGet(() -> super.retrieveModelElement(partition));
    }

}
