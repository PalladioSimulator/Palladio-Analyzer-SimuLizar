package org.palladiosimulator.simulizar.di.modules.stateless.configuration;

import java.util.Optional;

import jakarta.inject.Named;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simulation.AbstractSimulationConfig;

@Module
public interface SimulationConfigBindingModule {
    public static final String MAX_SIMULATION_TIME = "maxSimTime";
    public static final String RECORDERFRAMEWORK_RECORDERNAME = "recorderframework.recordername";

    @Provides
    @Named(MAX_SIMULATION_TIME)
    static Optional<Double> provideMaxSimTime(AbstractSimulationConfig configuration) {
        return configuration.getSimuTime() > 0 ? Optional.of(configuration.getSimuTime() * 1.0) : Optional.empty();
    }

    @Provides
    @Named(RECORDERFRAMEWORK_RECORDERNAME)
    static String provideRecorderName(AbstractSimulationConfig configuration) {
        return configuration.getRecorderName();
    }

}
