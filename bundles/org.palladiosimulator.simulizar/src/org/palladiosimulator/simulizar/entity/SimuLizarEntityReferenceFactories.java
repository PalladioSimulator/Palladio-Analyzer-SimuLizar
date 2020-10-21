package org.palladiosimulator.simulizar.entity;

import org.palladiosimulator.simulizar.entity.EntityReference.AbstractEntityReferenceFactory;

public final class SimuLizarEntityReferenceFactories {
    public static final class ResourceContainer
            extends AbstractEntityReferenceFactory<org.palladiosimulator.pcm.resourceenvironment.ResourceContainer> {
        @Override
        public EntityReference<org.palladiosimulator.pcm.resourceenvironment.ResourceContainer> create(String id) {
            return new ResourceContainerReference(id);
        }
    }
    
    public static final class LinkingResource extends AbstractEntityReferenceFactory<org.palladiosimulator.pcm.resourceenvironment.LinkingResource> {
        @Override
        public EntityReference<org.palladiosimulator.pcm.resourceenvironment.LinkingResource> create(String id) {
            return new LinkingResourceReference(id);
        }
    }

}
