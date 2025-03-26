package org.palladiosimulator.simulizar.di.extension.sample;

import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;

import dagger.Component;

@Component(dependencies = ComponentA.class)
public interface ComponentB extends ExtensionComponent{
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ComponentB create(ComponentA compA);
    }

}
