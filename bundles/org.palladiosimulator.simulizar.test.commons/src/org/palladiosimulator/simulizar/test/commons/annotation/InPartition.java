package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.palladiosimulator.analyzer.workflow.ConstantsContainer;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface InPartition {
    
    String value() default ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID;

}
