package org.palladiosimulator.simulizar.di.modules.component.extensions;

import java.util.Set;

import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.InterpreterResultListener;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public interface SimulationRuntimeExtensions {
    
    @Multibinds
    Set<RuntimeStateEntityManager> entityManagers();
    
    @Multibinds
    Set<RuntimeStateEntityObserver> entityObservers();
    
    @Multibinds
    Set<IInterpreterListener> bindInterpreterListeners();
    
    @Multibinds
    Set<IModelObserver> modelObservers();
    
    @Multibinds
    Set<RDSeffSwitchContributionFactory> rdseffSwitchFactories();
    
    @Multibinds
    Set<InterpreterResultListener> resultListeners();

}
