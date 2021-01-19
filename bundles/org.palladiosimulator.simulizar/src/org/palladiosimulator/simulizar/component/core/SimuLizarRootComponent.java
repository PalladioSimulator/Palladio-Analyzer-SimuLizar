package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.simulizar.modules.component.core.SimuLizarRootModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.BindsInstance;
import dagger.Component;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Component(modules = SimuLizarRootModule.class)
public interface SimuLizarRootComponent {
    IJob rootJob();
    
    MDSDBlackboard blackboard();
    
    SimuLizarWorkflowConfiguration config();
    
    @Component.Factory
    public interface Factory {
        SimuLizarRootComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration);
    }

}
