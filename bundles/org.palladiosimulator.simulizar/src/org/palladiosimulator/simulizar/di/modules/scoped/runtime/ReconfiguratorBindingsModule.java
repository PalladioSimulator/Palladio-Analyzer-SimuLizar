package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import org.palladiosimulator.simulizar.di.modules.component.extensions.ReconfigurationExtensions;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module(includes = ReconfigurationExtensions.class)
public interface ReconfiguratorBindingsModule {
    
    @Provides
    static IReconfigurationListener bindReconfigurationListenerDispatcher(Reconfigurator reconfigurator) {
        return reconfigurator.getEventDispatcher();
    }
    
    @Binds
    @IntoSet
    IModelObserver bindReconfigurator(Reconfigurator impl);
}
