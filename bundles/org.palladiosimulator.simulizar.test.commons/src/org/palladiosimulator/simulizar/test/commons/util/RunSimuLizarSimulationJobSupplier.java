package org.palladiosimulator.simulizar.test.commons.util;

import java.util.Optional;

import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarSimulationJobSupplier extends AbstractSimulationJobSupplier<SimuLizarWorkflowConfiguration> {
    
    public RunSimuLizarSimulationJobSupplier(ExtensionContext context) {
        super(Optional.of(SimuLizarWorkflowConfiguration.class), context);
    }

    @Override
    public IBlackboardInteractingJob<MDSDBlackboard> get() {
        return new PCMStartInterpretationJob(configuration.get()) {
            @Override
            protected SimuLizarRuntimeState buildRuntimeState(IProgressMonitor monitor) {
                return buildRuntimeState(DaggerSimuLizarTestComponent.builder(), monitor);
            }
        };
    }

}
