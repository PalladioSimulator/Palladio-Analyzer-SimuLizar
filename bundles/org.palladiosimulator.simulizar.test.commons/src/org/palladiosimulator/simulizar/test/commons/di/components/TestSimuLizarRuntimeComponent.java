package org.palladiosimulator.simulizar.test.commons.di.components;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRuntimeModule;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationEngine;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QvtoReconfigurationLoader;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import dagger.Binds;
import dagger.Component;
import dagger.MembersInjector;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Component(modules = TestSimuLizarRuntimeComponent.TestRuntimeModule.class, dependencies = {
        SimuLizarRootComponent.class, SimuComFrameworkComponent.class, QUALComponent.class, SimEngineComponent.class })
@SimulationRuntimeScope
public interface TestSimuLizarRuntimeComponent extends SimuLizarRuntimeComponent {

    @Component.Factory
    public static interface Factory extends SimuLizarRuntimeComponent.Factory {
    }

    @dagger.Module(includes = { SimuLizarRuntimeModule.class })
    public static interface TestRuntimeModule {
        @Binds
        SimuLizarRuntimeComponent bindRuntimeComponent(TestSimuLizarRuntimeComponent textComponent);
        
        @Provides
        @IntoSet
        @SimulationRuntimeScope
        static IReconfigurationEngine provideQVTOEngine(MembersInjector<IReconfigurationEngine> injector) {
            var engine = new QVTOReconfigurator();
            injector.injectMembers(engine);
            return engine;
        }

        @Provides
        @IntoSet
        @SimulationRuntimeScope
        static AbstractReconfigurationLoader provideQVTOLoader() {
            return new QvtoReconfigurationLoader();
        }
    }

}
