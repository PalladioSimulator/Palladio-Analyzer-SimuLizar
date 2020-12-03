package org.palladiosimulator.simulizar.modules.eclipse;

import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.extension.SimuLizarExtension;
import org.palladiosimulator.simulizar.extension.adapter.EclipseExtensionPointExtensionAdapter;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoSet;

@Module
public interface EclipseSimuLizarExtensionModule {
    public static final String SIMULIZAREXTENSION_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.extension";
    public static final String SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE = "simulizarExtension";

    
    @Provides
    @ElementsIntoSet
    public static Set<SimuLizarExtension.Factory<?>> provideFactories() {
        return Set.copyOf(ExtensionHelper.getExecutableExtensions(SIMULIZAREXTENSION_EXTENSION_POINT_ID,
                SIMULIZAREXTENSION_EXTENSION_ATTRIBUTE));
    }
    
    @Provides
    @IntoSet
    static SimuLizarExtension.Factory<?> provideLegacyAdapter(EclipseExtensionPointExtensionAdapter impl) {
        return (comp) -> impl;
    }
}
