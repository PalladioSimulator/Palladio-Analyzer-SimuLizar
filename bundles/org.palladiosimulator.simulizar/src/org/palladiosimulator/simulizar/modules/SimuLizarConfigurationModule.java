package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Module;
import dagger.Provides;

@Module
public class SimuLizarConfigurationModule {
    
    private final SimuLizarWorkflowConfiguration configuration;

    public SimuLizarConfigurationModule(SimuLizarWorkflowConfiguration configuration) {
        this.configuration = configuration;
    }
    
    @Provides
    SimuLizarWorkflowConfiguration provideConfiguration() {
        return configuration;
    }

}
