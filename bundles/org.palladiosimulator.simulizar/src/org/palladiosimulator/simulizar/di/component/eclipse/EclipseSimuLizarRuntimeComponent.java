package org.palladiosimulator.simulizar.di.component.eclipse;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.core.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.di.modules.component.eclipse.EclipseSimulizarRuntimeModule;

import dagger.Component;

@Component(dependencies = { SimuLizarRootComponent.class, SimuComFrameworkComponent.class, QUALComponent.class,
        SimEngineComponent.class }, modules = { EclipseSimulizarRuntimeModule.class })
@SimulationRuntimeScope
public interface EclipseSimuLizarRuntimeComponent extends SimuLizarRuntimeComponent {
    
    @Component.Factory
    public static interface Factory extends SimuLizarRuntimeComponent.Factory {
    }

}
