package org.palladiosimulator.simulizar.modules.component.core;

import javax.inject.Named;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.ExtensibleCalculatorFactoryDelegatingFactory;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.simulizar.modules.shared.configuration.SimulationConfigBindingModule;
import org.palladiosimulator.simulizar.scopes.AnalysisDependencyScope;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.calculator.RecorderAttachingCalculatorFactoryDecorator;

@Module(includes = { SimulationConfigBindingModule.class })
public interface QUALModule {

    @Provides
    @AnalysisDependencyScope
    static IGenericCalculatorFactory provideCalculatorFactory(@Named(SimulationConfigBindingModule.RECORDERFRAMEWORK_RECORDERNAME) String recorderName) {
        return new RecorderAttachingCalculatorFactoryDecorator(
                new ExtensibleCalculatorFactoryDelegatingFactory(), recorderName);
    }

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
