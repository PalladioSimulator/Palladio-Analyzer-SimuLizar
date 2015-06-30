package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import org.eclipse.emf.common.util.EList;

import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;

/**
 * Executes an instance of {@code eurema.InitialOperation} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class InitialOperationExecutor extends ControlOperationExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	InitialOperationExecutor(GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.InitialOperation}.
	 * 
	 * @param initialOperation
	 *            the initial operation to be executed.
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the operation to be executed
	 *            that has been taken. For an initial operation the incoming
	 *            transition is always <code>null</code> since initial
	 *            operations do not have any incoming transitions.
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation has just been executed.
	 */
	public eurema.Transition execute(eurema.InitialOperation initialOperation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		// Initial operations currently have no actual behavior.
		// Retrieve the single outgoing transition, update execution
		// information, and retrieve the target of the outgoing transition.

		EList<eurema.Exit> exits = initialOperation.getExits();
		// An InitialOperation has exactly one outgoing transition.
		assert exits.size() == 1;
		eurema.Transition enabledOutgoingTransition =
				exits.get(0).getOutgoing();
		assert enabledOutgoingTransition != null;
		return enabledOutgoingTransition;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.Transition execute(eurema.ControlOperation controlOperation,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		assert controlOperation instanceof eurema.InitialOperation;
		return this.execute((eurema.InitialOperation) controlOperation, before,
				context);
	}

}
