package org.palladiosimulator.simulizar.modules.custom;

import java.util.Set;

import org.palladiosimulator.simulizar.extension.SimuLizarExtension;

import com.google.common.collect.ImmutableSet;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public class CustomSimuLizarExtensionsProvidingModule {
    Set<SimuLizarExtension.Factory<?>> factories;

    public CustomSimuLizarExtensionsProvidingModule() {
        this(ImmutableSet.of());
    }

    public CustomSimuLizarExtensionsProvidingModule(Set<SimuLizarExtension.Factory<?>> factories) {
        this.factories = ImmutableSet.copyOf(factories);
    }

    public static CustomSimuLizarExtensionsProvidingModule of(SimuLizarExtension.Factory<?> factory) {
        return new CustomSimuLizarExtensionsProvidingModule(ImmutableSet.of(factory));
    }

    public static CustomSimuLizarExtensionsProvidingModule of(SimuLizarExtension reusableExtension) {
        return new CustomSimuLizarExtensionsProvidingModule(ImmutableSet.of(() -> reusableExtension));
    }

    @Provides
    @ElementsIntoSet
    public Set<SimuLizarExtension.Factory<?>> provideExtensionFactories() {
        return factories;
    }

}
