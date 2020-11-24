package org.palladiosimulator.simulizar.extension;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;

/**
 * The entrypoint for extension to SimuLizar. To implement an extension using DI see
 * {@link AbstractSimuLizarExtensionFactory}.
 *
 * @param <ExtensionType> The type of the extension.
 */
public interface SimuLizarExtensionFactory<ExtensionType extends SimuLizarExtension> {

    ExtensionType createExtension(SimuLizarCoreComponent simuLizarComponent);

}
