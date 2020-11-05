package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimulationJob;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimulationJobExtension implements BeforeEachCallback, AfterEachCallback {

    @SuppressWarnings("unchecked")
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (findAnnotation(context.getElement(), RunSimulationJob.class).isPresent()) {
            var job = SimuLizarTestExtensionCommons.getObjectFromStore(context, IJob.class)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cannot run simulation without a simulation job present. Check your annotation setup!"));
            if (job instanceof IBlackboardInteractingJob) {
                var blackboard = SimuLizarTestExtensionCommons.getObjectFromStore(context, MDSDBlackboard.class)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Simulation job requires blackboard. No MDSDBlackboard registered."));
                ((IBlackboardInteractingJob<MDSDBlackboard>) job).setBlackboard(blackboard);
            }
            var monitor = new NullProgressMonitor();
            job.execute(monitor);
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (findAnnotation(context.getElement(), RunSimulationJob.class).isPresent()) {
                var monitor = new NullProgressMonitor();
                SimuLizarTestExtensionCommons.getObjectFromStore(context, IJob.class)
                    .orElseThrow(() -> new IllegalStateException(
                            "No job was registered. There was an error during initialization."))
                    .cleanup(monitor);
            }
    }

}
