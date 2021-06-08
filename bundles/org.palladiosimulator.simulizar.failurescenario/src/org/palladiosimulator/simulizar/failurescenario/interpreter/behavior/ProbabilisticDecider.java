package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior;

import de.uka.ipd.sdq.probfunction.math.IRandomGenerator;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;

public class ProbabilisticDecider implements BehavioralDecider {

	IRandomGenerator randomNumberGenerator;
	private final String probabilitySpec;

	public ProbabilisticDecider(IRandomGenerator randomNumberGenerator, String probabilitySpec) {
		this.randomNumberGenerator = randomNumberGenerator;
		this.probabilitySpec = probabilitySpec;
	}

	@Override
	public boolean decide() {
		double probabilityOfOccurrence = NumberConverter.toDouble(StackContext.evaluateStatic(probabilitySpec));
		double randVar = randomNumberGenerator.random();
		return (probabilityOfOccurrence < randVar);
	}

}
