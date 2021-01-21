package org.palladiosimulator.simulizar.di.extension;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface ExtensionLookup extends Function<Class<? extends Extension>, Collection<Supplier<Extension>>> {
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    default <T extends Extension> Collection<Supplier<T>> lookup(Class<T> extensionClass) {
        return (Collection<Supplier<T>>) ((Collection) apply(extensionClass));
    }

}
