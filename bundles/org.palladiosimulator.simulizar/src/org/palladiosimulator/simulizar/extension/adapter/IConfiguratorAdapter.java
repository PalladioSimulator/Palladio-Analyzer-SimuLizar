package org.palladiosimulator.simulizar.extension.adapter;

import org.palladiosimulator.simulizar.extension.facets.ModelCompletion;
import org.palladiosimulator.simulizar.launcher.IConfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class IConfiguratorAdapter implements ModelCompletion {
    
    private final IConfigurator configurator;
    private final MDSDBlackboard blackboard;
    private final SimuLizarWorkflowConfiguration workflowConfiguration;

    public IConfiguratorAdapter(IConfigurator configurator, SimuLizarWorkflowConfiguration workflowConfiguration,
            MDSDBlackboard blackboard) {
        this.configurator = configurator;
        this.workflowConfiguration = workflowConfiguration;
        this.blackboard = blackboard;
    }
    
    @Override
    public boolean runModelCompletion() {
        configurator.configure(workflowConfiguration, blackboard);
        return false;
    }

}
