package de.mdelab.eurema.test.dummyselfrepair.analyze;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;

public class DeepCheckForFailures implements IModelOperation {

	@Override
	public ModelOperationResult run(List<Resource> models) {

		System.out.println("Executing the model operations implementation: "
				+ this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();

		System.out.println("Input Models:");
		for (Resource r : models) {
			System.out.println(r.getURI().toString());
			if (r.getURI().toString().contains("Architectural")) {
				output.add(r);
			}
		}

		ModelOperationResult result = new ModelOperationResult(
				"detailed results", output);

		return result;
	}

}
