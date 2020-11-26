package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.simulizar.modules.core.DebuggingModule;
import org.palladiosimulator.simulizar.modules.core.LinkingResourceSimulationModule;
import org.palladiosimulator.simulizar.modules.core.SimuComFrameworkModule;
import org.palladiosimulator.simulizar.modules.core.SimuLizarCoreSimulationModule;
import org.palladiosimulator.simulizar.modules.core.SimuLizarInterpreterExtensionSupportModule;

import dagger.Module;

@Module(includes = { DebuggingModule.class, LinkingResourceSimulationModule.class,
        SimuComFrameworkModule.class, SimuLizarCoreSimulationModule.class, SimuLizarInterpreterExtensionSupportModule.class,
        SimulationCancelationModule.class })
public interface SimuLizarCoreAggregateModule {

}
