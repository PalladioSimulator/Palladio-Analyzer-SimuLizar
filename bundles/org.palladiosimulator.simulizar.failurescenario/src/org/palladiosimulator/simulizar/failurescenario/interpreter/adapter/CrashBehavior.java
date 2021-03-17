package org.palladiosimulator.simulizar.failurescenario.interpreter.adapter;

import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

/**
 * Simple Crash behavior which returns the InterpreterResult during its execute() call.
 * 
 * @author Jonas Lehmann
 *
 */
public class CrashBehavior extends PreInterpretationBehavior {

	public CrashBehavior(InterpreterResult result) {
		
		super(result);
	}
}
