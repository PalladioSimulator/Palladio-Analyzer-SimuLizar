package org.palladiosimulator.simulizar.extension.facets;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.SimuLizarModelCompletionComponent;

public class ModelCompletionDelegateFactory implements ModelCompletion.Factory {
    private final Set<ModelCompletion.Factory> delegateFactories;

    @Inject
    public ModelCompletionDelegateFactory(Set<ModelCompletion.Factory> delegateFactories) {
        this.delegateFactories = delegateFactories;
    }

    @Override
    public ModelCompletion create(SimuLizarModelCompletionComponent component) {
        final var delegates = delegateFactories.stream()
            .map(fact -> fact.create(component))
            .collect(Collectors.toSet());
        return new ModelCompletion() {
            @Override
            public boolean runModelCompletion() {
                // We use count here, to prevent a short-cirquit operation, as we want all
                // delegates to be executed
                return delegates.stream()
                    .map(ModelCompletion::runModelCompletion)
                    .filter(Boolean.TRUE::equals)
                    .count() > 0;
            }
        };
    }
}
