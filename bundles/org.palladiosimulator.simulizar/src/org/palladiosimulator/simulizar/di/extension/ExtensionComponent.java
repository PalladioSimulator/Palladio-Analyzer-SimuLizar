package org.palladiosimulator.simulizar.di.extension;

import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.ImmutableSet;

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
    
    public default <ET extends Extension> Set<ExtensionContribution<? extends Extension, ComponentType>> contribute(Class<ET> extensionType,
            Function<ComponentType, ET> supplier) {
        return ImmutableSet.of(ExtensionContribution.of(extensionType, supplier));
    }

}
