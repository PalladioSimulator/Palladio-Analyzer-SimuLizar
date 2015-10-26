package org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.analyzer.workflow.jobs.ValidatePCMModelsJob;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.impl.RepositoryImpl;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.SDMReconfigurationSpaceExplorer;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.mdsd.validation.ModelValidationJob;
import de.uni_paderborn.fujaba.muml.reachanalysis.reachabilityGraph.sdm.StepGraph;

public class RunSimuLizarScalabilityAnalysisJob extends SequentialJob
		implements IBlackboardInteractingJob<MDSDBlackboard> {

	private static final Logger LOGGER = Logger.getLogger(RunSimuLizarScalabilityAnalysisJob.class.getName());

	// private final SDMReconfigurationSpaceExplorer explorer;

	private final SimuLizarWorkflowConfiguration configuration;

	private MDSDBlackboard blackboard;

	private final List<PCMStartInterpretationJob> jobs;

	public RunSimuLizarScalabilityAnalysisJob(final SimuLizarWorkflowConfiguration configuration) {
		super();
		this.configuration = configuration;
		this.jobs = new ArrayList<PCMStartInterpretationJob>();
	}

	@Override
	public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
		final ResourceSetPartition resourceSetPartition = this.blackboard
				.getPartition(SDMReconfigurationSpaceExplorer.SDM_RECONFIGURATION_STATE_SPACE);

		final String temporaryDataLocation = this.configuration.getTemporaryDataLocation();

		createAndOpenProject(monitor, temporaryDataLocation);

		final URI temporaryReachabilityGraphURI = URI
				.createPlatformResourceURI(temporaryDataLocation + "/model/simulizar.reachabilitygraph", true);

		final List<EObject> reachabilityGraph = resourceSetPartition.getContents(temporaryReachabilityGraphURI);
		try {
			resourceSetPartition.storeAllResources();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final PCMResourceSetPartition pcmPartition = new PCMResourceSetPartition();

		/*
		 * Setting up the name of the ExperimentSetting in the EDP2 repository
		 * so that all the jobs created below fall under the separate
		 * ExperimentSetting.
		 */
		Map<String, Object> attributes = new TreeMap<String, Object>(configuration.getAttributes());
		attributes.put("variationId", Calendar.getInstance().getTime().toString());

		// List<PCMStartInterpretationJob> simuLizarJobs;
		int j = 0;
		for (final EObject stepGraph : reachabilityGraph) {
			final StepGraph models = ((StepGraph) stepGraph);

			URI modelURI = URI.createFileURI(temporaryDataLocation + "/model/PCM_partition_state_" + j++);

			// resource.getContents().addAll(models.getContainedNodes());
			for (int i = 0; i < models.getContainedNodes().size(); i++) {
				final Resource resource = pcmPartition.getResourceSet().createResource(
						URI.createFileURI(temporaryDataLocation + "/model/PCM_partition_state_" + models.getHash()));
				final EObject model = EcoreUtil.copy(models.getContainedNodes().get(i));
				Diagnostic diagnostic = Diagnostician.INSTANCE.validate(model);
				if(!(diagnostic.getSeverity() == Diagnostic.OK)){
					StringBuilder sb = new StringBuilder();
					sb.append("The validation failed at: ").append(model.toString()).append("\n");
					sb.append(diagnostic).append("\n");
					throw new RuntimeException(sb.toString());
				}
				LOGGER.info("Adding model " + model.toString());
				resource.getContents().add(model);
				exportPcmModel(model, modelURI, monitor);
			}

			try {
				pcmPartition.storeAllResources();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			final MDSDBlackboard jobBlackboard = new MDSDBlackboard();
			jobBlackboard.addPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, pcmPartition);

			/*
			 * Creating a new configuration for a job with a new SimuComConfig
			 * in order to have a new ExperimentRun in the EDP2 repository per
			 * job. New ExperimentRun is created every time new SimuComConfig is
			 * created.
			 */
			SimuLizarWorkflowConfiguration conf = new SimuLizarWorkflowConfiguration(attributes);

			conf.setMonitorRepositoryFile(this.configuration.getMonitorRepositoryFile());
			conf.setReconfigurationRulesFolder(this.configuration.getReconfigurationRulesFolder());
			conf.setUsageEvolutionFile(this.configuration.getUsageEvolutionFile());
			conf.setServiceLevelObjectivesFile(this.configuration.getServiceLevelObjectivesFile());

			SimuComConfig simulationConfiguration = new SimuComConfig(attributes, false);
			conf.setSimuComConfiguration(simulationConfiguration);

			final PCMStartInterpretationJob simulizarJob = new PCMStartInterpretationJob(conf);
			simulizarJob.setBlackboard(jobBlackboard);
			this.add(simulizarJob);

		}

		for (final IJob job : this.myJobs) {
			if (job instanceof IBlackboardInteractingJob) {
				((IBlackboardInteractingJob) job).setBlackboard(this.blackboard);
			}
		}
		super.execute(monitor);
	}

	private void exportPcmModel(final EObject model, final URI folderPath, IProgressMonitor monitor) {
		IWorkspaceRunnable exportPcmModelRunnable = new IWorkspaceRunnable() {

			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				try {
					final ResourceSet resourceSet = new ResourceSetImpl();
					String modelURI = folderPath.toString() + "/" + model.toString();
					if (model.toString().contains("Repository")) {
						modelURI += ".repository";
					}
					if (model.toString().contains("System")) {
						modelURI += ".system";
					}
					if (model.toString().contains("ResourceEnvironment")) {
						modelURI += ".resourceenvironment";
					}
					if (model.toString().contains("Allocation")) {
						modelURI += ".allocation";
					}
					if (model.toString().contains("UsageModel")) {
						modelURI += ".usagemodel";
					}
					final URI fileURI = URI.createPlatformResourceURI(modelURI, true);
					final Resource resource = resourceSet.createResource(fileURI);
					resource.getContents().add(model);

					// Save the contents of the resource to the file system.
					final Map<Object, Object> options = new HashMap<Object, Object>();
					options.put(XMLResource.OPTION_ENCODING, "utf-8");
					resource.save(options);
				} catch (IOException e) {
					LOGGER.error("PCM model \"" + model.toString() + "\" could not be exported!");
					e.printStackTrace();
				}
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(exportPcmModelRunnable, monitor);
		} catch (CoreException e) {
			LOGGER.error("IWorkspaceRunnable could not be executed ");
			e.printStackTrace();
		}

	}

	@Override
	public void cleanup(final IProgressMonitor arg0) throws CleanupFailedException {
		// TODO Auto-generated method stub

	}

	/**
	 * @param progessMonitor
	 * @param temporaryProject
	 */
	private void createAndOpenProject(final IProgressMonitor progessMonitor, final String temporaryDataLocation) {

		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		final IProject temporaryProject = workspaceRoot.getProject(temporaryDataLocation);

		if (!temporaryProject.exists()) {
			try {
				temporaryProject.create(progessMonitor);
				temporaryProject.open(null);
			} catch (final CoreException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} else if (temporaryProject.exists() && !temporaryProject.isOpen()) {
			try {
				temporaryProject.open(null);
			} catch (final CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBlackboard(final MDSDBlackboard blackboard) {
		this.blackboard = blackboard;
	}

	public List<PCMStartInterpretationJob> getJobs() {
		return this.jobs;
	}

}
