package org.palladiosimulator.simulizar.action.interpreter;

import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState.TransientEffectInterpreterBuilder;

/**
 * Factory interface for TransientEffectInterpreterBuilder with assitedinject
 * @author Jens Manig
 *
 */
public interface TransientEffectInterpreterBuilderFactory {
	TransientEffectInterpreterBuilder create(RoleSet roleSet, AdaptationBehaviorRepository repository);
}
