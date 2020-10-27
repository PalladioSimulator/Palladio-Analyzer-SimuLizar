package org.palladiosimulator.simulizar.runconfig;

import java.util.Arrays;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.entity.SimuLizarEntityReferenceFactories;
import org.palladiosimulator.simulizar.entity.access.SimulatedContainerAssemblyAllocationLookupAdapter;
import org.palladiosimulator.simulizar.entity.access.SimulatedLinkingResourceAccess;
import org.palladiosimulator.simulizar.entity.access.SimulatedResourceContainerAccess;
import org.palladiosimulator.simulizar.interpreter.ComposedStructureInnerSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;
import org.palladiosimulator.simulizar.interpreter.linking.impl.DefaultSimuLizarTransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.impl.MiddlewareCompletionAwareDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.impl.NoDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.impl.ResourceEnvironmentObservingLegacyRouter;
import org.palladiosimulator.simulizar.interpreter.linking.impl.SimulatedLinkingResourceContainerTransmissionStrategy;
import org.palladiosimulator.simulizar.interpreter.linking.impl.StackFrameBytesizeAccumulatingDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.reconfiguration.NumberOfResourceContainerTrackingReconfiguratorFactory;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.ReconfiguratorFactory;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.SimuComModelFactory;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.runtimestate.SimulationCancelationDelegate;
import org.palladiosimulator.simulizar.runtimestate.SimulationPreferencesSimEngineFactoryProvider;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class WorkflowConfigBasedModule extends AbstractModule {

    private SimuLizarWorkflowConfiguration configuration;
    private MDSDBlackboard blackboard;
    private SimulationCancelationDelegate cancelationDelegate;

    public WorkflowConfigBasedModule(final SimuLizarWorkflowConfiguration configuration,
            final MDSDBlackboard blackboard,
            final SimulationCancelationDelegate cancelationDelegate) {
        this.configuration = configuration;
        this.blackboard = blackboard;
        this.cancelationDelegate = cancelationDelegate;
    }

    @Override
    protected void configure() {
        configureParameterBindings();
        configureDefaultBindings();
        configureSimuComBindings();
        configureInterpreterFactories();
        configureNetworkSimulation();
        configureReconfigurationInfrastructure();
        configureInterpreterListeners();
        configureProbeFrameworkListener();
    }

    protected void configureReconfigurationInfrastructure() {
        bind(ReconfiguratorFactory.class).to(NumberOfResourceContainerTrackingReconfiguratorFactory.class);
        bind(Reconfigurator.class).toProvider(ReconfiguratorFactory.class).in(Singleton.class);
    }

    protected void configureParameterBindings() {
        bind(SimulationCancelationDelegate.class).toInstance(cancelationDelegate);
        bind(SimuLizarWorkflowConfiguration.class).toInstance(configuration);
        bind(MDSDBlackboard.class).toInstance(blackboard);
    }

    protected void configureDefaultBindings() {
        bind(IResourceTableManager.class).to(ResourceTableManager.class).in(Singleton.class);
        bind(new TypeLiteral<EntityReferenceFactory<ResourceContainer>>() {})
            .to(SimuLizarEntityReferenceFactories.ResourceContainer.class);
        bind(new TypeLiteral<EntityReferenceFactory<LinkingResource>>() {})
            .to(SimuLizarEntityReferenceFactories.LinkingResource.class);
        bind(new TypeLiteral<ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer>>() {})
            .to(SimulatedResourceContainerAccess.class);
        bind(PCMPartitionManager.class).in(Singleton.class);
        bind(InterpreterDefaultContext.class)
            .annotatedWith(InterpreterDefaultContext.MainContext.class)
            .to(InterpreterDefaultContext.class)
            .in(Singleton.class);
        bind(new TypeLiteral<IAssemblyAllocationLookup<EntityReference<ResourceContainer>>>() {})
            .to(AllocationLookupSyncer.class).asEagerSingleton();
        bind(new TypeLiteral<IAssemblyAllocationLookup<AbstractSimulatedResourceContainer>>() {})
            .to(SimulatedContainerAssemblyAllocationLookupAdapter.class);
        bind(UsageEvolverFacade.class).in(Singleton.class);
        bind(EventNotificationHelper.class).in(Singleton.class);
        bind(ComponentInstanceRegistry.class).in(Singleton.class);
        
        bind(SimuLizarRuntimeState.class).in(Singleton.class);
    }
    
    protected void configureSimuComBindings() {
        bind(ISimEngineFactory.class).toProvider(SimulationPreferencesSimEngineFactoryProvider.class);
        bind(SimuComModel.class).toProvider(SimuComModelFactory.class).in(Singleton.class);
    }
    
    protected void configureInterpreterListeners() {
        var listenerBinder = Multibinder.newSetBinder(binder(), IInterpreterListener.class);
        getInterpreterListenerTypes().forEach(cls -> listenerBinder.addBinding()
            .to(cls)
            .in(Singleton.class));
    }
    
    protected Iterable<Class<? extends IInterpreterListener>> getInterpreterListenerTypes() {
        return Arrays.asList(LogDebugListener.class, AbstractProbeFrameworkListener.class);
    }
    
    protected void configureNetworkSimulation() {
        if (configuration.getSimulateLinkingResources()) {
            bind(new TypeLiteral<ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double>>() {})
                .to(MiddlewareCompletionAwareDemandCalculator.class);
        } else if (configuration.getSimulateThroughputOfLinkingResources()) {
            bind(new TypeLiteral<ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double>>() {})
                .to(StackFrameBytesizeAccumulatingDemandCalculator.class);
        } else {
            bind(new TypeLiteral<ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double>>() {})
                .to(NoDemandCalculator.class);
        }
        
        bind(new TypeLiteral<ISimulatedModelEntityAccess<LinkingResource, SimulatedLinkingResource>>() {})
            .to(SimulatedLinkingResourceAccess.class);
        bind(new TypeLiteral<ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>>>() {})
            .to(ResourceEnvironmentObservingLegacyRouter.class).in(Singleton.class);
        bind(new TypeLiteral<ITransmissionSimulationStrategy<EntityReference<LinkingResource>, Double, InterpreterDefaultContext>>() {})
            .to(SimulatedLinkingResourceContainerTransmissionStrategy.class).in(Singleton.class);
        bind(new TypeLiteral<ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext>>(){})
            .to(new TypeLiteral<DefaultSimuLizarTransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>>>() {})
            .in(Singleton.class);
    }
    
    protected void configureInterpreterFactories() {
        install(new FactoryModuleBuilder()
            .build(ComposedStructureInnerSwitchFactory.class));
    }
    
    protected void configureProbeFrameworkListener() {
        bind(AbstractProbeFrameworkListener.class).to(ProbeFrameworkListener.class);
    }
   

    /*
     * Bindings through @Provides methods allow to resolve dependencies first
     */
    
    
    @Provides
    @Singleton
    @Global
    protected PCMResourceSetPartition provideGlobalPartition(PCMPartitionManager manager) {
        return manager.getGlobalPCMModel();
    }
        
    @Provides
    protected ISimulationControl provideSimulationControl(final SimuComModel model) {
        return model.getSimulationControl();
    }
    
    @Provides
    protected ResourceRegistry provideResourceRegistry(final SimuComModel model) {
        return model.getResourceRegistry();
    }
}
