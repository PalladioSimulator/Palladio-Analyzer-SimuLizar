package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.analyzer.workflow.jobs.LoadModelIntoBlackboardJob;
import org.palladiosimulator.analyzer.workflow.jobs.PreparePCMBlackboardPartitionJob;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

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
            final boolean loadExtensions) {
        super(false);

        addJob(new PreparePCMBlackboardPartitionJob());
        
        addLoadPCMModelJobs(configuration);
        addLoadMonitorRepository(configuration);
        addSLORepository(configuration);
        addUsageEvolution(configuration);
        
        if (loadExtensions) {
            addModelLoadExtensionJobs(configuration);
        }
        
    }
    
    protected void addLoadPCMModelJobs(final SimuLizarWorkflowConfiguration configuration) {
        configuration.getPCMModelFiles().forEach(m -> LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(m, this));
    }
    
    protected void addLoadMonitorRepository(final SimuLizarWorkflowConfiguration configuration) {
        LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(configuration.getMonitorRepositoryFile(), this);
    }
    
    protected void addSLORepository(final SimuLizarWorkflowConfiguration configuration) {
        LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(configuration.getServiceLevelObjectivesFile(), this);
    }
    
    protected void addUsageEvolution(final SimuLizarWorkflowConfiguration configuration) {
        LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(configuration.getUsageEvolutionFile(), this);
    }
    
    private void addModelLoadExtensionJobs(final SimuLizarWorkflowConfiguration configuration) {
        final Iterable<AbstractWorkflowExtensionJob<MDSDBlackboard>> loadJobs = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE);

        for (final AbstractWorkflowExtensionJob<MDSDBlackboard> loadJob : loadJobs) {
            // check if corresponding config builder is available
            // filter available extensions by name of job class
            // this can be improved
            final AbstractWorkflowExtensionConfigurationBuilder builder = ExtensionHelper.getExecutableExtension(
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
