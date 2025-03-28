package org.palladiosimulator.simulizar.di.modules.component.core;

import java.util.Set;

import org.palladiosimulator.simulizar.di.base.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.base.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.CoreRuntimeExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.CoreSimulationRuntimeEntitiesBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.ExtensionComponentReconfigurationExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.ExtensionComponentRuntimeExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.LinkingResourceSimulationModule;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.QUALRuntimeExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.ReconfiguratorBindingsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.DefaultSimuLizarSimulatedThreadFactoryModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RuntimeComponentFactoriesModule;

import com.google.common.collect.ImmutableSet;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = { 
        // Import mapping of the SimuLizar Workflow Configuration
        SimuLizarConfigurationModule.class,
        
        // Import bindings of central classes as e.g. EventDispatcher and PCMPartitionManager
        CoreSimulationRuntimeEntitiesBindings.class,
        
        //Component factories for components to create (i. e. SimulatedThreadComponent)
        RuntimeComponentFactoriesModule.class, 
        DefaultSimuLizarSimulatedThreadFactoryModule.class,

        // Import runtime extensions 
        CoreRuntimeExtensionBindings.class, // required observers, as e.g. UsageModelSyncer
        QUALRuntimeExtensionBindings.class, // extensions contributed by QUAL
        ExtensionComponentRuntimeExtensionBindings.class, // dynamic externally provided extensions
        ExtensionComponentReconfigurationExtensionBindings.class, // externally provided reconfiguration extensions 
        
        // Import integral extensions to the interpreters, 
        ReconfiguratorBindingsModule.class, // reconfiguration support
        LinkingResourceSimulationModule.class // linking resource simulation support
})
public interface SimuLizarRuntimeModule {
    
    @RegisteredComponent
    @Provides
    @ElementsIntoSet
    @SimulationRuntimeScope
    static Set<Object> bindRegisteredComponents(SimuLizarRootComponent root, SimuComFrameworkComponent simuCom, QUALComponent qual,
            SimEngineComponent simEngine, SimuLizarRuntimeComponent runtimeComponent) {
        return ImmutableSet.of(root, simuCom, qual, simEngine, runtimeComponent);
    }
}
