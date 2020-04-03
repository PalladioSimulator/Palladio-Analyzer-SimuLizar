package org.palladiosimulator.simulizar.runconfig;

import org.palladiosimulator.pcm.core.composition.util.CompositionSwitch;
import org.palladiosimulator.simulizar.interpreter.ComposedStructureInnerSwitch;
import org.palladiosimulator.simulizar.interpreter.ComposedStructureInnerSwitchFactory;
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
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class WorkflowConfigBasedModule extends AbstractModule {

    private AbstractSimuLizarRuntimeState runtimeState;
    private SimuLizarWorkflowConfiguration configuration;

    public WorkflowConfigBasedModule(AbstractSimuLizarRuntimeState runtimeState,
            SimuLizarWorkflowConfiguration configuration) {
        this.runtimeState = runtimeState;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(PCMPartitionManager.class).toInstance(runtimeState.getPCMPartitionManager());
        
        bind(new TypeLiteral<ISimulatedModelEntityAccess<Identifier, AbstractSimulatedResourceContainer>>() {})
            .toInstance(runtimeState.getModel().getResourceRegistry()::getResourceContainer);
        
        bind(new TypeLiteral<IAssemblyAllocationLookup<AbstractSimulatedResourceContainer>>() {})
            .to(AllocationLookupSyncer.class).asEagerSingleton();
        
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
        bind(new TypeLiteral<ITransmissionSimulationStrategy<SimulatedLinkingResource, Double, SimuComSimProcess>>() {})
            .to(SimulatedLinkingResourceContainerTransmissionStrategy.class).in(Singleton.class);
        bind(new TypeLiteral<ITransmissionInterpreter<AbstractSimulatedResourceContainer, InterpreterDefaultContext>>(){})
            .to(DefaultSimuLizarTransmissionInterpreter.class).in(Singleton.class);
        
    	install(new FactoryModuleBuilder()
    			.implement(new TypeLiteral<CompositionSwitch<SimulatedStackframe<Object>>>() {}, ComposedStructureInnerSwitch.class)
    			.build(ComposedStructureInnerSwitchFactory.class));
    }

}
