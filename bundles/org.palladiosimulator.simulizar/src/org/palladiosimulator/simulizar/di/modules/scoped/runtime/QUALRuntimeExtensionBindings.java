package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.recorderframework.config.IRecorderConfigurationFactory;
import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulationRuntimeExtensions;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module(includes = SimulationRuntimeExtensions.class)
public interface QUALRuntimeExtensionBindings {
    
    @Binds
    @IntoSet
    @SimulationRuntimeScope
    IInterpreterListener bindProbeFrameworkListener(ProbeFrameworkListener listener);
    
    @Provides
    @IntoSet
    @SimulationRuntimeScope
    static RuntimeStateEntityManager provideProbeFrameworkCleanupTask(SimuLizarWorkflowConfiguration config, ProbeFrameworkContext ctx, IRecorderConfigurationFactory fact) {
        return new RuntimeStateEntityManager() {
            @Override
            public void initialize() {
                fact.initialize(config.getAttributes());
            }
            @Override
            public void cleanup() {
                ctx.finish();
                fact.finalizeRecorderConfigurationFactory();
            }
        };
    }
}
