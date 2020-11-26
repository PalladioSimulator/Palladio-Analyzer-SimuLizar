package org.palladiosimulator.simulizar.extension.facets;

import org.palladiosimulator.simulizar.SimuLizarSimulationComponent;
import org.palladiosimulator.simulizar.scopes.ExtensionScope;

import dagger.Component;


@Component( dependencies = SimuLizarSimulationComponent.class )
@ExtensionScope
public interface InterpreterExtension {
   
    @Component.Factory
    interface Factory {
        InterpreterExtension create(SimuLizarSimulationComponent component);
    }
    
    default void preInitialize() {
    }
    
    default void initialized() {
    }
    
    default void completed() {
    }

}
