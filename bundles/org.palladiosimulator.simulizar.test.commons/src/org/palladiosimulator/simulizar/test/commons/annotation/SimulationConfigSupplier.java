package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Supplier;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
public @interface SimulationConfigSupplier {
    
    Class<? extends Supplier<SimuLizarWorkflowConfiguration>> value();

}
