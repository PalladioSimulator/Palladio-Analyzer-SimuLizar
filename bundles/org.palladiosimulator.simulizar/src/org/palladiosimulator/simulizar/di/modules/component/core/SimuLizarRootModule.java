package org.palladiosimulator.simulizar.di.modules.component.core;

import java.util.Set;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.modules.scoped.root.DependencyComponentsModule;
import org.palladiosimulator.simulizar.di.modules.scoped.root.ExtensionComponentRootExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.ComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.DefaultSimuLizarRuntimeFactoryModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.launcher.jobs.SimuLizarRootJob;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import de.uka.ipd.sdq.workflow.jobs.IJob;

@dagger.Module(includes = {
        MDSDBlackboardProvidingModule.class,
        SimuLizarConfigurationModule.class,
        ComponentFactoriesModule.class,
        ExtensionComponentRootExtensionBindings.class,
        DependencyComponentsModule.class,
        DefaultSimuLizarRuntimeFactoryModule.class
})
public interface SimuLizarRootModule {
    
    @Binds IJob bindRootJob(SimuLizarRootJob job);
    
    @RegisteredComponent
    @Provides
    @ElementsIntoSet
    @AnalysisRootScope
    static Set<Object> bindRegisteredComponents(SimuLizarRootComponent root) {
        return ImmutableSet.of(root);
    }

}
