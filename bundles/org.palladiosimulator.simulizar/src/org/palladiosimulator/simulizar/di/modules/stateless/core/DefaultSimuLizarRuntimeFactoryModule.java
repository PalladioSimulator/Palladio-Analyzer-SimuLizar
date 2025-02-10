package org.palladiosimulator.simulizar.di.modules.stateless.core;

import java.util.Set;

import jakarta.inject.Provider;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.extension.RootExtensions;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;

import com.google.common.collect.Sets;

import dagger.Provides;

@dagger.Module
public interface DefaultSimuLizarRuntimeFactoryModule {

    @Provides
    static AnalysisRuntimeComponent.Factory provideAnalysisRuntimeComponentFactory(SimuLizarRootComponent rootComponent,
            SimuLizarRuntimeComponent.Factory runtimeFactory, Provider<QUALComponent> qualComponent,
            Provider<SimuComFrameworkComponent> simucomComponent, Provider<SimEngineComponent> simEngineComponent,
            @RegisteredComponent Set<Object> bootStrappingComponents,
            @RootExtensions Set<ExtensionComponent> rootExtensions,
            Set<ExtensionComponent.Factory> extensionComponentFactories) {
        return () -> {
            return runtimeFactory.create(rootComponent, simucomComponent.get(), qualComponent.get(),
                    simEngineComponent.get(),
                    new ExtensionComponentsModule(extensionComponentFactories,
                            Sets.union(bootStrappingComponents, rootExtensions)),
                    runtimeFactory.defaultRuntimeComponentFactoriesModule());
        };
    }

}
