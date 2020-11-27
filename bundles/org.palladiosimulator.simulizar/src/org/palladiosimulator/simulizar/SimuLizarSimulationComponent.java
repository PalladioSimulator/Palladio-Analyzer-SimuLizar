package org.palladiosimulator.simulizar;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.extension.InterpreterSwitchExtensionRegistry;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.modules.SimuLizarCoreAggregateModule;
import org.palladiosimulator.simulizar.modules.core.DefaultQUALModule;
import org.palladiosimulator.simulizar.modules.core.SimuLizarInterpreterExtensionSupportModule;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.scopes.SimulationScope;

import dagger.Subcomponent;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This component interface constitutes the foundation of all SimuLizar based dagger components.
 * 
 * SimuLizar analysis should provide an own component interface which extends this component and
 * binds the relevant modules. 
 * 
 * The default component for a standard SimuLizar simulation can be found in {@link SimuLizarRootComponent}.
 *
 */
@Subcomponent(modules = { SimuLizarCoreAggregateModule.class, DefaultQUALModule.class, SimuLizarInterpreterExtensionSupportModule.class })
@SimulationScope
public interface SimuLizarSimulationComponent {
    /**
     * @return the {@link SimuLizarRuntimeState} instance of this component. The state is created if
     *         called the first time.
     */
    SimuLizarRuntimeState runtimeState();

    IResourceTableManager getResourceTableManager();

    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> getResourceContainerLookup();

    ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext> getTransmissionInterpreter();
    
    InterpreterSwitchExtensionRegistry extensionRegistry();
    
    IObservableCalculatorRegistry calculatorRegistry();
    
    @Subcomponent.Builder
    public interface Builder {
        SimuLizarSimulationComponent build();
    }

}
