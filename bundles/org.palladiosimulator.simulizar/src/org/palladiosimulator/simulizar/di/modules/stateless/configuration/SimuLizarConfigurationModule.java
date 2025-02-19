 package org.palladiosimulator.simulizar.di.modules.stateless.configuration;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simulation.core.AbstractSimulationConfig;

@Module(includes = SimulationConfigBindingModule.class)
public interface SimuLizarConfigurationModule {
    
    @Provides
    static AbstractSimulationConfig provideSimConfig(SimuLizarWorkflowConfiguration simulizarConfig) {
        return simulizarConfig.getSimulationConfiguration();
    }
    
    @Provides
    static SimuComConfig provideSimuComConfig(SimuLizarWorkflowConfiguration simulizarConfig) {
        return (SimuComConfig) simulizarConfig.getSimulationConfiguration();
    }

}
