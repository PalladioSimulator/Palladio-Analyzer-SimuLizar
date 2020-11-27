package org.palladiosimulator.simulizar.extension.facets.internal;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.SimuLizarModelLoadComponent;
import org.palladiosimulator.simulizar.extension.facets.ModelLoad;
import org.palladiosimulator.simulizar.extension.facets.ModelLoad.Factory;

import de.uka.ipd.sdq.workflow.jobs.IJob;

public class ModelLoadDelegateFactory implements ModelLoad.Factory {
    private final Set<ModelLoad.Factory> delegateFactories;

    @Inject
    public ModelLoadDelegateFactory(Set<ModelLoad.Factory> delegateFactories) {
        this.delegateFactories = delegateFactories;
    }

    @Override
    public ModelLoad create(SimuLizarModelLoadComponent component) {
        final var delegates = delegateFactories.stream()
            .map(fact -> fact.create(component))
            .collect(Collectors.toSet());
        return new ModelLoad() {
            @Override
            public void appendModelLoadJobs(Consumer<IJob> modelLoadJob) {
                delegates.forEach(ml -> ml.appendModelLoadJobs(modelLoadJob));
            }
        };
    }
}
