package org.palladiosimulator.simulizar.interpreter.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ExplicitDispatchComposedSwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class ExtensibleComposedRDSeffSwitchFactory implements ComposedRDSeffSwitchFactory {

    private final Provider<Set<RDSeffSwitchContributionFactory>> elementFactoriesProvider;

    @Inject
    public ExtensibleComposedRDSeffSwitchFactory(Provider<Set<RDSeffSwitchContributionFactory>> elementFactoriesProvider) {
        this.elementFactoriesProvider = elementFactoriesProvider;
    }

    @Override
    public Switch<InterpreterResult> createRDSeffSwitch(InterpreterDefaultContext context) {
        final  ExplicitDispatchComposedSwitch interpreter = new ExplicitDispatchComposedSwitch();
        elementFactoriesProvider.get().stream().forEach(s -> interpreter.addSwitch(
                s.createRDSeffSwitch(context, interpreter)));
        
        return interpreter;
    }

}
