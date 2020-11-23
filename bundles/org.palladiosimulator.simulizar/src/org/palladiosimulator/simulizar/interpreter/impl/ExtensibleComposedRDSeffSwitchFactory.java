package org.palladiosimulator.simulizar.interpreter.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ExplicitDispatchComposedSwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchFactory;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;

public class ExtensibleComposedRDSeffSwitchFactory implements ComposedRDSeffSwitchFactory {
    
    public static final String RDSEFFSWITCH_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.interpreter.rdseffswitch";
    public static final String RDSEFFSWITCH_EXTENSION_ATTRIBUTE = "rdseffswitch";
    
    private final ComponentInstanceRegistry componentInstanceRegistry;
    private final Set<AbstractRDSeffSwitchFactory> elementFactories;

    @Inject
    public ExtensibleComposedRDSeffSwitchFactory(ComponentInstanceRegistry componentInstanceRegistry,
            Set<AbstractRDSeffSwitchFactory> elementFactories) {
        this.componentInstanceRegistry = componentInstanceRegistry;
        this.elementFactories = elementFactories;
    }

    @Override
    public Switch<Object> createRDSeffSwitch(InterpreterDefaultContext context) {
        
        final FQComponentID componentID = context.computeFQComponentID();
        final SimulatedBasicComponentInstance basicComponentInstance = (SimulatedBasicComponentInstance) componentInstanceRegistry.getComponentInstance(componentID);
        
        final List<AbstractRDSeffSwitchFactory> switchFactories = ExtensionHelper
                .getExecutableExtensions(RDSEFFSWITCH_EXTENSION_POINT_ID, RDSEFFSWITCH_EXTENSION_ATTRIBUTE);
        final  ExplicitDispatchComposedSwitch<Object> interpreter = new ExplicitDispatchComposedSwitch<Object>();
        switchFactories.stream().forEach(s -> interpreter.addSwitch(
                s.createRDSeffSwitch(context, basicComponentInstance, interpreter)));
        // add default RDSeffSwitch
        interpreter.addSwitch(basicSwitchFactory.create(context, basicComponentInstance, interpreter));
        
        return interpreter;
    }

}
