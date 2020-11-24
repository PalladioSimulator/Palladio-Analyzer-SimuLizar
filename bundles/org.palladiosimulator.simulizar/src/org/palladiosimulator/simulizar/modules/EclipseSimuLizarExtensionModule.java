package org.palladiosimulator.simulizar.modules;

import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.SimuLizarCoreComponent;
import org.palladiosimulator.simulizar.extension.SimuLizarExtensionFactory;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module(includes = BasicSimuLizarExtensionModule.class)
public interface EclipseSimuLizarExtensionModule {
    public static final String SIMULIZAREXTENSION_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.extension";
    public static final String SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE = "simulizarExtension";

    @Provides
    @ElementsIntoSet
    public static Set<SimuLizarExtensionFactory<?>> provideExtensionFactories(SimuLizarCoreComponent component) {
        return Set.copyOf(ExtensionHelper.getExecutableExtensions(SIMULIZAREXTENSION_EXTENSION_POINT_ID,
                SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE));
    }
}
