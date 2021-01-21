package org.palladiosimulator.simulizar.di.modules.stateless.core;

import javax.inject.Provider;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.AnalysisRuntimeComponent;

import dagger.Provides;

@dagger.Module
public interface DefaultSimuLizarRuntimeFactoryModule {
    
    @Provides
    static AnalysisRuntimeComponent.Factory provideAnalysisRuntimeComponentFactory(SimuLizarRootComponent rootComponent,
            SimuLizarRuntimeComponent.Factory runtimeFactory, Provider<QUALComponent> qualComponent,
            Provider<SimuComFrameworkComponent> simucomComponent, Provider<SimEngineComponent> simEngineComponent) {
        return () -> {
            return runtimeFactory.create(rootComponent, simucomComponent.get(), qualComponent.get(),
                    simEngineComponent.get());
        };
    }

}
