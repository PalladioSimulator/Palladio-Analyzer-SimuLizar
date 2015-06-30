package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import strategies.StrategiesFactory;
import strategies.Strategy;

public class ExecuteImplementation implements IModelOperation {

	@Override
	public ModelOperationResult run(List<Resource> models) {
		System.out.println("Executing the model operations implementation: " + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();
		// TODO: implement the Execute here!!!

		RuntimeStrategiesModel sRuntime = StrategiesFactory.eINSTANCE.createRuntimeStrategiesModel();
		System.out.println("Input Models:");
		for (Resource r : models) {
			System.out.println(r.getURI().toString()); // print each model
			for (EObject c : r.getContents()) {
				if (c instanceof RuntimeStrategiesModel) {
					sRuntime = (RuntimeStrategiesModel) c;
				}
			}
		}

		// get the PCM models
		// IModelAccess access = state.getModelAccess();

		// List<EObject> pcmAllocation = Arrays.asList((EObject)
		// access.getGlobalPCMModel().getAllocation());
		// ModelExtent inAllocation = new BasicModelExtent(pcmAllocation);

		ExecutionContextImpl executionContext = new ExecutionContextImpl();

		// iterate through all strategies available in the Runtime strategies
		// model
		for (Strategy strategy : sRuntime.getStrategies()) {
			// TransformationExecutor conflictCheckExecutor = new
			// TransformationExecutor(URI.createURI(strategy.getStrategyType().getBehavior().get(0).getCodeRef()));
			// execute controller completion
			// ExecutionDiagnostic result =
			// conflictCheckExecutor.execute(executionContext, inAllocation);

		}

		sRuntime.getStrategies().clear();

		ModelOperationResult result = new ModelOperationResult("executed", output);

		return result;
	}

}
