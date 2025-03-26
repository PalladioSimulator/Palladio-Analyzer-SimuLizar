package org.palladiosimulator.simulizar.di.modules.component.core;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.simulizar.di.core.scopes.AnalysisDependencyScope;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimulationConfigBindingModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = { SimulationConfigBindingModule.class, SimuLizarConfigurationModule.class })
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
