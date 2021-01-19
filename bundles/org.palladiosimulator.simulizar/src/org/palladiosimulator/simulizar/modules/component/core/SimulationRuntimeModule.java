package org.palladiosimulator.simulizar.modules.component.core;

import java.util.Set;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.modules.shared.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.modules.shared.core.CoreBindingsModule;
import org.palladiosimulator.simulizar.modules.shared.mdsd.PCMPartitionManagerAdapterModule;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;

@Module(includes = { CoreBindingsModule.class,
        SimuLizarConfigurationModule.class,
        PCMPartitionManagerAdapterModule.class
})
public interface SimulationRuntimeModule {
    
    @Provides
    @SimulationRuntimeScope
    @MainContext 
    static InterpreterDefaultContext provideMainContext(InterpreterDefaultContext context) {
        return context;
    }
    
    @Multibinds
    Set<IInterpreterListener> bindInterpreterListeners();
    
    @Binds
    @SimulationRuntimeScope
    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> bindAssemblyAllocationLookup(AssemblyAllocationManager manager);

}
