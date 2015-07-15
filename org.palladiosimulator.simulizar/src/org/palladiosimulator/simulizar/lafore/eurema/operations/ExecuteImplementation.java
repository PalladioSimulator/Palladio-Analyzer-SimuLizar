package org.palladiosimulator.simulizar.lafore.eurema.operations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
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
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.utils.FileUtil;

import de.mdelab.eurema.operation.IModelOperation;
import de.mdelab.eurema.operation.ModelOperationResult;
import strategies.RuntimeStrategiesModel;
import strategies.StrategiesRepository;
import strategies.Strategy;
import strategies.StrategyType;
import violations.RuntimeViolationsModel;

/**
 * This class implements the Execute operation for the LAFORE feedback loop.
 * 
 * @author Goran Piskachev
 * 
 */
public class ExecuteImplementation implements IModelOperation {

	/**
	 * This class' internal LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(Reconfigurator.class);

	// These runtime models are taken from SimuLizar. They should not be here,
	// but the current implementation of the EUREMA interpreter can not handle
	// shared runtime models. TODO: we need a better solution with this
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

		// System.out.println("Executing the model operations implementation: "
		// + this.getClass().getCanonicalName());

		List<Resource> output = new LinkedList<Resource>();

		StrategiesRepository sReposorty = null;

		// get the Strategies Repository
		for (Resource r : models) {
			for (EObject c : r.getContents()) {
				// if (c instanceof RuntimeStrategiesModel) {
				// sRuntime = (RuntimeStrategiesModel) c;
				// }
				if (c instanceof StrategiesRepository) {
					sReposorty = (StrategiesRepository) c;
				}
			}
		}

		// get the Allocation from the runtime PCM
		List<EObject> pcmAllocation = Arrays.asList((EObject) access.getGlobalPCMModel().getAllocation());

		// Find the repository
		// FIXME: This is ZNN.COM specific. We need a general solution here.
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

		// Count the active servers in the pool. This is for debugging.
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
		// model and execute the reconfiguratiuon actions

		for (Strategy strategy : sRun.getStrategies()) {
			StrategyType st = getStrategyType(strategy.getStrategyType().getId(), sReposorty);
			String absPath = FileUtil.getAbsoluteFilename("org.palladiosimulator.simulizar",
					st.getBehavior().get(0).getCodeRef());
			URI uriqvto = URI.createFileURI(absPath);
			TransformationExecutor conflictCheckExecutor = new TransformationExecutor(uriqvto); // execute
																								// controller
																								// completion
																								// ExecutionDiagnostic
			ExecutionDiagnostic result = conflictCheckExecutor.execute(executionContext, inAllocation, inRep, inSys);
			LOGGER.info("Reconfiguration action: " + st.getEntityName());
			// add reconfiguration demands to be simulated
			addDemand();

		}

		// again, just for debugging
		countActive = 0;
		for (RepositoryComponent cmp : lst) {
			String z = cmp.getEntityName();
			if (z.equals("NewsService")) {
				ResourceDemandingSEFF seff = (ResourceDemandingSEFF) ((BasicComponent) cmp)
						.getServiceEffectSpecifications__BasicComponent().get(0);
				for (AbstractAction action : seff.getSteps_Behaviour()) {
					if (action instanceof BranchAction) {
						for (AbstractBranchTransition prob : ((BranchAction) action).getBranches_Branch()) {
							if (((ProbabilisticBranchTransition) prob).getBranchProbability() == 0.0
									&& ((ProbabilisticBranchTransition) prob).getEntityName()
											.equals("selectMultimedia"))
								LOGGER.info("Content: Textual");
							if (((ProbabilisticBranchTransition) prob).getBranchProbability() == 1.0
									&& ((ProbabilisticBranchTransition) prob).getEntityName()
											.equals("selectMultimedia"))
								LOGGER.info("Content: Multimedia");
						}
					}
				}
			}

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
		LOGGER.info("Active servers: " + countActive);

		sRun.getStrategies().clear();

		// output.add(sRun.eResource());
		ModelOperationResult result = new ModelOperationResult("executed", output);

		return result;
	}

	/**
	 * Method that executed the addDemand.qvto transformation
	 */
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
		URI uriqvto = URI.createFileURI(FileUtil.getAbsoluteFilename("org.palladiosimulator.simulizar",
				"reconfigurationDemands/addDemand.qvto"));
		TransformationExecutor conflictCheckExecutor = new TransformationExecutor(uriqvto); // execute
																							// controller
																							// completion
																							// ExecutionDiagnostic
		ExecutionDiagnostic result = conflictCheckExecutor.execute(executionContext, inAllocation, inRep, inSys);
		LOGGER.info("Added Reconfiguration Demand");

	}

	/**
	 * Method that returns the strategy type object from the strategies
	 * repository for a given id.
	 * 
	 * @param id
	 *            the id of the strategy type
	 * @param sRepository
	 *            the strategies repository
	 * 
	 * @return the strategy type object
	 */
	public StrategyType getStrategyType(String id, StrategiesRepository sRepository) {
		for (ServiceEffectSpecification st : sRepository.getServiceEffectSpecifications__BasicComponent()) {
			if (((StrategyType) st).getId() == id)
				return (StrategyType) st;
		}
		return null;
	}

}
