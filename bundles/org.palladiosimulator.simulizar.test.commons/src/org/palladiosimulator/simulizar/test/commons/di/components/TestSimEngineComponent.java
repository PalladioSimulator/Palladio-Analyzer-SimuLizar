package org.palladiosimulator.simulizar.test.commons.di.components;

import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimEngineModule;
import org.palladiosimulator.simulizar.di.scopes.AnalysisDependencyScope;

import dagger.Component;
import dagger.Provides;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.desmoj.DesmoJSimEngineFactory;

@Component(modules = TestSimEngineComponent.Module.class)
@AnalysisDependencyScope
public interface TestSimEngineComponent extends SimEngineComponent {
    @Component.Factory
    public static interface Factory extends SimEngineComponent.Factory {
    }
    
    @dagger.Module(includes = { SimEngineModule.class })
    public static interface Module {
        @Provides
        @AnalysisDependencyScope
        static ISimEngineFactory provideSimEngineFactory() {
            return new DesmoJSimEngineFactory();
        }    
    }
}
