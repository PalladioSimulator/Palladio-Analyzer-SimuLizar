package org.palladiosimulator.simulizar.utils;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

public class TransitionDeterminerFactory {
	
	public TransitionDeterminer createTransitionDeterminer(final InterpreterDefaultContext context) {
		
		return new DefaultTransitionDeterminer(context);
		
	}

}
