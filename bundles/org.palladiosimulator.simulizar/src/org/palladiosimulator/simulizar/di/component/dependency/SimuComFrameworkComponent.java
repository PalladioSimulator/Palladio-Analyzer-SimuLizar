package org.palladiosimulator.simulizar.di.component.dependency;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.di.base.scopes.SimuComFrameworkScope;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuComFrameworkModule;

import dagger.BindsInstance;
import dagger.Component;
import de.uka.ipd.sdq.probfunction.math.IRandomGenerator;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.core.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.core.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.core.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.core.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.core.resources.ISimulatedModelEntityAccess;
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
    
    IRandomGenerator randomGenerator();

    @Component.Factory
    public interface Factory {
        SimuComFrameworkComponent create(@BindsInstance SimuComConfig simuComConfig, SimEngineComponent simEngineComponent, QUALComponent qualComponent);
    }

}
