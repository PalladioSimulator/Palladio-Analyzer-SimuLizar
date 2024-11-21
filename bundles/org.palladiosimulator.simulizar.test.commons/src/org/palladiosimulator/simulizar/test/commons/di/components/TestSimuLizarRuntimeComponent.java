package org.palladiosimulator.simulizar.test.commons.di.components;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRuntimeModule;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.LegacyRuntimeStateAccessorAdapterModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RuntimeComponentFactoriesModule;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.test.commons.di.components.TestSimuLizarRuntimeComponent.TestSimulizarRuntimeModule;

import dagger.Binds;
import dagger.Component;

@Component(dependencies = { SimuLizarRootComponent.class, SimuComFrameworkComponent.class, QUALComponent.class,
        SimEngineComponent.class }, modules = { TestSimulizarRuntimeModule.class })
@SimulationRuntimeScope
public interface TestSimuLizarRuntimeComponent extends SimuLizarRuntimeComponent {
    
    
    
    @Component.Factory
    public interface Factory extends SimuLizarRuntimeComponent.Factory {
        
    }
    
    @dagger.Module(includes = { SimuLizarRuntimeModule.class, LegacyRuntimeStateAccessorAdapterModule.class })
    public interface TestSimulizarRuntimeModule {
        @Binds SimuLizarRuntimeComponent bindRuntimeComponent(TestSimuLizarRuntimeComponent component);
    }
    
}
