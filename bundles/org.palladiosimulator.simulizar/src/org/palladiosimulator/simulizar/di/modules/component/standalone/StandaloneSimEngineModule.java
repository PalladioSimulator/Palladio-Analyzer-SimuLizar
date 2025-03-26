package org.palladiosimulator.simulizar.di.modules.component.standalone;

import org.palladiosimulator.simulizar.di.core.scopes.AnalysisDependencyScope;
import org.palladiosimulator.simulizar.di.modules.component.core.SimEngineModule;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;

@Module(includes = { SimEngineModule.class })
public class StandaloneSimEngineModule {
    
    private final ISimEngineFactory simEngineFactory;
    
    public StandaloneSimEngineModule (ISimEngineFactory simEngineFactory) {
        this.simEngineFactory = simEngineFactory;    
    }

    @Provides
    @AnalysisDependencyScope
    public ISimEngineFactory provideSimEngineFactory() {
        return simEngineFactory;
    }
    
}