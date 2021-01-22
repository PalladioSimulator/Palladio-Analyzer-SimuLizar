package org.palladiosimulator.simulizar.di.component.core;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRuntimeModule;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.launcher.jobs.SimuLizarRuntimeJob;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import dagger.Component;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;

@Component(dependencies = { SimuLizarRootComponent.class, SimuComFrameworkComponent.class, QUALComponent.class,
        SimEngineComponent.class }, modules = { SimuLizarRuntimeModule.class })
@SimulationRuntimeScope
public interface SimuLizarRuntimeComponent extends AnalysisRuntimeComponent {
    
    @Override
    SimuLizarRuntimeJob runtimeJob();
    
    PCMPartitionManager globalPartitionManager();

    @Global PCMResourceSetPartition globalPCMModel();

    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> resourceContainerLookup();
    
    ComponentInstanceRegistry componentInstanceRegistry();
    
    AssemblyAllocationManager allocationManager();
    
    EventDispatcher eventDispatcher();
    
    @MainContext InterpreterDefaultContext mainContext();

    @Component.Factory
    public interface Factory {
        SimuLizarRuntimeComponent create(
                SimuLizarRootComponent rootComponent,
                SimuComFrameworkComponent frameworkComponent, 
                QUALComponent qualComponent,
                SimEngineComponent simEngineComponent);
    }

}
