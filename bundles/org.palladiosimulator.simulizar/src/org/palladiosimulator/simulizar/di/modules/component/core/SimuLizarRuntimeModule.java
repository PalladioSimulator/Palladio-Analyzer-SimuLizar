package org.palladiosimulator.simulizar.di.modules.component.core;

import java.util.Set;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.core.SimulatedThreadComponent;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.extension.RegisteredComponent;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.CoreRuntimeExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.ExtensionComponentRuntimeExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.LinkingResourceSimulationModule;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.QUALRuntimeExtensionBindings;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.ReconfiguratorBindingsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.impl.ExtensibleComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;

@Module(includes = { 
        SimuLizarConfigurationModule.class,

        // Import runtime extensions
        CoreRuntimeExtensionBindings.class,
        QUALRuntimeExtensionBindings.class,
        ExtensionComponentRuntimeExtensionBindings.class,
        
        ReconfiguratorBindingsModule.class,
        LinkingResourceSimulationModule.class
},
subcomponents = {
        SimulatedThreadComponent.class
})
public interface SimuLizarRuntimeModule {
    
    @RegisteredComponent
    @Provides
    @ElementsIntoSet
    @SimulationRuntimeScope
    static Set<Object> bindRegisteredComponents(SimuLizarRootComponent root, SimuComFrameworkComponent simuCom, QUALComponent qual,
            SimEngineComponent simEngine, SimuLizarRuntimeComponent runtimeComponent) {
        return ImmutableSet.of(root, simuCom, qual, simEngine, runtimeComponent);
    }
    
    @Provides
    @SimulationRuntimeScope
    @MainContext 
    static InterpreterDefaultContext provideInterpreterDefaultContext(final SimuComModel simuComModel, PCMPartitionManager partitionManager,
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> assemblyAllocationLookup,
            ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simRCAccess,
            IResourceTableManager resourceTableManager) {
        return new InterpreterDefaultContext(simuComModel, partitionManager, assemblyAllocationLookup, simRCAccess, resourceTableManager);
    }

    @Provides
    @SimulationRuntimeScope
    static EventDispatcher provideEventNotificationHelper() {
        return new EventDispatcher();
    }
    
    @Binds
    ComposedRDSeffSwitchFactory bindComposedSwitchFactory(ExtensibleComposedRDSeffSwitchFactory impl);
}
