package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultRootContext;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Module
public interface CoreSimulationRuntimeEntitiesBindings {
    
    @Provides
    @SimulationRuntimeScope
    static PCMPartitionManager providePartitionManager(final MDSDBlackboard blackboard, final SimuLizarWorkflowConfiguration config) {
        return new PCMPartitionManager(blackboard, config);
    }
    
    @Provides
    @SimulationRuntimeScope
    static EventDispatcher provideEventNotificationHelper() {
        return new EventDispatcher();
    }

    @Binds
    @SimulationRuntimeScope
    @MainContext 
    InterpreterDefaultContext bindMainInterpreterDefaultContext(InterpreterDefaultRootContext rootContext);
}
