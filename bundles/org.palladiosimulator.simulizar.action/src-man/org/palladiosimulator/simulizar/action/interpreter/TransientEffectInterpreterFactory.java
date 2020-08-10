package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Optional;

import org.palladiosimulator.simulizar.action.context.ExecutionContext;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
/**
 * Factory interface for TransientEffectInterpreter with assitedinject
 * @author Jens Manig
 *
 */
public interface TransientEffectInterpreterFactory {
	public TransientEffectInterpreter create(RoleSet set,
			ControllerCallInputVariableUsageCollection controllerCallsInputVariableUsages,
			AdaptationBehaviorRepository repository, boolean executeAsync,
			Optional<ExecutionContext> executionContext);
}
