package org.palladiosimulator.simulizar.di.modules.component.extensions;

import java.util.Set;

import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;

import com.google.common.collect.ImmutableSet;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public class ExtensionComponentsModule {
    Set<ExtensionComponent.Factory> extensionFactories;
    
    public ExtensionComponentsModule() {
        extensionFactories = ImmutableSet.of();
    }
    
    public ExtensionComponentsModule(ExtensionComponent.Factory...factories) {
        extensionFactories = ImmutableSet.copyOf(factories);
    }
    
    @Provides
    @ElementsIntoSet
    Set<ExtensionComponent.Factory> provideExtensionFactories() {
        return extensionFactories;
    }

}
