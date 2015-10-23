package org.palladiosimulator.simulizar.tests.jobs;

import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public final class MinimalPCMInterpreterRootCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
        implements IBlackboardInteractingJob<MDSDBlackboard> {

    public MinimalPCMInterpreterRootCompositeJob(SimuLizarWorkflowConfiguration configuration) {
        super(false);

        this.setBlackboard(new MDSDBlackboard());
        // do not look for registered extensions; consequently, those won't be loaded
        this.addJob(new LoadSimuLizarModelsIntoBlackboardJob(configuration, false));
        this.addJob(new PCMStartInterpretationJob(configuration));
    }

}
