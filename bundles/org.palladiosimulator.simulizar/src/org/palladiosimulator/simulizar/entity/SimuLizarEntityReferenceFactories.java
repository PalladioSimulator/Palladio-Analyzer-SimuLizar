package org.palladiosimulator.simulizar.entity;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.entity.EntityReference.AbstractEntityReferenceFactory;

/**
 * This class contains specialized entity reference factory implementations for the relevant model
 * element types used within SimuLizar.
 *
 */
public final class SimuLizarEntityReferenceFactories {
    private SimuLizarEntityReferenceFactories() {
        // No instance should be constructible, as this is a utility class
    }

    public static final class ResourceContainer
            extends AbstractEntityReferenceFactory<org.palladiosimulator.pcm.resourceenvironment.ResourceContainer> {
        @Inject
        public ResourceContainer() {
        }

        @Override
        public EntityReference<org.palladiosimulator.pcm.resourceenvironment.ResourceContainer> create(String id) {
            return new ResourceContainerReference(id);
        }
    }

    public static final class LinkingResource
            extends AbstractEntityReferenceFactory<org.palladiosimulator.pcm.resourceenvironment.LinkingResource> {
        @Inject
        public LinkingResource() {
        }

        @Override
        public EntityReference<org.palladiosimulator.pcm.resourceenvironment.LinkingResource> create(String id) {
            return new LinkingResourceReference(id);
        }
    }

}
