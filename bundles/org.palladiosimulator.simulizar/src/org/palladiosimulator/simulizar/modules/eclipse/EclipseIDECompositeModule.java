package org.palladiosimulator.simulizar.modules.eclipse;

import dagger.Module;

@Module(includes = { EclipseIDEPreferencesModule.class, EclipseSimuLizarExtensionModule.class })
public interface EclipseIDECompositeModule {
}
