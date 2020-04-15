package org.palladiosimulator.simulizar.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.extension.ParameterContext;
import org.palladiosimulator.simulizar.test.SimuLizarTestExtension;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

/**
 * This annotation can be used together with {@link SimuLizarTestExtension} to select a particular
 * model entity from the resource set by its id. The annotation should be added to the test method
 * parameter which needs to be resolved.
 * 
 * @author Sebastian Krach
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@PredicateFactory(Identified.Factory.class)
public @interface Identified {
    /** The predicate factory for the enclosing annotation */
    public static class Factory implements Function<ParameterContext, Predicate<Object>> {
        /**
         * Creates a predicate which checks, whether the object is of type Identifier and if so,
         * whether the identifier matches the value of the Identified annotation of the parameter.
         */
        @Override
        public Predicate<Object> apply(ParameterContext t) {
            return t.findAnnotation(Identified.class)
                .<Predicate<Object>> map(id -> obj -> IdentifierPackage.Literals.IDENTIFIER.isInstance(obj)
                        && ((EObject) obj).eGet(IdentifierPackage.Literals.IDENTIFIER__ID)
                            .equals(id.value()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "The required annotation was not present. This should not occur."));
        }
    };

    String value();
}