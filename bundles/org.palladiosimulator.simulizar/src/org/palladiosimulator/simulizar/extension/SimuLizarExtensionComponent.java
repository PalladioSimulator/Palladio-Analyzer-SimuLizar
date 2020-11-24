package org.palladiosimulator.simulizar.extension;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;

public interface SimuLizarExtensionComponent<ExtensionType extends SimuLizarExtension> {

    ExtensionType getExtension();

    interface Builder<ExtensionType extends SimuLizarExtension> {
        Builder<ExtensionType> coreComponent(SimuLizarCoreComponent coreComponent);

        SimuLizarExtensionComponent<ExtensionType> build();
    }

}
