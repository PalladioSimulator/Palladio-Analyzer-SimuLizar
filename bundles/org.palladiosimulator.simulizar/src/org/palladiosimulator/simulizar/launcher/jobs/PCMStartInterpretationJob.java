package org.palladiosimulator.simulizar.launcher.jobs;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.component.core.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.component.core.QUALComponent;
import org.palladiosimulator.simulizar.component.core.SimEngineComponent;
import org.palladiosimulator.simulizar.component.core.SimEngineComponent.Factory;
import org.palladiosimulator.simulizar.component.core.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.component.core.SimuLizarRuntimeComponent;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
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

    private final AnalysisRuntimeComponent.Factory runtimeComponentFactory;

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    @Inject
    public PCMStartInterpretationJob(AnalysisRuntimeComponent.Factory runtimeComponentFactory) {
        this.runtimeComponentFactory = runtimeComponentFactory;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        LOGGER.info("Start job: " + this);

        LOGGER.info("Initialise Simulizar runtime state");
                
        var runtimeComponent = runtimeComponentFactory.create();
        
        try {
            var initJob = runtimeComponent.initializeJob();
            setBlackboardIfRequired(initJob);
            initJob.execute(monitor);
            initJob.cleanup(monitor);

            var analysisJob = runtimeComponent.runInterpreterJob();
            setBlackboardIfRequired(analysisJob);
            analysisJob.execute(monitor);
            analysisJob.cleanup(monitor);
        } catch (CleanupFailedException ex) {
            throw new JobFailedException("Cleanup Failed", ex);
        }

        LOGGER.info("finished job: " + this);
    }
    
    @SuppressWarnings("unchecked")
    private void setBlackboardIfRequired(IJob job) {
        if (job instanceof IBlackboardInteractingJob) {
            ((IBlackboardInteractingJob<MDSDBlackboard>) job).setBlackboard(blackboard);
        }
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
