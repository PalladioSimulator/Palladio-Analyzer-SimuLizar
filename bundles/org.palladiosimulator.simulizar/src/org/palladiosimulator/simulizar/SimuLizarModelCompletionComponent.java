package org.palladiosimulator.simulizar;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Subcomponent;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Subcomponent
public interface SimuLizarModelCompletionComponent {

    MDSDBlackboard blackboard();
    
    SimuLizarWorkflowConfiguration configuration();
    
    @Subcomponent.Builder
    public interface Builder {
        SimuLizarModelCompletionComponent build();
    }
}
