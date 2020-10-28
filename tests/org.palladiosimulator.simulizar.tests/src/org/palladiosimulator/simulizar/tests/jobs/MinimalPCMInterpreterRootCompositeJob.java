package org.palladiosimulator.simulizar.tests.jobs;

import java.util.Objects;

import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.desmoj.DesmoJSimEngineFactory;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class MinimalPCMInterpreterRootCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    protected SimuLizarWorkflowConfiguration configuration;

    public MinimalPCMInterpreterRootCompositeJob(SimuLizarWorkflowConfiguration configuration,
            MDSDBlackboard blackboard) {
        super(false);
        this.configuration = configuration;

        if (Objects.requireNonNull(blackboard).hasPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID)) {
            throw new IllegalArgumentException("Injected blackboard must not contain a PCM partition!");
        }
        this.setBlackboard(Objects.requireNonNull(blackboard));
        // this.setBlackboard(new MDSDBlackboard());
        // do not look for registered extensions; consequently, those won't be loaded
        this.addJob(createModelsLoaderJob());
        this.addJob(createSimulatorJob());
    }
    
    protected IJob createModelsLoaderJob() {
        return new LoadSimuLizarModelsIntoBlackboardJob(
            Objects.requireNonNull(configuration, "Workflow config must not be null!"), false);
    }
    
    protected IJob createSimulatorJob() {
        return new PCMStartInterpretationJob(configuration) {
            @Override
            protected Module createSimulationModule(IProgressMonitor monitor) {
                var superModule = super.createSimulationModule(monitor);
                return Modules.override(superModule).with(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(ISimEngineFactory.class).to(DesmoJSimEngineFactory.class);
                    }
                });
            }
        };
    }
}
