package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.demandmodifying;

import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.Decider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.IDecisionDecorated;

import de.uka.ipd.sdq.simucomframework.resources.DemandModificationDTO;
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
	 * Scales a demand with the scalingFactor (newDemand = prevDemand /
	 * scalingFactor). Returns the scaled demand and an additive demand of time
	 * units (delay, latency).
	 * 
	 * @param previousDemand demand value before modification
	 * @return A Demand Modification Data Transfer Object which store the two
	 *         values.
	 */
	@Override
	public DemandModificationDTO modifyDemand(double previousDemand) {
		if (this.decider.decide()) {
			return this.decoratedBehavior.modifyDemand(previousDemand);
		}
		return super.modifyDemand(previousDemand);
	}
}
