package org.palladiosimulator.simulizar.core.entity;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.core.entity.EntityReference.AbstractEntityReferenceFactory;

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

    public static final class UsageScenario
            extends AbstractEntityReferenceFactory<org.palladiosimulator.pcm.usagemodel.UsageScenario> {
        @Inject
        public UsageScenario() {
        }

        @Override
        public EntityReference<org.palladiosimulator.pcm.usagemodel.UsageScenario> create(String id) {
            return new UsageScenarioReference(id);
        }
    }

}
