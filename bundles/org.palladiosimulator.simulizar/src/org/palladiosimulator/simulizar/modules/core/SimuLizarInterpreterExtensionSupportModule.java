package org.palladiosimulator.simulizar.modules.core;

import java.util.Set;

import org.palladiosimulator.simulizar.extension.InterpreterSwitchExtensionRegistry;
import org.palladiosimulator.simulizar.extension.impl.SimuLizarExtensionRegistryImpl;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.scopes.SimulationScope;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public interface SimuLizarInterpreterExtensionSupportModule {


    @Provides
    @SimulationScope
    static SimuLizarExtensionRegistryImpl<AbstractRDSeffSwitchFactory> provideAbstractRDSeffSwitchFactoryRegistry() {
        return new SimuLizarExtensionRegistryImpl<>();
    }

    @Provides
    @SimulationScope
    static InterpreterSwitchExtensionRegistry bindInterpreterSwitchExtensionRegistry(
            SimuLizarExtensionRegistryImpl<AbstractRDSeffSwitchFactory> registry) {
        return registry::register;
    }

    @Provides
    @ElementsIntoSet
    static Set<AbstractRDSeffSwitchFactory> provideExtensionRDSeffSwitchFactory(SimuLizarExtensionRegistryImpl<AbstractRDSeffSwitchFactory> registry) {
        return registry.getExtensions();
    }
}
