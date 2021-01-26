package org.palladiosimulator.simulizar.di.component.eclipse;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.modules.component.eclipse.EclipseSimuLizarRootModule;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;

import dagger.Component;

@Component(modules = EclipseSimuLizarRootModule.class)
@AnalysisRootScope
public interface EclipseSimuLizarRootComponent extends SimuLizarRootComponent {
    
    @Component.Factory
    public static interface Factory extends SimuLizarRootComponent.Factory {
    }

}
