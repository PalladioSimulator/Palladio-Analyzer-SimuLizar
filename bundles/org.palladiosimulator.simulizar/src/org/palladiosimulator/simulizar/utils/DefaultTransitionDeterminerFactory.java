package org.palladiosimulator.simulizar.utils;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
/**
 * Factory implementation for DefaultTransistionDeterminerFactory
 * @author Jens Manig
 *
 */
public class DefaultTransitionDeterminerFactory implements TransitionDeterminerFactory{

	@Override
	public TransitionDeterminer create(InterpreterDefaultContext context) {
		return new DefaultTransitionDeterminer(context);
	}

}
