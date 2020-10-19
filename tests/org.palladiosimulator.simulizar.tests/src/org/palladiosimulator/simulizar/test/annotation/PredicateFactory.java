package org.palladiosimulator.simulizar.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.extension.ParameterContext;
import org.palladiosimulator.simulizar.test.SimuLizarTestExtension;

/**
 * This meta annotation allows to extend the set of model entity selecting annotations for
 * {@link SimuLizarTestExtension}. For each annotation it provides the Factory class which is used
 * to create a filtering predicate based on the JUnit parameter context.
 * 
 * @see Identified
 * @see Named
 * 
 * The factory class needs to be provide a default constructor. 
 * 
 * @author Sebastian Krach
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface PredicateFactory {
    Class<? extends Function<ParameterContext, Predicate<Object>>> value();
}
