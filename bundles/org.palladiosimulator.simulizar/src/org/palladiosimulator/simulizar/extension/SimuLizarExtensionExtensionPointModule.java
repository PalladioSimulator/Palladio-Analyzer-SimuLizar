package org.palladiosimulator.simulizar.extension;

import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public interface SimuLizarExtensionExtensionPointModule {
    public static final String SIMULIZAREXTENSION_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.extension";
    public static final String SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE = "simulizarExtension";

    @Provides
    @ElementsIntoSet
    static Set<SimuLizarExtension> provideSimuLizarExtensions() {
        return Set.copyOf(ExtensionHelper.getExecutableExtensions(SIMULIZAREXTENSION_EXTENSION_POINT_ID,
                SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE));
    }
}
