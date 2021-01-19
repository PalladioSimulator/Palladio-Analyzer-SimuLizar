package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.ParentContext;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;

import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = { SimuLizarRuntimeComponent.class, SimuComFrameworkComponent.class })
@SimulatedThreadScope
public interface SimulatedThreadComponent {
    @SimulatedThreadScope
    InterpreterDefaultContext localContext();

    @SimulatedThreadScope
    @ParentContext
    InterpreterDefaultContext parentContext();
    
    @Component.Factory
    public interface Factory {
        SimulatedThreadComponent create(@BindsInstance @ParentContext InterpreterDefaultContext parentContext, SimuLizarRuntimeComponent runtimeComponent,
                SimuComFrameworkComponent simucomComponent);
    }

}
