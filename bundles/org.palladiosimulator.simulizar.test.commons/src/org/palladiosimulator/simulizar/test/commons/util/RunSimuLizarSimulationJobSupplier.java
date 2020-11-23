package org.palladiosimulator.simulizar.test.commons.util;

import java.util.function.Supplier;

import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.SimuLizarCoreComponent;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarSimulationJobSupplier implements Supplier<IJob> {
    protected final SimuLizarWorkflowConfiguration configuration;

    public RunSimuLizarSimulationJobSupplier(ExtensionContext context) {
        configuration = SimuLizarTestExtensionCommons.getObjectFromStore(context, IJobConfiguration.class)
            .filter(SimuLizarWorkflowConfiguration.class::isInstance)
            .map(SimuLizarWorkflowConfiguration.class::cast)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Configuration present repository initialized. Please make sure to annotate your test accordingly."));
    }

    @Override
    public IBlackboardInteractingJob<MDSDBlackboard> get() {
        var result = new SequentialBlackboardInteractingJob<MDSDBlackboard>(false);

        result.add(new PCMStartInterpretationJob(configuration) {
            @Override
            protected SimuLizarCoreComponent buildSimuLizarComponent(IProgressMonitor monitor) {
                return buildSimuLizarComponent(DaggerSimuLizarTestComponent.builder(), monitor);
            }
        });

        return result;
    }

}
