package org.palladiosimulator.simulizar.action.di;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.action.interpreter.util.TransientEffectTransformationCacheKeeper;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

import dagger.Component;

@Component(dependencies = SimuLizarRuntimeComponent.class)
@RuntimeExtensionScope
public interface ActionRuntimeComponent extends ExtensionComponent {
    
    TransientEffectTransformationCacheKeeper cacheKeeper();
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ActionRuntimeComponent create(SimuLizarRuntimeComponent runtimeComponent);
    }

    public static class EclipseFactory implements IExecutableExtensionFactory {
        @Override
        public Object create() throws CoreException {
            return DaggerActionRuntimeComponent.factory();
        }
    }

}
