package org.palladiosimulator.simulizar.test.commons.util;

import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.entity.SimuLizarEntityReferenceFactories;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class BaseTestModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(new TypeLiteral<EntityReferenceFactory<ResourceContainer>>() {})
            .to(SimuLizarEntityReferenceFactories.ResourceContainer.class);
        bind(new TypeLiteral<EntityReferenceFactory<LinkingResource>>() {})
            .to(SimuLizarEntityReferenceFactories.LinkingResource.class); 
    }

}
