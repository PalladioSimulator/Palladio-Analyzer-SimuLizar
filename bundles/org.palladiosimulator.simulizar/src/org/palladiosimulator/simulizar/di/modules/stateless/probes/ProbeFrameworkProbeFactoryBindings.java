package org.palladiosimulator.simulizar.di.modules.stateless.probes;

import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationTimeProvider;

@dagger.Module
public interface ProbeFrameworkProbeFactoryBindings {
    
    @Provides
    static TakeCurrentSimulationTimeProbe providesGlobalPartition(ISimulationTimeProvider simulationTimeProvider) {
        return new TakeCurrentSimulationTimeProbe(simulationTimeProvider);
    }

}
