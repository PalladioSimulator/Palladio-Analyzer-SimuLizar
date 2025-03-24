package org.palladiosimulator.simulizar.action.di;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.action.jobs.AdaptationBehaviorModelContribution;
import org.palladiosimulator.simulizar.action.jobs.AdaptationBehaviorPartitionContribution;
import org.palladiosimulator.simulizar.action.jobs.config.LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.action.ui.configuration.AdaptationBehaviorRepositoryFileInputConfigBuilder;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.scopes.RootExtensionScope;

import dagger.Component;
import dagger.Provides;

@Component(dependencies = SimuLizarRootComponent.class, modules = ActionRootComponent.Module.class)
@RootExtensionScope
public interface ActionRootComponent extends ExtensionComponent {

    AdaptationBehaviorPartitionContribution partitionContribution();

    AdaptationBehaviorModelContribution modelContribution();

    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ActionRootComponent create(SimuLizarRootComponent component);
    }
    
    public static class EclipseFactory implements IExecutableExtensionFactory {
        @Override
        public Object create() throws CoreException {
            return DaggerActionRootComponent.factory();
        }
    }

    @dagger.Module
    public static interface Module {
        @Provides
        @RootExtensionScope
        static LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig provideConfig(
                SimuLizarWorkflowConfiguration config) {
            return AdaptationBehaviorRepositoryFileInputConfigBuilder.createConfig(config.getAttributes());
        }
    }

}
