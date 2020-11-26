package org.palladiosimulator.simulizar.modules.custom;

import java.util.Collections;
import java.util.Set;

import org.palladiosimulator.simulizar.extension.SimuLizarExtension;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public class CustomSimuLizarExtensionsProvidingModule {
    
    @Provides
    @ElementsIntoSet
    public Set<SimuLizarExtension.Builder<?>> provideBuilders() {
        return Collections.emptySet();
    }

}
