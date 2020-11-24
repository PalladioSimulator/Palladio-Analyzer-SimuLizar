package org.palladiosimulator.simulizar.modules;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;
import org.palladiosimulator.simulizar.extension.InterpreterSwitchExtensionRegistry;
import org.palladiosimulator.simulizar.extension.SimuLizarExtension;
import org.palladiosimulator.simulizar.extension.SimuLizarExtensionFactory;
import org.palladiosimulator.simulizar.extension.impl.SimuLizarExtensionRegistryImpl;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchFactory;
import org.palladiosimulator.simulizar.scopes.ExtensionManagementScope;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public interface BasicSimuLizarExtensionModule {

    @Provides
    @ElementsIntoSet
    public static Set<SimuLizarExtensionFactory<?>> provideExtensionFactories() {
        return Collections.emptySet();
    }

    @Provides
    @ElementsIntoSet
    @ExtensionManagementScope
    public static Set<SimuLizarExtension> provideExtensions(Set<SimuLizarExtensionFactory<?>> factories,
            SimuLizarCoreComponent component) {
        return factories.stream()
            .map(fact -> fact.createExtension(component))
            .collect(Collectors.toSet());
    }

    @Provides
    @Singleton
    static SimuLizarExtensionRegistryImpl<AbstractRDSeffSwitchFactory> provideAbstractRDSeffSwitchFactoryRegistry() {
        return new SimuLizarExtensionRegistryImpl<>();
    }

    @Provides
    @Singleton
    static InterpreterSwitchExtensionRegistry bindInterpreterSwitchExtensionRegistry(
            SimuLizarExtensionRegistryImpl<AbstractRDSeffSwitchFactory> registry) {
        return registry::register;
    }

    @Provides
    @ElementsIntoSet
    static Set<AbstractRDSeffSwitchFactory> provideExtensionRDSeffSwitchFactory(RDSeffSwitchFactory basicFactory,
            SimuLizarExtensionRegistryImpl<AbstractRDSeffSwitchFactory> registry) {
        return registry.getExtensions();
    }
}
