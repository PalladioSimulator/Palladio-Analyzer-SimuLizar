package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;

@Retention(RUNTIME)
@Target(METHOD)
@Repeatable(SimuLizarExtensions.class)
public @interface UseSimuLizarExtension {
    Class<? extends ExtensionComponent> value();
}
