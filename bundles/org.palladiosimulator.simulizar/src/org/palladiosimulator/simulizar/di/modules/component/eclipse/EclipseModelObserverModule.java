package org.palladiosimulator.simulizar.di.modules.component.eclipse;

import java.util.List;
import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationEngine;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.ObservationScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.common.collect.ImmutableSet;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public interface EclipseModelObserverModule {
    
    @Provides
    @ObservationScope
    @ElementsIntoSet
    static Set<IReconfigurationEngine> provideReconfigurationEngines(SimuLizarWorkflowConfiguration configuration,
            PCMPartitionManager partitionManager) {
        final List<IReconfigurationEngine> reconfigEngines = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ID,
                SimulizarConstants.RECONFIGURATION_ENGINE_EXTENSION_POINT_ENGINE_ATTRIBUTE);
        reconfigEngines.forEach(engine -> {
            engine.setConfiguration(configuration);
            engine.setPCMPartitionManager(partitionManager);
        });
        return ImmutableSet.copyOf(reconfigEngines);
    }
    

    

}
