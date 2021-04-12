package org.palladiosimulator.simulizar.failurescenario.interpreter.provider;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.failuremodel.failuretype.Byzantine;
import org.palladiosimulator.failuremodel.failuretype.Content;
import org.palladiosimulator.failuremodel.failuretype.Crash;
import org.palladiosimulator.failuremodel.failuretype.Failure;
import org.palladiosimulator.failuremodel.failuretype.SWTimingFailure;
import org.palladiosimulator.failuremodel.failuretype.Timing;
import org.palladiosimulator.failuremodel.failuretype.Transient;
import org.palladiosimulator.failuremodel.failuretype.util.FailuretypeSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.Decider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.ProbabilisticDecider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation.CorruptContentBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation.CrashBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation.DelayBehavior;
import org.palladiosimulator.simulizar.failurescenario.interpreter.dto.FailureBehaviorChangeDTO;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl.AddDemandModifyingBehaviorStrategy;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.impl.AddPreInterpretationBehaviorStrategy;

import de.uka.ipd.sdq.probfunction.math.IRandomGenerator;
import de.uka.ipd.sdq.simucomframework.resources.DemandModifyingBehavior;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;

/**
 * A Provider for FailureBehaviorChange DataTransferObjects, which contain a
 * strategy for behavior changes.
 * 
 * @author Jonas Lehmann
 *
 */
public class FailureBehaviorChangesProviderSwitch extends FailuretypeSwitch<List<FailureBehaviorChangeDTO>> {

	private final IRandomGenerator randomNumberGenerator;

	public FailureBehaviorChangesProviderSwitch(IRandomGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	// Crash Failures
	// ------------------------------------------------------------------------------------------

	@Override
	public List<FailureBehaviorChangeDTO> caseCrash(Crash object) {
		FailureBehaviorChangeDTO dto = null;
		if (object instanceof Failure) {
			dto = new FailureBehaviorChangeDTO(
					new AddPreInterpretationBehaviorStrategy(new CrashBehavior((Failure) object)), 0.0);
		}
		return Arrays.asList(new FailureBehaviorChangeDTO[] { dto });
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
		DelayBehavior delayPib = new DelayBehavior(object.getDelay().getSpecification());
		return Arrays.asList(new FailureBehaviorChangeDTO[] {
				new FailureBehaviorChangeDTO(new AddPreInterpretationBehaviorStrategy(delayPib), 0.0) });
	}

	// Content Failures
	// ------------------------------------------------------------------------------------------

	@Override
	public List<FailureBehaviorChangeDTO> caseContent(Content object) {
		FailureBehaviorChangeDTO dto = new FailureBehaviorChangeDTO(new AddPreInterpretationBehaviorStrategy(
				new CorruptContentBehavior(object.getDegreeOfCorruption().getSpecification())), 0.0);
		return Arrays.asList(new FailureBehaviorChangeDTO[] { dto });
	}

	// Transient Failures
	// ------------------------------------------------------------------------------------------

	@Override
	public <Failuretype extends Failure> List<FailureBehaviorChangeDTO> caseTransient(Transient<Failuretype> object) {

		FailureBehaviorChangeDTO addingBehavior = this.doSwitch(object.getDecoratedFailure()).get(0);
		Double relativePointInTimeOfRemoving = NumberConverter
				.toDouble(StackContext.evaluateStatic(object.getDuration().getSpecification()));
		FailureBehaviorChangeDTO removingBehavior = new FailureBehaviorChangeDTO(
				addingBehavior.getStrategy().getRevertedStrategy(), relativePointInTimeOfRemoving);

		return Arrays.asList(new FailureBehaviorChangeDTO[] { addingBehavior, removingBehavior });
	}

	// Byzantine Failures
	// ------------------------------------------------------------------------------------------

	@Override
	public <Failuretype extends Failure> List<FailureBehaviorChangeDTO> caseByzantine(Byzantine<Failuretype> object) {

		String probabilitySpec = object.getProbabilityOfOccurrence().getSpecification();
		Decider decider = new ProbabilisticDecider(randomNumberGenerator, probabilitySpec);

		FailureBehaviorChangeDTO behavior = this.doSwitch(object.getDecoratedFailure()).get(0);
		behavior.getStrategy().setDecider(decider);
		return Arrays.asList(new FailureBehaviorChangeDTO[] { behavior });
	}

	/**
	 * Returns the failure behavior changes (Represented as DataTransferObjects with
	 * a changingStrategy and a relativePointInTime)
	 */
	@Override
	public List<FailureBehaviorChangeDTO> doSwitch(EObject object) {
		return super.doSwitch(object);
	}
}
