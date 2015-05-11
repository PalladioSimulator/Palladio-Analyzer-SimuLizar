package de.mdelab.eurema.test.dummyselfrepair.plan;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;

public class Repair implements IModelOperation {

	@Override
	public ModelOperationResult run(List<Resource> models) {

		System.out.println("Executing the model operations implementation: "
				+ this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();

		System.out.println("Input Models:");
		for (Resource r : models) {
			System.out.println(r.getURI().toString());
			// no output models
		}

		ModelOperationResult result = new ModelOperationResult("repaired", output);

		return result;
	}

}
