package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.jobs.PreparePCMBlackboardPartitionJob;

/**
 * Composite Job for preparing Blackboard and loading PCM Models into it.
 * 
 * @author Joachim Meyer
 * @author Matthias Becker
 * 
 */
public class LoadSimuLizarModelsIntoBlackboardJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

	public static final String PCM_MODELS_ANALYZED_PARTITION_ID = "org.palladiosimulator.analyzed.partition";

	/**
	 * @param config
	 */
	public LoadSimuLizarModelsIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration) {
		this(configuration, true);
	}

	/**
	 * @param config
	 */
	public LoadSimuLizarModelsIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration,
			boolean loadExtensions) {
		super(false);

		this.addJob(new PreparePCMBlackboardPartitionJob());
		this.addJob(new LoadPCMModelsInterpreterJob(configuration));
		this.addJob(new LoadModelIntoBlackboardAndResolveAllJob(configuration,
				SimulizarConstants.MONITOR_REPOSITORY_FILE, "Loading Monitor Respository Model"));
		this.addJob(new LoadModelIntoBlackboardAndResolveAllJob(configuration,
				SimulizarConstants.SERVICELEVELOBJECTIVEREPOSITORY_FILE, "Loading Service Level Objectives Model"));
		this.addJob(new LoadModelIntoBlackboardAndResolveAllJob(configuration,
				SimulizarConstants.RECONFIGURATION_RULES_FILE, "Loading Reconfiguration Model"));
		this.addJob(new LoadModelIntoBlackboardAndResolveAllJob(configuration, SimulizarConstants.USAGEEVOLUTION_FILE,
				"Loading Usage Evolution Model"));
		if (loadExtensions) {
			addModelLoadExtensionJobs(configuration);
		}
	}

	private void addModelLoadExtensionJobs(SimuLizarWorkflowConfiguration configuration) {
		Iterable<AbstractWorkflowExtensionJob<MDSDBlackboard>> loadJobs = ExtensionHelper.getExecutableExtensions(
				SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
				SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE,
				SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE);

		for (AbstractWorkflowExtensionJob<MDSDBlackboard> loadJob : loadJobs) {
			// check if corresponding config builder is available
			// filter available extensions by name of job class
			// this can be improved
			AbstractWorkflowExtensionConfigurationBuilder builder = ExtensionHelper.getExecutableExtension(
					SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
					SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_CONFIG_BUILDER_ATTRIBUTE,
					SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE, loadJob.getClass().getName());
			if (builder != null) {
				// may be null as it is an optional attribute
				loadJob.setJobConfiguration(builder.buildConfiguration(configuration.getAttributes()));
			}
			this.add(loadJob);
		}
	}

}
