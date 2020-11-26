package org.palladiosimulator.simulizar.middlewarecompletions;

import org.palladiosimulator.simulizar.SimuLizarSimulationComponent;
import org.palladiosimulator.simulizar.extension.InterpreterSwitchExtensionRegistry;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.scopes.ExtensionScope;

import dagger.Component;

@Component(dependencies = SimuLizarSimulationComponent.class)
@ExtensionScope
public abstract class CompletionsRDSeffSwitchExtension implements InterpreterExtension {
    
    abstract InterpreterSwitchExtensionRegistry extensionRegistry();
    abstract CompletionsRDSeffSwitchFactory switchFactory();
    
    @Override
    public void preInitialize() {
        extensionRegistry().registerRDSeffSwitchFactory(switchFactory());
    }
    
    @Component.Factory
    interface Factory extends InterpreterExtension.Factory { }
    
}
