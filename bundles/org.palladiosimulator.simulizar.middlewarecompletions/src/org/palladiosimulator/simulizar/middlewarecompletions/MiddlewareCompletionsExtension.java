package org.palladiosimulator.simulizar.middlewarecompletions;

import java.util.Set;

import org.palladiosimulator.simulizar.extension.AbstractSimuLizarExtension;
import org.palladiosimulator.simulizar.extension.SimuLizarExtension;

public class MiddlewareCompletionsExtension extends AbstractSimuLizarExtension {
    
    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.InterpreterExtension.Factory> getInterpreterExtensions() {
        return Set.of(DaggerCompletionsRDSeffSwitchExtension.factory());
    }


    public class Factory implements SimuLizarExtension.Factory<MiddlewareCompletionsExtension> {
        @Override
        public MiddlewareCompletionsExtension create() {
            return new MiddlewareCompletionsExtension();
        }  
    }

}
