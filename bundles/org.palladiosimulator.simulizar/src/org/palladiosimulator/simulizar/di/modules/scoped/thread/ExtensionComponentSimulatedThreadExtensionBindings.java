package org.palladiosimulator.simulizar.di.modules.scoped.thread;

import java.util.Set;

import org.palladiosimulator.simulizar.di.extension.ExtensionLookup;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public interface ExtensionComponentSimulatedThreadExtensionBindings {
    
    @Provides
    @SimulatedThreadScope
    @ElementsIntoSet
    static Set<RDSeffSwitchContributionFactory> provideContributionFactories(ExtensionLookup lookup) {
        return lookup.lookup(RDSeffSwitchContributionFactory.class);
    }
    
 
}
