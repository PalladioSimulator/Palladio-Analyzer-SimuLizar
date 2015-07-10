package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;

public class MonitorImplementation implements IModelOperation {

	private RuntimeViolationsModel vRun;
	private IModelAccess access;
	private RuntimeStrategiesModel sRun;

	public void setRuntimeViolationsModel(RuntimeViolationsModel v) {
		vRun = v;
	}

	public void setRuntimeStrategiesModel(RuntimeStrategiesModel s) {
		sRun = s;
	}

	public void setModelAccess(IModelAccess modAccess) {
		access = modAccess;
	}

	@Override
	public ModelOperationResult run(List<Resource> models) {

		System.out.println("Executing the model operations implementation: " + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();

		MonitorRepository mr;

		// print the input models and get the Monitors
		// System.out.println("Input Models:");
		for (Resource r : models) {
			// System.out.println(r.getURI().toString()); // print each model

			if (r.getURI().toString().contains("Monitors")) {
				mr = (MonitorRepository) r.getContents().get(0); // get the
																	// monitors
																	// in mr
			}

		}

		// get the RuntimeMeasurements model and create MeasuredMonitor-s from
		// mr
		for (Resource r : models) {

			if (r.getURI().toString().contains("RuntimeMeasurements")) {
				// rmm = (RuntimeMeasurementsModel)r.getContents().get(0);

				// TODO: implement the monitoring here!!! At the moment this is
				// performer in Simulizar, before calling the Loop. Therefore,
				// we should not repeat the monitoring twice.

				// output.add((Resource)rmm); // add the rmm in the output
				// models
			}
		}

		ModelOperationResult result = new ModelOperationResult("monitored", output);

		return result;
	}
}
