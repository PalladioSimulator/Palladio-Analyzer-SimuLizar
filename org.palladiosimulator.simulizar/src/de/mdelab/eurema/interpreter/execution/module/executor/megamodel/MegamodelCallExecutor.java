package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import org.eclipse.emf.common.util.EList;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;
import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;

/**
 * Executes an instance of {@code eurema.MegamodelCall} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class MegamodelCallExecutor extends OperationBehaviorExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	MegamodelCallExecutor(GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.MegamodelCall}.
	 * 
	 * @param megamodelCall
	 *            the megamodel call to be executed
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the megamodel call to be
	 *            executed that has been taken
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation, i.e., the megamodel call, has just been
	 *         executed.
	 */
	public eurema.Transition execute(eurema.MegamodelCall megamodelCall,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		// Info: Models used by the MegamodelCall operation as eurema.Input need
		// not to be loaded here. These inputs just declare the models used by
		// the invoked megamodel module for comprehension and analysis purposes
		// of feedback loops defined by FLDs. The used models are actually
		// loaded when executing the invoked megamodel module's ModelOperations.

		// Obtain megamodel module to invoke
		eurema.MegamodelModule toBeinvokedMegamodelModule =
				megamodelCall.getBinding();

		// Retrieve initial operation of the toBeinvokedMegamodelModule:
		// Entry.name of the megamodel call == InitialOperation.name of the
		// invoked megamodel module

		// Name of the entry compartment of the megamodel call to which the
		// incoming transition that has been taken points to.
		String initialOperationName = before.getTarget().getName();

		// find initial operation with the given name
		eurema.InitialOperation eInitialOperation = null;
		eurema.Megamodel eMegamodel = toBeinvokedMegamodelModule.getMegamodel();
		for (eurema.InitialOperation i : eMegamodel.getInitialOperations()) {
			if (i.getName().equals(initialOperationName)) {
				eInitialOperation = i;
				break;
			}
		}
		// if no initial operation has been found with the given name, return
		// the initial operation of the megamodel iff there is exactly one
		// initial operation
		if (eInitialOperation == null) {
			EList<eurema.InitialOperation> eInitialOps =
					eMegamodel.getInitialOperations();
			if (eInitialOps.size() == 1) {
				eInitialOperation = eInitialOps.get(0);
			}
		}
		assert eInitialOperation != null;

		@SuppressWarnings("unchecked")
		ModuleExecutable<eurema.MegamodelModule, eurema.MegamodelModuleTrigger, eurema.InitialOperation, eurema.FinalOperation> exec =
				(ModuleExecutable<eurema.MegamodelModule, eurema.MegamodelModuleTrigger, eurema.InitialOperation, eurema.FinalOperation>) super.globalExecutionContext
						.getExecutable(toBeinvokedMegamodelModule);

		// invoke the megamodel module
		eurema.FinalOperation eFinalOperation = exec.run(eInitialOperation);
		String eFinalOperationName = eFinalOperation.getName();

		// retrieve the exit of the megamodel call based on the final operation
		// of the invoked megamodel module: Exit.name of the megamodel call ==
		// FinalOperation.name of the invoked megamodel module

		// find Exit of the megamodel call with the given name
		eurema.Exit eEnabledExit = null;
		for (eurema.Exit eExit : megamodelCall.getExits()) {
			if (eFinalOperationName.equals(eExit.getName())) {
				eEnabledExit = eExit;
				break;
			}
		}
		// if the megamodel call has exactly one Exit, use this one.
		if (eEnabledExit == null) {
			if (megamodelCall.getExits().size() == 1) {
				eEnabledExit = megamodelCall.getExits().get(0);
			}
		}

		// if no exit has been found, throw an exception
		if (eEnabledExit == null) {
			throw new EuremaInterpreterException("The magamodel call "
					+ megamodelCall.getName()
					+ " has no exit compartment with the name "
					+ eFinalOperationName);
		}
		return eEnabledExit.getOutgoing();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public eurema.Transition execute(
			eurema.OperationBehavior operationBehavior,
			eurema.Transition before, MegamodelModuleExecutionContext context) {
		assert operationBehavior instanceof eurema.MegamodelCall;
		return this.execute((eurema.MegamodelCall) operationBehavior, before,
				context);
	}

}
