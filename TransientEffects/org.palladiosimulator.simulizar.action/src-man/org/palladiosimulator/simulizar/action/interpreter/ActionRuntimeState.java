package org.palladiosimulator.simulizar.action.interpreter;

import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public class ActionRuntimeState implements IRuntimeStateAccessor {
	private static SimuLizarRuntimeState state;
	
	public static TransientEffectInterpreter createTransientEffectInterpreter(RoleSet set, ActionRepository repository) {
		return new TransientEffectInterpreter(state, set, repository);
	} 
	
	@Override
	public void setRuntimeStateModel(SimuLizarRuntimeState passedState) {
		ActionRuntimeState.state = passedState;
	}
	
}
