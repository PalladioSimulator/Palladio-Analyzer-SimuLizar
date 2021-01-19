package org.palladiosimulator.simulizar.modules.component.standalone;

import org.palladiosimulator.simulizar.modules.component.core.SimEngineModule;
import org.palladiosimulator.simulizar.scopes.AnalysisDependencyScope;

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