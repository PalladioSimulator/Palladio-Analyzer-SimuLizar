package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.demandmodifying;

import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.Decider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.IDecisionDecorated;

import de.uka.ipd.sdq.simucomframework.resources.DemandModifyingBehavior;

public class DecisionDecoratedDemandModifyingBehavior extends DemandModifyingBehavior implements IDecisionDecorated {

	private final DemandModifyingBehavior decoratedBehavior;
	private Decider decider;

	public DecisionDecoratedDemandModifyingBehavior(DemandModifyingBehavior decoratedBehavior, Decider decider) {
		super("1.0", "0.0");
		this.decoratedBehavior = decoratedBehavior;
		this.decider = decider;
	}

	@Override
	public Decider getDecider() {
		return this.decider;
	}

	@Override
	public void setDecider(Decider d) {
		this.decider = d;
	}

	/**
	 * The return value is calculated by the decorated behavior if the decision
	 * delegation returns true.
	 *
	 * Calculates an additive demand of time units: additiveValue = prevDemand /
	 * scaleFactor + delay - prevDemand.
	 * 
	 * @param previousDemand demand value before modification
	 * @return an additive value to get the newDemand by adding it to the prevDemand
	 */
	@Override
	public double getAdditiveDemandValue(double previousDemand) {
		if (this.decider.decide()) {
			return this.decoratedBehavior.getAdditiveDemandValue(previousDemand);
		}
		return super.getAdditiveDemandValue(previousDemand);
	}
}
