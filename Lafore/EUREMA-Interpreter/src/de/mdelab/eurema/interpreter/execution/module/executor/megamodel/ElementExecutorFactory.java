package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import org.eclipse.emf.ecore.EClass;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.Metamodel;
import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;
import eurema.EuremaPackage;

/**
 * Factory to obtain executors to run elements of a megamodel module.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class ElementExecutorFactory {

	/**
	 * Executor for instances of {@code eurema.Transition}.
	 */
	private TransitionExecutor transitionExecutor;
	/**
	 * Executor for instances of {@code eurema.InitialOperation}.
	 */
	private InitialOperationExecutor initialOperationExecutor;
	/**
	 * Executor for instances of {@code eurema.DecisionOperation}.
	 */
	private DecisionOperationExecutor decisionOperationExecutor;
	/**
	 * Executor for instances of {@code eurema.FinalOperation}.
	 */
	private FinalOperationExecutor finalOperationExecutor;
	/**
	 * Executor for instances of {@code eurema.MegamodelCall}.
	 */
	private MegamodelCallExecutor megamodelCallExecutor;
	/**
	 * Executor for instances of {@code eurema.ModelOperation}.
	 */
	private ModelOperationExecutor modelOperationExecutor;

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context of the interpreter.
	 */
	public ElementExecutorFactory(GlobalExecutionContext globalExecutionContext) {
		this.transitionExecutor =
				new TransitionExecutor(globalExecutionContext);
		this.initialOperationExecutor =
				new InitialOperationExecutor(globalExecutionContext);
		this.decisionOperationExecutor =
				new DecisionOperationExecutor(globalExecutionContext);
		this.finalOperationExecutor =
				new FinalOperationExecutor(globalExecutionContext);
		this.megamodelCallExecutor =
				new MegamodelCallExecutor(globalExecutionContext);
		this.modelOperationExecutor =
				new ModelOperationExecutor(globalExecutionContext);
	}

	/**
	 * Returns the executor for the megamodel module element to be executed. The
	 * element is an <tt>EObject</tt> that has a certain <tt>EClass</tt>.
	 * 
	 * @param eClass
	 *            the <tt>EClass</tt> of the executable element.
	 * @return the executor for the executable element with the given
	 *         <tt>EClass</tt>.
	 */
	ElementExecutor getElementExecutorByClass(EClass eClass) {
		ElementExecutor elementExecutor = null;
		EuremaPackage euremaPackage = Metamodel.INSTANCE.getEuremaPackage();
		if (eClass.equals(euremaPackage.getTransition())) {
			elementExecutor = this.transitionExecutor;
		} else if (eClass.equals(euremaPackage.getModelOperation())) {
			elementExecutor = this.modelOperationExecutor;
		} else if (eClass.equals(euremaPackage.getMegamodelCall())) {
			elementExecutor = this.megamodelCallExecutor;
		} else if (eClass.equals(euremaPackage.getInitialOperation())) {
			elementExecutor = this.initialOperationExecutor;
		} else if (eClass.equals(euremaPackage.getFinalOperation())) {
			elementExecutor = this.finalOperationExecutor;
		} else if (eClass.equals(euremaPackage.getDecisionOperation())) {
			elementExecutor = this.decisionOperationExecutor;
		} else {
			throw new EuremaInterpreterException(
					"Error in executing the megamodel module. Megamodel element of class "
							+ eClass.getName() + " cannot be executed.");
		}
		return elementExecutor;

	}
}
