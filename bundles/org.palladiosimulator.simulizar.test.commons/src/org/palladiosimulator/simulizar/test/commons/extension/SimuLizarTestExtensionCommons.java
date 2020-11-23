package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.simulizar.test.commons.annotation.InPartition;
import org.palladiosimulator.simulizar.test.commons.annotation.PredicateFactory;

import com.google.common.collect.Streams;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public final class SimuLizarTestExtensionCommons {
    public final static Namespace SIMULIZAR = create("org.palladiosimulator.simulizar");

    /**
     * Retrieves an object from the cached store.
     */
    public static <T> Optional<T> getObjectFromStore(ExtensionContext extensionContext, Class<T> clazz) {
        return Optional.ofNullable(extensionContext.getStore(SIMULIZAR)
            .get(clazz.getName(), clazz));
    }

    /**
     * Retrieves an object from the cached store.
     */
    public static <T> void putObjectIntoStore(ExtensionContext extensionContext, Class<? super T> key, T object) {
        extensionContext.getStore(SIMULIZAR).put(key.getName(), object);
    }

    /**
     * Resolves a PCMResourceSetPartition from the extensionContext and looks for objects conforming
     * to the parameter context.
     */
    public static Optional<Object> findModelEntity(ParameterContext parameterContext,
            ExtensionContext extensionContext) {
        return getObjectFromStore(extensionContext, MDSDBlackboard.class).flatMap(bb -> {
            var partitionName = parameterContext.findAnnotation(InPartition.class)
                    .map(InPartition::value)
                    .orElse(ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID);
            var rsp = bb.getPartition(partitionName);
            var filter = getParameterFilterPredicate(parameterContext);
            return Streams.stream(EcoreUtil.getAllContents(rsp.getResourceSet(), true))
                .filter(filter)
                .findAny();
        });
    }

    /**
     * Returns the predicate suitable for parameter lookup based on the parameter context. If the
     * parameter is annotated with a {@link PredicateFactory}-based annotation the appropriate
     * factory is used to create the filter predicate.
     */
    public static Predicate<Object> getParameterFilterPredicate(ParameterContext parameterContext) {
        Predicate<Object> basePredicate = parameterContext.getParameter()
            .getType()::isInstance;

        return parameterContext.findAnnotation(PredicateFactory.class)
            .map(pf -> {
                try {
                    return pf.value()
                        .getDeclaredConstructor()
                        .newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    throw new RuntimeException(e);
                }
            })
            .map(factory -> factory.apply(parameterContext))
            .map(basePredicate::and)
            .orElse(basePredicate);
    }
    
    public static <T> T loadInstance(Class<T> supplierClass, ExtensionContext context) {
        try {
            for (var constr : supplierClass.getDeclaredConstructors()) {
                if (constr.getParameterCount() == 1 && constr.getParameters()[0].getType()
                    .isAssignableFrom(ExtensionContext.class)) {
                    return supplierClass.getDeclaredConstructor(ExtensionContext.class)
                        .newInstance(context);
                }
            }
            return supplierClass.getDeclaredConstructor()
                .newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T loadSupplierInstance(Class<? extends Supplier<T>> supplierClass, ExtensionContext context) {
        return loadInstance(supplierClass, context).get();
    }

}
