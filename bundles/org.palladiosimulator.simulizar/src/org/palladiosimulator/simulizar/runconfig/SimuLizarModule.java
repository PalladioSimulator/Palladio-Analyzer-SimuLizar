package org.palladiosimulator.simulizar.runconfig;

import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContextFactory;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.SimuComModelFactory;
import org.palladiosimulator.simulizar.runtimestate.SimulationCancelationDelegate;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
/**
 * Module for dependency injection for RuntimeState
 * @author Jens Manig
 *
 */
public class SimuLizarModule extends AbstractModule{
	private final SimuLizarWorkflowConfiguration configuration;
	private final SimulationCancelationDelegate cancelationDelegate;
	private final SimuComModel model;
	private final InterpreterDefaultContext context;
	private final AllocationLookupSyncer allocationLookup;
	private final UsageEvolverFacade usageEvolverFacade;
	private final EventNotificationHelper eventHelper;
	private final ComponentInstanceRegistry componentInstanceRegistry;
	private final SimulatedUsageModels usageModels;
	private final PCMPartitionManager pcmPartitionManager;

	
	public SimuLizarModule(final SimuLizarWorkflowConfiguration configuration,
            final MDSDBlackboard blackboard, final SimulationCancelationDelegate cancelationDelegate) {
		this.configuration = configuration;
		this.pcmPartitionManager = new PCMPartitionManager(blackboard, configuration);
		this.cancelationDelegate = cancelationDelegate;
		this.model = SimuComModelFactory.createSimuComModel(configuration);
		
		ISimulatedModelEntityAccess<Identifier, AbstractSimulatedResourceContainer> resourceContainerAccess = 
	                this.model.getResourceRegistry()::getResourceContainer;
	    this.allocationLookup = new AllocationLookupSyncer(resourceContainerAccess);
	    this.context = InterpreterDefaultContextFactory.Factory.create(this.model, allocationLookup);
	    this.usageEvolverFacade = new UsageEvolverFacade(this.pcmPartitionManager, this.model);
	    
	    this.eventHelper = new EventNotificationHelper();
	    this.componentInstanceRegistry = new ComponentInstanceRegistry();
	    this.usageModels = new SimulatedUsageModels(this.context, this.componentInstanceRegistry,
                this.eventHelper, this.pcmPartitionManager);
	}
	
	@Override
	protected void configure() {
		bind(SimuLizarWorkflowConfiguration.class).toInstance(this.configuration);
		bind(SimulationCancelationDelegate.class).toInstance(this.cancelationDelegate);
		bind(PCMPartitionManager.class).toInstance(this.pcmPartitionManager);
		bind(EventNotificationHelper.class).toInstance(this.eventHelper);
		bind(ComponentInstanceRegistry.class).toInstance(this.componentInstanceRegistry);
		bind(SimuComModel.class).toInstance(this.model);
		bind(InterpreterDefaultContext.class).toInstance(this.context);
		bind(AllocationLookupSyncer.class).toInstance(this.allocationLookup);
		bind(UsageEvolverFacade.class).toInstance(this.usageEvolverFacade);
		bind(SimulatedUsageModels.class).toInstance(this.usageModels);
		//bind(SimuLizarRuntimeState.class).in(Singleton.class);
		
		//install(new FactoryModuleBuilder()
			//     .build(SimulatedUsageModelsFactory.class));
		//install(new FactoryModuleBuilder()
				//.implement(IComposableSwitch.class, RDSeffSwitch.class)
			//     .build(RDSeffSwitchFactory.class));
	}
}
