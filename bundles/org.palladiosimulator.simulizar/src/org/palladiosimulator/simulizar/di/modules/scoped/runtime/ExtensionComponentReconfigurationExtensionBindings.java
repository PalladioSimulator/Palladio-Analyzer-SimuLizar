package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import java.util.Set;

import org.palladiosimulator.simulizar.di.extension.ExtensionLookup;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ReconfigurationExtensions;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationEngine;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = { ExtensionComponentRuntimeExtensionBindings.class, ReconfigurationExtensions.class })
public interface ExtensionComponentReconfigurationExtensionBindings {
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<AbstractReconfigurationLoader> bindReconfigurationLoaders(ExtensionLookup lookup) {
        return lookup.lookup(AbstractReconfigurationLoader.class);
    }
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<IReconfigurationEngine> bindReconfigurationEngines(ExtensionLookup lookup) {
        return lookup.lookup(IReconfigurationEngine.class);
    }

}
