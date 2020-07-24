package org.palladiosimulator.simulizar.interpreter;

import java.util.List;

import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;
import org.palladiosimulator.simulizar.utils.TransitionDeterminerFactory;
/**
 * Implementation of ExplicitDispatchComposedSwitchFactory
 * @author Jens Manig 
 *
 */
public class ExplicitDispatchComposedSwitchFactoryImpl implements ExplicitDispatchComposedSwitchFactory{

	@Override
	public ExplicitDispatchComposedSwitch<Object> create(SimulatedBasicComponentInstance basicComponentInstance,
			InterpreterDefaultContext context, final RDSeffSwitchFactory RDSwitchFactory, final TransitionDeterminerFactory determinerFactory,
			final List<AbstractRDSeffSwitchFactory> switchFactories) {
        final  ExplicitDispatchComposedSwitch<Object> interpreter = new ExplicitDispatchComposedSwitch<Object>();
        switchFactories.stream().forEach(s -> interpreter.addSwitch(
        		s.createRDSeffSwitch(context, basicComponentInstance, interpreter)));
        // add default RDSeffSwitch
        // TODO
        interpreter.addSwitch(RDSwitchFactory.create(context, basicComponentInstance, interpreter, 
        		determinerFactory.create(context)));
		return interpreter;
	}

}
