package org.palladiosimulator.simulizar.test.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.test.commons.extension.LoadModelFromSupplierExtension;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestParameterProvider;

/**
 * Uses the provided Supplier to retrieve the pcm instance for running the test. It needs to provide
 * a constructor either without parameters or accepting a JUnit extension context as parameter.
 * 
 * By default, a valid but empty pcm instance is provided.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@ExtendWith({ LoadModelFromSupplierExtension.class, SimuLizarTestParameterProvider.class })
public @interface PCMInstanceFromSupplier {
    Class<? extends Supplier<PCMResourceSetPartition>> value();
}