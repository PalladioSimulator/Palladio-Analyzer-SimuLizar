package org.palladiosimulator.simulizar.di.modules.stateless.core;

import org.palladiosimulator.simulizar.interpreter.ForkedBehaviorProcessFactory;
import org.palladiosimulator.simulizar.interpreter.impl.ForkedBehaviorProcessFactoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface SimulatedThreadBindingsModule {
    @Binds ForkedBehaviorProcessFactory bindForkedBehaviorProcessFactory(ForkedBehaviorProcessFactoryImpl impl);
}
