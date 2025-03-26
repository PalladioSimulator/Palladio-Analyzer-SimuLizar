package org.palladiosimulator.simulizar.di.modules.stateless.extension;

import java.util.Set;
import java.util.stream.Collectors;

import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponentDependencyResolution;
import org.palladiosimulator.simulizar.di.extension.GenericExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.Multibinds;

@Module(includes = ExtensionComponentsModule.class)
public interface ExtensionSupportModule {

    @RegisteredComponent
    @Multibinds
    Set<Object> bindRegisteredComponents();

    @Multibinds
    Set<ExtensionComponent.Factory> bindExtensionFactories();

    @Provides
    @ElementsIntoSet
    static Set<ExtensionComponent> providesExtensionComponents(ExtensionComponentDependencyResolution resolution) {
        return resolution.getExtensionComponents();
    }
    
    @Provides
    @ElementsIntoSet
    static Set<GenericExtensionComponent> providesGenericExtensionComponents(Set<ExtensionComponent> extensions) {
        return extensions.stream()
            .map(GenericExtensionComponent::new)
            .collect(Collectors.toSet());
    }

}
