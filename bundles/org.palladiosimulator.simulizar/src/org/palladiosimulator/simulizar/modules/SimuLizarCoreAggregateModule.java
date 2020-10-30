package org.palladiosimulator.simulizar.modules;

import dagger.Module;

@Module(includes = { CoreInterpretersModule.class, DebuggingModule.class, LinkingResourceSimulationModule.class,
        SchedulerModule.class, SimuComInteropModule.class, SimuLizarCoreModule.class })
public interface SimuLizarCoreAggregateModule {

}
