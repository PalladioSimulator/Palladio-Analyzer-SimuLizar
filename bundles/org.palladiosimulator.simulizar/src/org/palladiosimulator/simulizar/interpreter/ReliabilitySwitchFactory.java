package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;

public class ReliabilitySwitchFactory extends AbstractRDSeffSwitchFactory{

	@Override
	protected Switch<Object> createRDSeffSwitch(InterpreterDefaultContext context, SimulatedBasicComponentInstance basicComponentInstance, ExplicitDispatchComposedSwitch<Object> parentSwitch) {
		return new ReliabilitySwitch(context, parentSwitch);
	}

}
