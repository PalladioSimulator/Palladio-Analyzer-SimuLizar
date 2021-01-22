package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.impl.ExtensibleComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Module
public interface CoreSimulationRuntimeEntitiesBindings {
    
    @Provides
    @SimulationRuntimeScope
    static PCMPartitionManager providePartitionManager(final MDSDBlackboard blackboard, final SimuLizarWorkflowConfiguration config) {
        return new PCMPartitionManager(blackboard, config);
    }
    
    @Provides
    @SimulationRuntimeScope
    static EventDispatcher provideEventNotificationHelper() {
        return new EventDispatcher();
    }
    
    @Binds
    ComposedRDSeffSwitchFactory bindComposedSwitchFactory(ExtensibleComposedRDSeffSwitchFactory impl);
    
    @Provides
    @SimulationRuntimeScope
    @MainContext 
    static InterpreterDefaultContext provideInterpreterDefaultContext(final SimuComModel simuComModel, PCMPartitionManager partitionManager,
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> assemblyAllocationLookup,
            ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simRCAccess,
            IResourceTableManager resourceTableManager) {
        return new InterpreterDefaultContext(simuComModel, partitionManager, assemblyAllocationLookup, simRCAccess, resourceTableManager);
    }
}
