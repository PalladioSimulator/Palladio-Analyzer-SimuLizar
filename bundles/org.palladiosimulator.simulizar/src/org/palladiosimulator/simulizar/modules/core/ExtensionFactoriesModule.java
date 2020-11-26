package org.palladiosimulator.simulizar.modules.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.extension.SimuLizarExtension;
import org.palladiosimulator.simulizar.extension.facets.Cleanup;
import org.palladiosimulator.simulizar.extension.facets.CleanupDelegateFactory;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtensionDelegateFactory;
import org.palladiosimulator.simulizar.extension.facets.ModelCompletion;
import org.palladiosimulator.simulizar.extension.facets.ModelCompletionDelegateFactory;
import org.palladiosimulator.simulizar.extension.facets.ModelLoad;
import org.palladiosimulator.simulizar.extension.facets.ModelLoadDelegateFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public interface ExtensionFactoriesModule {
    
    static <T> Set<T> builderFromExtensions(Set<SimuLizarExtension> extensions, Function<SimuLizarExtension, Collection<T>> mapper) {
        return Collections.unmodifiableSet(extensions.stream()
                .map(mapper)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()));
    }
    
    @Provides
    @Singleton
    public static Set<ModelLoad.Factory> provideModelLoadBuilders(Set<SimuLizarExtension> extensions) {
        return builderFromExtensions(extensions, SimuLizarExtension::getModelLoaders);
    }
    
    @Provides
    @Singleton
    public static Set<ModelCompletion.Factory> provideModelCompletionBuilders(Set<SimuLizarExtension> extensions) {
        return builderFromExtensions(extensions, SimuLizarExtension::getModelCompletions);
    }
    
    @Provides
    @Singleton
    public static Set<InterpreterExtension.Factory> provideInterpreterExtensionBuilders(Set<SimuLizarExtension> extensions) {
        return builderFromExtensions(extensions, SimuLizarExtension::getInterpreterExtensions);
    }
    
    @Provides
    @Singleton
    public static Set<Cleanup.Factory> provideCleanupBuilders(Set<SimuLizarExtension> extensions) {
        return builderFromExtensions(extensions, SimuLizarExtension::getCleanups);
    }
    
    @Binds @Singleton ModelLoad.Factory bindModelLoadDelegateFactory(ModelLoadDelegateFactory impl);
    @Binds @Singleton ModelCompletion.Factory bindModelCompletionDelegateFactory(ModelCompletionDelegateFactory impl);
    @Binds @Singleton InterpreterExtension.Factory bindInterpreterExtensionDelegateFactory(InterpreterExtensionDelegateFactory impl);
    @Binds @Singleton Cleanup.Factory bindCleanupDelegateFactory(CleanupDelegateFactory impl);
}
