package org.palladiosimulator.simulizar.di.modules.scoped.root;

import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulationRootExtensions;
import org.palladiosimulator.simulizar.di.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.launcher.jobs.ModelCompletionJobContributor;
import org.palladiosimulator.simulizar.launcher.jobs.extensions.DefaultMonitorRepositoryCompletionContributor;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module(includes = { SimulationRootExtensions.class })
public interface CoreRuntimeExtensionBindings {

    @Binds
    @IntoSet
    @AnalysisRootScope
    ModelCompletionJobContributor bindDefaultMonitorRepositoryCompletion(DefaultMonitorRepositoryCompletionContributor impl);

}
