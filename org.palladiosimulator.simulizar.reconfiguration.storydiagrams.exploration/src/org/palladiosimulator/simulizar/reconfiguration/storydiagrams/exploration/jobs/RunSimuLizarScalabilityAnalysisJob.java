package org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.SDMReconfigurationSpaceExplorer;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

public class RunSimuLizarScalabilityAnalysisJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

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

        final URI temporaryReachabilityGraphURI = URI.createPlatformResourceURI(temporaryDataLocation
                + "/model/simulizar.reachabilitygraph", true);

        final List<EObject> reachabilityGraph = resourceSetPartition.getContents(temporaryReachabilityGraphURI);
        try {
            resourceSetPartition.storeAllResources();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final PCMResourceSetPartition pcmPartition = new PCMResourceSetPartition();
        this.blackboard.removePartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);

        // List<PCMStartInterpretationJob> simuLizarJobs;
        for (final EObject stepGraph : reachabilityGraph) {
            final StepGraph models = ((StepGraph) stepGraph);

            // resource.getContents().addAll(models.getContainedNodes());
            for (int i = 0; i < models.getContainedNodes().size(); i++) {
                final Resource resource = pcmPartition.getResourceSet().createResource(
                        URI.createFileURI(temporaryDataLocation + "/model/PCM_partition_state_" + models.getHash()));
                final EObject model = models.getContainedNodes().get(i);
                LOGGER.info("Adding model " + model.toString());
                resource.getContents().add(model);
            }

            try {
                pcmPartition.storeAllResources();
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            final MDSDBlackboard jobBlackboard = new MDSDBlackboard();
            jobBlackboard.addPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, pcmPartition);
            final PCMStartInterpretationJob simulizarJob = new PCMStartInterpretationJob(this.configuration);
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
