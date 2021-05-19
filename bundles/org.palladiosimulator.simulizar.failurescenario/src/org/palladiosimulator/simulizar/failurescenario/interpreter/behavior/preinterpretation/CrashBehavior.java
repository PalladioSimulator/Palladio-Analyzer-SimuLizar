package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation;

import org.palladiosimulator.failuremodel.failuretype.Failure;
import org.palladiosimulator.simulizar.failurescenario.interpreter.issue.FailureOccurredIssue;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

/**
 * Simple Crash behavior which returns the InterpreterResult during its execute() call.
 * 
 * @author Jonas Lehmann
 *
 */
public class CrashBehavior extends PreInterpretationBehavior {

	public CrashBehavior(Failure failure) {
		super(InterpreterResult.of(new FailureOccurredIssue(failure)));
	}
}
