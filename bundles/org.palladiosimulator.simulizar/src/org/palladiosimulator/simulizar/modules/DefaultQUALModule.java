package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * This module bind the default implementations for interfaces to the frameworks of the Quality
 * Analysis Lab (QUAL).
 */
@Module
public interface DefaultQUALModule {

    @Binds
    @IntoSet
    IInterpreterListener bindProbeFrameworkListener(ProbeFrameworkListener impl);

}
