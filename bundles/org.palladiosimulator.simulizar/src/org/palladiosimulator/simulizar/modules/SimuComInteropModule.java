package org.palladiosimulator.simulizar.modules;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.entity.access.SimulatedContainerAssemblyAllocationLookupAdapter;
import org.palladiosimulator.simulizar.runtimestate.SimuComModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

@Module
public interface SimuComInteropModule {

    @Provides
    @Singleton
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
}
