package org.palladiosimulator.simulizar.middlewarecompletions;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.extension.InterpreterSwitchExtensionRegistry;
import org.palladiosimulator.simulizar.extension.SimuLizarExtension;

import dagger.Lazy;

public class MiddlewareCompletionsExtension implements SimuLizarExtension {

    private final InterpreterSwitchExtensionRegistry switchRegistry;
    private final Lazy<CompletionsRDSeffSwitchFactory> rdseffSwitchFactory;

    @Inject
    public MiddlewareCompletionsExtension(InterpreterSwitchExtensionRegistry switchRegistry,
            Lazy<CompletionsRDSeffSwitchFactory> rdseffSwitchFactory) {
        this.switchRegistry = switchRegistry;
        this.rdseffSwitchFactory = rdseffSwitchFactory;
    }

    @Override
    public void preInitialize() {
        switchRegistry.registerRDSeffSwitchFactory(rdseffSwitchFactory.get());
    }

}
