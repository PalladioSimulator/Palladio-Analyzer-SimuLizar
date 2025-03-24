package org.palladiosimulator.simulizar.di.modules.component.core;

import java.util.Set;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.modules.scoped.root.CoreRuntimeExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.root.DependencyComponentsModule;
import org.palladiosimulator.simulizar.di.modules.scoped.root.ExtensionComponentRootExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RootComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.DefaultSimuLizarRuntimeFactoryModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.di.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.launcher.jobs.SimuLizarRootJob;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import de.uka.ipd.sdq.workflow.jobs.IJob;

@dagger.Module(includes = {
        MDSDBlackboardProvidingModule.class,          // Provides a shared blackboard instance
        SimuLizarConfigurationModule.class,           // Provides the underlying configuration support
        
        RootComponentFactoriesModule.class,
        DependencyComponentsModule.class,             // Provides shared instances of dependency components
        DefaultSimuLizarRuntimeFactoryModule.class,   // Provides the default SimuLizar analysis setup
        
        CoreRuntimeExtensionBindings.class,
        ExtensionComponentRootExtensionBindings.class // Binds root component extensions
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
