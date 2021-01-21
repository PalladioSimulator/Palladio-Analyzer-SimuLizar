package org.palladiosimulator.simulizar.di.modules.stateless.extension;

import java.util.Set;
import java.util.function.Supplier;

import org.palladiosimulator.simulizar.di.extension.Extension;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponentDependencyResolution;
import org.palladiosimulator.simulizar.di.extension.ExtensionContribution;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

@Module
public interface ExtensionSupportModule {

    @RegisteredComponent
    @Multibinds
    Set<Object> bindRegisteredComponents();
    
    @Multibinds
    Set<ExtensionComponent.Factory> bindExtensionFactories();
        
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Provides
    static Multimap<Class<? extends Extension>, Supplier<Extension>> provideExtensions(ExtensionComponentDependencyResolution resolution) {
        Multimap<Class<? extends Extension>, Supplier<Extension>> result = LinkedHashMultimap.create();
        
        resolution.initializeExtensionComponentSet().forEach(component -> {
            component.contributions().forEach(contribution -> {
                result.put(contribution.getExtensionType(), () -> (Extension) ((ExtensionContribution) contribution).contribute(component));
            });
        });
        
        return result;
    }
}
