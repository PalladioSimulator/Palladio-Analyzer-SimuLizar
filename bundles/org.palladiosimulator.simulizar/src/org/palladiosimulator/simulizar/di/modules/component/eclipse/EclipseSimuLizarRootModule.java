package org.palladiosimulator.simulizar.di.modules.component.eclipse;

import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.eclipse.EclipseSimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRootModule;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = { SimuLizarRootModule.class })
public interface EclipseSimuLizarRootModule {
    @Binds SimuLizarRootComponent bindRootComponent(EclipseSimuLizarRootComponent impl); 

    @Provides
    @ElementsIntoSet
    static Set<ExtensionComponent.Factory> provideExtensionFactories() {
        return ImmutableSet.copyOf(ExtensionHelper.getExecutableExtensions(
                    SimulizarConstants.EXTENSION_COMPONENT_EXTENSION_POINT_ID,
                    SimulizarConstants.EXTENSION_COMPONENT_EXTENSION_POINT_ATTRIBUTE));
        
    }
}
