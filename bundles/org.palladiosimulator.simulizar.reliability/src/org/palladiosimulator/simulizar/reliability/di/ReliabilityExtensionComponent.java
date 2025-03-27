package org.palladiosimulator.simulizar.reliability.di;

import java.util.Set;

import javax.inject.Provider;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.di.base.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.base.scopes.RuntimeExtensionScope;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.modules.stateless.probes.ProbeFrameworkProbeFactoryBindings;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.reliability.interpreter.RDSeffReliabilityInterpreter;
import org.palladiosimulator.simulizar.reliability.interpreter.listener.ReliabilityProbeFrameworkAdapter;

import com.google.common.collect.ImmutableSet;

import dagger.Component;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Component(dependencies = { SimuLizarRuntimeComponent.class, SimuComFrameworkComponent.class, QUALComponent.class, SimuLizarRootComponent.class },
        modules = { ProbeFrameworkProbeFactoryBindings.class, ReliabilityExtensionComponent.ExtensionModule.class })
@RuntimeExtensionScope
public interface ReliabilityExtensionComponent extends ExtensionComponent {
    
    Set<RDSeffSwitchContributionFactory> interpreterContributions();

    ReliabilityProbeFrameworkAdapter probeFrameworkAdapter();
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ReliabilityExtensionComponent create(SimuLizarRuntimeComponent runtimeComponent, SimuComFrameworkComponent frameworkComponent, QUALComponent qualComponent,
                SimuLizarRootComponent rootCompoment);
    }
    
    public static class EclipseFactory implements IExecutableExtensionFactory {
        @Override
        public Object create() throws CoreException {
            return DaggerReliabilityExtensionComponent.factory();
        }
    }
    
    @dagger.Module
    public interface ExtensionModule {
        @Provides
        @ElementsIntoSet
        static Set<RDSeffSwitchContributionFactory> provideReliabilitySwitch(SimuLizarWorkflowConfiguration config,
                RDSeffReliabilityInterpreter.Factory factory) {
            if (config.getSimulateFailures()) {
                return ImmutableSet.of(factory);
            } else {
                return ImmutableSet.of();
            }
        }
    }
}
