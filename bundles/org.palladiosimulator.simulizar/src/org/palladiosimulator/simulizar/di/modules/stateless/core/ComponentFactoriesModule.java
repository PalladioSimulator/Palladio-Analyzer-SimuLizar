package org.palladiosimulator.simulizar.di.modules.stateless.core;

import org.palladiosimulator.simulizar.di.component.core.DaggerSimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.DaggerQUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.DaggerSimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.DaggerSimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;

import dagger.Provides;

@dagger.Module
public class ComponentFactoriesModule {

    @Provides public SimuLizarRuntimeComponent.Factory providesRuntimeComponentFactory() {
        return DaggerSimuLizarRuntimeComponent.factory(); 
    }
    
    @Provides public QUALComponent.Factory providesQUALComponentFactory() {
        return DaggerQUALComponent.factory(); 
    }
    
    @Provides public SimEngineComponent.Factory providesSimEngineComponentFactory() {
        return DaggerSimEngineComponent.factory(); 
    }
    
    @Provides public SimuComFrameworkComponent.Factory providesSimuComFrameworkComponentFactory() {
        return DaggerSimuComFrameworkComponent.factory(); 
    }

}
