package org.palladiosimulator.simulizar.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;

/**
 * Uses the provided Supplier to retrieve the pcm instance for running the test. It needs to provide
 * a constructor either without parameters or accepting a JUnit extension context as parameter.
 * 
 * By default, a valid but empty pcm instance is provided.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface PCMInstanceFromSupplier {
    Class<? extends Supplier<PCMResourceSetPartition>> value();
}