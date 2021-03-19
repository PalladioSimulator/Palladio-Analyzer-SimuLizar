package org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation;

import org.palladiosimulator.failuremodel.failuretype.HWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.LinkCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.SWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.util.FailuretypeSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.issue.FailureOccurredIssue;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class InterpretationBehaviorProvider extends FailuretypeSwitch<PreInterpretationBehavior>{
	
	@Override
	public PreInterpretationBehavior caseSWCrashFailure(SWCrashFailure object) {
		return new CrashBehavior(InterpreterResult.of(new FailureOccurredIssue(object)));
	}
	
	@Override
	public PreInterpretationBehavior caseHWCrashFailure(HWCrashFailure object) {
		return new CrashBehavior(InterpreterResult.of(new FailureOccurredIssue(object)));
	}
	
	@Override
	public PreInterpretationBehavior caseLinkCrashFailure(LinkCrashFailure object) {
		return new CrashBehavior(InterpreterResult.of(new FailureOccurredIssue(object)));
	}
}
