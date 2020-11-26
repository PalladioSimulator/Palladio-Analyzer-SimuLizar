package org.palladiosimulator.simulizar.extension;

import java.util.Collections;
import java.util.Set;

public class AbstractSimuLizarExtension implements SimuLizarExtension {

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.ModelLoad.Factory> getModelLoaders() {
        return Collections.emptySet();
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.ModelCompletion.Factory> getModelCompletions() {
        return Collections.emptySet();
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.InterpreterExtension.Factory> getInterpreterExtensions() {
        return Collections.emptySet();
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.Cleanup.Factory> getCleanups() {
        return Collections.emptySet();
    }

}
