package org.palladiosimulator.simulizar.di.modules.component.extensions;

import java.util.Set;

import org.palladiosimulator.simulizar.launcher.jobs.ModelCompletionJobContributor;
import org.palladiosimulator.simulizar.launcher.jobs.ModelContribution;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public interface SimulationRootExtensions {
    
    @Multibinds
    Set<ModelContribution> bindModelContributions();
    
    @Multibinds
    Set<ModelCompletionJobContributor> bindModelCompletionJobContributer();

}
