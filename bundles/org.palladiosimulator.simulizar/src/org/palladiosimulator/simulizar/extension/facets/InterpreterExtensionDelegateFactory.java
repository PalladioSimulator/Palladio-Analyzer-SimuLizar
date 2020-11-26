package org.palladiosimulator.simulizar.extension.facets;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.SimuLizarSimulationComponent;

public class InterpreterExtensionDelegateFactory implements InterpreterExtension.Factory {
    private final Set<InterpreterExtension.Factory> delegateFactories;

    @Inject
    public InterpreterExtensionDelegateFactory(Set<InterpreterExtension.Factory> delegateFactories) {
        this.delegateFactories = delegateFactories;
    }

    @Override
    public InterpreterExtension create(SimuLizarSimulationComponent component) {
        final var delegates = delegateFactories.stream()
            .map(fact -> fact.create(component))
            .collect(Collectors.toSet());
        return new InterpreterExtension() {
            public void preInitialize() {
                delegates.forEach(InterpreterExtension::preInitialize);
            };
            
            public void initialized() {
                delegates.forEach(InterpreterExtension::initialized);
            };
            public void completed() {
                delegates.forEach(InterpreterExtension::completed);
            };
        };
    }
}
