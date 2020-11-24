package org.palladiosimulator.simulizar.interpreter.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ExplicitDispatchComposedSwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;

public class ExtensibleComposedRDSeffSwitchFactory implements ComposedRDSeffSwitchFactory {
    
    public static final String RDSEFFSWITCH_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.interpreter.rdseffswitch";
    public static final String RDSEFFSWITCH_EXTENSION_ATTRIBUTE = "rdseffswitch";
    
    private final ComponentInstanceRegistry componentInstanceRegistry;
    private final Provider<Set<AbstractRDSeffSwitchFactory>> elementFactoriesProvider;

    @Inject
    public ExtensibleComposedRDSeffSwitchFactory(ComponentInstanceRegistry componentInstanceRegistry,
            Provider<Set<AbstractRDSeffSwitchFactory>> elementFactoriesProvider) {
        this.componentInstanceRegistry = componentInstanceRegistry;
        this.elementFactoriesProvider = elementFactoriesProvider;
    }

    @Override
    public Switch<Object> createRDSeffSwitch(InterpreterDefaultContext context) {
        
        final FQComponentID componentID = context.computeFQComponentID();
        final SimulatedBasicComponentInstance basicComponentInstance = (SimulatedBasicComponentInstance) componentInstanceRegistry.getComponentInstance(componentID);
        
        final  ExplicitDispatchComposedSwitch<Object> interpreter = new ExplicitDispatchComposedSwitch<Object>();
        elementFactoriesProvider.get().stream().forEach(s -> interpreter.addSwitch(
                s.createRDSeffSwitch(context, basicComponentInstance, interpreter)));
        
        return interpreter;
    }

}
