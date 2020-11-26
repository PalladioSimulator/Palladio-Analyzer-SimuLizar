package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.runtimestate.SimulationCancelationDelegate;

import dagger.Module;
import dagger.Provides;

@Module
public class SimulationCancelationModule {
    private final SimulationCancelationDelegate delegate;
    
    public SimulationCancelationModule() {
        delegate = () -> false;
    }
    
    public SimulationCancelationModule(SimulationCancelationDelegate delegate) {
        this.delegate = delegate;
    }
    
    @Provides
    SimulationCancelationDelegate provideSimulationDelegate() {
        return delegate;
    }
}
