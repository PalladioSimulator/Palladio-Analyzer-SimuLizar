package org.palladiosimulator.simulizar.test.commons.util;

import java.util.function.Supplier;

import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.desmoj.DesmoJSimEngineFactory;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarSimulationJobSupplier implements Supplier<IBlackboardInteractingJob<MDSDBlackboard>> {
    protected final SimuLizarWorkflowConfiguration configuration;

    public RunSimuLizarSimulationJobSupplier(ExtensionContext context) {
        configuration = SimuLizarTestExtensionCommons.getObjectFromStore(context, SimuLizarWorkflowConfiguration.class)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Configuration present repository initialized. Please make sure to annotate your test accordingly."));
    }

    @Override
    public IBlackboardInteractingJob<MDSDBlackboard> get() {
        var result = new SequentialBlackboardInteractingJob<MDSDBlackboard>(false);
        
        result.add(new PCMStartInterpretationJob(configuration) {
            @Override
            protected Module createSimulationModule(IProgressMonitor monitor) {
                var superModule = super.createSimulationModule(monitor);
                return Modules.override(superModule)
                    .with(new AbstractModule() {
                        @Override
                        protected void configure() {
                            bind(ISimEngineFactory.class).to(DesmoJSimEngineFactory.class);
                        }
                    });
            }
        });

        return result;
    }

}
