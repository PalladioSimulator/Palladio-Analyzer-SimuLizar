package org.palladiosimulator.simulizar.interpreter;

import java.util.List;

import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;
import org.palladiosimulator.simulizar.utils.TransitionDeterminerFactory;

/**
 * Factory for ExplicitDispatchComposedSwitch
 * @author Jens Manig
 *
 */
public interface ExplicitDispatchComposedSwitchFactory {
	public ExplicitDispatchComposedSwitch<Object> create(final SimulatedBasicComponentInstance basicComponentInstance, final InterpreterDefaultContext context, 
			final RDSeffSwitchFactory RDSwitchFactory, final TransitionDeterminerFactory determinerFactory,
			final List<AbstractRDSeffSwitchFactory> switchFactories);

}
