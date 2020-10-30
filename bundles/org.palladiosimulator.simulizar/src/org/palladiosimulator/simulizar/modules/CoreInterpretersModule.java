package org.palladiosimulator.simulizar.modules;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;

import dagger.Module;
import dagger.Provides;

@Module
public interface CoreInterpretersModule {
    
    @Provides @Singleton @MainContext static InterpreterDefaultContext provideMainContext(InterpreterDefaultContext impl) {
        return impl;
    }

}
