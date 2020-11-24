package org.palladiosimulator.simulizar.middlewarecompletions;

import org.palladiosimulator.simulizar.extension.AbstractSimuLizarExtensionFactory;
import org.palladiosimulator.simulizar.extension.SimuLizarExtensionComponent.Builder;

public class MiddlewareCompletionsExtensionFactory extends AbstractSimuLizarExtensionFactory<MiddlewareCompletionsExtension> {

    @Override
    protected Builder<MiddlewareCompletionsExtension> createExtensionComponentBuilder() {
        return DaggerCompletionExtensionComponent.builder();
    }

}
