package org.palladiosimulator.simulizar.entity;

import java.util.Iterator;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;

/**
 * This implementation provides a more specialized linking resource lookup strategy. It is fully
 * compliant to the contract of {@link EntityReference}.
 * 
 * @see SimuLizarEntityReferenceFactories.LinkingResource
 */
public class LinkingResourceReference extends EntityReference<LinkingResource> {

    LinkingResourceReference(String id) {
        super(id, LinkingResource.class);
    }

    @Override
    protected Iterator<LinkingResource> retrieveModelElements(PCMResourceSetPartition partition) {
        return partition.getResourceEnvironment()
            .getLinkingResources__ResourceEnvironment()
            .stream()
            .filter(it -> it.getId()
                .equals(getId()))
            .iterator();
    }

}
