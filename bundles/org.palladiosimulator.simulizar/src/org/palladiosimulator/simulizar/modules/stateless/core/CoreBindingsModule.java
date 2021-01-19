package org.palladiosimulator.simulizar.modules.stateless.core;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.entity.SimuLizarEntityReferenceFactories;

import dagger.Binds;
import dagger.Module;

@Module
public interface CoreBindingsModule {
    
    @Binds EntityReferenceFactory<UsageScenario> bindUsageScenarioReferenceFactory(SimuLizarEntityReferenceFactories.UsageScenario impl);
    
    @Binds EntityReferenceFactory<ResourceContainer> bindResourceContainerReferenceFactory(SimuLizarEntityReferenceFactories.ResourceContainer impl);

}
