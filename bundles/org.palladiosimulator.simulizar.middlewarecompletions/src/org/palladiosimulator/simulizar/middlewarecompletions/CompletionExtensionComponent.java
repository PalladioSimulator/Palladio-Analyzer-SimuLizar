package org.palladiosimulator.simulizar.middlewarecompletions;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;
import org.palladiosimulator.simulizar.extension.SimuLizarExtensionComponent;
import org.palladiosimulator.simulizar.scopes.ExtensionScope;

import dagger.Component;

@ExtensionScope
@Component(dependencies = SimuLizarCoreComponent.class)
public interface CompletionExtensionComponent extends SimuLizarExtensionComponent<MiddlewareCompletionsExtension> {
    
    @Component.Builder
    interface Builder extends SimuLizarExtensionComponent.Builder<MiddlewareCompletionsExtension> {
    }

}
