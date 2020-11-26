package org.palladiosimulator.simulizar.modules.custom;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Module
public class CustomMDSDBlackboardProvidingModule {
    private final MDSDBlackboard blackboard;
    
    public CustomMDSDBlackboardProvidingModule() {
        blackboard = new MDSDBlackboard();
    }
    
    public CustomMDSDBlackboardProvidingModule(MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
    }
    
    @Provides @Singleton MDSDBlackboard provideBlackboard() {
        return blackboard;
    }
}
