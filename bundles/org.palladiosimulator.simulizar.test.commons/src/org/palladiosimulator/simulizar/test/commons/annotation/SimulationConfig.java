package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.palladiosimulator.simulizar.test.commons.util.SimuLizarWorkflowConfigurationProvider;

import de.uka.ipd.sdq.simulation.AbstractSimulationConfig;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@SimulationConfigSupplier(SimuLizarWorkflowConfigurationProvider.class)
public @interface SimulationConfig {
    boolean simulateLinkingResource() default false;
    boolean simulateLinkThroughput() default false;
    boolean simulateReliability() default false;
    String maxSimTime() default AbstractSimulationConfig.DEFAULT_SIMULATION_TIME;
    String maxMeasurements() default AbstractSimulationConfig.DEFAULT_MAXIMUM_MEASUREMENT_COUNT;
    SetConfigProperty[] value() default {};
}
