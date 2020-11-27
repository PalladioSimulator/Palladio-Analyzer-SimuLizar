package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.SimuLizarModelCompletionComponent;
import org.palladiosimulator.simulizar.SimuLizarModelLoadComponent;
import org.palladiosimulator.simulizar.SimuLizarSimulationComponent;
import org.palladiosimulator.simulizar.modules.core.ExtensionFactoriesModule;
import org.palladiosimulator.simulizar.modules.custom.CustomMDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.modules.custom.CustomSimuLizarExtensionsProvidingModule;

import dagger.Module;

@Module(subcomponents = { SimuLizarModelLoadComponent.class, SimuLizarModelCompletionComponent.class, SimuLizarSimulationComponent.class }, 
        includes = { CustomSimuLizarExtensionsProvidingModule.class, CustomMDSDBlackboardProvidingModule.class,
                SimuLizarConfigurationModule.class, ExtensionFactoriesModule.class })
public interface SimuLizarRootModule {

}
