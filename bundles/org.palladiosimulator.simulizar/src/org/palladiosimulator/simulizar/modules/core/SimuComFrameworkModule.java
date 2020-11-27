package org.palladiosimulator.simulizar.modules.core;

import java.util.Optional;

import org.palladiosimulator.simulizar.entity.access.SimulatedContainerAssemblyAllocationLookupAdapter;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.SimuComModelFactory;
import org.palladiosimulator.simulizar.scopes.SimulationScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
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

    @Provides
    @SimulationScope
    static IResourceTableManager provideResourceTableManager() {
        return new ResourceTableManager();
    }

    @Provides
    @Reusable
    static SimuComConfig provideSimuComConfig(SimuLizarWorkflowConfiguration workflowConfiguration) {
        return Optional.ofNullable(workflowConfiguration.getSimulationConfiguration())
            .filter(SimuComConfig.class::isInstance)
            .map(SimuComConfig.class::cast)
            .orElseThrow(() -> new IllegalStateException(
                    "SimuLizar expects the simulation configuration to be of type SimuComConfig."));
    }
}
