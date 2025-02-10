package org.palladiosimulator.simulizar.di.extension;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.inject.Qualifier;

@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, PARAMETER })
@Qualifier
public @interface RegisteredComponent {

}
