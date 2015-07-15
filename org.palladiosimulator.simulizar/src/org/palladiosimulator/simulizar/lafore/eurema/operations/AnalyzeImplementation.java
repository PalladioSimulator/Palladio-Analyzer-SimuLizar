package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationType;
import violations.ViolationsFactory;
import violations.ViolationsRepository;

/**
 * This class implements the Analyze operation for the LAFORE feedback loop.
 * 
 * @author Goran Piskachev
 * 
 */
public class AnalyzeImplementation implements IModelOperation {

	/**
	 * This class' internal LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(Reconfigurator.class);

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

	@Override
	public ModelOperationResult run(List<Resource> models) {

		// System.out.println("Executing the model operations implementation: "
		// + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();

		ViolationsRepository vRepository = null;

		// System.out.println("Input Models:");
		for (Resource r : models) {
			// System.out.println(r.getURI().toString()); // print each model
			for (EObject c : r.getContents()) {
				// if (c instanceof RuntimeMeasurementModel) {
				// rmm = (RuntimeMeasurementModel) c;
				// }
				if (c instanceof ViolationsRepository) {
					vRepository = (ViolationsRepository) c;
				}
				// if (c instanceof RuntimeViolationsModel) {
				// vRuntime = (RuntimeViolationsModel) c;
				// }
			}
		}

		// iterate through all measurements available in the Runtime measurement
		// model
		for (RuntimeMeasurement rMeasurement : access.getRuntimeMeasurementModel().getMeasurements()) {
			// iterate through all violation type available in the Violations
			// Repository
			LOGGER.info("Measured value: " + rMeasurement.getMeasuringValue());
			for (ViolationType violationT : vRepository.getViolationTypes()) {
				// check whether the measurement specifications match
				// (MetricDesctiption and MeasuringPoint)
				String s1 = rMeasurement.getMeasurementSpecification().getName();
				String s2 = violationT.getSlo().getMeasurementSpecification().getName();
				if (s1.equals(s2)) {
					// check the upper value of the threshold

					if (violationT.getSlo().getUpperThreshold() != null) {
						Double val1 = Double
								.parseDouble(violationT.getSlo().getUpperThreshold().getThresholdLimit().toString());
						if (rMeasurement.getMeasuringValue() > val1)

						// TODO: Check the Quantifiable and NonQuantifiable
						// cases
						{
							Violation violationInstance = ViolationsFactory.eINSTANCE.createQuantifiableViolation();
							violationInstance.setViolationType(violationT);

							violationsRun.getViolations().add(violationInstance);

						}
					}

					// check the lower value of the threshold
					if (violationT.getSlo().getLowerThreshold() != null) {
						Double val2 = Double
								.parseDouble(violationT.getSlo().getLowerThreshold().getThresholdLimit().toString());
						if (rMeasurement.getMeasuringValue() < val2)
						// TODO: Check the Quantifiable and NonQuantifiable
						// cases
						{
							Violation violationInstance = ViolationsFactory.eINSTANCE.createQuantifiableViolation();
							violationInstance.setViolationType(violationT);

							violationsRun.getViolations().add(violationInstance);

						}
					}
				}

			}
		}

		output.add(violationsRun.eResource());

		ModelOperationResult result = new ModelOperationResult("analyzed", output);

		return result;
	}

}
