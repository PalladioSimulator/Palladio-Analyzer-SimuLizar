package org.palladiosimulator.simulizar.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.extension.ParameterContext;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.simulizar.test.SimuLizarTestExtension;

/**
 * This annotation can be used together with {@link SimuLizarTestExtension} to select a particular
 * model entity from the resource set by its name. The annotation should be added to the test method
 * parameter which needs to be resolved.
 * 
 * @author Sebastian Krach
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@PredicateFactory(Named.Factory.class)
public @interface Named {
    /** The predicate factory for the enclosing annotation */
    public static class Factory implements Function<ParameterContext, Predicate<Object>> {
        /**
         * Creates a predicate which checks, whether the object is of type Identifier and if so,
         * whether the identifier matches the value of the Named annotation of the parameter.
         */
        @Override
        public Predicate<Object> apply(ParameterContext t) {
            return t.findAnnotation(Named.class)
                .<Predicate<Object>> map(n -> obj -> EntityPackage.Literals.NAMED_ELEMENT.isInstance(obj)
                        && ((EObject) obj).eGet(EntityPackage.Literals.NAMED_ELEMENT__ENTITY_NAME)
                            .equals(n.value()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "The required annotation was not present. This should not occur."));
        }
    };

    String value();
}