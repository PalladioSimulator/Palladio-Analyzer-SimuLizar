package org.palladiosimulator.simulizar.di.extension;

import java.util.Set;

public interface ExtensionComponent<ComponentType extends ExtensionComponent<ComponentType>> {

    Set<ExtensionContribution<? extends Extension, ComponentType>> contributions();

    /**
     * This is a marker interface to signal that the registered component factory is compatible with
     * the SimuLizar DI Extension system.
     * 
     * @author krach
     *
     */
    public interface Factory {

    }

}
