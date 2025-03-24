package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import java.util.Set;

import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager.Global;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ReconfigurationExtensions;
import org.palladiosimulator.simulizar.di.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.NumberOfResourceContainerTrackingListener;
import org.palladiosimulator.simulizar.reconfiguration.ReconfigurationProcessFactory;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationTimeProvider;

@Module(includes = ReconfigurationExtensions.class)
public interface ReconfiguratorBindingsModule {
    
    @Provides
    static IReconfigurationListener bindReconfigurationListenerDispatcher(Reconfigurator reconfigurator) {
        return reconfigurator.getEventDispatcher();
    }
    
    @Provides
    @SimulationRuntimeScope
    static Reconfigurator provideReconfigurator(@Global PCMResourceSetPartition partition,
            ReconfigurationProcessFactory processFactory,
            ISimulationTimeProvider simTimeProvider,
            Set<IReconfigurationListener> reconfigurationListeners) {
        var reconfigurator = new Reconfigurator(partition, processFactory, simTimeProvider);
        reconfigurationListeners.forEach(reconfigurator::addObserver);
        return reconfigurator;
    }
    
    @Binds
    @IntoSet
    IModelObserver bindReconfigurator(Reconfigurator impl);
    
    @Binds
    @IntoSet
    @SimulationRuntimeScope
    IReconfigurationListener bindResourceContainerCounter(NumberOfResourceContainerTrackingListener impl);
}
