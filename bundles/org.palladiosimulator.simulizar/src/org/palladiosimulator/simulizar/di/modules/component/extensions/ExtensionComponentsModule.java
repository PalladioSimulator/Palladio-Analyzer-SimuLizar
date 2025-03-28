package org.palladiosimulator.simulizar.di.modules.component.extensions;

import java.util.Set;

import org.palladiosimulator.simulizar.di.base.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.base.extension.RegisteredComponent;

import com.google.common.collect.ImmutableSet;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public class ExtensionComponentsModule {
    Set<ExtensionComponent.Factory> extensionFactories;
    Set<Object> bootstrapComponents;
    
    public ExtensionComponentsModule() {
        extensionFactories = ImmutableSet.of();
        bootstrapComponents = ImmutableSet.of();
    }
    
    public ExtensionComponentsModule(Set<ExtensionComponent.Factory> factories, Set<Object> bootstrapComponents) {
        extensionFactories = ImmutableSet.copyOf(factories);
        this.bootstrapComponents = ImmutableSet.copyOf(bootstrapComponents);
    }
    
    @Provides
    @ElementsIntoSet
    Set<ExtensionComponent.Factory> provideExtensionFactories() {
        return extensionFactories;
    }
    
    @Provides
    @ElementsIntoSet
    @RegisteredComponent
    Set<Object> provideBootstrapExtensions() {
        return bootstrapComponents;
    }

}
