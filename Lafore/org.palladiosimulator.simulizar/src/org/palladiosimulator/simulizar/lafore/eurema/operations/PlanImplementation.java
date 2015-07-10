package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import strategies.StrategiesFactory;
import strategies.StrategiesRepository;
import strategies.Strategy;
import strategies.StrategyType;
import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationType;
import violations.ViolationsRepository;
import violationstrategymappings.ViolationStrategyMapping;
import violationstrategymappings.ViolationStrategyMappingRepository;

public class PlanImplementation implements IModelOperation {

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
		// TODO: implement the plan here!!!

		// RuntimeViolationsModel vRuntime = null;
		ViolationStrategyMappingRepository vsMappingRepository = null;
		StrategiesRepository sRepository = null;
		ViolationsRepository vRepository = null;

		// output
		// RuntimeStrategiesModel sRuntime =
		// StrategiesFactory.eINSTANCE.createRuntimeStrategiesModel();

		// System.out.println("Input Models:");
		for (Resource r : models) {
			// System.out.println(r.getURI().toString()); // print each model
			for (EObject c : r.getContents()) {
				// if (c instanceof RuntimeViolationsModel) {
				// vRuntime = (RuntimeViolationsModel) c;
				// }
				if (c instanceof ViolationStrategyMappingRepository) {
					vsMappingRepository = (ViolationStrategyMappingRepository) c;
				}
				// if (c instanceof RuntimeStrategiesModel) {
				// sRuntime = (RuntimeStrategiesModel) c;
				// }
				if (c instanceof StrategiesRepository) {
					sRepository = (StrategiesRepository) c;
				}
				if (c instanceof ViolationsRepository) {
					vRepository = (ViolationsRepository) c;
				}
			}
		}

		// iterate through all violations available in the Runtime violations
		// model
		for (Violation violation : vRun.getViolations()) {

			List<StrategyType> foundStrategies = new ArrayList<StrategyType>();
			List<Integer> priorities = new ArrayList<Integer>();

			// find a mapping to strategy for each violation
			for (ViolationStrategyMapping vsMap : vsMappingRepository.getVsmappings()) {
				if (vsMap.getViolation().getId() == violation.getViolationType().getId()) {
					foundStrategies.add(getStrategyType(vsMap.getStrategy().getId(), sRepository));
					priorities.add(vsMap.getStrategyPriority());
				}

			}
			// We add all found strategies. The order is set according to the
			// priorities of the mappings
			while (!foundStrategies.isEmpty()) {
				int maxIndex = 0;
				int maxPriority = priorities.get(maxIndex);
				for (int i : priorities) {
					if (i > maxPriority) {
						maxPriority = i;
						maxIndex = priorities.indexOf(i);
					}
				}
				Strategy newStrategy = StrategiesFactory.eINSTANCE.createStrategy();
				newStrategy.setStrategyType(foundStrategies.get(maxIndex));
				foundStrategies.remove(maxIndex);
				priorities.remove(maxIndex);
				// TODO: Check if the strategy is already there. If yes, do not
				// add it.
				if (!existsInList(newStrategy))
					sRun.getStrategies().add(newStrategy);
			}

		}

		ResourceSet resourceSet = new ResourceSetImpl();
		URI platformPluginURI = URI.createFileURI(getAbsoluteFilename("org.palladiosimulator.simulizar",
				"knowledgeModels/MyRuntimeStrategies.strategies"));
		Resource resource = resourceSet.createResource(platformPluginURI);
		resource.getContents().add(sRun);

		try {
			resource.save(Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// if (sRuntime.eResource() != null)
		// output.add(sRun.eResource());

		vRun.getViolations().clear();

		ModelOperationResult result = new ModelOperationResult("planned", output);

		return result;
	}

	private boolean existsInList(Strategy newStrategy) {
		for (Strategy st : sRun.getStrategies()) {
			if (st.getStrategyType().getEntityName().equals(newStrategy.getStrategyType().getEntityName()))
				return true;
		}
		return false;
	}

	public String getAbsoluteFilename(String bundleName, String relativePath) {
		String absoluteFilename = "";
		URI platformPluginURI = URI.createPlatformPluginURI(bundleName + '/' + relativePath, true);
		absoluteFilename = platformPluginURI.toFileString();

		Bundle bundle = Platform.getBundle(bundleName);
		URL base = bundle.getEntry(relativePath);

		// FIXME: this is hack !
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

	public StrategyType getStrategyType(String id, StrategiesRepository sRepository) {
		for (ServiceEffectSpecification st : sRepository.getServiceEffectSpecifications__BasicComponent()) {
			if (((StrategyType) st).getId() == id)
				return (StrategyType) st;
		}
		return null;
	}

	public ViolationType getViolationTypeType(String id, ViolationsRepository vRepository) {
		for (ViolationType vt : vRepository.getViolationTypes()) {
			if (vt.getId() == id)
				return vt;
		}
		return null;
	}

}
