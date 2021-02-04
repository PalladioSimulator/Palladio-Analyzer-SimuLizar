package org.palladiosimulator.simulizar.action.di;

import org.palladiosimulator.simulizar.action.interpreter.util.TransientEffectTransformationCacheKeeper;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;

import dagger.Component;

@Component(dependencies = SimuLizarRuntimeComponent.class)
public interface ActionRuntimeComponent extends ExtensionComponent {
    
    TransientEffectTransformationCacheKeeper cacheKeeper();

}
