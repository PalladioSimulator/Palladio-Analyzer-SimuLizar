package org.palladiosimulator.simulizar.modules.component.core;

import javax.inject.Provider;

import org.palladiosimulator.simulizar.component.core.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.component.core.DaggerQUALComponent;
import org.palladiosimulator.simulizar.component.core.DaggerSimEngineComponent;
import org.palladiosimulator.simulizar.component.core.DaggerSimuComFrameworkComponent;
import org.palladiosimulator.simulizar.component.core.DaggerSimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.component.core.QUALComponent;
import org.palladiosimulator.simulizar.component.core.SimEngineComponent;
import org.palladiosimulator.simulizar.component.core.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.launcher.jobs.PCMInterpreterRootCompositeJob;
import org.palladiosimulator.simulizar.modules.shared.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.modules.shared.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;

import dagger.Binds;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simulation.AbstractSimulationConfig;
import de.uka.ipd.sdq.workflow.jobs.IJob;

@dagger.Module(includes = {
        MDSDBlackboardProvidingModule.class,
        SimuLizarConfigurationModule.class
})
public interface SimuLizarRootModule {
    
    @Binds IJob bindRootJob(PCMInterpreterRootCompositeJob job);
    
    @Provides static SimuLizarRuntimeComponent.Factory providesRuntimeComponentFactory() {
        return DaggerSimuLizarRuntimeComponent.factory(); 
    }
    
    @Provides static QUALComponent.Factory providesQUALComponentFactory() {
        return DaggerQUALComponent.factory(); 
    }
    
    @Provides static SimEngineComponent.Factory providesSimEngineComponentFactory() {
        return DaggerSimEngineComponent.factory(); 
    }
    
    @Provides static SimuComFrameworkComponent.Factory providesSimuComFrameworkComponentFactory() {
        return DaggerSimuComFrameworkComponent.factory(); 
    }
    
    @AnalysisRootScope
    @Provides
    static QUALComponent provideQualComponent(AbstractSimulationConfig config, QUALComponent.Factory factory) {
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
    
    @Provides static AnalysisRuntimeComponent.Factory providesAnalysisRuntimeComponentFactory(SimuLizarRootComponent rootComponent, SimuLizarRuntimeComponent.Factory runtimeFactory, Provider<QUALComponent> qualComponent, Provider<SimuComFrameworkComponent> simucomComponent, 
            Provider<SimEngineComponent> simEngineComponent) {
        return () -> {
            return runtimeFactory.create(rootComponent, simucomComponent.get(), qualComponent.get(),
                    simEngineComponent.get());
        };
    }

}
