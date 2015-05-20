package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.jobs.PreparePCMBlackboardPartionJob;

/**
 * Composite Job for preparing Blackboard and loading PCM Models into it.
 * 
 * @author Joachim Meyer
 * @author Matthias Becker
 * 
 */
public class LoadSimuLizarModelsIntoBlackboardJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    public static final String ORIGINAL_PCM_MODELS_PARTITION_ID = "de.uka.ipd.sdq.pcmmodels.partition.original";

    /**
     * @param config
     */
    public LoadSimuLizarModelsIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration) {
        super(false);

        this.addJob(new PreparePCMBlackboardPartionJob());
        this.addJob(new LoadPCMModelsInterpreterJob(configuration));
        this.addJob(new LoadMonitorRepositoryModelIntoBlackboardJob(configuration));
        this.addJob(new LoadServiceLevelObjectiveRepositoryIntoBlackboardJob(configuration));
        this.addJob(new LoadUEModelIntoBlackboardJob(configuration));
        addModelLoadExtensionJobs(configuration);
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
