package org.palladiosimulator.simulizar.modules.component.core;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.simulizar.modules.stateless.configuration.SimulationConfigBindingModule;
import org.palladiosimulator.simulizar.scopes.AnalysisDependencyScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = { SimulationConfigBindingModule.class })
public interface QUALModule {

    @Provides
    @AnalysisDependencyScope
    static ProbeFrameworkContext provideProbeframeworkContext(IGenericCalculatorFactory calcFactory) {
        return new ProbeFrameworkContext(calcFactory);
    }

    @Provides
    static IObservableCalculatorRegistry bindObservableCalculatorRegistry(ProbeFrameworkContext context) {
        return context.getCalculatorRegistry();
    }

}
