package org.palladiosimulator.simulizar.tests.jobs;

import java.util.Objects;

import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public final class MinimalPCMInterpreterRootCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    public MinimalPCMInterpreterRootCompositeJob(SimuLizarWorkflowConfiguration configuration,
            MDSDBlackboard blackboard) {
        super(false);

        if (Objects.requireNonNull(blackboard).hasPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID)) {
            throw new IllegalArgumentException("Injected blackboard must not contain a PCM partition!");
        }
        this.setBlackboard(Objects.requireNonNull(blackboard));
        // this.setBlackboard(new MDSDBlackboard());
        // do not look for registered extensions; consequently, those won't be loaded
        this.addJob(new LoadSimuLizarModelsIntoBlackboardJob(
                Objects.requireNonNull(configuration, "Workflow config must not be null!"), false));
        this.addJob(new PCMStartInterpretationJob(configuration));
    }
}
