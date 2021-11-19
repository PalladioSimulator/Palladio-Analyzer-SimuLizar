package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import java.util.Set;

import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponentDependencyResolution;
import org.palladiosimulator.simulizar.di.extension.ExtensionLookup;
import org.palladiosimulator.simulizar.di.extension.GenericExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.extension.RuntimeExtensions;
import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulationRuntimeExtensions;
import org.palladiosimulator.simulizar.di.modules.stateless.extension.ExtensionSupportModule;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.InterpreterResultListener;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = { ExtensionSupportModule.class, SimulationRuntimeExtensions.class })
public interface ExtensionComponentRuntimeExtensionBindings {
    
    @Provides
    @SimulationRuntimeScope
    static ExtensionComponentDependencyResolution provideDependencyResolution(@RegisteredComponent Set<Object> bootStrappingComponents,
            Set<ExtensionComponent.Factory> extensionComponentFactories) {
        return new ExtensionComponentDependencyResolution(bootStrappingComponents, extensionComponentFactories);
    }
    
    @Provides
    @RuntimeExtensions
    @SimulationRuntimeScope
    static Set<ExtensionComponent> provideRuntimeExtensions(Set<ExtensionComponent> extensions) {
        return extensions;
    }
    
    @Provides
    @SimulationRuntimeScope
    static ExtensionLookup provideExtensionLookup(Set<GenericExtensionComponent> genericRuntimeExtensions) {
        return ExtensionLookup.createLookup(genericRuntimeExtensions);
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<RuntimeStateEntityManager> entityManagers(ExtensionLookup lookup) {
        return lookup.lookup(RuntimeStateEntityManager.class);
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<RuntimeStateEntityObserver> entityObservers(ExtensionLookup lookup) {
        return lookup.lookup(RuntimeStateEntityObserver.class);
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<IInterpreterListener> bindInterpreterListeners(ExtensionLookup lookup) {
        return lookup.lookup(IInterpreterListener.class);
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<InterpreterResultListener> bindInterpreterResultListeners(ExtensionLookup lookup) {
        return lookup.lookup(InterpreterResultListener.class);
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<IModelObserver> modelObservers(ExtensionLookup lookup) {
        return lookup.lookup(IModelObserver.class);
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<RDSeffSwitchContributionFactory> rdseffSwitchFactories(ExtensionLookup lookup) {
        return lookup.lookup(RDSeffSwitchContributionFactory.class);
    }

}
