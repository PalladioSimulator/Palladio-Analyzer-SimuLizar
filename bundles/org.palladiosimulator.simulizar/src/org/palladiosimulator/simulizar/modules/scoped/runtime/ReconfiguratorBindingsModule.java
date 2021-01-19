package org.palladiosimulator.simulizar.modules.scoped.runtime;

import org.palladiosimulator.simulizar.modules.component.extensions.ReconfigurationExtensions;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.NumberOfResourceContainerTrackingReconfiguratorFactory;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.ReconfiguratorFactory;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = ReconfigurationExtensions.class)
public interface ReconfiguratorBindingsModule {
    @Provides
    @SimulationRuntimeScope
    static Reconfigurator provideReconfigurator(ReconfiguratorFactory reconfiguratorFactory) {
        return reconfiguratorFactory.get();
    }
    
    @Binds
    ReconfiguratorFactory bindReconfiguratorFactory(NumberOfResourceContainerTrackingReconfiguratorFactory impl);
    
    @Provides
    static IReconfigurationListener bindReconfigurationListenerDispatcher(Reconfigurator reconfigurator) {
        return reconfigurator.getEventDispatcher();
    }
}
