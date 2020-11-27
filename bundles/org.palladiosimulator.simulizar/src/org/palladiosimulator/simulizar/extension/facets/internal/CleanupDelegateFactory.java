package org.palladiosimulator.simulizar.extension.facets.internal;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.extension.facets.Cleanup;
import org.palladiosimulator.simulizar.extension.facets.Cleanup.Factory;

public class CleanupDelegateFactory implements Cleanup.Factory {
    private final Set<Cleanup.Factory> delegateFactories;

    @Inject
    public CleanupDelegateFactory(Set<Cleanup.Factory> delegateFactories) {
        this.delegateFactories = delegateFactories;
    }

    @Override
    public Cleanup create() {
        final var delegates = delegateFactories.stream()
            .map(fact -> fact.create())
            .collect(Collectors.toSet());
        
        return new Cleanup() {
            @Override
            public void cleanup() {
                delegates.forEach(Cleanup::cleanup);
            }
        };
    }
}
