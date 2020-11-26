package org.palladiosimulator.simulizar.modules.eclipse;

import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.extension.SimuLizarExtension.Builder;

import dagger.Module;

@Module
public class EclipseSimuLizarExtensionModule {
    public static final String SIMULIZAREXTENSION_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.extension";
    public static final String SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE = "simulizarExtension";

    public static Set<Builder<?>> provideBuilders() {
        return Set.copyOf(ExtensionHelper.getExecutableExtensions(SIMULIZAREXTENSION_EXTENSION_POINT_ID,
                SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE));
    }
}
