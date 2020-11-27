package org.palladiosimulator.simulizar.modules.core;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.ExtensibleCalculatorFactoryDelegatingFactory;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.scopes.SimulationScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.calculator.RecorderAttachingCalculatorFactoryDecorator;

/**
 * This module bind the default implementations for interfaces to the frameworks of the Quality
 * Analysis Lab (QUAL).
 */
@Module
public interface DefaultQUALModule {

    @Provides
    @SimulationScope
    static ProbeFrameworkContext provideProbeFrameworkContext(SimuComConfig config) {
        return new ProbeFrameworkContext(
                new RecorderAttachingCalculatorFactoryDecorator(
                new ExtensibleCalculatorFactoryDelegatingFactory(), config));
    }
    
    @Provides
    @SimulationScope
    static IObservableCalculatorRegistry provideObservableCalculatorRegistry(ProbeFrameworkContext context) {
        return context.getCalculatorRegistry();
    }
    
    @Binds
    @IntoSet
    IInterpreterListener bindProbeFrameworkListener(ProbeFrameworkListener impl);
}
