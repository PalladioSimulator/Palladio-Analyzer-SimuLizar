package org.palladiosimulator.simulizar;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Subcomponent;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Subcomponent
public interface SimuLizarRootExtensionComponent {
    
    SimuLizarWorkflowConfiguration configuration();
    
    MDSDBlackboard blackboard();

    @Subcomponent.Builder
    public interface Builder {
        SimuLizarRootExtensionComponent build();
    }
}
