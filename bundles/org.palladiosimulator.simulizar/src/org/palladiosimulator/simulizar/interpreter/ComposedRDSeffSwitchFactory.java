package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public interface ComposedRDSeffSwitchFactory {

    Switch<InterpreterResult> createRDSeffSwitch(final InterpreterDefaultContext context);

}
