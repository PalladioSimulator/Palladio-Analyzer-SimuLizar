package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.ParentContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterFacade;
import org.palladiosimulator.simulizar.modules.component.core.SimulatedThreadModule;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;

import dagger.BindsInstance;
import dagger.Subcomponent;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

@Subcomponent(modules = { SimulatedThreadModule.class })
@SimulatedThreadScope
public interface SimulatedThreadComponent {
    InterpreterFacade interpreterFacade();

    @Subcomponent.Factory
    interface Factory {
        SimulatedThreadComponent create(@BindsInstance @ParentContext InterpreterDefaultContext parentContext,
                @BindsInstance SimuComSimProcess simProcess);
    }

}
