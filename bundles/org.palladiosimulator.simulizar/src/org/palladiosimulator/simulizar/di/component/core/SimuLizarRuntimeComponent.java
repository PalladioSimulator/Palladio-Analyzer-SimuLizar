package org.palladiosimulator.simulizar.di.component.core;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent;
import org.palladiosimulator.simulizar.di.component.dependency.SimuComFrameworkComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.SimulatedThreadComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionLookup;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRuntimeModule;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RuntimeComponentFactoriesModule;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.launcher.jobs.SimuLizarRuntimeJob;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import dagger.Component;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

@Component(dependencies = { SimuLizarRootComponent.class, SimuComFrameworkComponent.class, QUALComponent.class,
        SimEngineComponent.class }, modules = { SimuLizarRuntimeModule.class })
@SimulationRuntimeScope
public interface SimuLizarRuntimeComponent extends AnalysisRuntimeComponent {

    @Override
    SimuLizarRuntimeJob runtimeJob();

    PCMPartitionManager globalPartitionManager();

    @Global
    PCMResourceSetPartition globalPCMModel();

    IAssemblyAllocationLookup<EntityReference<ResourceContainer>> resourceContainerLookup();

    ComponentInstanceRegistry componentInstanceRegistry();

    AssemblyAllocationManager allocationManager();

    EventDispatcher eventDispatcher();

    @MainContext
    InterpreterDefaultContext mainContext();
    
    SimulatedThreadComponent.Factory interpreterThreadComponentFactory();
    
    ITransmissionInterpreter<EntityReference<ResourceContainer>,SimulatedStackframe<Object>,InterpreterDefaultContext> transmissionInterpreter();
    
    ExtensionLookup runtimeExtensionLookup();
    
    PreInterpretationBehaviorManager preInterpretationBehaviorManager();

    @Component.Factory
    public interface Factory {
        SimuLizarRuntimeComponent create(SimuLizarRootComponent rootComponent,
                SimuComFrameworkComponent frameworkComponent, QUALComponent qualComponent,
                SimEngineComponent simEngineComponent, ExtensionComponentsModule extensionModule,
                RuntimeComponentFactoriesModule componentFactoriesModule);

        default ExtensionComponentsModule defaultExtensionComponentsModule() {
            return new ExtensionComponentsModule();
        }

        default RuntimeComponentFactoriesModule defaultRuntimeComponentFactoriesModule() {
            return new RuntimeComponentFactoriesModule();
        }

        default SimuLizarRuntimeComponent create(SimuLizarRootComponent rootComponent,
                SimuComFrameworkComponent frameworkComponent, QUALComponent qualComponent,
                SimEngineComponent simEngineComponent) {
            return create(rootComponent, frameworkComponent, qualComponent, simEngineComponent,
                    defaultExtensionComponentsModule(), defaultRuntimeComponentFactoriesModule());
        }
    }

}
