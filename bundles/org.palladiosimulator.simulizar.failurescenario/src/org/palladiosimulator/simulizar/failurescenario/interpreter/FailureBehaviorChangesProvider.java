package org.palladiosimulator.simulizar.failurescenario.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.palladiosimulator.failuremodel.failuretype.HWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.LinkCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.SWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.SWTransientCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.util.FailuretypeSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.issue.FailureOccurredIssue;
import org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation.CrashBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.AddCrashBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.FailureBehaviorChangingStrategy;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class FailureBehaviorChangesProvider extends FailuretypeSwitch<List<FailureBehaviorChangeDTO>> {

	@Override
	public List<FailureBehaviorChangeDTO> caseSWCrashFailure(SWCrashFailure object) {
		List<FailureBehaviorChangeDTO> fbChanges = new ArrayList<FailureBehaviorChangeDTO>();
		PreInterpretationBehavior pib = new CrashBehavior(InterpreterResult.of(new FailureOccurredIssue(object)));
		fbChanges.add(new FailureBehaviorChangeDTO(new AddCrashBehaviorStrategy(pib), 0.0));
		return fbChanges;
	}

	@Override
	public List<FailureBehaviorChangeDTO> caseSWTransientCrashFailure(SWTransientCrashFailure object) {
		List<FailureBehaviorChangeDTO> fbChanges = new ArrayList<FailureBehaviorChangeDTO>();
		PreInterpretationBehavior pib = new CrashBehavior(
				InterpreterResult.of(new FailureOccurredIssue(object.getDecoratedFailure())));

		FailureBehaviorChangingStrategy addFailure = new AddCrashBehaviorStrategy(pib);

		fbChanges.add(new FailureBehaviorChangeDTO(addFailure, 0.0));
		fbChanges.add(new FailureBehaviorChangeDTO(addFailure.getRevertedStrategy(),
				Double.parseDouble(object.getDuration().getSpecification())));
		return fbChanges;
	}

	@Override
	public List<FailureBehaviorChangeDTO> caseHWCrashFailure(HWCrashFailure object) {
		List<FailureBehaviorChangeDTO> fbChanges = new ArrayList<FailureBehaviorChangeDTO>();
		PreInterpretationBehavior pib = new CrashBehavior(InterpreterResult.of(new FailureOccurredIssue(object)));
		fbChanges.add(new FailureBehaviorChangeDTO(new AddCrashBehaviorStrategy(pib), 0.0));
		return fbChanges;
	}

	@Override
	public List<FailureBehaviorChangeDTO> caseLinkCrashFailure(LinkCrashFailure object) {
		List<FailureBehaviorChangeDTO> fbChanges = new ArrayList<FailureBehaviorChangeDTO>();
		PreInterpretationBehavior pib = new CrashBehavior(InterpreterResult.of(new FailureOccurredIssue(object)));
		fbChanges.add(new FailureBehaviorChangeDTO(new AddCrashBehaviorStrategy(pib), 0.0));
		return fbChanges;
	}
}
