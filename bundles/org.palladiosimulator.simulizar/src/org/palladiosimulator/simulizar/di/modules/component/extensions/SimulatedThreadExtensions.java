package org.palladiosimulator.simulizar.di.modules.component.extensions;

import java.util.Set;

import org.palladiosimulator.simulizar.stack.StackFramePrePostProcessor;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public interface SimulatedThreadExtensions {
    
    @Multibinds
    Set<StackFramePrePostProcessor> stackFramePrePostProcessors();
}
