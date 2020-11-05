package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.runtimestate.SimulationPreferencesSimEngineFactoryProvider;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;

/**
 * This dagger module binds implementations which determine simulation behavior based on preferences
 * configured within Eclipse IDE.
 */
@Module
public interface EclipseIDEPreferencesModule {

    @Provides
    static ISimEngineFactory provideSimEngineFactory(SimulationPreferencesSimEngineFactoryProvider impl) {
        return impl.get();
    }

}
