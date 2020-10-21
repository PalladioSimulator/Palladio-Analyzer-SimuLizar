package org.palladiosimulator.simulizar.runconfig;

import java.util.Arrays;

import org.palladiosimulator.pcm.core.composition.util.CompositionSwitch;
import org.palladiosimulator.simulizar.interpreter.ComposedStructureInnerSwitch;
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
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
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
        bind(PCMPartitionManager.class).in(Singleton.class);
        bind(InterpreterDefaultContext.class)
            .annotatedWith(InterpreterDefaultContext.MainContext.class)
            .to(InterpreterDefaultContext.class)
            .in(Singleton.class);
        bind(new TypeLiteral<IAssemblyAllocationLookup<AbstractSimulatedResourceContainer>>() {})
            .to(AllocationLookupSyncer.class).asEagerSingleton();
        bind(UsageEvolverFacade.class).in(Singleton.class);
        bind(EventNotificationHelper.class).in(Singleton.class);
        bind(ComponentInstanceRegistry.class).in(Singleton.class);
        bind(SimuLizarRuntimeState.class).in(Singleton.class);
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
            bind (new TypeLiteral<ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double>>() {})
                .to(MiddlewareCompletionAwareDemandCalculator.class);
        } else if (configuration.getSimulateThroughputOfLinkingResources()) {
            bind (new TypeLiteral<ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double>>() {})
                .to(StackFrameBytesizeAccumulatingDemandCalculator.class);
        } else {
            bind (new TypeLiteral<ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double>>() {})
                .to(NoDemandCalculator.class);
        }
        
        bind(new TypeLiteral<ILinkingResourceRouter<AbstractSimulatedResourceContainer, SimulatedLinkingResource>>() {})
            .to(ResourceEnvironmentObservingLegacyRouter.class).in(Singleton.class);
        bind(new TypeLiteral<ITransmissionSimulationStrategy<SimulatedLinkingResource, Double, InterpreterDefaultContext>>() {})
            .to(SimulatedLinkingResourceContainerTransmissionStrategy.class).in(Singleton.class);
        bind(new TypeLiteral<ITransmissionInterpreter<AbstractSimulatedResourceContainer, SimulatedStackframe<Object>, InterpreterDefaultContext>>(){})
            .to(new TypeLiteral<DefaultSimuLizarTransmissionInterpreter<AbstractSimulatedResourceContainer, SimulatedStackframe<Object>>>() {})
            .in(Singleton.class);
    }
    
    protected void configureInterpreterFactories() {
        install(new FactoryModuleBuilder()
                .implement(new TypeLiteral<CompositionSwitch<SimulatedStackframe<Object>>>() {}, ComposedStructureInnerSwitch.class)
                .build(ComposedStructureInnerSwitchFactory.class));
    }
    
    protected void configureProbeFrameworkListener() {
        bind(AbstractProbeFrameworkListener.class).to(ProbeFrameworkListener.class);
    }
   

    /*
     * Bindings through @Provides methods allow to resolve dependencies first
     */
    
    
    @Provides
    protected ISimulatedModelEntityAccess<Identifier, AbstractSimulatedResourceContainer> provideSimulatedResourceContainerAccess(SimuComModel model) {
        return model.getResourceRegistry()::getResourceContainer;
    }
    
    @Provides
    @Singleton
    protected SimuComModel provideSimuComModel(final SimuLizarWorkflowConfiguration configuration, IResourceTableManager resourceTableManager) {
        return SimuComModelFactory.createSimuComModel(configuration, resourceTableManager);
    }
    
    @Provides
    protected ISimulationControl provideSimulationControl(final SimuComModel model) {
        return model.getSimulationControl();
    }
}
