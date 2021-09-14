package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import java.util.Set;

import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.di.modules.component.extensions.SimulationRuntimeExtensions;
import org.palladiosimulator.simulizar.di.modules.stateless.core.CoreBindingsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.PCMPartitionManagerAdapterModule;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.impl.SimulatedThreadComponentDelegatingScenarioRunner;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.modelobserver.ResourceEnvironmentSyncer;
import org.palladiosimulator.simulizar.modelobserver.UsageEvolutionSyncer;
import org.palladiosimulator.simulizar.modelobserver.UsageModelSyncer;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.usagemodel.IScenarioRunnerFactory;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;

@Module(includes = { CoreBindingsModule.class, SimulationRuntimeExtensions.class, PCMPartitionManagerAdapterModule.class, UserProcessCountMonitorModule.class })
public interface CoreRuntimeExtensionBindings {

    @Provides
    @ElementsIntoSet
    static Set<RuntimeStateEntityManager> provideCoreEntityManagers(ComponentInstanceRegistry registry,
            AssemblyAllocationManager allocationManager) {
        return ImmutableSet.of(registry, allocationManager);
    }

    @Provides
    @ElementsIntoSet
    static Set<IModelObserver> provideCoreModelObservers(AllocationLookupSyncer allocationSyncer,
            ResourceEnvironmentSyncer resourceEnvironmentSyncer, UsageModelSyncer usageModelSyncer,
            UsageEvolutionSyncer usageEvolutionSyncer) {
        return ImmutableSet.of(allocationSyncer, resourceEnvironmentSyncer, usageModelSyncer, usageEvolutionSyncer);
    }

    @Binds
    @SimulationRuntimeScope
    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> bindAssemblyAllocationLookup(
            AssemblyAllocationManager manager);
    
    @Binds
    @SimulationRuntimeScope
    IScenarioRunnerFactory<Entity> bindScenarioRunnerFactory(SimulatedThreadComponentDelegatingScenarioRunner.Factory impl);

}
