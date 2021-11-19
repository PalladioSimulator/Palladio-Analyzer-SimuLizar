package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultRootContext;
import org.palladiosimulator.simulizar.interpreter.listener.InterpreterResultEventEmitter;
import org.palladiosimulator.simulizar.interpreter.listener.InterpreterResultListenerDispatch;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultHandler;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.impl.BasicInterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.impl.NoIssuesHandler;
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
    
    @Binds
    @SimulationRuntimeScope
    InterpreterResultMerger bindResultMerger(BasicInterpreterResultMerger merger);
    
    @Binds
    @SimulationRuntimeScope
    InterpreterResultHandler bindResultHandler(NoIssuesHandler merger);
    
    @Binds
    @SimulationRuntimeScope
    InterpreterResultEventEmitter bindEventEmitter(InterpreterResultListenerDispatch impl);
}
