package org.palladiosimulator.simulizar.modules.component.extensions;

import java.util.Set;

import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public interface SimulationRuntimeExtensions {
    
    @Multibinds
    Set<RuntimeStateEntityManager> entityManagers();
    
    @Multibinds
    Set<IInterpreterListener> bindInterpreterListeners();
    
    @Multibinds
    Set<IModelObserver> modelObservers();
    
    @Multibinds
    Set<AbstractRDSeffSwitchFactory> rdseffSwitchFactories();

}
