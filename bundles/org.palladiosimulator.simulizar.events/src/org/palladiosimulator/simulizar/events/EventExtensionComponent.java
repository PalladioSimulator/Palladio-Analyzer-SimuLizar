package org.palladiosimulator.simulizar.events;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.core.scopes.RootExtensionScope;

import dagger.Component;

@Component(dependencies = SimuLizarRootComponent.class, modules = EventsTransformationConfigurationModule.class)
@RootExtensionScope
public interface EventExtensionComponent extends ExtensionComponent {
    
    EventsTransformationWorkflowExtensionJob completionJob();
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        EventExtensionComponent create(SimuLizarRootComponent rootComponent);
    }
    
    public static class EclipseFactory implements IExecutableExtensionFactory {
        @Override
        public Object create() throws CoreException {
            return DaggerEventExtensionComponent.factory();
        }    
    }
}
