package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.SimulationResult;
import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunInterpreterJob extends AbstractBlackboardInteractingJob<MDSDBlackboard> {
    private static final Logger LOGGER = Logger.getLogger(RunInterpreterJob.class);
    private final SimuComModel simuComModel;
    private final PCMPartitionManager partitionManager;
    private final Reconfigurator reconfigurator;
    private final EventNotificationHelper eventHelper;
    private final Set<IModelObserver> modelObservers;
    
    @Inject
    public RunInterpreterJob(SimuComModel simuComModel,
            PCMPartitionManager partitionManager,
            Reconfigurator reconfigurator,
            EventNotificationHelper eventHelper,
            Set<IModelObserver> modelObservers) {
        this.simuComModel = simuComModel;
        this.partitionManager = partitionManager;
        this.reconfigurator = reconfigurator;
        this.eventHelper = eventHelper;
        this.modelObservers = modelObservers;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        LOGGER.debug("Starting Simulizar simulation...");
        final double simRealTimeNano = ExperimentRunner.run(this.simuComModel);
        LOGGER.debug(
                "Finished Simulation. Simulator took " + (simRealTimeNano / Math.pow(10, 9)) + " real time seconds");
        
        if (!SimulationResult.OK.equals(this.simuComModel.getErrorStatus())) {
            throw new RuntimeException("The simulation was aborted due to an exception during interpretation.", 
                    this.simuComModel.getErrorThrowable());
        }
    }

    @Override
    public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
        LOGGER.debug("Deregister all listeners and execute cleanup code");
        eventHelper.removeAllListener();
        reconfigurator.removeAllObserver();
        reconfigurator.cleanUp();
        partitionManager.stopObservingPcmChanges();
        simuComModel.getProbeFrameworkContext().finish();
        simuComModel.getConfiguration().getRecorderConfigurationFactory().finalizeRecorderConfigurationFactory();
        modelObservers.forEach(IModelObserver::unregister);
        partitionManager.cleanUp();
    }

    @Override
    public String getName() {
        return "Run SimuLizar Interpreter Job";
    }

}
