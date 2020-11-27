package org.palladiosimulator.simulizar.elasticity.jobs;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.launcher.jobs.EvaluateResultsJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Lazy;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class SimuLizarElasticityAnalysisCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
		implements IBlackboardInteractingJob<MDSDBlackboard> {

    @Inject
	public SimuLizarElasticityAnalysisCompositeJob(final SimuLizarWorkflowConfiguration configuration,
	        RunElasticityAnalysisJob analysisJob,
	        Lazy<EvaluateResultsJob> resultsJob) {
        super(false);

        this.addJob(analysisJob);

        if (configuration.getServiceLevelObjectivesFile() != null
                && !(configuration.getServiceLevelObjectivesFile().equals(""))) {
            this.addJob(resultsJob.get());
        }

    }
	
}
