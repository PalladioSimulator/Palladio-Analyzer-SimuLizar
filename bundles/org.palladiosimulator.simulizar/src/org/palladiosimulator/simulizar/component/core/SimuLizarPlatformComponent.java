package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.simulizar.modules.component.core.PlatformModule;
import org.palladiosimulator.simulizar.scopes.PlatformScope;

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
