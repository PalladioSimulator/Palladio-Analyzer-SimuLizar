package org.palladiosimulator.simulizar.di.modules.component.core;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.eclipse.DaggerEclipseSimuLizarRootComponent;

import dagger.Module;
import dagger.Provides;

@Module
public interface PlatformModule {
    @Provides
    static SimuLizarRootComponent.Factory bindRootComponentFactory() {
        return DaggerEclipseSimuLizarRootComponent.factory();
    }
}
