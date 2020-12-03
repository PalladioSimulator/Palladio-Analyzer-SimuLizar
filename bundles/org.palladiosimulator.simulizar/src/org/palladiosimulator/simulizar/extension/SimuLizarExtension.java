package org.palladiosimulator.simulizar.extension;

import java.util.Set;

import org.palladiosimulator.simulizar.SimuLizarRootExtensionComponent;
import org.palladiosimulator.simulizar.extension.facets.Cleanup;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.extension.facets.ModelCompletion;
import org.palladiosimulator.simulizar.extension.facets.ModelLoad;

import dagger.multibindings.Multibinds;

public interface SimuLizarExtension {

    Set<ModelLoad.Factory> getModelLoaders();
    Set<ModelCompletion.Factory> getModelCompletions();
    Set<InterpreterExtension.Factory> getInterpreterExtensions();
    Set<Cleanup.Factory> getCleanups();
    
    @FunctionalInterface
    interface Factory<ExtensionType extends SimuLizarExtension> {
        ExtensionType create(SimuLizarRootExtensionComponent rootComponent);
    }
    
    @dagger.Module
    public interface DefaultExtensionModule {
        @Multibinds Set<ModelLoad.Factory> getModelLoaders();
        @Multibinds Set<ModelCompletion.Factory> getModelCompletions();
        @Multibinds Set<InterpreterExtension.Factory> getInterpreterExtensions();
        @Multibinds Set<Cleanup.Factory> getCleanups();   
    }
    
}
