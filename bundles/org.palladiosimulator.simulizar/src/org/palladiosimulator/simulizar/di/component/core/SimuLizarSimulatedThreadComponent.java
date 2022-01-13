package org.palladiosimulator.simulizar.di.component.core;

import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.SimulatedThreadComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimulatedThreadModule;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.ParentContext;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;

import dagger.BindsInstance;
import dagger.Component;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

@Component(modules = { SimulatedThreadModule.class }, dependencies = {SimuLizarRuntimeComponent.class, 
        SimuComFrameworkComponent.class, SimuLizarRootComponent.class})
@SimulatedThreadScope
public interface SimuLizarSimulatedThreadComponent extends SimulatedThreadComponent {
    
    @Component.Factory
    interface Factory {
        SimuLizarSimulatedThreadComponent create(@BindsInstance @ParentContext InterpreterDefaultContext parentContext,
                @BindsInstance SimuComSimProcess simProcess, SimuLizarRuntimeComponent runtimeComponent, SimuComFrameworkComponent frameworkComponent, SimuLizarRootComponent rootComponent);
    }

}
