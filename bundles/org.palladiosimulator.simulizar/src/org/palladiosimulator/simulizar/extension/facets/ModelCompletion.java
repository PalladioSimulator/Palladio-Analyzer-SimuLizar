package org.palladiosimulator.simulizar.extension.facets;

import org.palladiosimulator.simulizar.SimuLizarModelCompletionComponent;
import org.palladiosimulator.simulizar.scopes.ExtensionScope;

import dagger.Component;

@Component(dependencies = SimuLizarModelCompletionComponent.class)
@ExtensionScope
public interface ModelCompletion {

    @Component.Factory
    interface Factory {
        ModelCompletion create(SimuLizarModelCompletionComponent component);
    }

    /**
     * Iteratively requests all extension to perform their completions until no extension reports
     * any model changes anymore.
     * 
     * @return true, if the extension performed changes to the model.
     */
    default boolean runModelCompletion() {
        return false;
    }
    

}
