package org.palladiosimulator.simulizar.runtimestate;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * This class provides access to all simulation and SimuLizar related objects. This includes access
 * to the original SimuComModel (containing the simulated resources, simulated processes, etc.), to
 * SimuLizars central simulator event distribution object, and to simulated component instances
 * (e.g. to access their current state of passive resources, etc.).
 *
 * Per simulation run, there should be exactly one instance of this class and all of its managed
 * information objects.
 *
 * @author Steffen Becker, Sebastian Lehrig, slightly adapted by Florian Rosenthal
 *
 */
public class SimuLizarRuntimeState extends AbstractSimuLizarRuntimeState {

	private static final Logger LOGGER = Logger.getLogger(SimuLizarRuntimeState.class);

    @Inject
    public SimuLizarRuntimeState(final SimuLizarWorkflowConfiguration configuration, final SimulationCancelationDelegate cancelationDelegate,
    		final PCMPartitionManager pcmPartitionManager, final SimuComModel model, final ComponentInstanceRegistry componentInstanceRegistry,
            final EventNotificationHelper eventHelper,@Named("RootContext") InterpreterDefaultContext context, AllocationLookupSyncer allocationLookup,
            final UsageEvolverFacade usageEvolverFacade, final SimulatedUsageModels usageModels, Injector injector) {
        super(configuration, cancelationDelegate, pcmPartitionManager, model, componentInstanceRegistry, eventHelper, context, allocationLookup,
        		usageEvolverFacade, usageModels, injector);
    }

    protected void initializeInterpreterListeners(final Reconfigurator reconfigurator) {
        LOGGER.debug("Adding Debug and monitoring interpreter listeners");
        this.eventHelper.addObserver(new LogDebugListener());
        this.eventHelper.addObserver(new ProbeFrameworkListener(this.getPCMPartitionManager(), this.getModel(), reconfigurator));
    }

    public UsageEvolverFacade getUsageEvolverFacade() {
        return this.usageEvolverFacade;
    }
}
