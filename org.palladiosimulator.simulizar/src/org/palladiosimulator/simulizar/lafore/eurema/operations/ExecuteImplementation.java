package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.osgi.framework.Bundle;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ProbabilisticBranchTransition;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.simulizar.access.IModelAccess;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import strategies.StrategiesRepository;
import strategies.Strategy;
import strategies.StrategyType;
import violations.RuntimeViolationsModel;

public class ExecuteImplementation implements IModelOperation {

	// get the PCM models FIXME: This should not be here!!!
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

		URI a = null;

		// IFile ifile =
		// org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot()
		// .getFile(new Path(a.toPlatformString(true)));
		// ifile.getFullPath().toOSString();
		// ifile.getRawLocation().makeAbsolute().toOSString();

		System.out.println("Executing the model operations implementation: " + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();

		// RuntimeStrategiesModel sRuntime = null;
		StrategiesRepository sReposorty = null;

		// System.out.println("Input Models:");
		for (Resource r : models) {
			// System.out.println(r.getURI().toString()); // print each model
			for (EObject c : r.getContents()) {
				// if (c instanceof RuntimeStrategiesModel) {
				// sRuntime = (RuntimeStrategiesModel) c;
				// }
				if (c instanceof StrategiesRepository) {
					sReposorty = (StrategiesRepository) c;
				}
			}
		}

		List<EObject> pcmAllocation = Arrays.asList((EObject) access.getGlobalPCMModel().getAllocation());

		Repository r = null;
		for (Repository rTemp : access.getGlobalPCMModel().getRepositories()) {
			String b = rTemp.getEntityName();
			if (b.equals("znnRepository"))
				r = rTemp;
		}
		List<EObject> pcmRep = Arrays.asList((EObject) r);
		List<EObject> pcmSys = Arrays.asList((EObject) access.getGlobalPCMModel().getSystem());

		ModelExtent inAllocation = new BasicModelExtent(pcmAllocation);
		ModelExtent inRep = new BasicModelExtent(pcmRep);
		ModelExtent inSys = new BasicModelExtent(pcmSys);

		int countActive = 0;
		EList<RepositoryComponent> lst = r.getComponents__Repository();
		for (RepositoryComponent cmp : lst) {
			String z = cmp.getEntityName();
			if (z.equals("LoadBalancer")) {
				ResourceDemandingSEFF seff = (ResourceDemandingSEFF) ((BasicComponent) cmp)
						.getServiceEffectSpecifications__BasicComponent().get(0);
				for (AbstractAction action : seff.getSteps_Behaviour()) {
					if (action instanceof BranchAction) {
						for (AbstractBranchTransition prob : ((BranchAction) action).getBranches_Branch()) {
							if (((ProbabilisticBranchTransition) prob).getBranchProbability() != 0.0)
								countActive++;
						}
					}
				}
			}
		}
		ExecutionContextImpl executionContext = new ExecutionContextImpl();

		// iterate through all strategies available in the Runtime strategies
		// model

		for (Strategy strategy : sRun.getStrategies()) {
			StrategyType st = getStrategyType(strategy.getStrategyType().getId(), sReposorty);
			URI uriqvto = URI.createFileURI(st.getBehavior().get(0).getCodeRef());
			TransformationExecutor conflictCheckExecutor = new TransformationExecutor(uriqvto); // execute
																								// controller
																								// completion
																								// ExecutionDiagnostic
			ExecutionDiagnostic result = conflictCheckExecutor.execute(executionContext, inAllocation, inRep, inSys);
			System.out.println("Reconfiguration action: " + uriqvto);
			addDemand();

		}

		countActive = 0;
		for (RepositoryComponent cmp : lst) {
			String z = cmp.getEntityName();
			if (z.equals("LoadBalancer")) {
				ResourceDemandingSEFF seff = (ResourceDemandingSEFF) ((BasicComponent) cmp)
						.getServiceEffectSpecifications__BasicComponent().get(0);
				for (AbstractAction action : seff.getSteps_Behaviour()) {
					if (action instanceof BranchAction) {
						for (AbstractBranchTransition prob : ((BranchAction) action).getBranches_Branch()) {
							if (((ProbabilisticBranchTransition) prob).getBranchProbability() != 0.0)
								countActive++;
						}
					}
				}
			}
		}
		System.out.println("Active servers: " + countActive);

		sRun.getStrategies().clear();

		// output.add(sRun.eResource());
		ModelOperationResult result = new ModelOperationResult("executed", output);

		return result;
	}

	public void addDemand() {
		List<EObject> pcmAllocation = Arrays.asList((EObject) access.getGlobalPCMModel().getAllocation());

		Repository r = null;
		for (Repository rTemp : access.getGlobalPCMModel().getRepositories()) {
			String b = rTemp.getEntityName();
			if (b.equals("znnRepository"))
				r = rTemp;
		}
		List<EObject> pcmRep = Arrays.asList((EObject) r);
		List<EObject> pcmSys = Arrays.asList((EObject) access.getGlobalPCMModel().getSystem());

		ModelExtent inAllocation = new BasicModelExtent(pcmAllocation);
		ModelExtent inRep = new BasicModelExtent(pcmRep);
		ModelExtent inSys = new BasicModelExtent(pcmSys);

		ExecutionContextImpl executionContext = new ExecutionContextImpl();
		URI uriqvto = URI.createFileURI(
				getAbsoluteFilename("org.palladiosimulator.simulizar", "reconfigurationDemands/addDemand.qvto"));
		TransformationExecutor conflictCheckExecutor = new TransformationExecutor(uriqvto); // execute
																							// controller
																							// completion
																							// ExecutionDiagnostic
		ExecutionDiagnostic result = conflictCheckExecutor.execute(executionContext, inAllocation, inRep, inSys);
		System.out.println("Added Demand!");

	}

	public StrategyType getStrategyType(String id, StrategiesRepository sRepository) {
		for (ServiceEffectSpecification st : sRepository.getServiceEffectSpecifications__BasicComponent()) {
			if (((StrategyType) st).getId() == id)
				return (StrategyType) st;
		}
		return null;
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

}
