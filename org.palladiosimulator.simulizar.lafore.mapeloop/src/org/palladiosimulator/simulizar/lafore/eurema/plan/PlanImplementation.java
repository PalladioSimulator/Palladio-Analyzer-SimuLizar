package org.palladiosimulator.simulizar.lafore.eurema.plan;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;

public class PlanImplementation implements IModelOperation{

	@Override
	public ModelOperationResult run(List<Resource> models) {
		System.out.println("Executing the model operations implementation: "
				+ this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();
		// TODO: implement the plan here!!!

		ModelOperationResult result = new ModelOperationResult(
				"planned", output);

		return result;
	}
	

}
