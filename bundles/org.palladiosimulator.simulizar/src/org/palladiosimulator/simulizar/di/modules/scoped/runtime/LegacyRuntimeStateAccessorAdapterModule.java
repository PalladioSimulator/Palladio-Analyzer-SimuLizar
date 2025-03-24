package org.palladiosimulator.simulizar.di.modules.scoped.runtime;

import java.util.List;
import java.util.Set;

import javax.inject.Provider;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.common.collect.ImmutableSet;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoSet;

/**
 * This module will be removed once org.simulizar.action has been migrated to the extension API
 * 
 * TODO Remove.
 */
@Deprecated
@Module
public interface LegacyRuntimeStateAccessorAdapterModule {
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<IRuntimeStateAccessor> provideRuntimeStateAccessors(SimuLizarWorkflowConfiguration configuration,
            PCMPartitionManager partitionManager) {
        final List<IRuntimeStateAccessor> reconfigLoaders = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.RUNTIME_STATE_ACCESS_EXTENSION_POINT_ID,
                SimulizarConstants.RUNTIME_STATE_ACCESS_EXTENSION_POINT_ACCESSOR_ATTRIBUTE);
        return ImmutableSet.copyOf(reconfigLoaders);
    }
    
    @Provides
    @SimulationRuntimeScope
    @IntoSet
    static RuntimeStateEntityObserver provideIRuntimeStateAdapter(Set<IRuntimeStateAccessor> accessors, Provider<SimuLizarRuntimeState> runtimeStateProvider) {
        return new RuntimeStateEntityObserver() {
            @Override
            public void initialize() {
                var runtimeState = runtimeStateProvider.get();
                accessors.forEach(accessor -> accessor.setRuntimeStateModel(runtimeState));
                RuntimeStateEntityObserver.super.initialize();
            }
        };
    }
}
