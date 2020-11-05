package org.palladiosimulator.simulizar;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.runtimestate.SimulationCancelationDelegate;

import dagger.BindsInstance;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * This component interface constitutes the foundation of all SimuLizar based dagger components.
 * 
 * SimuLizar analysis should provide an own component interface which extends this component and
 * binds the relevant modules. 
 * 
 * The default component for a standard SimuLizar simulation can be found in {@link SimuLizarComponent}.
 *
 */
public interface SimuLizarCoreComponent {

    /**
     * @return the {@link SimuLizarRuntimeState} instance of this component. The state is created if
     *         called the first time.
     */
    SimuLizarRuntimeState runtimeState();

    interface Builder {
        /**
         * Sets the cancelation delegate for the core component to construct.
         */
        @BindsInstance
        Builder cancelationDelegate(SimulationCancelationDelegate delegate);

        /**
         * Sets the workflow configuration for the core component to construct.
         */
        @BindsInstance
        Builder configuration(SimuLizarWorkflowConfiguration configuration);

        /**
         * Sets the blackboard for the core component to construct.
         */
        @BindsInstance
        Builder blackboard(MDSDBlackboard delegate);

        /**
         * Creates a new component instance.
         */
        SimuLizarCoreComponent build();
    }

}
