package org.palladiosimulator.simulizar.launcher.jobs;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.core.SimulationResult;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class RunInterpreterJob extends SequentialJob {
    private static final Logger LOGGER = Logger.getLogger(RunInterpreterJob.class);
    private final Provider<SimuComModel> simuComModelProvider;
    
    @Inject
    public RunInterpreterJob(Provider<SimuComModel> simuComModelProvider) {
        this.simuComModelProvider = simuComModelProvider;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        LOGGER.debug("Starting Simulizar simulation...");
        var simuComModel = simuComModelProvider.get();
        simuComModel.getSimulationControl().addStopCondition(monitor::isCanceled);
        final double simRealTimeNano = ExperimentRunner.run(simuComModel);
        LOGGER.debug(
                "Finished Simulation. Simulator took " + (simRealTimeNano / Math.pow(10, 9)) + " real time seconds");
    }

    @Override
    public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
        var simuComModel = simuComModelProvider.get();
        if (!SimulationResult.OK.equals(simuComModel.getErrorStatus())) {
            throw new RuntimeException("The simulation was aborted due to an exception during interpretation.", 
                    simuComModel.getErrorThrowable());
        }
    }

    @Override
    public String getName() {
        return "Run SimuLizar Interpreter Job";
    }

}
