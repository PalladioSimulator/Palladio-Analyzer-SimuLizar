package org.palladiosimulator.simulizar.di.extension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Streams;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;

import dagger.Component;

public class GenericComponentFactory<ComponentType> implements Supplier<ComponentType> {
    private Class<ComponentType> componentType;
    private Map<Class<?>, Supplier<?>> suppliers;
    private Set<Class<?>> unfullfilledRequirements;
    private Supplier<ComponentType> daggerComponentFactory;

    @SuppressWarnings("unchecked")
    public GenericComponentFactory(ExtensionComponent.Factory daggerComponentFactory) {
        var factoryClass = daggerComponentFactory.getClass();
        if (factoryClass.getInterfaces().length != 1) throw new IllegalStateException(
                "The following logic assumes the concrete factory to "
                + "solely implement the component factory interface. If "
                + "this assumption does not hold anymore, this logic needs to be adapted.");
                
        var factoryInterface = factoryClass.getInterfaces()[0];
        
        var factoryAnnotation = factoryInterface.getAnnotation(Component.Factory.class);
        if (factoryAnnotation == null) {
            throw new IllegalStateException("The dynamic extension concept currently relies on Dagger Factories");
        }
        
        componentType = (Class<ComponentType>) factoryInterface.getEnclosingClass();
        
        var componentAnnotation = componentType.getAnnotation(Component.class);
        unfullfilledRequirements = new HashSet<>(Arrays.asList(componentAnnotation.dependencies()));
        
        var factoryMethod = Arrays.asList(factoryInterface.getClass().getDeclaredMethods()).stream()
                .filter(m -> componentType.isAssignableFrom(m.getReturnType()) &&
                        requirementsFullfillFactoryMethod(m, unfullfilledRequirements))
                .findAny();
        
        if (factoryMethod.isEmpty()) {
            throw new IllegalStateException("No compatible factory method found on interface " + factoryInterface.getName());
        }
        
        this.daggerComponentFactory = () -> {
            var method = factoryMethod.get();
            var parameterTypes = method.getParameters();
            var parameters = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                parameters[i] = suppliers.get(parameterTypes[i].getType()).get();
            }
            try {
                return (ComponentType) factoryMethod.get().invoke(daggerComponentFactory, parameters);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    private boolean requirementsFullfillFactoryMethod(Method m, Set<Class<?>> requirements) {
        var set = Arrays.asList(m.getParameters()).stream().map(Parameter::getType).collect(Collectors.toSet());
        return Sets.difference(set, unfullfilledRequirements).isEmpty();
    }

    public Class<ComponentType> getProvidedComponentType() {
        return componentType;
    }
    public Set<Class<?>> getUnfullfilledRequirements() {
        return ImmutableSet.copyOf(unfullfilledRequirements);
    }

    <T> void fulfillRequirement(Class<T> componentType, Supplier<T> factory) {
        Streams.stream(getClassHierarchy(componentType))
            .filter(cls -> cls.getAnnotation(Component.class) != null)
            .forEach(cls -> {
                var requirement = unfullfilledRequirements.remove(cls);
                if (requirement)
                    suppliers.put(cls, factory);
            });
    }

    @Override
    public ComponentType get() {
        return daggerComponentFactory.get();
    }
    
    /* The following method to traverse the super-type and super-interface hierarchy of a class is taken from
     * https://stackoverflow.com/questions/49861434/how-to-easily-iterate-over-all-super-classes-interfaces/49862841#49862841*/
     public static Iterable<Class<?>> getClassHierarchy(Class<?> baseClass) {
        return Traverser.forGraph(
            (SuccessorsFunction<Class<?>>) node -> {
                Class<?> superclass = node.getSuperclass();
                List<Class<?>> interfaces = Arrays.asList(node.getInterfaces());
                return superclass == null ? interfaces
                    : Iterables.concat(interfaces, Collections.singleton(superclass));
            }
        ).breadthFirst(baseClass);
    }

}
