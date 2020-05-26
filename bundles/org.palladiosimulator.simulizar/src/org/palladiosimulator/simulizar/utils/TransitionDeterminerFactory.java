package org.palladiosimulator.simulizar.utils;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
/**
 * Factory for TransitionDeterminer
 * @author Jens Manig
 *
 */
public class TransitionDeterminerFactory {
	
	public static TransitionDeterminer createTransitionDeterminer(final InterpreterDefaultContext context) {
		
		return new DefaultTransitionDeterminer(context);
		
	}

}
