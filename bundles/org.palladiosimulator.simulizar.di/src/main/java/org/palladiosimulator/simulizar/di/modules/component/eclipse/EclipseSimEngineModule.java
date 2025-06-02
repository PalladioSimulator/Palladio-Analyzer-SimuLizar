package org.palladiosimulator.simulizar.di.modules.component.eclipse;

import org.palladiosimulator.simulizar.core.runtimestate.SimulationPreferencesSimEngineFactoryProvider;
import org.palladiosimulator.simulizar.di.base.scopes.AnalysisDependencyScope;
import org.palladiosimulator.simulizar.di.modules.component.core.SimEngineModule;

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
