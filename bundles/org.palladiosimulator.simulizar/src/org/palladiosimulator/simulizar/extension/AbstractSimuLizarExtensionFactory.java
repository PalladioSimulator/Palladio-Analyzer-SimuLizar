package org.palladiosimulator.simulizar.extension;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;

public abstract class AbstractSimuLizarExtensionFactory<ExtensionType extends SimuLizarExtension>
        implements SimuLizarExtensionFactory<ExtensionType> {

    @Override
    public ExtensionType createExtension(SimuLizarCoreComponent simuLizarComponent) {
        return createComponent(simuLizarComponent).getExtension();
    }

    protected SimuLizarExtensionComponent<ExtensionType> createComponent(SimuLizarCoreComponent simuLizarComponent) {
        return createExtensionComponentBuilder().coreComponent(simuLizarComponent)
            .build();
    }

    protected abstract SimuLizarExtensionComponent.Builder<ExtensionType> createExtensionComponentBuilder();

}
