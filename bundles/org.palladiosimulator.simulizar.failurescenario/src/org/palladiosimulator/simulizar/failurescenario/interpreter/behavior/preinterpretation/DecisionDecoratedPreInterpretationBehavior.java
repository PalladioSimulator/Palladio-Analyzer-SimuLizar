package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation;

import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.Decider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.IDecisionDecorated;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class DecisionDecoratedPreInterpretationBehavior extends PreInterpretationBehavior
		implements IDecisionDecorated {

	private final PreInterpretationBehavior decoratedBehavior;
	private Decider decider;

	public DecisionDecoratedPreInterpretationBehavior(PreInterpretationBehavior decoratedBehavior, Decider decider) {
		super(InterpreterResult.OK);
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
	 * Executes the decorated behavior if the decision delegation returns true.
	 * 
	 * @return InterpreterResult of the decorated behavior or OK
	 */
	@Override
	public InterpreterResult execute(InterpreterDefaultContext context) {
		if (this.decider.decide()) {
			return this.decoratedBehavior.execute(context);
		}
		return super.execute(context);
	}

}
