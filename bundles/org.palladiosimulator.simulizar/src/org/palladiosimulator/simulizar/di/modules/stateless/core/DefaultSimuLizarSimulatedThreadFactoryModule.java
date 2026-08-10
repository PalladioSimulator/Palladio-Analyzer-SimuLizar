package org.palladiosimulator.simulizar.di.modules.stateless.core;

import jakarta.inject.Provider;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarSimulatedThreadComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.SimulatedThreadComponent;

import dagger.Provides;

@dagger.Module
public interface DefaultSimuLizarSimulatedThreadFactoryModule {

    @Provides
    static SimulatedThreadComponent.Factory provideSimulatedThreadComponentFactory(
            SimuLizarSimulatedThreadComponent.Factory threadComponentFactory,
            Provider<SimuLizarRuntimeComponent> runtimeComponent,
            Provider<SimuComFrameworkComponent> simucomComponent,
            Provider<SimuLizarRootComponent> rootComponent) {
        return (context, process) -> threadComponentFactory.create(context, process, runtimeComponent.get(),
                simucomComponent.get(), rootComponent.get());
    }

}
