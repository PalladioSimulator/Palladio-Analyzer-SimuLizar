package org.palladiosimulator.simulizar.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;

@Module
public interface SchedulerModule {
    
    @Provides @Singleton static IResourceTableManager provideResourceTableManager() {
        return new ResourceTableManager();
    }

}
