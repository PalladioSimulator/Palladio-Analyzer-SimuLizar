package org.palladiosimulator.simulizar.di.component.dependency;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.recorderframework.core.config.IRecorderConfigurationFactory;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.di.core.scopes.AnalysisDependencyScope;
import org.palladiosimulator.simulizar.di.modules.component.eclipse.EclipseQUALModule;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = { EclipseQUALModule.class })
@AnalysisDependencyScope
public interface QUALComponent {

    IGenericCalculatorFactory calculatorFactory();
    
    IObservableCalculatorRegistry calculatorRegistry();
    
    ProbeFrameworkContext probeFrameworkContext();
    
    IRecorderConfigurationFactory recorderConfigurationFactory();

    @Component.Factory
    public interface Factory {
        QUALComponent create(@BindsInstance SimuLizarWorkflowConfiguration simConfig);
    }

}
