package org.palladiosimulator.simulizar.modules.component.core;

import java.util.Set;

import org.palladiosimulator.simulizar.modelobserver.IModelObserver;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public interface ModelObserverModule {
    
    @Multibinds Set<IModelObserver> bindModelObservers();

}
