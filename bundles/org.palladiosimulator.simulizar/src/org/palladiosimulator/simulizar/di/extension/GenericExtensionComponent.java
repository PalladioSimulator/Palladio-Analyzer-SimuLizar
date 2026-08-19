package org.palladiosimulator.simulizar.di.extension;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.palladiosimulator.simulizar.di.base.extension.ExtensionComponent;

import com.google.common.collect.Sets;
import com.google.common.collect.Streams;

public class GenericExtensionComponent {
    
    private ExtensionComponent decoratedComponent;

    public GenericExtensionComponent(ExtensionComponent decoratedComponent) {
        this.decoratedComponent = decoratedComponent;
    }
    
    public <T> Supplier<Set<T>> getExtensions(Class<T> extensionsType) {
        Lookup lookup = MethodHandles.lookup();
        var supplierMethods = Arrays.asList(decoratedComponent.getClass().getMethods()).stream()
            .filter(m -> (m.getParameterCount() == 0) && isCompatibleSignature(m.getGenericReturnType(), extensionsType))
            .collect(Collectors.toSet());
        
        var singleMethods = supplierMethods.stream().filter(m -> extensionsType.isAssignableFrom(m.getReturnType()))
            .collect(Collectors.toSet());
        var iterableMethods = Sets.difference(supplierMethods, singleMethods);
        
        var singleSuppliers = singleMethods.stream().map(m -> this.<T>createSupplier(m, lookup));
        var iterableSuppliers = iterableMethods.stream().map(m -> this.<Iterable<T>>createSupplier(m, lookup));
        
        return () -> Streams.concat(
                singleSuppliers.map(Supplier::get), 
                iterableSuppliers.map(Supplier::get).flatMap(Streams::stream))
            .collect(Collectors.toSet());
    }
    
    /**
     * Dagger generates the component implementation as a private nested class, whose methods cannot
     * be unreflected. The component interfaces are public and declare the same methods, so the
     * declaration is taken from there when the implementing class is not accessible.
     */
    private static Method accessibleDeclarationOf(Method m) {
        if (Modifier.isPublic(m.getDeclaringClass().getModifiers())) {
            return m;
        }
        for (var iface : m.getDeclaringClass().getInterfaces()) {
            try {
                return iface.getMethod(m.getName(), m.getParameterTypes());
            } catch (NoSuchMethodException e) {
                // not declared by this interface, keep looking
            }
        }
        return m;
    }

    private <T> boolean isCompatibleSignature(Type type, Class<T> extensionsType) {
        if (type instanceof Class<?>) {
            return extensionsType.isAssignableFrom((Class<?>)type);
        } else if (type instanceof ParameterizedType) {
            var paramType = (ParameterizedType) type;
            if (paramType.getRawType() instanceof Class<?> && paramType.getActualTypeArguments().length == 1) {
                return Iterable.class.isAssignableFrom((Class<?>) paramType.getRawType())
                        && extensionsType.isAssignableFrom((Class<?>) paramType.getActualTypeArguments()[0]);
            }
        }
        return false;
    }
    
    private <T> Supplier<T> createSupplier(Method m, Lookup lookup) {
        try {
            var handle = lookup.unreflect(accessibleDeclarationOf(m));
            return () -> {
                try {
                    return (T) handle.invoke(decoratedComponent);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }; 
            
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
