package org.palladiosimulator.simulizar.test.commons.util;

import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.modules.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.modules.custom.CustomMDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarSimulationJobSupplier implements Supplier<IJob> {
    protected final SimuLizarWorkflowConfiguration configuration;
    protected final MDSDBlackboard blackboard;

    public RunSimuLizarSimulationJobSupplier(ExtensionContext context) {
        configuration = SimuLizarTestExtensionCommons.getObjectFromStore(context, IJobConfiguration.class)
            .filter(SimuLizarWorkflowConfiguration.class::isInstance)
            .map(SimuLizarWorkflowConfiguration.class::cast)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Configuration present repository initialized. Please make sure to annotate your test accordingly."));
        blackboard = SimuLizarTestExtensionCommons.getObjectFromStore(context, MDSDBlackboard.class)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Blackboard initialized. Please make sure to annotate your test accordingly."));
    }

    @Override
    public IBlackboardInteractingJob<MDSDBlackboard> get() {
        var result = new SequentialBlackboardInteractingJob<MDSDBlackboard>(false);
        
        var component = DaggerSimuLizarTestComponent.builder()
            .simuLizarConfigurationModule(new SimuLizarConfigurationModule(configuration))
            .customMDSDBlackboardProvidingModule(new CustomMDSDBlackboardProvidingModule(blackboard))
            .build();
        
        result.setBlackboard(blackboard);
        result.addJob(component.interpreterJob());
        
        return result;
    }

}
