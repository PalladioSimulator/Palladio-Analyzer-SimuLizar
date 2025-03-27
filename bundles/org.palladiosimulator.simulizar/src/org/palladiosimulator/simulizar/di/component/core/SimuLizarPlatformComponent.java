package org.palladiosimulator.simulizar.di.component.core;

import org.palladiosimulator.simulizar.di.base.scopes.PlatformScope;
import org.palladiosimulator.simulizar.di.modules.component.core.PlatformModule;

import dagger.Component;

@Component(modules = PlatformModule.class)
@PlatformScope
public interface SimuLizarPlatformComponent {
   
    SimuLizarRootComponent.Factory analysisFactory();
    
    @Component.Factory
    public interface Factory {
        SimuLizarPlatformComponent create();
    }
   
}
