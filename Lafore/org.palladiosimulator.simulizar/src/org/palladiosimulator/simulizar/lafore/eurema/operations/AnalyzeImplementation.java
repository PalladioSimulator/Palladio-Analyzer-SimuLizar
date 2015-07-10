package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.Bundle;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationType;
import violations.ViolationsFactory;
import violations.ViolationsRepository;

public class AnalyzeImplementation implements IModelOperation {

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
		// TODO: implement the analysis here!!!

		// RuntimeMeasurementModel rmm = null;
		ViolationsRepository vRepository = null;

		// output model
		// RuntimeViolationsModel vRuntime =
		// ViolationsFactoryImpl.eINSTANCE.createRuntimeViolationsModel();

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
			System.out.println("Measured value: " + rMeasurement.getMeasuringValue());
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

							vRun.getViolations().add(violationInstance);

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

							vRun.getViolations().add(violationInstance);

						}
					}
				}

			}
		}

		ResourceSet resourceSet = new ResourceSetImpl();
		URI platformPluginURI = URI.createFileURI(getAbsoluteFilename("org.palladiosimulator.simulizar",
				"knowledgeModels/MyRuntimeViolations.violations"));
		Resource resource = resourceSet.createResource(platformPluginURI);
		resource.getContents().add(vRun);

		try {
			resource.save(Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		output.add(vRun.eResource());

		ModelOperationResult result = new ModelOperationResult("analyzed", output);

		return result;
	}

	public String getAbsoluteFilename(String bundleName, String relativePath) {
		String absoluteFilename = "";
		URI platformPluginURI = URI.createPlatformPluginURI(bundleName + '/' + relativePath, true);
		absoluteFilename = platformPluginURI.toFileString();

		Bundle bundle = Platform.getBundle(bundleName);
		URL base = bundle.getEntry(relativePath);

		// FIXME: this is a hack !
		try {
			absoluteFilename = FileLocator.toFileURL(base).toString();
			if (absoluteFilename.startsWith("file:/")) {
				absoluteFilename = absoluteFilename.substring(6);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return absoluteFilename;
	}
}
