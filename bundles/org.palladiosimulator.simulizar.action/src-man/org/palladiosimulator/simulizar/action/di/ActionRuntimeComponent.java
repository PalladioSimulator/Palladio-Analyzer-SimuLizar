package org.palladiosimulator.simulizar.action.di;

import java.util.Set;

import org.palladiosimulator.simulizar.action.interpreter.util.TransientEffectTransformationCacheKeeper;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.extension.Extension;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionContribution;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;

import com.google.common.collect.ImmutableSet;

import dagger.Component;

@Component(dependencies = SimuLizarRuntimeComponent.class)
public interface ActionRuntimeComponent extends ExtensionComponent<ActionRuntimeComponent> {
    
    TransientEffectTransformationCacheKeeper cacheKeeper();
    
    @Override
    default Set<ExtensionContribution<? extends Extension, ActionRuntimeComponent>> contributions() {
        return ImmutableSet.of(new ExtensionContribution<>(IModelObserver.class, ActionRuntimeComponent::cacheKeeper));
    }
    

}
