package org.palladiosimulator.simulizar.di.extension.sample;

import javax.inject.Named;

import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;

import dagger.Component;

@Component(modules = ModuleA.class)
public interface ComponentA extends ExtensionComponent {

    @Named("name") String name();
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ComponentA create();
    }

}
