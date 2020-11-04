package org.palladiosimulator.simulizar.modules;

import dagger.Module;

@Module(includes = { DebuggingModule.class, LinkingResourceSimulationModule.class,
        SimuComFrameworkModule.class, SimuLizarCoreModule.class })
public interface SimuLizarCoreAggregateModule {

}
