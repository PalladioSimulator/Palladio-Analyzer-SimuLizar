package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.condition.ConditionExpParser;
import de.mdelab.eurema.interpreter.condition.QueryExecutionInformation;
import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;

/**
 * Executes an instance of {@code eurema.DecisionOperation} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class DecisionOperationExecutor extends ControlOperationExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	DecisionOperationExecutor(GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.DecisionOperation}.
	 * 
	 * @param decisionOperation
	 *            the decision operation to be executed.
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the decision operation to be
	 *            executed that has been taken
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation has just been executed. The condition annotated
	 *         to this enabled outgoing transition has been evaluated to
	 *         <code>true</code>.
	 */
	public eurema.Transition execute(
			eurema.DecisionOperation decisionOperation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {

		ConditionExpParser conditionExpParser = new ConditionExpParser();

		eurema.Transition enabledOutgoingTransition = null;
		eurema.Transition elseTransition = null;

		// retrieve outgoing transitions and evaluate their conditions
		for (eurema.Exit exit : decisionOperation.getExits()) {
			eurema.Transition transition = exit.getOutgoing();

			eurema.Condition condition = transition.getCondition();
			assert condition != null;

			String expression = condition.getExpr();
			if (expression.equals(QueryExecutionInformation.DEFAULT)) {
				elseTransition = transition;
			} else {

				boolean expressionResult =
						conditionExpParser.parseExp(expression, context);

				if (expressionResult) {
					// expression evaluates to true, which enables the
					// transition
					enabledOutgoingTransition = transition;
					break;
				}
			}
		}

		// if no expression evaluates to true, check for an "ELSE" transition.
		if (enabledOutgoingTransition == null) {
			if (elseTransition != null) {
				// there is an ELSE transition.
				enabledOutgoingTransition = elseTransition;
			} else {
				// no transition enabled since no condition evaluates to true
				// and there is no ELSE transition.
				throw new EuremaInterpreterException(
						"Error in executing decision operation "
								+ decisionOperation.getName()
								+ " because no transition could be taken. No condition has been evaluated to true.");
			}
		}

		return enabledOutgoingTransition;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.Transition execute(eurema.ControlOperation controlOperation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		assert controlOperation instanceof eurema.DecisionOperation;
		return this.execute((eurema.DecisionOperation) controlOperation,
				before, context);
	}

}
