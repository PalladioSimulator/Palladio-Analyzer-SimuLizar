package org.palladiosimulator.simulizar.extension;

import java.util.Set;

import org.palladiosimulator.simulizar.extension.facets.Cleanup;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.extension.facets.ModelCompletion;
import org.palladiosimulator.simulizar.extension.facets.ModelLoad;

import com.google.common.collect.ImmutableSet;

public class AbstractSimuLizarExtension implements SimuLizarExtension {
    private final Set<ModelLoad.Factory> modelLoaders;
    private final Set<ModelCompletion.Factory> modelCompletions;
    private final Set<InterpreterExtension.Factory> interpreterExtensions;
    private final Set<Cleanup.Factory> cleanups;

    public AbstractSimuLizarExtension() {
        this(ImmutableSet.of(), ImmutableSet.of(), ImmutableSet.of(), ImmutableSet.of());
    }

    public AbstractSimuLizarExtension(Set<ModelLoad.Factory> modelLoaders,
            Set<ModelCompletion.Factory> modelCompletions, Set<InterpreterExtension.Factory> interpreterExtensions,
            Set<Cleanup.Factory> cleanups) {
        this.modelLoaders = modelLoaders;
        this.modelCompletions = modelCompletions;
        this.interpreterExtensions = interpreterExtensions;
        this.cleanups = cleanups;
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.ModelLoad.Factory> getModelLoaders() {
        return modelLoaders;
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.ModelCompletion.Factory> getModelCompletions() {
        return modelCompletions;
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.InterpreterExtension.Factory> getInterpreterExtensions() {
        return interpreterExtensions;
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.Cleanup.Factory> getCleanups() {
        return cleanups;
    }

}
