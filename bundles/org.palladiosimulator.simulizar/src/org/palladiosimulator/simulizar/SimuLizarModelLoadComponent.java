package org.palladiosimulator.simulizar;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Subcomponent;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Subcomponent
public interface SimuLizarModelLoadComponent {
    
    MDSDBlackboard blackboard();
    
    SimuLizarWorkflowConfiguration configuration();
    
    @Subcomponent.Builder
    public interface Builder {
        SimuLizarModelLoadComponent build();
    }
    
}
