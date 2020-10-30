package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.runtimestate.SimulationPreferencesSimEngineFactoryProvider;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;

@Module
public interface EclipseIDEPreferencesModule {
    
    @Provides
    static ISimEngineFactory provideSimEngineFactory(SimulationPreferencesSimEngineFactoryProvider impl) {
        return impl.get();
    }
    
}
