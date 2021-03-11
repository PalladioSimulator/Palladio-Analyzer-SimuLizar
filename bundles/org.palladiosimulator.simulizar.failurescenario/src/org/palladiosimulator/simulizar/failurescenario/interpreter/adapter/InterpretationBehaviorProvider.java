package org.palladiosimulator.simulizar.failurescenario.interpreter.adapter;

import org.palladiosimulator.failuremodel.failuretype.HWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.LinkCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.SWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.util.FailuretypeSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.issue.FailureOccurredIssue;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class InterpretationBehaviorProvider extends FailuretypeSwitch<PreInterpretationBehavior>{
	
	@Override
	public PreInterpretationBehavior caseSWCrashFailure(SWCrashFailure object) {
		return new SWCrashBehavior(InterpreterResult.of(new FailureOccurredIssue(object)));
	}
	
	@Override
	public PreInterpretationBehavior caseHWCrashFailure(HWCrashFailure object) {
		
		//var result = FailureManager;
		return null;
	}
	
	@Override
	public PreInterpretationBehavior caseLinkCrashFailure(LinkCrashFailure object) {
		
		//var result = FailureManager;
		return null;
	}
}
