package org.palladiosimulator.simulizar.launcher.jobs;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.exceptions.PMSModelLoadException;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.partitions.DEMResourceSetPartition;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

public class LoadDEModelIntoBlackBoardJob implements IJob,
		IBlackboardInteractingJob<MDSDBlackboard> {

	public static final String DE_MODEL_PARTITION_ID = "org.palladiosimulator.simulizar.dynamicenvironment";

	private MDSDBlackboard blackboard;

	private final String path;

	private static final Logger LOGGER = Logger
			.getLogger(LoadDEModelIntoBlackBoardJob.class);

	/**
	 * Constructor
	 * 
	 * @param configuration
	 *            the SimuCom workflow configuration.
	 */
	public LoadDEModelIntoBlackBoardJob(
			final SimuComWorkflowConfiguration configuration) {
		this.path = (String) configuration.getAttributes().get(
				SimulizarConstants.DEM_FILE);
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(final IProgressMonitor monitor)
			throws JobFailedException, UserCanceledException {
		if (this.getPCMResourceSetPartition() == null) {
			throw new PMSModelLoadException(
					"The PCM models must be loaded first");
		}

		final DEMResourceSetPartition demPartition = new DEMResourceSetPartition(
				this.getPCMResourceSetPartition());
		if (!this.getPath().equals("")) {

			// add file protocol if necessary
			String filePath = getPath();
			if (!getPath().startsWith("platform:")) {
				filePath = "file:///" + filePath;
			}

			demPartition.loadModel(URI.createURI(filePath));

		}
		this.getBlackboard().addPartition(DE_MODEL_PARTITION_ID, demPartition);
		// now resolve all cross references from current resource to PCM
		demPartition.resolveAllProxies();

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
		return "Perform DE Model Load";
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
	public void cleanup(final IProgressMonitor monitor)
			throws CleanupFailedException {

	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IBlackboardInteractingJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
	 */
	@Override
	public void setBlackboard(final MDSDBlackboard blackboard) {
		this.blackboard = blackboard;

	}

}
