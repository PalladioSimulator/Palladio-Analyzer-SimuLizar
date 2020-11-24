package org.palladiosimulator.simulizar;

import javax.inject.Singleton;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.extension.InterpreterSwitchExtensionRegistry;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.modules.BasicSimuLizarExtensionModule;
import org.palladiosimulator.simulizar.modules.DefaultQUALModule;
import org.palladiosimulator.simulizar.modules.EclipseIDEPreferencesModule;
import org.palladiosimulator.simulizar.modules.SimuLizarCoreAggregateModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.runtimestate.SimulationCancelationDelegate;

import dagger.BindsInstance;
import dagger.Component;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
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
@Component(modules = { SimuLizarCoreAggregateModule.class, DefaultQUALModule.class, EclipseIDEPreferencesModule.class, BasicSimuLizarExtensionModule.class })
@Singleton
public interface SimuLizarCoreComponent {

    /**
     * @return the {@link SimuLizarRuntimeState} instance of this component. The state is created if
     *         called the first time.
     */
    SimuLizarRuntimeState runtimeState();

    IResourceTableManager getResourceTableManager();

    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> getResourceContainerLookup();

    ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext> getTransmissionInterpreter();
    
    InterpreterSwitchExtensionRegistry extensionRegistry();

    @Component.Builder
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
