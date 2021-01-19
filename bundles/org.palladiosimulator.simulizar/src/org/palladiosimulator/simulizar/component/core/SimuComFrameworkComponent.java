package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.modules.component.core.SimuComFrameworkModule;
import org.palladiosimulator.simulizar.scopes.SimuComFrameworkScope;

import dagger.BindsInstance;
import dagger.Component;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationTimeProvider;

@Component(dependencies = { SimEngineComponent.class, QUALComponent.class }, modules = { SimuComFrameworkModule.class })
@SimuComFrameworkScope
public interface SimuComFrameworkComponent {
    
    SimuComModel simuComModel();
    
    ISimulationControl simulationControl();
    
    IResourceTableManager resourceTableManager();

    ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> resourceContainerAccess();

    ResourceRegistry resourceRegistry();
    
    ISimulationTimeProvider simTimeProvider();

    @Component.Factory
    public interface Factory {
        SimuComFrameworkComponent create(@BindsInstance SimuComConfig simuComConfig, SimEngineComponent simEngineComponent, QUALComponent qualComponent);
    }

}
