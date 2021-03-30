package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;

/**
 * Simple Delay behavior which returns the InterpreterResult during its
 * execute() call.
 * 
 * 
 * @author Jonas Lehmann
 *
 */
public class DelayBehavior extends PreInterpretationBehavior {

	private String delaySpec;

	public DelayBehavior(String delaySpec) {
		super(InterpreterResult.OK);
		this.delaySpec = delaySpec;
	}

	/**
	 * Holds the simulation for the amount of time of the delay.
	 * 
	 * @return InterpreterResult.OK
	 */
	@Override
	public InterpreterResult execute(InterpreterDefaultContext context) {
		if (context != null) {
			Double delay = NumberConverter.toDouble(StackContext.evaluateStatic(delaySpec));
			context.getThread().hold(delay);
		}
		return super.execute(context);
	}
}
