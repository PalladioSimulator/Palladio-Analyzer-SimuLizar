package org.palladiosimulator.simulizar.utils;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
/**
 * Factory interface for TransitionDeterminer
 * @author Jens Manig
 *
 */
public interface TransitionDeterminerFactory { 
	
	public  TransitionDeterminer create(final InterpreterDefaultContext context);

}
