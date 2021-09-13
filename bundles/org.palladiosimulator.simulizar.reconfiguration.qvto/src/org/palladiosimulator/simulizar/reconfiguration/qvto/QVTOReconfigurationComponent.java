package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

import dagger.Component;

@Component(dependencies = { SimuLizarRuntimeComponent.class, SimuLizarRootComponent.class })
@RuntimeExtensionScope
public interface QVTOReconfigurationComponent extends ExtensionComponent {
    
    QVTOReconfigurator reconfigurator();
    
    QVTOReconfigurationLoader loader();
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        QVTOReconfigurationComponent create(SimuLizarRootComponent rootComponent, SimuLizarRuntimeComponent runtimeComponent);
    }
    
    public static class EclipseFactory implements IExecutableExtensionFactory {
        @Override
        public Object create() throws CoreException {
            return DaggerQVTOReconfigurationComponent.factory();
        }    
    }
}
