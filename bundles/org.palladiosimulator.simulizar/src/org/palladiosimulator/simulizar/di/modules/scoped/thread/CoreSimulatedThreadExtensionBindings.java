package org.palladiosimulator.simulizar.di.modules.scoped.thread;

import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulatedThreadExtensions;
import org.palladiosimulator.simulizar.stack.StackFramePrePostProcessor;
import org.palladiosimulator.simulizar.stack.processors.ImplicitParameterPostProcessor;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module( includes = SimulatedThreadExtensions.class )
public interface CoreSimulatedThreadExtensionBindings {
    
    @Binds
    @IntoSet
    StackFramePrePostProcessor bindImplicittParameterPreprocessor(ImplicitParameterPostProcessor impl);

}
