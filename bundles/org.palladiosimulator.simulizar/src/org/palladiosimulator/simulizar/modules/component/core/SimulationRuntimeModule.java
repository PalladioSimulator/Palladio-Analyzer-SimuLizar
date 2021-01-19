package org.palladiosimulator.simulizar.modules.component.core;

import java.util.Set;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.component.core.SimulatedThreadComponent;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.RDSeffPerformanceSwitch;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitch;
import org.palladiosimulator.simulizar.interpreter.impl.ExtensibleComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.modelobserver.ResourceEnvironmentSyncer;
import org.palladiosimulator.simulizar.modelobserver.UsageEvolutionSyncer;
import org.palladiosimulator.simulizar.modelobserver.UsageModelSyncer;
import org.palladiosimulator.simulizar.modules.component.extensions.SimulationRuntimeExtensions;
import org.palladiosimulator.simulizar.modules.scoped.runtime.ReconfiguratorBindingsModule;
import org.palladiosimulator.simulizar.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.modules.stateless.core.CoreBindingsModule;
import org.palladiosimulator.simulizar.modules.stateless.mdsd.PCMPartitionManagerAdapterModule;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;

@Module(includes = { 
        CoreBindingsModule.class,
        SimuLizarConfigurationModule.class,
        PCMPartitionManagerAdapterModule.class,
        SimulationRuntimeExtensions.class,
        ReconfiguratorBindingsModule.class
},
subcomponents = {
        SimulatedThreadComponent.class
})
public interface SimulationRuntimeModule {
    
    @Provides
    @ElementsIntoSet
    static Set<RuntimeStateEntityManager> provideCoreEntityManagers(
            ComponentInstanceRegistry registry,
            AssemblyAllocationManager allocationManager) {
        return ImmutableSet.of(registry, allocationManager);
    }
    
    @Provides
    @ElementsIntoSet
    static Set<IInterpreterListener> provideCoreInterpreterListeners(
            ProbeFrameworkListener probeFrameworkListener) {
        return ImmutableSet.of(probeFrameworkListener);
    }
    
    @Provides
    @ElementsIntoSet
    static Set<IModelObserver> provideCoreModelObservers(
            AllocationLookupSyncer allocationSyncer,
            ResourceEnvironmentSyncer resourceEnvironmentSyncer,
            UsageModelSyncer usageModelSyncer,
            UsageEvolutionSyncer usageEvolutionSyncer,
            Reconfigurator reconfigurator) {
        return ImmutableSet.of(allocationSyncer, resourceEnvironmentSyncer,
                usageModelSyncer, usageEvolutionSyncer, reconfigurator);
    }
    
    @Provides
    @ElementsIntoSet
    static Set<AbstractRDSeffSwitchFactory> provideCoreRDSeffSwitchFactories(
            RDSeffPerformanceSwitch.Factory performanceSwitchFactory,
            RDSeffSwitch.Factory rdseffSwitchFactory) {
        return ImmutableSet.of(rdseffSwitchFactory, performanceSwitchFactory);
    }
    
    
    @Provides
    @SimulationRuntimeScope
    @MainContext 
    static InterpreterDefaultContext provideMainContext(InterpreterDefaultContext context) {
        return context;
    }
    
    @Binds
    @SimulationRuntimeScope
    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> bindAssemblyAllocationLookup(AssemblyAllocationManager manager);
    
    @Provides
    @SimulationRuntimeScope
    static EventDispatcher provideEventNotificationHelper() {
        return new EventDispatcher();
    }
    
    @Binds
    ComposedRDSeffSwitchFactory bindComposedSwitchFactory(ExtensibleComposedRDSeffSwitchFactory impl);
}
