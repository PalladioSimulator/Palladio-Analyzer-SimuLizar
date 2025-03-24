package org.palladiosimulator.simulizar.runtimestate;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.di.component.interfaces.SimulatedThreadComponent;
import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;

import dagger.Lazy;
import de.uka.ipd.sdq.simucomframework.core.model.SimuComModel;

/**
 * This class provides access to all simulation and SimuLizar related objects. This includes access
 * to the original SimuComModel (containing the simulated resources, simulated processes, etc.), to
 * SimuLizars central simulator event distribution object, and to simulated component instances
 * (e.g. to access their current state of passive resources, etc.).
 *
 * Per simulation run, there should be exactly one instance of this class and all of its managed
 * information objects.
 * 
 * NOTE: In an effort to improve testability, the number of class inter-dependencies need to be
 * reduced. Therefore, please refrain from referencing the RuntimeState to access dependencies. Use
 * dependency injection to retrieve the desired service / simulation entity directly.
 *
 * @author Steffen Becker, Sebastian Lehrig, Florian Rosenthal, Sebastian Krach
 *
 */
@Deprecated
@SimulationRuntimeScope
public class SimuLizarRuntimeState {

    protected final SimuComModel model;
    protected final EventDispatcher eventHelper;
    private final ComponentInstanceRegistry componentInstanceRegistry;
    private final SimulatedUsageModels usageModels;
    private final PCMPartitionManager pcmPartitionManager;
    private final Reconfigurator reconfigurator;
    protected final UsageEvolverFacade usageEvolverFacade;
    private final Lazy<InterpreterDefaultContext> mainContext;
    private final SimulatedThreadComponent.Factory simulatedThreadComponentFactory;

    /**
     * @param configuration
     * @param modelAccess
     */
    @Inject
    public SimuLizarRuntimeState(PCMPartitionManager pcmPartitionManager, 
            SimuComModel simuComModel,
            EventDispatcher eventHelper,
            ComponentInstanceRegistry componentInstanceRegistry, 
            SimulatedUsageModels simulatedUsageModels, 
            Reconfigurator reconfigurator,
            UsageEvolverFacade usageEvolverFacade,  
            @MainContext Lazy<InterpreterDefaultContext> mainContext,
            SimulatedThreadComponent.Factory simulatedThreadComponentFactory) {
        this.pcmPartitionManager = pcmPartitionManager;
        this.model = simuComModel;
        this.eventHelper = eventHelper;
        this.componentInstanceRegistry = componentInstanceRegistry;
		this.usageModels = simulatedUsageModels;
        this.reconfigurator = reconfigurator;
        this.usageEvolverFacade = usageEvolverFacade;
        this.mainContext = mainContext;
        this.simulatedThreadComponentFactory = simulatedThreadComponentFactory;
    }

    /**
     * @return the SimuCom model
     * @deprecated Use dependency injection to retrieve the SimuComModel instance.
     */
    public SimuComModel getModel() {
        return this.model;
    }

    /**
     * @return the event notification helper
     * @deprecated Use dependency injection to retrieve the EventNotificationHelper instance.
     */
    public EventDispatcher getEventNotificationHelper() {
        return this.eventHelper;
    }

    /**
     * @return the component instance registry
     * @deprecated Use dependency injection to retrieve the ComponentInstanceRegistry instance.
     */
    public final ComponentInstanceRegistry getComponentInstanceRegistry() {
        return this.componentInstanceRegistry;
    }

    /**
     * @return the main Context
     * @deprecated Use dependency injection to retrieve the main context.
     */
    @Deprecated
    public InterpreterDefaultContext getMainContext() {
        return mainContext.get();
    }

    /**
     * @return the simulated usage model singleton
     * @deprecated Use dependency injection to retrieve the SimulatedUsageModels instance.
     */
    @Deprecated
    public SimulatedUsageModels getUsageModels() {
        return this.usageModels;
    }

    /**
     * @return the PCM partition manager
     * @deprecated Use dependency injection to retrieve the PCMPartitionManager instance.
     */
    @Deprecated
    public PCMPartitionManager getPCMPartitionManager() {
        return this.pcmPartitionManager;
    }


    /**
     * Returns the reconfigurator responsible for executing reconfigurations and
     * notifying listeners of changes.
     *
     * @return The reconfigurator.
     * 
     * @deprecated Use dependency injection to retrieve the Reconfigurator instance.
     */
    @Deprecated
    public Reconfigurator getReconfigurator() {
        return this.reconfigurator;
    }
    
    /**
     * @return the Usage Evolver Facade
     * @deprecated Use dependency injection to retrieve the UsageEvolverFacade instance.
     */
    @Deprecated
    public UsageEvolverFacade getUsageEvolverFacade() {
        return this.usageEvolverFacade;
    }
    
    public SimulatedThreadComponent.Factory getSimulatedThreadComponentFactory() {
        return this.simulatedThreadComponentFactory;
    }
    
}
