package org.palladiosimulator.simulizar.launcher.jobs;

import javax.inject.Inject;

import org.palladiosimulator.analyzer.workflow.jobs.PreparePCMBlackboardPartitionJob;
import org.palladiosimulator.simulizar.SimuLizarModelLoadComponent;
import org.palladiosimulator.simulizar.extension.facets.ModelLoad;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

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
    @Inject
    public LoadSimuLizarModelsIntoBlackboardJob(final SimuLizarWorkflowConfiguration configuration,
            SimuLizarModelLoadComponent.Builder modelLoadComponentBuilder,
            ModelLoad.Factory modelLoaderFactory) {
        super(false);

        this.addJob(new PreparePCMBlackboardPartitionJob());
        this.addJob(new LoadPCMModelsInterpreterJob(configuration));
        this.addJob(new LoadMonitorRepositoryModelIntoBlackboardJob(configuration));
        this.addJob(new LoadServiceLevelObjectiveRepositoryIntoBlackboardJob(configuration));
        this.addJob(new LoadUEModelIntoBlackboardJob(configuration));

        modelLoaderFactory.create(modelLoadComponentBuilder.build()).appendModelLoadJobs(this::addJob);
    }


}
