package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.simulizar.test.commons.extension.RunSimulationJobExtension;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@ExtendWith(RunSimulationJobExtension.class)
public @interface RunSimulationJob {
    boolean skipSimulation() default false;
}
