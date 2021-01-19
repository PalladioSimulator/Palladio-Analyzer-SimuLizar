package org.palladiosimulator.simulizar.component.core;

import java.util.Set;

import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.modules.component.core.ModelObserverModule;
import org.palladiosimulator.simulizar.scopes.ObservationScope;

import dagger.Component;

@Component(dependencies = SimuLizarRuntimeComponent.class, modules = { ModelObserverModule.class })
@ObservationScope
public interface ModelObserverComponent {

    Set<IModelObserver> modelObservers();

    @Component.Factory
    public interface Factory {
        ModelObserverComponent create(SimuLizarRuntimeComponent runtimeComponent);
    }

}
