package org.palladiosimulator.simulizar.di.modules.scoped.root;

import javax.inject.Provider;

import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.di.base.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.core.SimuComConfig;

@Module
public interface DependencyComponentsModule {

    @AnalysisRootScope
    @Provides
    static QUALComponent provideQualComponent(SimuLizarWorkflowConfiguration config, QUALComponent.Factory factory) {
        return factory.create(config);
    }
    
    @AnalysisRootScope
    @Provides
    static SimEngineComponent provideSimEngineComponent(SimEngineComponent.Factory factory) {
        return factory.create();
    }
    
    @AnalysisRootScope
    @Provides
    static SimuComFrameworkComponent provideSimuComFrameworkComponent(SimuComFrameworkComponent.Factory factory, SimuComConfig simuComConfig, Provider<QUALComponent> qualComponent, Provider<SimEngineComponent> simEngineComponent) {
        return factory.create(simuComConfig, simEngineComponent.get(), qualComponent.get());
    }
    
}
