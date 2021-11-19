package org.palladiosimulator.simulizar.reliability.di;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.modules.stateless.probes.ProbeFrameworkProbeFactoryBindings;
import org.palladiosimulator.simulizar.reliability.interpreter.RDSeffReliabilityInterpreter;
import org.palladiosimulator.simulizar.reliability.interpreter.listener.ReliabilityProbeFrameworkAdapter;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

import dagger.Component;

@Component(dependencies = { SimuLizarRuntimeComponent.class, SimuComFrameworkComponent.class, QUALComponent.class },
        modules = { ProbeFrameworkProbeFactoryBindings.class })
@RuntimeExtensionScope
public interface ReliabilityExtensionComponent extends ExtensionComponent {
    
    RDSeffReliabilityInterpreter.Factory reliabilityInterpreterFactory();

    ReliabilityProbeFrameworkAdapter probeFrameworkAdapter();
    
    @Component.Factory
    public static interface Factory extends ExtensionComponent.Factory {
        ReliabilityExtensionComponent create(SimuLizarRuntimeComponent runtimeComponent, SimuComFrameworkComponent frameworkComponent, QUALComponent qualComponent);
    }
    
    public static class EclipseFactory implements IExecutableExtensionFactory {

        @Override
        public Object create() throws CoreException {
            return DaggerReliabilityExtensionComponent.factory();
        }
        
    }
}
