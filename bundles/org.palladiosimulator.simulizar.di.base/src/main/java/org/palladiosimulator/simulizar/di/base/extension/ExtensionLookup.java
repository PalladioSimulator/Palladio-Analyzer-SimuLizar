package org.palladiosimulator.simulizar.di.base.extension;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
public interface ExtensionLookup extends Function<Class<? extends Extension>, Set<Extension>> {
    
    @SuppressWarnings({ "unchecked" })
    default <T extends Extension> Set<T> lookup(Class<T> extensionClass) {
        return (Set<T>) apply(extensionClass);
    }
    
    public static ExtensionLookup createLookup(final Set<GenericExtensionComponent> components) {
        return cls -> {
            return components.stream()
                .map(comp -> comp.getExtensions(cls)
                    .get())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        };
    }

}
