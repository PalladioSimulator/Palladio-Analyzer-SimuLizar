package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.entity.SimuLizarEntityReferenceFactories;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

@Module
public interface EntityReferenceFactoryFoundationModule {

    @Binds
    @Reusable
    EntityReferenceFactory<ResourceContainer> bindResourceContainerReferenceFactory(
            SimuLizarEntityReferenceFactories.ResourceContainer factoryImpl);

    @Binds
    @Reusable
    EntityReferenceFactory<LinkingResource> bindLinkContainerReferenceFactory(
            SimuLizarEntityReferenceFactories.LinkingResource factoryImpl);
    
}
