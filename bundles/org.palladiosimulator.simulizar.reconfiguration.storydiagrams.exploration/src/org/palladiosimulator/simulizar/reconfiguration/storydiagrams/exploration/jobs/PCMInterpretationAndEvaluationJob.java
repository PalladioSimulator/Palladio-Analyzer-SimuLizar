package org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.launcher.jobs.EvaluateResultsJob;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class PCMInterpretationAndEvaluationJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

	private SimuLizarWorkflowConfiguration configuration;
	
	public PCMInterpretationAndEvaluationJob(SimuLizarWorkflowConfiguration configuration) {
		super();
		this.configuration = configuration;
	}

	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
		this.add(new PCMStartInterpretationJob(configuration));
		this.add(new EvaluateResultsJob(configuration));
		super.execute(monitor);
	}

}
