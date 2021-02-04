package org.palladiosimulator.simulizar.test.commons.util;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.launcher.jobs.PCMInterpreterRootCompositeJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimulationJob;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarCompositeJobSupplier extends AbstractSimulationJobSupplier<SimuLizarWorkflowConfiguration> {
    
    protected final Optional<IJob> interpretationJob;

    public RunSimuLizarCompositeJobSupplier(ExtensionContext context) {
        super(Optional.of(SimuLizarWorkflowConfiguration.class), context);
        interpretationJob = findAnnotation(context.getElement(), RunSimulationJob.class)
            .filter(annotation -> !annotation.skipSimulation())
            .map(annotation -> new RunSimuLizarSimulationJobSupplier(context).get());
    }

    @Override
    public IBlackboardInteractingJob<MDSDBlackboard> get() {
        return new PCMInterpreterRootCompositeJob(configuration.get()) {
            @Override
            protected void addLoadLinkingResourceSimulationModelsJob(SimuLizarWorkflowConfiguration configuration) {
                // We already loaded the partition into the blackboard through our test extension
            }
            
            @Override
            protected void addLoadSimuLizarModelsJob(SimuLizarWorkflowConfiguration configuration) {
                // We already loaded the partition into the blackboard through our test extension
            }

            @Override
            protected void addRunInterpretationJob(SimuLizarWorkflowConfiguration configuration) {
                interpretationJob.ifPresent(this::add);
            }
        };
    }

}
