package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.palladiosimulator.simulizar.di.extension.Extension;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionLookup;
import org.palladiosimulator.simulizar.di.extension.RuntimeExtensions;
import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulationRuntimeExtensions;
import org.palladiosimulator.simulizar.di.modules.stateless.extension.ExtensionSupportModule;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import com.google.common.collect.Multimap;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = { ExtensionSupportModule.class, SimulationRuntimeExtensions.class })
public interface ExtensionComponentRuntimeExtensionBindings {
    
    @Provides
    @RuntimeExtensions
    @SimulationRuntimeScope
    static Set<? extends ExtensionComponent<?>> provideRuntimeExtensions(Set<? extends ExtensionComponent<?>> extensions) {
        return extensions;
    }
    
    @Provides
    @SimulationRuntimeScope
    static ExtensionLookup provideExtensionLookup(Multimap<Class<? extends Extension>, Supplier<Extension>> extensionsMap) {
        return extensionsMap::get;
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<RuntimeStateEntityManager> entityManagers(ExtensionLookup lookup) {
        return lookup.lookup(RuntimeStateEntityManager.class).stream().map(Supplier::get).collect(Collectors.toSet());
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<RuntimeStateEntityObserver> entityObservers(ExtensionLookup lookup) {
        return lookup.lookup(RuntimeStateEntityObserver.class).stream().map(Supplier::get).collect(Collectors.toSet());
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<IInterpreterListener> bindInterpreterListeners(ExtensionLookup lookup) {
        return lookup.lookup(IInterpreterListener.class).stream().map(Supplier::get).collect(Collectors.toSet());
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<IModelObserver> modelObservers(ExtensionLookup lookup) {
        return lookup.lookup(IModelObserver.class).stream().map(Supplier::get).collect(Collectors.toSet());
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<RDSeffSwitchContributionFactory<Object>> rdseffSwitchFactories(ExtensionLookup lookup) {
        return lookup.lookup(RDSeffSwitchContributionFactory.class).stream().map(Supplier::get).collect(Collectors.toSet());
    }

}
