package org.palladiosimulator.simulizar.di.modules.stateless.mdsd;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Module
public class MDSDBlackboardProvidingModule {
    private final MDSDBlackboard blackboard;
    
    public MDSDBlackboardProvidingModule() {
        blackboard = new MDSDBlackboard();
    }
    
    public MDSDBlackboardProvidingModule(MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
    }
    
    @Provides MDSDBlackboard provideBlackboard() {
        return blackboard;
    }
}
