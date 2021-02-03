package org.palladiosimulator.simulizar.di.modules.stateless.core;

import org.palladiosimulator.simulizar.di.component.core.DaggerSimuLizarSimulatedThreadComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarSimulatedThreadComponent;

import dagger.Provides;

@dagger.Module
public class RuntimeComponentFactoriesModule {

    @Provides public SimuLizarSimulatedThreadComponent.Factory providesSimulatedThreadComponent() {
        return DaggerSimuLizarSimulatedThreadComponent.factory(); 
    }

}
