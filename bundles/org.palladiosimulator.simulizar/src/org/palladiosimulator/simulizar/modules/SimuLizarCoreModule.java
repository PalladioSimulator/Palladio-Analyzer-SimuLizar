package org.palladiosimulator.simulizar.modules;

import java.util.Set;

import javax.inject.Singleton;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.entity.SimuLizarEntityReferenceFactories;
import org.palladiosimulator.simulizar.entity.access.SimulatedResourceContainerAccess;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.reconfiguration.NumberOfResourceContainerTrackingReconfiguratorFactory;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.ReconfiguratorFactory;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import dagger.multibindings.Multibinds;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;

@Module
public interface SimuLizarCoreModule {

    @Binds
    @Reusable
    ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> bindResourceContainerAccess(
            SimulatedResourceContainerAccess accessImpl);

    @Binds
    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> bindAllocationLookupSyncer(
            AllocationLookupSyncer impl);

    @Multibinds
    Set<IInterpreterListener> interpreterListeners();
    
    @Provides
    @Singleton
    @Global
    static PCMResourceSetPartition provideGlobalPartition(PCMPartitionManager manager) {
        return manager.getGlobalPCMModel();
    }
    
    @Provides
    @Singleton
    static Reconfigurator provideReconfigurator(ReconfiguratorFactory factory) {
        return factory.get();
    }
    
    @Binds
    ReconfiguratorFactory bindReconfiguratorFactory(NumberOfResourceContainerTrackingReconfiguratorFactory impl);
    

    @Binds
    @Reusable
    EntityReferenceFactory<ResourceContainer> bindResourceContainerReferenceFactory(
            SimuLizarEntityReferenceFactories.ResourceContainer factoryImpl);

    @Binds
    @Reusable
    EntityReferenceFactory<LinkingResource> bindLinkContainerReferenceFactory(
            SimuLizarEntityReferenceFactories.LinkingResource factoryImpl);
    
    
    @Provides @Singleton @MainContext static InterpreterDefaultContext provideMainContext(InterpreterDefaultContext impl) {
        return impl;
    }
    
}
