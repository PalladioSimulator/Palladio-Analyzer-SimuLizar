package org.palladiosimulator.simulizar.modules.component.extensions;

import java.util.Set;

import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationEngine;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public interface ReconfigurationExtensions {
    @Multibinds
    Set<AbstractReconfigurationLoader> bindReconfigurationLoaders();
    
    @Multibinds
    Set<IReconfigurationEngine> bindReconfigurationEngines();
}
