package org.palladiosimulator.simulizar.di.extension.sample;

import jakarta.inject.Named;

import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;

import dagger.Component;

@Component(dependencies = { ComponentA.class, ComponentB.class} )
public interface ComponentC extends ExtensionComponent {
    
    @Named("name") String name();
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ComponentC create(ComponentA a, ComponentB b);
    }

}
