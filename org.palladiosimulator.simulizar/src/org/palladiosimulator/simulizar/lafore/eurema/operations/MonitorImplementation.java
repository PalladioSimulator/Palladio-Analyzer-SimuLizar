package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;

/**
 * This class implements the Monitor operation for the LAFORE feedback loop.
 * 
 * @author Goran Piskachev
 * 
 */
public class MonitorImplementation implements IModelOperation {

	private RuntimeViolationsModel violationsRun;
	private IModelAccess access;
	private RuntimeStrategiesModel strategiesRun;

	public void setRuntimeViolationsModel(RuntimeViolationsModel v) {
		violationsRun = v;
	}

	public void setRuntimeStrategiesModel(RuntimeStrategiesModel s) {
		strategiesRun = s;
	}

	public void setModelAccess(IModelAccess modAccess) {
		access = modAccess;
	}

	// This operation is Dummy, because the creation of the RuntimeMeasurements
	// is already done in SimuLizar.
	@Override
	public ModelOperationResult run(List<Resource> models) {

		System.out.println("Executing the model operations implementation: " + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();

		ModelOperationResult result = new ModelOperationResult("monitored", output);

		return result;
	}
}
