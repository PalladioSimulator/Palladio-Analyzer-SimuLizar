package org.palladiosimulator.simulizar.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.palladiosimulator.simulizar.test.SimuLizarTestExtension;

/**
 * Initiates initialization of SimuLizar simulation infrastructure mocks for testing.
 * 
 * @see SimuLizarTestExtension
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MockSimulation {
    boolean initializeRegistry() default false;
}