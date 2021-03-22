package org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

/**
 * Simple Delay behavior which returns the InterpreterResult during its
 * execute() call.
 * 
 * 
 * @author Jonas Lehmann
 *
 */
public class DelayBehavior extends PreInterpretationBehavior {

	private double delay;

	public DelayBehavior(double delay) {
		super(InterpreterResult.OK);
		this.delay = delay;
	}

	/**
	 * Implementations should override this and execute intern behavior. After that
	 * call super.execute(); to return the InterpreterResult.
	 * 
	 * @return the InterpreterResult
	 */
	@Override
	public InterpreterResult execute(InterpreterDefaultContext context) {
		if (context != null) {
			context.getThread().hold(delay);
		}
		return super.execute(context);
	}
}
