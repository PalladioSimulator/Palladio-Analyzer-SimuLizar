package org.palladiosimulator.simulizar.test.commons.extension;

import static org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons.findModelEntity;
import static org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons.getObjectFromStore;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;;


public class SimuLizarTestParameterProvider implements ParameterResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return getObjectFromStore(extensionContext, parameterContext.getParameter()
            .getType()).isPresent() || findModelEntity(parameterContext, extensionContext).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return getObjectFromStore(extensionContext, (Class<Object>) parameterContext.getParameter()
            .getType()).or(() -> findModelEntity(parameterContext, extensionContext))
                .orElse(null);
    }

}
