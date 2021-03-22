package org.palladiosimulator.simulizar.failurescenario.interpreter.provider;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.failuremodel.failuretype.Failure;
import org.palladiosimulator.failuremodel.failuretype.HWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.LinkCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.SWCrashFailure;
import org.palladiosimulator.failuremodel.failuretype.SWTimingFailure;
import org.palladiosimulator.failuremodel.failuretype.Timing;
import org.palladiosimulator.failuremodel.failuretype.Transient;
import org.palladiosimulator.failuremodel.failuretype.util.FailuretypeSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.FailureBehaviorChangeDTO;
import org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation.CrashBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation.DelayBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl.AddCrashBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl.AddDelayBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl.AddDemandModifyingBehaviorStrategy;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;

import de.uka.ipd.sdq.simucomframework.resources.DemandModifyingBehavior;

public class FailureBehaviorChangesProviderSwitch extends FailuretypeSwitch<List<FailureBehaviorChangeDTO>> {

	// Crash Failures
	// ------------------------------------------------------------------------------------------

	@Override
	public List<FailureBehaviorChangeDTO> caseSWCrashFailure(SWCrashFailure object) {
		PreInterpretationBehavior pib = new CrashBehavior(object);
		return Arrays.asList(new FailureBehaviorChangeDTO[] {
				new FailureBehaviorChangeDTO(new AddCrashBehaviorStrategy(pib), 0.0) });
	}

	@Override
	public List<FailureBehaviorChangeDTO> caseHWCrashFailure(HWCrashFailure object) {
		PreInterpretationBehavior pib = new CrashBehavior(object);
		return Arrays.asList(new FailureBehaviorChangeDTO[] {
				new FailureBehaviorChangeDTO(new AddCrashBehaviorStrategy(pib), 0.0) });
	}

	@Override
	public List<FailureBehaviorChangeDTO> caseLinkCrashFailure(LinkCrashFailure object) {
		PreInterpretationBehavior pib = new CrashBehavior(object);
		return Arrays.asList(new FailureBehaviorChangeDTO[] {
				new FailureBehaviorChangeDTO(new AddCrashBehaviorStrategy(pib), 0.0) });
	}

	// Transient Failures
	// ------------------------------------------------------------------------------------------

	@Override
	public <Failuretype extends Failure> List<FailureBehaviorChangeDTO> caseTransient(Transient<Failuretype> object) {

		FailureBehaviorChangeDTO addingBehavior = this.doSwitch(object.getDecoratedFailure()).get(0);
		FailureBehaviorChangeDTO removingBehavior = new FailureBehaviorChangeDTO(
				addingBehavior.getStrategy().getRevertedStrategy(),
				Double.parseDouble(object.getDuration().getSpecification()));

		return Arrays.asList(new FailureBehaviorChangeDTO[] { addingBehavior, removingBehavior });
	}

	// Timing Failures
	// ------------------------------------------------------------------------------------------

	@Override
	public List<FailureBehaviorChangeDTO> caseTiming(Timing object) {
		DemandModifyingBehavior dmb = new DemandModifyingBehavior(object.getScalingFactor().getSpecification(),
				object.getDelay().getSpecification());
		return Arrays.asList(new FailureBehaviorChangeDTO[] {
				new FailureBehaviorChangeDTO(new AddDemandModifyingBehaviorStrategy(dmb), 0.0) });
	}

	@Override
	public List<FailureBehaviorChangeDTO> caseSWTimingFailure(SWTimingFailure object) {
		DelayBehavior delayPib = new DelayBehavior(Double.parseDouble(object.getDelay().getSpecification()));
		return Arrays.asList(new FailureBehaviorChangeDTO[] {
				new FailureBehaviorChangeDTO(new AddDelayBehaviorStrategy(delayPib), 0.0) });
	}

	/**
	 * returns the failure behavior changes (Represented as DataTransferObjects with
	 * a changingStrategy and a relativePointInTime)
	 */
	@Override
	public List<FailureBehaviorChangeDTO> doSwitch(EObject object) {
		return super.doSwitch(object);
	}
}
