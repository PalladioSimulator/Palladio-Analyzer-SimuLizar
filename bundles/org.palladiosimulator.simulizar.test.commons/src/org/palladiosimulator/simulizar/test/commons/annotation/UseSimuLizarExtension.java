package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
@Repeatable(SimuLizarExtensions.class)
public @interface UseSimuLizarExtension {
    /**
     * The Dagger generated component class, which provides the extension through a static
     * {@code factory()} method. Dagger generates a holder class that does not implement the
     * component interface itself, so this cannot be bound to {@code ExtensionComponent}; the
     * factory method is looked up reflectively instead.
     */
    Class<?> value();
}
