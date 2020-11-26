package org.palladiosimulator.simulizar;

import dagger.Subcomponent;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Subcomponent
public interface SimuLizarModelCompletionComponent {

    MDSDBlackboard blackboard();
    
    @Subcomponent.Builder
    public interface Builder {
        SimuLizarModelCompletionComponent build();
    }
}
