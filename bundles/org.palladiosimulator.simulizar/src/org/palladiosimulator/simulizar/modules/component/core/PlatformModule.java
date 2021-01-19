package org.palladiosimulator.simulizar.modules.component.core;

import org.palladiosimulator.simulizar.component.core.SimuLizarRootComponent;

import dagger.Module;
import dagger.Provides;

@Module
public interface PlatformModule {
    @Provides
    static SimuLizarRootComponent.Factory bindRootComponentFactory() {
        return null;
    }
}
