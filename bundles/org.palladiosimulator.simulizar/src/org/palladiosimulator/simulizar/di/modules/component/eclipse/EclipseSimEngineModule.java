package org.palladiosimulator.simulizar.di.modules.component.eclipse;

import org.palladiosimulator.simulizar.di.modules.component.core.SimEngineModule;
import org.palladiosimulator.simulizar.runtimestate.SimulationPreferencesSimEngineFactoryProvider;
import org.palladiosimulator.simulizar.scopes.AnalysisDependencyScope;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;

@Module(includes = { SimEngineModule.class })
public interface EclipseSimEngineModule {

    @Provides
    @AnalysisDependencyScope
    static ISimEngineFactory provideSimEngineFactory(SimulationPreferencesSimEngineFactoryProvider impl) {
        return impl.get();
    }
    
}
