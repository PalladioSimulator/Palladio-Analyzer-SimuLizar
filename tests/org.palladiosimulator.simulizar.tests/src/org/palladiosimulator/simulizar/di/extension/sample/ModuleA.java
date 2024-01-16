package org.palladiosimulator.simulizar.di.extension.sample;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public interface ModuleA {
    
    @Named("name") 
    @Provides
    static String provideName() {
        return "A";
    }

}
