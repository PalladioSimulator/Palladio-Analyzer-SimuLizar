package org.palladiosimulator.simulizar.failurescenario.di;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.failurescenario.interpreter.FailurescenarioObserver;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

import dagger.Component;

@Component(dependencies = { SimuLizarRuntimeComponent.class, SimuComFrameworkComponent.class,
		SimEngineComponent.class }
//, modules = {FailurescenarioExtensionModule.class}
)
@RuntimeExtensionScope
public interface FailurescenarioExtensionComponent extends ExtensionComponent {

	FailurescenarioObserver failurescenarioObserver();

	@Component.Factory
	public static interface Factory extends ExtensionComponent.Factory {
		FailurescenarioExtensionComponent create(SimuLizarRuntimeComponent runtimeComponent,
				SimuComFrameworkComponent frameworkComponent, SimEngineComponent simEngineComponent);
	}

	public static class EclipseFactory implements IExecutableExtensionFactory {
		@Override
		public Object create() throws CoreException {
			return DaggerFailurescenarioExtensionComponent.factory();
		}

	}
}
