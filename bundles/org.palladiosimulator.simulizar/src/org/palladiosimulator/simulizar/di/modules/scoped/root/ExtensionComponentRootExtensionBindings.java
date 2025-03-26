package org.palladiosimulator.simulizar.di.modules.scoped.root;

import java.util.Set;

import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.core.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponentDependencyResolution;
import org.palladiosimulator.simulizar.di.extension.ExtensionLookup;
import org.palladiosimulator.simulizar.di.extension.GenericExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.extension.RootExtensions;
import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulationRootExtensions;
import org.palladiosimulator.simulizar.di.modules.stateless.extension.ExtensionSupportModule;
import org.palladiosimulator.simulizar.launcher.jobs.ModelCompletionJobContributor;
import org.palladiosimulator.simulizar.launcher.jobs.ModelContribution;
import org.palladiosimulator.simulizar.launcher.jobs.PartitionContribution;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = { ExtensionSupportModule.class, SimulationRootExtensions.class })
public interface ExtensionComponentRootExtensionBindings {
    
    @Provides
    @AnalysisRootScope
    static ExtensionComponentDependencyResolution provideDependencyResolution(@RegisteredComponent Set<Object> bootStrappingComponents,
            Set<ExtensionComponent.Factory> extensionComponentFactories) {
        return new ExtensionComponentDependencyResolution(bootStrappingComponents, extensionComponentFactories);
    }
    
    @Provides
    @RootExtensions
    @AnalysisRootScope
    static Set<ExtensionComponent> provideRootExtensions(Set<ExtensionComponent> extensions) {
        return extensions;
    }
    
    @Provides
    @AnalysisRootScope
    static ExtensionLookup provideExtensionLookup(Set<GenericExtensionComponent> genericRuntimeExtensions) {
        return ExtensionLookup.createLookup(genericRuntimeExtensions);
    }
    
    @Provides
    @AnalysisRootScope
    @ElementsIntoSet
    static Set<PartitionContribution> provideExtensionPartitionContribution(ExtensionLookup lookup) {
        return lookup.lookup(PartitionContribution.class);
    }
    
    @Provides
    @AnalysisRootScope
    @ElementsIntoSet
    static Set<ModelContribution> modelContributions(ExtensionLookup lookup) {
        return lookup.lookup(ModelContribution.class);
    }
    
    @Provides
    @AnalysisRootScope
    @ElementsIntoSet
    static Set<ModelCompletionJobContributor> provideExtensionCompletionJobs(ExtensionLookup lookup) {
        return lookup.lookup(ModelCompletionJobContributor.class);
    }

}
