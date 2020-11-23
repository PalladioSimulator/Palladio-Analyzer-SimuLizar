package org.palladiosimulator.simulizar.test.commons.util;

import java.util.Collections;
import java.util.Set;

import org.palladiosimulator.simulizar.extension.SimuLizarExtension;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public class TestSimuLizarExtensionModule {
    @Provides
    @ElementsIntoSet
    static Set<SimuLizarExtension> provideSimuLizarExtensions() {
        return Collections.emptySet();
    }
}
