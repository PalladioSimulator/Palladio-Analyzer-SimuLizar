package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface DebuggingModule {

    @Binds @IntoSet IInterpreterListener bindDebugListener(LogDebugListener impl);
}
