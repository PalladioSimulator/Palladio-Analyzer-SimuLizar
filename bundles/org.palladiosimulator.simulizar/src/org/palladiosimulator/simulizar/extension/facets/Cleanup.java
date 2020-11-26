package org.palladiosimulator.simulizar.extension.facets;

import org.palladiosimulator.simulizar.scopes.ExtensionScope;

import dagger.Component;

@Component
@ExtensionScope
public interface Cleanup {
    
    @Component.Factory
    interface Factory {
        Cleanup create();
    }
    
    default void cleanup() {
        
    }

}
