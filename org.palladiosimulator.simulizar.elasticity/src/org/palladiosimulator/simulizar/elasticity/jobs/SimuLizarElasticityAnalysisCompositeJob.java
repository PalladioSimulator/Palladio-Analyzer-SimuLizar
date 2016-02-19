package org.palladiosimulator.simulizar.elasticity.jobs;

import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

import org.palladiosimulator.simulizar.launcher.jobs.EvaluateResultsJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;

public class SimuLizarElasticityAnalysisCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
		implements IBlackboardInteractingJob<MDSDBlackboard> {

	public SimuLizarElasticityAnalysisCompositeJob(final SimuLizarWorkflowConfiguration configuration) {
        super(false);

        LoadSimuLizarModelsIntoBlackboardJob loadSimuLizarModelsIntoBlackboardJob = new LoadSimuLizarModelsIntoBlackboardJob(configuration);

        this.addJob(new RunElasticityAnalysisJob(configuration, loadSimuLizarModelsIntoBlackboardJob));

        if (configuration.getServiceLevelObjectivesFile() != null
                && !(configuration.getServiceLevelObjectivesFile().equals(""))) {
            this.addJob(new EvaluateResultsJob(configuration));
        }

    }
	
}
