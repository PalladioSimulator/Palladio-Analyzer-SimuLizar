package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationType;
import violations.ViolationsFactory;
import violations.ViolationsRepository;

public class AnalyzeImplementation implements IModelOperation {

	@Override
	public ModelOperationResult run(List<Resource> models) {

		System.out.println("Executing the model operations implementation: " + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();
		// TODO: implement the analysis here!!!

		RuntimeMeasurementModel rmm = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurementModel();
		ViolationsRepository vRepository = ViolationsFactory.eINSTANCE.createViolationsRepository();

		RuntimeViolationsModel vRuntime = ViolationsFactory.eINSTANCE.createRuntimeViolationsModel();
		System.out.println("Input Models:");
		for (Resource r : models) {
			System.out.println(r.getURI().toString()); // print each model
			for (EObject c : r.getContents()) {
				if (c instanceof RuntimeMeasurementModel) {
					rmm = (RuntimeMeasurementModel) c;
				}
				if (c instanceof ViolationsRepository) {
					vRepository = (ViolationsRepository) c;
				}
			}
		}

		// iterate through all measurements available in the Runtime measurement
		// model
		for (RuntimeMeasurement rMeasurement : rmm.getMeasurements()) {
			// iterate through all violation type available in the Violations
			// Repository
			for (ViolationType violationT : vRepository.getViolationTypes()) {
				// check whether the measurement specifications match
				// (MetricDesctiption and MeasuringPoint)
				if (rMeasurement.getMeasurementSpecification() == violationT.getSlo().getMeasurementSpecification()) {
					// check the upper value of the threshold
					if (rMeasurement.getMeasuringValue() > (Double) violationT.getSlo().getUpperThreshold()
							.getThresholdLimit().getValue())
					// TODO: Check the Quantifiable and NonQuantifiable cases
					{
						Violation violationInstance = ViolationsFactory.eINSTANCE.createQuantifiableViolation();
						violationInstance.setViolationType(violationT);

						vRuntime.getViolations().add(violationInstance);

					}
					// check the lower value of the threshold
					if (rMeasurement.getMeasuringValue() < (Double) violationT.getSlo().getLowerThreshold()
							.getThresholdLimit().getValue())
					// TODO: Check the Quantifiable and NonQuantifiable cases
					{
						Violation violationInstance = ViolationsFactory.eINSTANCE.createQuantifiableViolation();
						violationInstance.setViolationType(violationT);

						vRuntime.getViolations().add(violationInstance);

					}
				}

			}
		}

		if (vRuntime.eResource() != null)
			output.add(vRuntime.eResource());

		ModelOperationResult result = new ModelOperationResult("analyzed", output);

		return result;
	}

}
