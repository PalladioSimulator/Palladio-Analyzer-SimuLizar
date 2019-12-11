package org.palladiosimulator.simulizar.runtimestate;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.interpreter.listener.LogDebugListener;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

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

    /**
     * @param configuration
     * @param modelAccess
     */
    public SimuLizarRuntimeState(final SimuLizarWorkflowConfiguration configuration, final MDSDBlackboard blackboard,
            final SimulationCancelationDelegate cancelationDelegate) {
        super(configuration, blackboard, cancelationDelegate);
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
