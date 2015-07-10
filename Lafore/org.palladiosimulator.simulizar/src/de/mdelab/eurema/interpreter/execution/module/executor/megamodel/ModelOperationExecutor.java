package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.lafore.eurema.operations.AnalyzeImplementation;
import org.palladiosimulator.simulizar.lafore.eurema.operations.ExecuteImplementation;
import org.palladiosimulator.simulizar.lafore.eurema.operations.MonitorImplementation;
import org.palladiosimulator.simulizar.lafore.eurema.operations.PlanImplementation;

import de.mdelab.eurema.interpreter.EuremaInterpreterException;
import de.mdelab.eurema.interpreter.execution.GlobalExecutionContext;
import de.mdelab.eurema.interpreter.models.Model;
import de.mdelab.eurema.interpreter.models.ModelRepository;
import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import eurema.OperationBehavior;
import eurema.Transition;

/**
 * Executes an instance of {@code eurema.ModelOperation} that is part of a
 * {@code eurema.Megamodel}.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class ModelOperationExecutor extends OperationBehaviorExecutor {

	/**
	 * Constructor.
	 * 
	 * @param globalExecutionContext
	 *            the global execution context.
	 */
	ModelOperationExecutor(GlobalExecutionContext globalExecutionContext) {
		super(globalExecutionContext);
	}

	/**
	 * Executes an instance of {@code eurema.ModelOperation}.
	 * 
	 * @param modelOperation
	 *            the model operation to be executed.
	 * @param before
	 *            the {@code eurema.Transition} instance executed just before,
	 *            i.e., the incoming transition of the model operation to be
	 *            executed that has been taken
	 * @param context
	 *            the execution context of the megamodel module
	 * @return the enabled outgoing {@code eurema.Transition} instance whose
	 *         source operation has just been executed.
	 */

	public eurema.Transition execute(eurema.ModelOperation modelOperation, eurema.Transition before,
			MegamodelModuleExecutionContext context) {

		eurema.SoftwareModule modelOperationImpl = modelOperation.getBinding();
		assert modelOperationImpl != null;
		String impl = modelOperationImpl.getImplementation();

		try {
			Class<?> implClass = Class.forName(impl);

			if (!this.implementsInterface(implClass, IModelOperation.class)) {
				throw new EuremaInterpreterException("Model operation implementation " + impl
						+ "does not implement the interface " + IModelOperation.class.getName());
			} else {
				// the class implements the predefined interface
				// de.mdelab.eurema.operation.IModelOperation

				// collect input models
				List<Resource> inputModels = this.retrieveInputModels(modelOperation);

				// instantiate and invoke the model operation implementation
				// To invoke the implementation, see the method
				// de.mdelab.eurema.operation.IModelOperation#run(List)
				Object targetObject = implClass.newInstance();

				if (MonitorImplementation.class.isAssignableFrom(implClass)) {

					IModelAccess modAccess = this.globalExecutionContext.getModelAccess();
					((MonitorImplementation) targetObject).setModelAccess(modAccess);

					((MonitorImplementation) targetObject)
							.setRuntimeViolationsModel(this.globalExecutionContext.getRuntimeViolationsModel());
					((MonitorImplementation) targetObject)
							.setRuntimeStrategiesModel(this.globalExecutionContext.getRuntimeStrategiesModel());
				}

				if (ExecuteImplementation.class.isAssignableFrom(implClass)) {

					IModelAccess modAccess = this.globalExecutionContext.getModelAccess();
					((ExecuteImplementation) targetObject).setModelAccess(modAccess);
					((ExecuteImplementation) targetObject)
							.setRuntimeViolationsModel(this.globalExecutionContext.getRuntimeViolationsModel());
					((ExecuteImplementation) targetObject)
							.setRuntimeStrategiesModel(this.globalExecutionContext.getRuntimeStrategiesModel());
				}

				if (AnalyzeImplementation.class.isAssignableFrom(implClass)) {

					IModelAccess modAccess = this.globalExecutionContext.getModelAccess();
					((AnalyzeImplementation) targetObject).setModelAccess(modAccess);

					((AnalyzeImplementation) targetObject)
							.setRuntimeViolationsModel(this.globalExecutionContext.getRuntimeViolationsModel());
					((AnalyzeImplementation) targetObject)
							.setRuntimeStrategiesModel(this.globalExecutionContext.getRuntimeStrategiesModel());
				}

				if (PlanImplementation.class.isAssignableFrom(implClass)) {

					IModelAccess modAccess = this.globalExecutionContext.getModelAccess();
					((PlanImplementation) targetObject).setModelAccess(modAccess);

					((PlanImplementation) targetObject)
							.setRuntimeViolationsModel(this.globalExecutionContext.getRuntimeViolationsModel());
					((PlanImplementation) targetObject)
							.setRuntimeStrategiesModel(this.globalExecutionContext.getRuntimeStrategiesModel());
				}

				Class<?>[] parameterTypes = new Class<?>[] { List.class };
				Method run = implClass.getMethod("run", parameterTypes);
				Object[] args = new Object[] { inputModels };
				Object result = run.invoke(targetObject, args);

				if (result == null || !(result instanceof ModelOperationResult)) {
					throw new EuremaInterpreterException(
							"Model operation implementation " + impl + "does not properly return results.");
				} else {
					// proper result available
					ModelOperationResult modelOperationResult = (ModelOperationResult) result;

					// update repository with the potentially, newly
					// created output models
					this.updateRepository(modelOperationResult.getResources());

					// check whether the input models have been removed
					// from the repository's resource set by adding them to
					// another resource set.
					this.updateRepository(inputModels);

					// retrieve enabled transitions and return it
					eurema.Exit eEnabledExit = this.retrieveExit(modelOperation,
							modelOperationResult.getReturnStatus());
					return eEnabledExit.getOutgoing();
				}

			}
		} catch (

		Exception e)

		{
			throw new EuremaInterpreterException("Failure in executing the model operation implementation: " + impl, e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Transition execute(OperationBehavior operationBehavior, eurema.Transition before,
			MegamodelModuleExecutionContext context) {
		assert operationBehavior instanceof eurema.ModelOperation;
		return this.execute((eurema.ModelOperation) operationBehavior, before, context);
	}

	/**
	 * Retrieves the models that are specified by a megamodel as the input
	 * models to the currently executed model operation.
	 * 
	 * @param modelOperation
	 *            the currently executed model operation
	 * @return the list of input models
	 */
	private List<Resource> retrieveInputModels(eurema.ModelOperation modelOperation) {
		EList<eurema.ModelUse> modelUses = modelOperation.getModelUsages();
		List<Resource> inputModels = new LinkedList<Resource>();

		for (eurema.ModelUse modelUse : modelUses) {
			// filter: Input vs. Output: select Inputs
			if (modelUse instanceof eurema.Input) {
				// retrieve model
				eurema.Model usedModel = modelUse.getModel();
				String modelURI = null;
				if (usedModel instanceof eurema.RuntimeModel) {
					modelURI = ((eurema.RuntimeModel) usedModel).getBinding().getURI();
				} else if (usedModel instanceof eurema.MegamodelProxy) {
					modelURI = ((eurema.MegamodelProxy) usedModel).getBinding().getBinding().getURI();
				}
				assert modelURI != null;
				Model model = ModelRepository.INSTANCE.loadModelOnDemand(modelURI, usedModel.getName());
				Resource modelResource = model.getEmfResource();
				inputModels.add(modelResource);
			} // else output model
		}
		return inputModels;
	}

	/**
	 * Checks whether a Java class implements a given Java interface
	 * 
	 * @param clazz
	 *            the class
	 * @param interfaze
	 *            the interface
	 * @return <code>true</code> if the <code>class</code> implements the
	 *         <code>interfaze</code>, else <code>false</code>.
	 */
	private boolean implementsInterface(Class<?> clazz, Class<?> interfaze) {
		boolean result = false;
		Class<?>[] interfaces = clazz.getInterfaces();
		String fqInterfaceName = interfaze.getName();
		for (int i = 0; i < interfaces.length; i++) {
			if (interfaces[i].getName().equals(fqInterfaceName)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Updates the model repository by adding models to the repository if they
	 * are not already part of it.
	 * 
	 * @param resources
	 *            the resources to be added.
	 */
	private void updateRepository(List<Resource> resources) {
		if (resources != null) {
			for (Resource r : resources) {
				if (r != null) {
					// &&
					// !ModelRepository.INSTANCE.containsEMFResource(r.getURI()))
					// {
					ModelRepository.INSTANCE.addModel(r, r.getURI().toString());
				}
			}
		}
	}

	/**
	 * Retrieves the {@code eurema.Exit} compartment of the
	 * {@code eurema.ModelOperation} by its name.
	 * 
	 * @param modelOperation
	 *            the {@code eurema.Operation} that is currently executed.
	 * @param returnStatus
	 *            the return state as a String that will be matched to the
	 *            <tt>name</tt> of {@code eurema.Exit} compartment of the
	 *            <code>modelOperation</code>.
	 * @return the exit compartment of the model operation with the given return
	 *         status as its name.
	 */
	private eurema.Exit retrieveExit(eurema.ModelOperation modelOperation, String returnStatus) {
		eurema.Exit resultExit = null;
		if (returnStatus != null) {
			for (eurema.Exit eExit : modelOperation.getExits()) {
				String eExitName = eExit.getName();
				if (eExitName.equals(returnStatus)) {
					resultExit = eExit;
					break;
				}
			}
		}
		if (resultExit == null) {
			throw new EuremaInterpreterException("The model operation " + modelOperation.getName()
					+ " has no exit compartment called: " + returnStatus);
		} else {
			return resultExit;
		}

	}
}
