package org.palladiosimulator.simulizar.di.extension.sample;

import javax.inject.Named;

import org.palladiosimulator.simulizar.di.base.extension.ExtensionComponent;

import dagger.Component;
import dagger.Provides;

@Component(modules = ComponentA2.Module.class)
public interface ComponentA2 extends ComponentA {
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ComponentA2 create();
    }
    
    @dagger.Module
    public static interface Module {
        @Named("name") 
        @Provides
        static String provideName() {
            return "A2";
        }
    }

}
