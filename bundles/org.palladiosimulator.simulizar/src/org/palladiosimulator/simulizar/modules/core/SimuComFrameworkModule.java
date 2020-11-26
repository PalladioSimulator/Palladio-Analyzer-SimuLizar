package org.palladiosimulator.simulizar.modules.core;

import org.palladiosimulator.simulizar.entity.access.SimulatedContainerAssemblyAllocationLookupAdapter;
import org.palladiosimulator.simulizar.runtimestate.SimuComModelFactory;
import org.palladiosimulator.simulizar.scopes.SimulationScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

@Module
public interface SimuComFrameworkModule {

    @Provides
    @SimulationScope
    static SimuComModel provideSimuComModel(SimuComModelFactory impl) {
        return impl.get();
    }

    @Binds
    @Reusable
    IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> bindSimulatedResourceContainerLookup(
            SimulatedContainerAssemblyAllocationLookupAdapter impl);
    
    @Provides
    @Reusable
    static ResourceRegistry provideResourceRegistry(final SimuComModel model) {
        return model.getResourceRegistry();
    }
    
    @Provides
    @Reusable
    static ISimulationControl provideSimulationControl(final SimuComModel model) {
        return model.getSimulationControl();
    }
    
    
    @Provides @SimulationScope static IResourceTableManager provideResourceTableManager() {
        return new ResourceTableManager();
    }
}
