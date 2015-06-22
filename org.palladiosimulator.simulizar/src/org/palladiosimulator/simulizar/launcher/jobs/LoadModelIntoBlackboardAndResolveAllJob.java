package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.exceptions.SimuLizarModelLoadException;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

public class LoadModelIntoBlackboardAndResolveAllJob implements IJob, IBlackboardInteractingJob<MDSDBlackboard> {

	private MDSDBlackboard blackboard;

	private final String path;

	private final String message;

	/**
	 * Constructor
	 * 
	 * @param configuration
	 *            the SimuCom workflow configuration.
	 */
	public LoadModelIntoBlackboardAndResolveAllJob(final SimuComWorkflowConfiguration configuration,
			final String modelFileConstant, final String message) {
		this.path = (String) configuration.getAttributes().get(modelFileConstant);
		this.message = message;
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
		if (this.getPCMResourceSetPartition() == null) {
			throw new SimuLizarModelLoadException("The PCM models must be loaded first");
		}

		// Use the PCMResoureSetPartition to load models
		PCMResourceSetPartition pcmPartition = this.getPCMResourceSetPartition();
		if (!this.getPath().equals("")) {

			// add file protocol if necessary
			String filePath = getPath();
			if (!getPath().startsWith("platform:")) {
				filePath = "file:///" + filePath;
			}

			pcmPartition.loadModel(URI.createURI(filePath));
			pcmPartition.resolveAllProxies();

		}
	}

	/**
	 * @return returns the blackboard.
	 */
	private MDSDBlackboard getBlackboard() {
		return this.blackboard;
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IJob#getName()
	 */
	@Override
	public String getName() {
		return this.message;
	}

	/**
	 * @return returns the path.
	 */
	private String getPath() {
		return this.path;
	}

	/**
	 * @return the pcm resource set partition
	 */
	private PCMResourceSetPartition getPCMResourceSetPartition() {
		return (PCMResourceSetPartition) (this.getBlackboard()
				.getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID));
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