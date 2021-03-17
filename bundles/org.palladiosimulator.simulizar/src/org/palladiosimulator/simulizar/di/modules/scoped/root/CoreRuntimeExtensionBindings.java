package org.palladiosimulator.simulizar.di.modules.scoped.root;

import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulationRootExtensions;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.impl.BasicInterpreterResultMerger;
import org.palladiosimulator.simulizar.launcher.jobs.ModelCompletionJobContributor;
import org.palladiosimulator.simulizar.launcher.jobs.extensions.DefaultMonitorRepositoryCompletionContributor;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module(includes = { SimulationRootExtensions.class })
public interface CoreRuntimeExtensionBindings {

    @Binds
    @IntoSet
    @AnalysisRootScope
    ModelCompletionJobContributor bindDefaultMonitorRepositoryCompletion(DefaultMonitorRepositoryCompletionContributor impl);
    
    @Binds
    @AnalysisRootScope
    InterpreterResultMerger bindResultMerger(BasicInterpreterResultMerger impl);
}
