package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.Switch;

public interface ComposedRDSeffSwitchFactory {

    Switch<Object> createRDSeffSwitch(final InterpreterDefaultContext context);

}
