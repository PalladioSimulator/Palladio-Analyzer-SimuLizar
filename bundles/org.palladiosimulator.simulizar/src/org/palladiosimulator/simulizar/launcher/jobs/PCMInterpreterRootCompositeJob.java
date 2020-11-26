package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.palladiosimulator.simulizar.extension.SimuLizarExtension;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Composite job loading pcm and Monitor Repository model, as well as all sdm models and usage
 * evolution model and starting pcm interpretation.
 *
 * @author Joachim Meyer
 *
 */
public class PCMInterpreterRootCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
        implements IBlackboardInteractingJob<MDSDBlackboard> {

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    @Inject
    public PCMInterpreterRootCompositeJob(final SimuLizarWorkflowConfiguration configuration,
            Set<SimuLizarExtension> extensions,
            MDSDBlackboard blackboard,
            @Named(SimulizarConstants.MODEL_LOAD_JOB_ID) IJob modelLoadJob,
            @Named(SimulizarConstants.INTERPRETER_JOB_ID) IJob interpreterJob) {
        super(false);
        setBlackboard(blackboard);
        
        this.addJob(modelLoadJob);
        this.addJob(interpreterJob);

        if (configuration.getServiceLevelObjectivesFile() != null
                && !(configuration.getServiceLevelObjectivesFile().equals(""))) {
            this.addJob(new EvaluateResultsJob(configuration));
        }

    }

}
