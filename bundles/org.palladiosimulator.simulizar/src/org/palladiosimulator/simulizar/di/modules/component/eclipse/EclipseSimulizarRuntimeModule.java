package org.palladiosimulator.simulizar.di.modules.component.eclipse;

import java.util.List;
import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.component.eclipse.EclipseSimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRuntimeModule;
import org.palladiosimulator.simulizar.di.modules.scoped.runtime.LegacyRuntimeStateAccessorAdapterModule;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = {SimuLizarRuntimeModule.class, LegacyRuntimeStateAccessorAdapterModule.class})
public interface EclipseSimulizarRuntimeModule {
    @Binds SimuLizarRuntimeComponent bindRuntimeComponent(EclipseSimuLizarRuntimeComponent impl);
    
    @Provides
    @SimulationRuntimeScope
    @ElementsIntoSet
    static Set<AbstractReconfigurationLoader> provideReconfigurationLoader(SimuLizarWorkflowConfiguration configuration,
            PCMPartitionManager partitionManager) {
        final List<AbstractReconfigurationLoader> reconfigLoaders = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.RECONFIGURATION_LOADER_EXTENSION_POINT_ID,
                SimulizarConstants.RECONFIGURATION_LOADER_EXTENSION_POINT_LOADER_ATTRIBUTE);
        return ImmutableSet.copyOf(reconfigLoaders);
    }

}
