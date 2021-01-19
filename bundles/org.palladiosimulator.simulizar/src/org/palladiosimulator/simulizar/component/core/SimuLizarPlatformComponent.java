package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.simulizar.modules.component.core.PlatformModule;
import org.palladiosimulator.simulizar.scopes.PlatformScope;

import dagger.Component;

@PlatformScope
@Component(modules = PlatformModule.class)
public interface SimuLizarPlatformComponent {
   
    SimuLizarRootComponent.Factory analysisFactory();
    
    public interface Factory {
        SimuLizarPlatformComponent create();
    }
   
}
