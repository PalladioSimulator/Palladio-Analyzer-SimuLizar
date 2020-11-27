package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.SimuLizarSimulationComponent;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.launcher.IConfigurator;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.modules.SimulationCancelationModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Job starting the pcm interpretation.
 *
 * @author Joachim Meyer
 *
 */
public class PCMStartInterpretationJob implements IBlackboardInteractingJob<MDSDBlackboard> {

    private static final Logger LOGGER = Logger.getLogger(PCMStartInterpretationJob.class.getName());

    protected MDSDBlackboard blackboard;

    protected final SimuLizarWorkflowConfiguration configuration;
    private final SimuLizarSimulationComponent.Builder componentBuilder;
    private final InterpreterExtension.Factory extensionsFactory;

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    @Inject
    public PCMStartInterpretationJob(final SimuLizarWorkflowConfiguration configuration, 
            SimuLizarSimulationComponent.Builder componentBuilder,
            InterpreterExtension.Factory extensionsFactory) {
        super();
        this.configuration = configuration;
        this.componentBuilder = componentBuilder;
        this.extensionsFactory = extensionsFactory;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        LOGGER.info("Start job: " + this);

        LOGGER.info("Initialise Simulizar runtime state");
        
        SimuLizarSimulationComponent component = componentBuilder
            .simulationCancelationModule(new SimulationCancelationModule(monitor::isCanceled))
            .build();
        
        var extension = extensionsFactory.create(component);
        
        var runtimeState = component.runtimeState();
        
        extension.preInitialize();
        runtimeState.initialize();
        extension.initialized();

        runtimeState.runSimulation();
        extension.completed();
        
        runtimeState.cleanUp();
        LOGGER.info("finished job: " + this);
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#getName()
     */
    @Override
    public String getName() {
        return "Run SimuLizar";
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IBlackboardInteractingJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
     */
    @Override
    public void setBlackboard(final MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
    }

}
