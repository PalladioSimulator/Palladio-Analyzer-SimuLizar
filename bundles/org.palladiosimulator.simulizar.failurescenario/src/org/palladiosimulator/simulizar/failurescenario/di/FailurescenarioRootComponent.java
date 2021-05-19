package org.palladiosimulator.simulizar.failurescenario.di;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.failurescenario.jobs.FailurescenarioModelContribution;
import org.palladiosimulator.simulizar.failurescenario.jobs.config.LoadFailurescenarioExtensionIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.failurescenario.ui.config.FailurescenarioExtensionFileInputConfigurationBuilder;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.RootExtensionScope;

import dagger.Component;
import dagger.Provides;

@Component(dependencies = SimuLizarRootComponent.class, modules = FailurescenarioRootComponent.Module.class)
@RootExtensionScope
public interface FailurescenarioRootComponent extends ExtensionComponent {
	
	FailurescenarioModelContribution modelContribution();

    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
    	FailurescenarioRootComponent create(SimuLizarRootComponent component);
    }
    
    public static class EclipseFactory implements IExecutableExtensionFactory {
        @Override
        public Object create() throws CoreException {
            return DaggerFailurescenarioRootComponent.factory();
        }
    }

    @dagger.Module
    public static interface Module {
        @Provides
        @RootExtensionScope
        static LoadFailurescenarioExtensionIntoBlackboardJobConfig provideConfig(
                SimuLizarWorkflowConfiguration config) {
            return FailurescenarioExtensionFileInputConfigurationBuilder.createConfig(config.getAttributes());
        }
    }
}
