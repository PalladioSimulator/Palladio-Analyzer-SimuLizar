package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.palladiosimulator.simulizar.test.commons.util.RunSimuLizarSimulationJobSupplier;

@Retention(RUNTIME)
@Target(METHOD)
@SimuLizarTest
@SimulationConfig
@SimulationJobSupplier(RunSimuLizarSimulationJobSupplier.class)
@RunSimulationJob
public @interface RunSimuLizar {

}
