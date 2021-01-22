package org.palladiosimulator.simulizar.di.modules.component.core;

import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.scoped.root.DependencyComponentsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.ComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.DefaultSimuLizarRuntimeFactoryModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.launcher.jobs.SimuLizarRootJob;

import dagger.Binds;
import de.uka.ipd.sdq.workflow.jobs.IJob;

@dagger.Module(includes = {
        MDSDBlackboardProvidingModule.class,
        SimuLizarConfigurationModule.class,
        ComponentFactoriesModule.class,
        ExtensionComponentsModule.class,
        DependencyComponentsModule.class,
        DefaultSimuLizarRuntimeFactoryModule.class
})
public interface SimuLizarRootModule {
    
    @Binds IJob bindRootJob(SimuLizarRootJob job);

}
