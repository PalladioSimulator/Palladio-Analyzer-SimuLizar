package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.palladiosimulator.simulizar.test.commons.util.SimuLizarWorkflowConfigurationProvider;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@SimulationConfigSupplier(SimuLizarWorkflowConfigurationProvider.class)
public @interface SetConfigProperties {
    SetConfigProperty[] value() default {};
}
