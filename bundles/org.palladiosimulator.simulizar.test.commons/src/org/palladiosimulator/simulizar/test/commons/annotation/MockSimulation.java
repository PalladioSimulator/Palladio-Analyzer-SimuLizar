package org.palladiosimulator.simulizar.test.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.simulizar.test.commons.extension.LoadModelFromSupplierExtension;
import org.palladiosimulator.simulizar.test.commons.extension.MockSimulationExtension;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestParameterProvider;

/**
 * Initiates initialization of SimuLizar simulation infrastructure mocks for testing.
 * 
 * @see MockSimulationExtension
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith({ LoadModelFromSupplierExtension.class, MockSimulationExtension.class, SimuLizarTestParameterProvider.class })
public @interface MockSimulation {
    boolean initializeRegistry() default false;
}