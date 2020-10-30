package org.palladiosimulator.simulizar;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.runtimestate.SimulationCancelationDelegate;

import dagger.BindsInstance;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Singleton
public interface SimuLizarCoreComponent {

    SimuLizarRuntimeState runtimeState();
    
    @MainContext InterpreterDefaultContext mainContext();

    interface Builder {
        @BindsInstance
        Builder cancelationDelegate(SimulationCancelationDelegate delegate);

        @BindsInstance
        Builder configuration(SimuLizarWorkflowConfiguration configuration);

        @BindsInstance
        Builder blackboard(MDSDBlackboard delegate);

        SimuLizarCoreComponent build();
    }

}
