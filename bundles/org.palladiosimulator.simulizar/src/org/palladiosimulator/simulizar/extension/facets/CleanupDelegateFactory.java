package org.palladiosimulator.simulizar.extension.facets;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

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
