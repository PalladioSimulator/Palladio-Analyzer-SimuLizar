package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.recorderframework.config.IRecorderConfigurationFactory;
import org.palladiosimulator.simulizar.modules.component.eclipse.EclipseQUALModule;
import org.palladiosimulator.simulizar.scopes.AnalysisDependencyScope;

import dagger.BindsInstance;
import dagger.Component;
import de.uka.ipd.sdq.simulation.AbstractSimulationConfig;

@Component(modules = { EclipseQUALModule.class })
@AnalysisDependencyScope
public interface QUALComponent {

    IObservableCalculatorRegistry calculatorRegistry();
    
    ProbeFrameworkContext probeFrameworkContext();
    
    IRecorderConfigurationFactory recorderConfigurationFactory();

    @Component.Factory
    public interface Factory {
        QUALComponent create(@BindsInstance AbstractSimulationConfig simConfig);
    }

}
