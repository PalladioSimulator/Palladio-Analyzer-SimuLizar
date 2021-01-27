package org.palladiosimulator.simulizar.di.component.eclipse;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.modules.component.eclipse.EclipseSimuLizarRootModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.ComponentFactoriesModule;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;

import dagger.Component;

@Component(modules = { EclipseSimuLizarRootModule.class })
@AnalysisRootScope
public interface EclipseSimuLizarRootComponent extends SimuLizarRootComponent {
    
    @Component.Factory
    public static interface Factory extends SimuLizarRootComponent.Factory {
        @Override
        default ComponentFactoriesModule defaultComponentFactoriesModule() {
            return new ComponentFactoriesModule() {
                @Override
                public org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent.Factory providesRuntimeComponentFactory() {
                    return DaggerEclipseSimuLizarRuntimeComponent.factory();
                }
            };
        }
    }

}
