package org.palladiosimulator.simulizar.test.commons.util;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.launcher.jobs.PCMInterpreterRootCompositeJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimulationJob;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarCompositeJobSupplier implements Supplier<IJob> {
    protected final SimuLizarWorkflowConfiguration configuration;
    protected final PCMResourceSetPartition partition;
    protected final Optional<IJob> interpretationJob;

    public RunSimuLizarCompositeJobSupplier(ExtensionContext context) {
        configuration = SimuLizarTestExtensionCommons.getObjectFromStore(context, IJobConfiguration.class)
            .filter(SimuLizarWorkflowConfiguration.class::isInstance)
            .map(SimuLizarWorkflowConfiguration.class::cast)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Configuration present repository initialized. Please make sure to annotate your test accordingly."));
        partition = SimuLizarTestExtensionCommons.getObjectFromStore(context, PCMResourceSetPartition.class)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No PCMResourceSet initialized. Please make sure to annotate your test accordingly."));
        interpretationJob = findAnnotation(context.getElement(), RunSimulationJob.class)
            .filter(annotation -> !annotation.skipSimulation())
            .map(annotation -> new RunSimuLizarSimulationJobSupplier(context).get());
    }

    @Override
    public IBlackboardInteractingJob<MDSDBlackboard> get() {
        var result = new SequentialBlackboardInteractingJob<MDSDBlackboard>(false);

        result.add(new PCMInterpreterRootCompositeJob(configuration) {
            @Override
            protected void addLoadSimuLizarModelsJob(SimuLizarWorkflowConfiguration configuration) {
                // We already loaded the partition into the blackboard through our test extension
            }

            @Override
            protected void addRunInterpretationJob(SimuLizarWorkflowConfiguration configuration) {
                interpretationJob.ifPresent(this::add);
            }
        });

        return result;
    }

}
