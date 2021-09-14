package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.usagemodel.IUserProcessCountMonitor;
import org.palladiosimulator.simulizar.usagemodel.SimulationPreferencesMaxUserProcessCounterProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.usage.IUserProcessMonitor;

@Module
public interface UserProcessCountMonitorModule {
    
    @Binds IUserProcessMonitor bindUserProcessMonitor(IUserProcessCountMonitor impl);
    
    
    @Provides
    @SimulationRuntimeScope
    static IUserProcessCountMonitor provideUserProcessMonitorCounter(SimulationPreferencesMaxUserProcessCounterProvider provider) {
        return provider.get();
    }
}
